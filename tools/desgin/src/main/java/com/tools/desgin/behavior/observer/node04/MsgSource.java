package com.tools.desgin.behavior.observer.node04;

import java.util.Observable;

/**
 * @author shenlx
 * @description
 * @date 2024/9/11 17:26
 */
public class MsgSource extends Observable {
    private String data="";

    public String getData(){
        return data;
    }

    public void setData(String data){
            if(!this.data.equals(data)){
                this.data=data;
                setChanged();
            }
            notifyObservers();
    }
}
