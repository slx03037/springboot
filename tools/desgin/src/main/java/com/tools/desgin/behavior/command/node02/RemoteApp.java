package com.tools.desgin.behavior.command.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 15:46
 */
public class RemoteApp implements Remote{

    private TVClient tvClient=null;

    public RemoteApp(TVClient tvClient){
        this.tvClient=tvClient;
    }

    @Override
    public void controlTV(String tvType, String task) {
        tvClient.action(tvType,task);
    }
}
