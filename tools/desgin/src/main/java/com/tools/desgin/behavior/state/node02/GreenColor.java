package com.tools.desgin.behavior.state.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:56
 */
public class GreenColor implements BodyColor{
    @Override
    public void change(Chameleon chameleon) {
        System.out.println("变化前:"+chameleon.color+";"+chameleon.contextDesc);

        chameleon.contextDesc="树叶环境";

        chameleon.color="绿色";

        System.out.println("变化前："+chameleon.color+";"+chameleon.contextDesc);

    }
}
