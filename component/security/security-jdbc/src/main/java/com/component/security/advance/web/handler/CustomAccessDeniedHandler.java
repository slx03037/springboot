package com.component.security.advance.web.handler;

/**
 * @author shenlx
 * @description 认证通过没有授权
 * @date 2026/6/9 11:20
 */
//@Component
//@Slf4j
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//
//        Map<String, Object> body = new HashMap<>();
//        body.put("status", HttpServletResponse.SC_FORBIDDEN);
//        body.put("error", "Forbidden");
//        body.put("message", accessDeniedException.getMessage());
//        body.put("path", request.getServletPath());
//        try {
//            response.setStatus(200);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("utf-8");
//            response.getWriter().print(result);
//        } catch (IOException e) {
//            // e.printStackTrace();
//            log.error("认证失败,没有请求的访问认证");
//        }
//    }
//}
