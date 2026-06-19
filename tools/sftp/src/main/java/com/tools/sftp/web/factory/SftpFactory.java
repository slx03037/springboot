package com.tools.sftp.web.factory;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.tools.sftp.web.config.SftpProperties;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * @author shenlx
 * @description
 * @date 2026/6/19 20:48
 */
public class SftpFactory extends BasePooledObjectFactory<ChannelSftp> {
    private final SftpProperties properties;

    public SftpFactory(SftpProperties properties) {
        this.properties = properties;
    }

    @Override
    public ChannelSftp create() throws Exception {
        JSch jsch = new JSch();
        // 如果配置了私钥路径，则使用私钥认证[reference:17]
        if (properties.getPrivateKeyPath() != null && !properties.getPrivateKeyPath().isEmpty()) {
            jsch.addIdentity(properties.getPrivateKeyPath());
        }

        Session session = jsch.getSession(properties.getUsername(), properties.getHost(), properties.getPort());
        if (properties.getPassword() != null && !properties.getPassword().isEmpty()) {
            session.setPassword(properties.getPassword());
        }

        // 注意：生产环境应配置 known hosts，此处仅为示例[reference:18]
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        session.connect(properties.getConnectTimeout());
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        return channel;
    }

    @Override
    public PooledObject<ChannelSftp> wrap(ChannelSftp channel) {
        return new DefaultPooledObject<>(channel);
    }

    @Override
    public void destroyObject(PooledObject<ChannelSftp> p) throws Exception {
        ChannelSftp channel = p.getObject();
        if (channel != null && channel.isConnected()) {
            channel.disconnect();
        }
        Session session = channel.getSession();
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }

    @Override
    public boolean validateObject(PooledObject<ChannelSftp> p) {
        ChannelSftp channel = p.getObject();
        return channel != null && channel.isConnected() && !channel.isClosed();
    }
}
