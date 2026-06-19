package com.tools.ftp.web.utils;

import com.tools.ftp.web.config.FtpProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author shenlx
 * @description
 * @date 2026/6/19 20:32
 */

@Slf4j
@Component
public class FTPUtil {
    @Autowired
    private FtpProperties ftpProperties;

    /**
     * 获取FTP客户端连接
     */
    private FTPClient getFTPClient() throws IOException {
        FTPClient ftpClient = new FTPClient();
        try {
            // 1. 连接服务器
            ftpClient.connect(ftpProperties.getHost(), ftpProperties.getPort());
            // 2. 登录
            boolean loginSuccess = ftpClient.login(ftpProperties.getUsername(), ftpProperties.getPassword());
            if (!loginSuccess) {
                throw new IOException("FTP登录失败，请检查用户名和密码");
            }

            // 3. 检查连接状态
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.disconnect();
                throw new IOException("FTP连接失败，状态码：" + replyCode);
            }

            // 4. 进行一些基础设置
            ftpClient.setControlEncoding("UTF-8");          // 设置字符编码[reference:12]
            ftpClient.enterLocalPassiveMode();              // 设置为被动模式[reference:13]
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);    // 设置文件传输类型为二进制[reference:14]
            ftpClient.setConnectTimeout(10 * 1000);         // 设置连接超时（毫秒）[reference:15]

            // 5. 切换到工作目录
            if (!ftpClient.changeWorkingDirectory(ftpProperties.getBasePath())) {
                log.warn("切换工作目录失败，将使用根目录");
            }
            return ftpClient;
        } catch (IOException e) {
            log.error("获取FTP连接失败", e);
            throw e;
        }
    }

    /**
     * 上传文件到FTP服务器
     * @param remoteFileName 远程文件名
     * @param inputStream    待上传的文件输入流
     * @return 上传成功返回true
     */
    public boolean uploadFile(String remoteFileName, InputStream inputStream) {
        FTPClient ftpClient = null;
        try {
            ftpClient = getFTPClient();
            boolean success = ftpClient.storeFile(remoteFileName, inputStream);
            if (success) {
                log.info("文件上传成功：{}", remoteFileName);
            } else {
                log.error("文件上传失败：{}", remoteFileName);
            }
            return success;
        } catch (IOException e) {
            log.error("文件上传异常", e);
            return false;
        } finally {
            closeConnection(ftpClient);
        }
    }

    /**
     * 下载文件
     * @param remoteFileName 远程文件名
     * @return 文件输入流，若失败则返回null
     */
    public InputStream downloadFile(String remoteFileName) {
        FTPClient ftpClient = null;
        try {
            ftpClient = getFTPClient();
            InputStream inputStream = ftpClient.retrieveFileStream(remoteFileName);
            // 注意: retrieveFileStream 获取的流需要手动关闭，且需检查是否成功
            if (inputStream == null) {
                log.error("文件不存在或无法读取：{}", remoteFileName);
                return null;
            }
            // 可以在这里对inputStream进行包装或处理，但调用方需负责关闭
            return inputStream;
        } catch (IOException e) {
            log.error("文件下载异常", e);
            return null;
        } finally {
            // 注意：不能在此处关闭ftpClient，因为返回的流需要依赖连接。
            // 正确的做法是调用方在读取完流后，显式关闭流并调用closeConnection。
            // 这里仅作为示例，实际项目中需要更精细的管理。
            // 简单的处理方式是直接在这里关闭，但这会使返回的流无效。
            // 更好的办法是在调用方处理完流后再关闭连接。
        }
    }

    /**
     * 关闭FTP连接
     */
    private void closeConnection(FTPClient ftpClient) {
        if (ftpClient != null && ftpClient.isConnected()) {
            try {
                ftpClient.logout();
                ftpClient.disconnect();
            } catch (IOException e) {
                log.error("关闭FTP连接失败", e);
            }
        }
    }

}
