package com.component.rest.http.web.node02;

import lombok.Getter;

/**
 * @program: my-spring-all-com.xinwen.mybatis.node01.service
 * @description:
 * @author: shenlx
 * @create: 2023-02-02 21:39
 **/
//@Component
//@ConfigurationProperties(prefix = "httpclient")
@Getter
public class HttpClientProperties {
    /**
     * 建立连接的超时时间
     */
    public static int connectTimeout = 20000;
    /**
     * 连接不够用的等待时间
     */
    public static int requestTimeout = 20000;
    /**
     * 每次请求等待返回的超时时间
     */
    public static int socketTimeout = 30000;
    /**
     * 每个主机最大连接数
     */
    public static int defaultMaxPerRoute = 100;
    /**
     * 最大连接数
     */
    public static int maxTotalConnections = 300;
    /**
     * 默认连接保持活跃的时间
     */
    public static int defaultKeepAliveTimeMillis = 20000;
    /**
     * 空闲连接生的存时间
     */
    public static int closeIdleConnectionWaitTimeSecs = 30;

//    public static int getConnectTimeout() {
//        return connectTimeout;
//    }
//
//    public  static void setConnectTimeout(int connectTimeout) {
//        this.connectTimeout = connectTimeout;
//    }
//
//    public int getRequestTimeout() {
//        return requestTimeout;
//    }
//
//    public void setRequestTimeout(int requestTimeout) {
//        this.requestTimeout = requestTimeout;
//    }
//
//    public int getSocketTimeout() {
//        return socketTimeout;
//    }
//
//    public void setSocketTimeout(int socketTimeout) {
//        this.socketTimeout = socketTimeout;
//    }
//
//    public int getDefaultMaxPerRoute() {
//        return defaultMaxPerRoute;
//    }
//
//    public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
//        this.defaultMaxPerRoute = defaultMaxPerRoute;
//    }
//
//    public int getMaxTotalConnections() {
//        return maxTotalConnections;
//    }
//
//    public void setMaxTotalConnections(int maxTotalConnections) {
//        this.maxTotalConnections = maxTotalConnections;
//    }
//
//    public int getDefaultKeepAliveTimeMillis() {
//        return defaultKeepAliveTimeMillis;
//    }
//
//    public void setDefaultKeepAliveTimeMillis(int defaultKeepAliveTimeMillis) {
//        this.defaultKeepAliveTimeMillis = defaultKeepAliveTimeMillis;
//    }
//
//    public int getCloseIdleConnectionWaitTimeSecs() {
//        return closeIdleConnectionWaitTimeSecs;
//    }
//
//    public void setCloseIdleConnectionWaitTimeSecs(int closeIdleConnectionWaitTimeSecs) {
//        this.closeIdleConnectionWaitTimeSecs = closeIdleConnectionWaitTimeSecs;
//    }
}
