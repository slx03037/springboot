package com.tools.sftp.web.utils;

import com.common.utils.exception.BusinessException;
import com.jcraft.jsch.*;
import org.apache.commons.lang3.StringUtils;

/**
 * @author shenlx
 * @description
 * @date 2026/6/19 20:47
 */
public class SFTPUtil {
    /**
     * 创建session
     *
     * @param userName   用户名
     * @param password   密码
     * @param host    域名
     * @param port    端口
     * @param privateKeyFile 密钥文件
     * @param passphrase  口令
     * @return
     * @throws BusinessException
     */
    public static Session createSession(String userName, String password, String host, int port, String privateKeyFile, String passphrase) throws BusinessException {
        return createSession(new JSch(), userName, password, host, port, privateKeyFile, passphrase);
    }


    /**
     * 创建session
     *
     * @param jSch
     * @param userName   用户名
     * @param password   密码
     * @param host    域名
     * @param port    端口
     * @param privateKeyFile 密钥
     * @param passphrase  口令
     * @return
     * @throws BusinessException
     */
    public static Session createSession(JSch jSch, String userName, String password, String host, int port, String privateKeyFile, String passphrase) throws BusinessException {
        try {
            if (!StringUtils.isEmpty(privateKeyFile)) {
                // 使用密钥验证方式，密钥可以是有口令的密钥，也可以是没有口令的密钥
                if (!StringUtils.isEmpty(passphrase)) {
                    jSch.addIdentity(privateKeyFile, passphrase);
                } else {
                    jSch.addIdentity(privateKeyFile);
                }
            }
            // 获取session
            Session session = jSch.getSession(userName, host, port);
            if (!StringUtils.isEmpty(password)) {
                session.setPassword(password);
            }
            // 不校验域名
            session.setConfig("StrictHostKeyChecking", "no");
            return session;
        } catch (Exception e) {
            throw new BusinessException(500, "create session fail");
        }
    }

    /**
     * 创建session
     *
     * @param jSch
     * @param userName 用户名
     * @param password 密码
     * @param host  域名
     * @param port  端口
     * @return
     * @throws BusinessException
     */
    public static Session createSession(JSch jSch, String userName, String password, String host, int port) throws BusinessException {
        return createSession(jSch, userName, password, host, port, StringUtils.EMPTY, StringUtils.EMPTY);
    }


    /**
     * 创建session
     *
     * @param jSch
     * @param userName 用户名
     * @param host  域名
     * @param port  端口
     * @return
     * @throws BusinessException
     */
    private Session createSession(JSch jSch, String userName, String host, int port) throws BusinessException {
        return createSession(jSch, userName, StringUtils.EMPTY, host, port, StringUtils.EMPTY, StringUtils.EMPTY);
    }


    /**
     * 开启session链接
     *
     * @param jSch
     * @param userName   用户名
     * @param password   密码
     * @param host    域名
     * @param port    端口
     * @param privateKeyFile 密钥
     * @param passphrase  口令
     * @param timeout    链接超时时间
     * @return
     * @throws BusinessException
     */
    public static Session openSession(JSch jSch, String userName, String password, String host, int port, String privateKeyFile, String passphrase, int timeout) throws BusinessException {
        Session session = createSession(jSch, userName, password, host, port, privateKeyFile, passphrase);
        try {
            if (timeout >= 0) {
                session.connect(timeout);
            } else {
                session.connect();
            }
            return session;
        } catch (Exception e) {
            throw new BusinessException(500, "session connect fail");
        }
    }

    /**
     * 开启session链接
     *
     * @param userName   用户名
     * @param password   密码
     * @param host    域名
     * @param port    端口
     * @param privateKeyFile 密钥
     * @param passphrase  口令
     * @param timeout    链接超时时间
     * @return
     * @throws BusinessException
     */
    public static Session openSession(String userName, String password, String host, int port, String privateKeyFile, String passphrase, int timeout) throws BusinessException {
        Session session = createSession(userName, password, host, port, privateKeyFile, passphrase);
        try {
            if (timeout >= 0) {
                session.connect(timeout);
            } else {
                session.connect();
            }
            return session;
        } catch (Exception e) {
            throw new BusinessException(500, "session connect fail");
        }
    }

    /**
     * 开启session链接
     *
     * @param jSch
     * @param userName 用户名
     * @param password 密码
     * @param host  域名
     * @param port  端口
     * @param timeout  链接超时时间
     * @return
     * @throws BusinessException
     */
    public static Session openSession(JSch jSch, String userName, String password, String host, int port, int timeout) throws BusinessException {
        return openSession(jSch, userName, password, host, port, StringUtils.EMPTY, StringUtils.EMPTY, timeout);
    }

    /**
     * 开启session链接
     *
     * @param userName 用户名
     * @param password 密码
     * @param host  域名
     * @param port  端口
     * @param timeout  链接超时时间
     * @return
     * @throws BusinessException
     */
    public static Session openSession(String userName, String password, String host, int port, int timeout) throws BusinessException {
        return openSession(userName, password, host, port, StringUtils.EMPTY, StringUtils.EMPTY, timeout);
    }

    /**
     * 开启session链接
     *
     * @param jSch
     * @param userName 用户名
     * @param host  域名
     * @param port  端口
     * @param timeout  链接超时时间
     * @return
     * @throws BusinessException
     */
    public static Session openSession(JSch jSch, String userName, String host, int port, int timeout) throws BusinessException {
        return openSession(jSch, userName, StringUtils.EMPTY, host, port, StringUtils.EMPTY, StringUtils.EMPTY, timeout);
    }

    /**
     * 开启session链接
     *
     * @param userName 用户名
     * @param host  域名
     * @param port  端口
     * @param timeout  链接超时时间
     * @return
     * @throws BusinessException
     */
    public static Session openSession(String userName, String host, int port, int timeout) throws BusinessException {
        return openSession(userName, StringUtils.EMPTY, host, port, StringUtils.EMPTY, StringUtils.EMPTY, timeout);
    }

    /**
     * 创建指定通道
     *
     * @param session
     * @param channelType
     * @return
     * @throws BusinessException
     */
    public static Channel createChannel(Session session, ChannelType channelType) throws BusinessException {
        try {
            if (!session.isConnected()) {
                session.connect();
            }
            return session.openChannel(channelType.getValue());
        } catch (Exception e) {
            throw new BusinessException(500, "open channel fail");
        }
    }


    /**
     * 创建sftp通道
     *
     * @param session
     * @return
     * @throws BusinessException
     */
    public static ChannelSftp createSftp(Session session) throws BusinessException {
        return (ChannelSftp) createChannel(session, ChannelType.SFTP);
    }

    /**
     * 创建shell通道
     *
     * @param session
     * @return
     * @throws BusinessException
     */
    public static ChannelShell createShell(Session session) throws BusinessException {
        return (ChannelShell) createChannel(session, ChannelType.SHELL);
    }

    /**
     * 开启通道
     *
     * @param session
     * @param channelType
     * @param timeout
     * @return
     * @throws BusinessException
     */
    public static Channel openChannel(Session session, ChannelType channelType, int timeout) throws BusinessException {
        Channel channel = createChannel(session, channelType);
        try {
            if (timeout >= 0) {
                channel.connect(timeout);
            } else {
                channel.connect();
            }
            return channel;
        } catch (Exception e) {
            throw new BusinessException(500, "connect channel fail");
        }
    }

    /**
     * 开启sftp通道
     *
     * @param session
     * @param timeout
     * @return
     * @throws BusinessException
     */
    public static ChannelSftp openSftpChannel(Session session, int timeout) throws BusinessException {
        return (ChannelSftp) openChannel(session, ChannelType.SFTP, timeout);
    }

    /**
     * 开启shell通道
     *
     * @param session
     * @param timeout
     * @return
     * @throws BusinessException
     */
    public static ChannelShell openShellChannel(Session session, int timeout) throws BusinessException {
        return (ChannelShell) openChannel(session, ChannelType.SHELL, timeout);
    }


    enum ChannelType {
        SESSION("session"),
        SHELL("shell"),
        EXEC("exec"),
        X11("x11"),
        AGENT_FORWARDING("auth-agent@openssh.com"),
        DIRECT_TCPIP("direct-tcpip"),
        FORWARDED_TCPIP("forwarded-tcpip"),
        SFTP("sftp"),
        SUBSYSTEM("subsystem");

        private final String value;

        ChannelType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
