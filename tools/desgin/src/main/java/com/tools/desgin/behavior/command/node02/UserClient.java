package com.tools.desgin.behavior.command.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 15:47
 */
public class UserClient {
    private Remote remote=null;


    public UserClient(Remote remote){
        this.remote=remote;
    }

    public void action(String tvType,String task){
        remote.controlTV(tvType,task);
    }
}
