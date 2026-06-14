package com.component.security.advance.web.cache;

import com.component.security.advance.web.dto.UserDTO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shenlx
 * @description
 * @date 2026/6/14 10:03
 */
@Data
public class UserCache {

    public static  ConcurrentHashMap<String, UserDTO> userMap=new ConcurrentHashMap<String, UserDTO>();


    //根据token获取用户信息
   public static UserDTO getUser(String token){
       if (StringUtils.isBlank(token)){
           return null;
       }

       if (userMap.isEmpty()){
           return null;
       }

       for ( Map.Entry<String, UserDTO> entry : userMap.entrySet()){
            if(entry.getKey().equals(token)){
                return entry.getValue();
            }
       }
       return null;
   }

}
