package com.tools.minio.single.web.advice;


import com.common.utils.model.Result;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author shenlx
 * @description
 * @date 2026/1/31 20:39
 */
@ControllerAdvice
@Slf4j
public class MinioExceptionHandler {
    @ExceptionHandler(MinioException.class)
    public ResponseEntity<Result<Void>> handleMinioException(MinioException e) {
        log.error("MinIO操作异常", e);

        if (e.getMessage().contains("NoSuchBucket")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Result.error("存储桶不存在"));
        }

        if (e.getMessage().contains("NoSuchKey")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Result.error("文件不存在"));
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.error("MinIO服务异常"));
    }
}
