package com.tools.desgin.behavior.Memento.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 16:49
 */
public class C01_Inscene {

    public static void main(String[]args){
        Record record=new Record();

        Player player=new Player();

        PlayData pd1=new PlayData("西游记","19:19");
        PlayData pd2=new PlayData("水浒传","20:20");

        player.setPlayData(pd1);

        player.saveProgress();

        record.put(new Progress(pd1));

        System.out.println("正在播放："+player.getPlayData().getVideoName()+":"+player.getPlayData().getPlateTime());

        System.out.println("------------切换播放视频---------------");

        player.setPlayData(pd2);

        player.saveProgress();

        System.out.println("正在播放："+player.getPlayData().getVideoName()+":"+player.getPlayData().getPlateTime());

        record.put(new Progress(pd1));

        System.out.println("------------切回上个视频---------------");

        player.resumeProgress((record.get(pd1.getVideoName())));

        System.out.println("正在播放："+player.getPlayData().getVideoName()+":"+player.getPlayData().getPlateTime());
    }
}
