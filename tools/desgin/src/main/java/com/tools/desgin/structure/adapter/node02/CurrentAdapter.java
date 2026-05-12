package com.tools.desgin.structure.adapter.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/8/26 14:57
 */
public class CurrentAdapter extends Current220V implements Current110V{
    @Override
    public int get110VCurrent() {
        int high=get220VCurrent();
        int low=high/2;
        return low;
    }
}
