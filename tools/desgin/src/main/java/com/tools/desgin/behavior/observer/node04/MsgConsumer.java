package com.tools.desgin.behavior.observer.node04;

import java.util.Observable;
import java.util.Observer;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 17:29
 */
public class MsgConsumer implements Observer {

    public MsgConsumer(Observable msgSource){
        msgSource.addObserver(this);
    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("消息内容:"+((MsgSource)o).getData());
    }
}
