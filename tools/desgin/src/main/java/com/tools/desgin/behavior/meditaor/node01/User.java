package com.tools.desgin.behavior.meditaor.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 17:27
 */
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name){
        this.name=name;
    }

    public void sendMessage(String message){
        ChatRoom.showMessage(this,message);
    }
}
