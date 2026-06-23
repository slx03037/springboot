package com.tools.minio.single.web.service;


import com.tools.minio.single.web.config.MinioProp;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author shenlx
 * @description
 * @date 2026/1/31 10:12
 */
@Service
@Slf4j
public class MinioService {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioProp minioProp;

    /**
     * 检查存储桶是否存在
     */
    public boolean bucketExists(String bucketName) throws Exception {
        return minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(bucketName)
                        .build()
        );
    }

    /**
     * 创建存储桶
     */
    public void createBucket(String bucketName) throws Exception {
        if (!bucketExists(bucketName)) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build()
            );

            // 设置桶的访问策略
            String strFormat =
                "{\n" +
                        "                    \"Version\": \"2012-10-17\",\n" +
                        "                    \"Statement\": [\n" +
                        "                        {\n" +
                        "                            \"Effect\": \"Allow\",\n" +
                        "                            \"Principal\": {\"AWS\": [\"*\"]},\n" +
                        "                            \"Action\": [\"s3:GetObject\"],\n" +
                        "                            \"Resource\": [\"arn:aws:s3:::%s/*\"]\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                }";
            String policy = String.format(strFormat, bucketName);

            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs.builder()
                            .bucket(bucketName)
                            .config(policy)
                            .build()
            );
        }
    }


    /**
     * 上传文件
     */
    public String uploadFile(MultipartFile file, String path) throws Exception {
        // 确保存储桶存在
        createBucket(minioProp.getBucketName());

        String fileName = file.getOriginalFilename();
        String objectName = path + "/" + UUID.randomUUID() +
                fileName.substring(fileName.lastIndexOf("."));

        // 上传文件
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(minioProp.getBucketName())
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        return objectName;
    }

    /**
     * 获取文件URL（有期限）
     */
    public String getFileUrl(String objectName, int expiryMinutes) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(minioProp.getBucketName())
                        .object(objectName)
                        .expiry(expiryMinutes * 60)  // 秒
                        .build()
        );
    }

    /**
     * 获取文件URL（永久，需设置桶策略为公开）
     */
    public String getFileUrl(String objectName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(minioProp.getBucketName())
                .object(objectName)
                .build()
        );
    }

    /**
     * 下载文件
     */
    public InputStream downloadFile(String objectName) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minioProp.getBucketName())
                        .object(objectName)
                        .build()
        );
    }

    /**
     * 删除文件
     */
    public void deleteFile(String objectName) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(minioProp.getBucketName())
                        .object(objectName)
                        .build()
        );
    }


    /**
     * 获取文件列表
     */
    public List<Item> listFiles(String prefix) throws Exception {
        List<Item> items = new ArrayList<>();

        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(minioProp.getBucketName())
                        .prefix(prefix)
                        .recursive(true)
                        .build()
        );

        for (Result<Item> result : results) {
            items.add(result.get());
        }

        return items;
    }
}
