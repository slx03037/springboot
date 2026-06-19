package com.tools.sftp.web.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.tools.sftp.web.config.SftpProperties;
import com.tools.sftp.web.model.SftpFileInfo;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author shenlx
 * @description
 * @date 2026/6/19 20:49
 */
@Service
public class SftpService {
    @Autowired
    private GenericObjectPool<ChannelSftp> sftpPool;

    @Autowired
    private SftpProperties sftpProperties;





    // ---------- 上传 ----------
    public void uploadFile(String remotePath, String fileName, InputStream inputStream) throws Exception {
        ChannelSftp channel = null;
        try {
            channel = sftpPool.borrowObject();
            ensureDirectoryExists(channel, remotePath); // 确保目录存在
            channel.put(inputStream, fileName, ChannelSftp.OVERWRITE);
        } finally {
            if (channel != null) {
                sftpPool.returnObject(channel);
            }
        }
    }

    // ---------- 下载 ----------
    public InputStream downloadFile(String remotePath, String fileName) throws Exception {
        ChannelSftp channel = null;
        try {
            channel = sftpPool.borrowObject();
            channel.cd(remotePath);
            return channel.get(fileName);
        } catch (Exception e) {
            if (channel != null) {
                sftpPool.returnObject(channel);
            }
            throw e;
        }
        // 注意：调用方必须负责关闭返回的 InputStream，并在 finally 中归还连接
    }

    // ---------- 删除文件 ----------
    public void deleteFile(String remotePath, String fileName) throws Exception {
        ChannelSftp channel = null;
        try {
            channel = sftpPool.borrowObject();
            channel.cd(remotePath);
            channel.rm(fileName);
        } finally {
            if (channel != null) {
                sftpPool.returnObject(channel);
            }
        }
    }

    // ---------- 删除目录（递归删除） ----------
    public void deleteDirectory(String remotePath) throws Exception {
        ChannelSftp channel = null;
        try {
            channel = sftpPool.borrowObject();
            recursiveDelete(channel, remotePath);
        } finally {
            if (channel != null) {
                sftpPool.returnObject(channel);
            }
        }
    }

    private void recursiveDelete(ChannelSftp channel, String path) throws SftpException {
        try {
            channel.cd(path);
            // 列出所有条目（包括子目录和文件）
            @SuppressWarnings("unchecked")
            Vector<ChannelSftp.LsEntry> entries = channel.ls(".");
            for (ChannelSftp.LsEntry entry : entries) {
                String filename = entry.getFilename();
                if (".".equals(filename) || "..".equals(filename)) {
                    continue;
                }
                if (entry.getAttrs().isDir()) {
                    // 递归删除子目录
                    recursiveDelete(channel, path + "/" + filename);
                } else {
                    channel.rm(path + "/" + filename);
                }
            }
            // 删除当前空目录
            channel.rmdir(path);
        } catch (SftpException e) {
            // 如果目录不存在，则忽略或抛出
            if (e.id != ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                throw e;
            }
        }
    }

    // ---------- 重命名 ----------
    public void renameFile(String oldPath, String newPath) throws Exception {
        ChannelSftp channel = null;
        try {
            channel = sftpPool.borrowObject();
            channel.rename(oldPath, newPath);
        } finally {
            if (channel != null) {
                sftpPool.returnObject(channel);
            }
        }
    }

    // ---------- 列出文件列表（返回文件名列表） ----------
    public List<String> listFiles(String remotePath) throws Exception {
        ChannelSftp channel = null;
        try {
            channel = sftpPool.borrowObject();
            @SuppressWarnings("unchecked")
            Vector<ChannelSftp.LsEntry> entries = channel.ls(remotePath);
            List<String> fileNames = new ArrayList<>();
            for (ChannelSftp.LsEntry entry : entries) {
                String name = entry.getFilename();
                if (!".".equals(name) && !"..".equals(name)) {
                    fileNames.add(name);
                }
            }
            return fileNames;
        } finally {
            if (channel != null) {
                sftpPool.returnObject(channel);
            }
        }
    }

    // ---------- 列出文件详情（包含大小、修改时间等） ----------
    public List<SftpFileInfo> listFileDetails(String remotePath) throws Exception {
        ChannelSftp channel = null;
        try {
            channel = sftpPool.borrowObject();
            @SuppressWarnings("unchecked")
            Vector<ChannelSftp.LsEntry> entries = channel.ls(remotePath);
            List<SftpFileInfo> infos = new ArrayList<>();
            for (ChannelSftp.LsEntry entry : entries) {
                String name = entry.getFilename();
                if (".".equals(name) || "..".equals(name)) {
                    continue;
                }
                SftpATTRS attrs = entry.getAttrs();
                SftpFileInfo info = new SftpFileInfo();
                info.setName(name);
                info.setSize(attrs.getSize());
                info.setModifyTime(attrs.getMTime() * 1000L); // 转为毫秒
                info.setIsDirectory(attrs.isDir());
                infos.add(info);
            }
            return infos;
        } finally {
            if (channel != null) {
                sftpPool.returnObject(channel);
            }
        }
    }

    // ---------- 获取文件属性 ----------
    public SftpATTRS getFileAttributes(String remotePath) throws Exception {
        ChannelSftp channel = null;
        try {
            channel = sftpPool.borrowObject();
            return channel.stat(remotePath);
        } finally {
            if (channel != null) {
                sftpPool.returnObject(channel);
            }
        }
    }

    // ---------- 创建目录（支持递归） ----------
    public void createDirectory(String remotePath) throws Exception {
        ChannelSftp channel = null;
        try {
            channel = sftpPool.borrowObject();
            ensureDirectoryExists(channel, remotePath);
        } finally {
            if (channel != null) {
                sftpPool.returnObject(channel);
            }
        }
    }

    // ---------- 切换目录（检查是否存在） ----------
    public boolean changeDirectory(String remotePath) throws Exception {
        ChannelSftp channel = null;
        try {
            channel = sftpPool.borrowObject();
            channel.cd(remotePath);
            return true;
        } catch (SftpException e) {
            return false;
        } finally {
            if (channel != null) {
                sftpPool.returnObject(channel);
            }
        }
    }

    // ---------- 内部辅助方法：确保目录存在 ----------
    private void ensureDirectoryExists(ChannelSftp channel, String dirPath) throws SftpException {
        if (dirPath == null || dirPath.isEmpty() || "/".equals(dirPath)) {
            return;
        }
        try {
            channel.cd(dirPath);
            return;
        } catch (SftpException e) {
            // 目录不存在，递归创建
            String parent = dirPath.substring(0, dirPath.lastIndexOf('/'));
            if (!parent.isEmpty() && !"/".equals(parent)) {
                ensureDirectoryExists(channel, parent);
            }
            channel.mkdir(dirPath);
            channel.cd(dirPath);
        }
    }
}
