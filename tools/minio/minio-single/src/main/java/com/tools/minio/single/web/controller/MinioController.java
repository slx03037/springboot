package com.tools.minio.single.web.controller;


import com.common.utils.model.Result;
import com.tools.minio.single.web.service.MinioService;
import com.tools.minio.single.web.utils.FileInfo;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shenlx
 * @description
 * @date 2026/1/31 20:36
 */
@RestController
@RequestMapping("/api/minio")
@Slf4j
public class MinioController {
    @Autowired
    private MinioService minioService;

    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam(value = "path", defaultValue = "") String path) {
        try {
            String objectName = minioService.uploadFile(file, path);
            String fileUrl = minioService.getFileUrl(objectName);
            Map<String, String> objectName1 = Map.of(
                    "objectName", objectName,
                    "url", fileUrl);
            String format = String.format("上传成功%s", objectName1);
            return Result.success(format);
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.error("上传失败: " + e.getMessage());
        }
    }

    @GetMapping("/download/{objectName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String objectName,
                                                 @RequestParam(required = false) String filename) {
        try {
            InputStream inputStream = minioService.downloadFile(objectName);

            // 设置响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" +
                            (filename != null ? filename : objectName) + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(inputStream));

        } catch (Exception e) {
            log.error("文件下载失败", e);
            return ResponseEntity.notFound().build();
        }
    }
//
//    @DeleteMapping("/add/{objectName}")
//    public Result<Void> create(@PathVariable String objectName) {
//        try {
//            minioService.deleteFile(objectName);
//            return Result.success("删除成功");
//        } catch (Exception e) {
//            log.error("文件删除失败", e);
//            return Result.error("删除失败: " + e.getMessage());
//        }
//    }

    @DeleteMapping("/{objectName}")
    public Result<Void> deleteFile(@PathVariable String objectName) {
        try {
            minioService.deleteFile(objectName);
            return Result.success("删除成功");
        } catch (Exception e) {
            log.error("文件删除失败", e);
            return Result.error("删除失败: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public Result<List<FileInfo>> listFiles(@RequestParam(required = false) String prefix) {
        try {
            List<Item> items = minioService.listFiles(prefix);
            List<FileInfo> fileInfos = items.stream()
                    .map(this::getListFileInfos)
                    .collect(Collectors.toList());

            return Result.success(fileInfos);
        } catch (Exception e) {
            log.error("获取文件列表失败", e);
            return Result.error("获取失败: " + e.getMessage());
        }
    }

    private FileInfo getListFileInfos( Item item ){
        try{
            return new FileInfo(
                item.objectName(),
                minioService.getFileUrl(item.objectName()),
                item.size(), convertToDate(item.lastModified())

        );
        }catch (Exception e){
            return null;
        }
    }
    public static Date convertToDate(ZonedDateTime zonedDateTime) {
        // ZonedDateTime -> Instant -> Date
        return Date.from(zonedDateTime.toInstant());
    }


}
