package com.tools.desgin.advanced.builder.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/21 14:21
 */
public class ProjectManager {
    private final BuilderSoft builderSoft;

    ProjectManager(BuilderSoft soft){
        this.builderSoft=soft;
    }

    public  void createSoft(){
        builderSoft.earlyWork();
        builderSoft.midWork();
        builderSoft.lateWork();
    }
}
