package com.tools.desgin.behavior.state.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/11 16:58
 */
public class GrayColor implements BodyColor{
    @Override
    public void change(Chameleon chameleon) {
        System.out.println("变化前:"+chameleon.color+";"+chameleon.contextDesc);

        chameleon.contextDesc="树枝环境";

        chameleon.color="灰色色";

        System.out.println("变化后："+chameleon.color+";"+chameleon.contextDesc);
    }
}
