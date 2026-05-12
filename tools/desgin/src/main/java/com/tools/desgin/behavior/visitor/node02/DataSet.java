package com.tools.desgin.behavior.visitor.node02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:22
 */
public class DataSet {
    private final List<Crowd> crowdList=new ArrayList<>();

    public void addCrowd(Crowd crowd){
        crowdList.add(crowd);
    }

    public void display(CrowdView crowdView){
        for(Crowd crowd:crowdList){
            crowd.accept(crowdView);
        }
    }
}
