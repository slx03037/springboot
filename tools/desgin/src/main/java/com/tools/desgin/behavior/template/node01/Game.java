package com.tools.desgin.behavior.template.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/9/24 11:35
 */
public abstract class Game {
    abstract void initialize();
    abstract void startPlay();
    abstract void endPlay();

    //模板
    public final void play(){
        //初始化游戏
        initialize();
        //开始游戏
        startPlay();
        //结束游戏
        endPlay();
    }
}
