package com.tools.desgin.behavior.Memento.node02;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 16:48
 */
public class Record {

    private final Map<String,Progress> dateMap=new HashMap<>();

    public void put(Progress progress){
        dateMap.put(progress.getPlayData().getVideoName(),progress);
    }

    public Progress get(String videoName){
        return dateMap.get(videoName);
    }
}
