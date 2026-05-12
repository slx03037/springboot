package com.tools.desgin.advanced.builder.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 14:29
 */
public class C01_InScene {
    public static void main(String[]args){
        BuilderSoft builderSoft=new SoftIml();

        ProjectManager projectManager = new ProjectManager(builderSoft);

        projectManager.createSoft();
    }
}
