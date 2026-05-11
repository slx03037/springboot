package com.socket.websocket.webmvc.server.handler;

import org.springframework.lang.NonNull;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shenlx
 * @description
 * @date 2025/2/27 16:50
 */
public class PrefixUrlPathHelper extends UrlPathHelper {

    private final String prefix;
    public PrefixUrlPathHelper(String prefix){this.prefix=prefix;}
    @Override
    public @NonNull String resolveAndCacheLookupPath(@NonNull HttpServletRequest request) {
        //获得原本的Path
        String path = super.resolveAndCacheLookupPath(request);
        //如果是指定前缀就返回对应的通配路径
        if (path.startsWith(prefix)) {
            return prefix + "/**";
        }
        return path;
    }
}
