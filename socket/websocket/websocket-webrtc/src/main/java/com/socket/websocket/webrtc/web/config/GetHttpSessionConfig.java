package com.socket.websocket.webrtc.web.config;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.List;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2025/3/2 0:32
 */
// 自定义WebSocket握手配置类
public class GetHttpSessionConfig extends ServerEndpointConfig.Configurator {

    // 在WebSocket握手期间修改握手请求和响应
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        Map<String, List<String>> headers = request.getHeaders();  // 获取HTTP请求头
        List<String> cookieList = headers.get("cookie");  // 从请求头中获取cookie列表

        // 检查cookie列表是否非空
        if (cookieList != null && !cookieList.isEmpty()) {
            String cookies = cookieList.get(0);  // 获取cookie字符串
            String[] cookieArray = cookies.split(";");  // 拆分多个cookie值

            // 遍历cookie数组
            for (String cookie : cookieArray) {
                // 查找以"token="开头的cookie
                if (cookie.trim().startsWith("token=")) {
                    String token = cookie.trim().substring("token=".length());  // 获取token的值
                    //String pe = decryptToken.decrypt(token);  // 解密token以获取有效负载
                    //String userName = spiltUtils.getUserName(pe);  // 从有效负载中提取用户名

                    // 将用户名添加到WebSocket的用户属性中，供后续使用
                    //sec.getUserProperties().put("userName", userName);
                    sec.getUserProperties().put("userName", "张三");
                    break;  // 找到用户名后退出循环
                }
            }
        }
    }
}
