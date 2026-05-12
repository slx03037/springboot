package com.tools.desgin.behavior.command.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/9 15:51
 */
public class C01_Inscene {

    public static void main(String[]args){
        TVClient tvClient = new TVClient();

        RemoteApp remoteApp = new RemoteApp(tvClient);

        UserClient userClient = new UserClient(remoteApp);

        userClient.action("HM","换台");
    }
}
