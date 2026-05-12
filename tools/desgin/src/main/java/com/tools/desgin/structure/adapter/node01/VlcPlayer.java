package com.tools.desgin.structure.adapter.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/26 14:25
 */
public class VlcPlayer implements AdvanceMediaPlayer{
    @Override
    public void plateVlc(String fileName) {
        System.out.println("Playing vlc file .name: "+fileName);
    }

    @Override
    public void playMp4(String fileName) {

    }
}
