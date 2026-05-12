package com.tools.desgin.behavior.visitor.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/14 10:20
 */
public class MiddleAge extends Crowd{
    @Override
    void accept(CrowdView crowdView) {
        crowdView.getMiddleAgeView(this);
    }
}
