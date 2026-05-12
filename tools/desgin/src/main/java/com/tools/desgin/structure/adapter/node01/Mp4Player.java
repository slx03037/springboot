package com.tools.desgin.structure.adapter.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/26 14:26
 */
public class Mp4Player implements AdvanceMediaPlayer{
    @Override
    public void plateVlc(String fileName) {

    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file .name: "+fileName);
    }
}
