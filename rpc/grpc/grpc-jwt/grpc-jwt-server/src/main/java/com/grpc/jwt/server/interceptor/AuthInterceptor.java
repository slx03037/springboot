package com.grpc.jwt.server.interceptor;


import com.grpc.jwt.common.AuthConstant;
import io.grpc.*;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

/**
 * @author shenlx
 * @description
 * @date 2025/3/31 17:02
 */
@Component
public class AuthInterceptor implements ServerInterceptor {
    JwtParser parser = Jwts.parserBuilder().setSigningKey(AuthConstant.JWT_KEY).build();


    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        //首先从 Metadata 中提取出当前请求所携带的 JWT 字符串（相当于从请求头中提取出来）。
        String authorization  = metadata.get(Metadata.Key.of(AuthConstant.AUTH_HEADER, Metadata.ASCII_STRING_MARSHALLER));

        Status status = Status.OK;

        if(authorization == null){
            //如果第一步提取到的值为 null 或者这个值不是以指定字符 Bearer 开始的，说明这个令牌是一个非法令牌，设置对应的响应 status 即可。
            status=Status.UNAUTHENTICATED.withDescription("miss authentication token");

        }else if(!authorization.startsWith(AuthConstant.AUTH_TOKEN_TYPE)){
            status = Status.UNAUTHENTICATED.withDescription("unknown token type");
        }else {
            //如果令牌都没有问题的话，接下来就进行令牌的校验，校验失败，则设置相应的 status 即可。
            Jws<Claims> claims=null;
            String token = authorization.substring(AuthConstant.AUTH_TOKEN_TYPE.length()).trim();

            try{
                //校验成功的话，我们就会获取到一个 Jws 对象，从这个对象中我们可以提取出来用户名，并存入到 Context 中
                // ，将来我们在 HelloServiceImpl 中就可以获取到这里的用户名了
                claims = parser.parseClaimsJws(token);
            }catch (JwtException e){
                status=Status.UNAUTHENTICATED.withDescription(e.getMessage()).withCause(e);
            }
            if (claims !=null){
                //登录成功的话，Contexts.interceptCall 方法构建监听器并返回；登录失败，则构建一个空的监听器返回
                Context context = Context.current()
                        .withValue(AuthConstant.AUTH_CLIENT_ID, claims.getBody().getSubject());

                return Contexts.interceptCall(context,serverCall,metadata,serverCallHandler);
            }
        }
        serverCall.close(status,new Metadata());

        return new ServerCall.Listener<ReqT>() {};
    }
}
