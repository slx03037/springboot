package com.tools.ftp.web.service;

import com.tools.ftp.web.utils.FTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2026/6/19 20:52
 */
@Service
public class FileService {
    @Autowired
    private FTPUtil ftpUtil;

    public String handleFileUpload(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            // 调用FTP工具类上传
            boolean success = ftpUtil.uploadFile(originalFilename, file.getInputStream());
            if (success) {
                return "文件上传成功: " + originalFilename;
            } else {
                return "文件上传失败";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传出错";
        }
    }
}
