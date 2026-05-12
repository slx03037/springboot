package com.tools.desgin.structure.adapter.node01;

/**
 * @author shenlx
 * @description
 * @date 2024/8/26 14:27
 */
public class MediaAdapter implements MediaPlayer{
    AdvanceMediaPlayer advanceMediaPlayer;

    public MediaAdapter(String audioType){
        if(audioType.equalsIgnoreCase("vlc")){
            advanceMediaPlayer=new VlcPlayer();
        }else if(audioType.equalsIgnoreCase("mp4")){
            advanceMediaPlayer=new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")){
            advanceMediaPlayer.plateVlc(fileName);
        }else if (audioType.equalsIgnoreCase("mp4")){
            advanceMediaPlayer.playMp4(fileName);
        }
    }
}
