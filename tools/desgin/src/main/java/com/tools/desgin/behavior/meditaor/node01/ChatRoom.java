package com.tools.desgin.behavior.meditaor.node01;

import java.util.Date;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 17:26
 */
public class ChatRoom {
    public static void showMessage(User user,String message){
        System.out.println(new Date() +"["+user.getName()+"]:"+message);
    }
}
