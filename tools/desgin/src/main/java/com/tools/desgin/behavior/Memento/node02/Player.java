package com.tools.desgin.behavior.Memento.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 16:45
 */
public class Player {
    private PlayData playData;

    public PlayData getPlayData() {
        return playData;
    }

    public void setPlayData(PlayData playData) {
        this.playData = playData;
    }

    public Progress saveProgress(){
        return new Progress(playData);
    }

    public  void resumeProgress(Progress progress){
        playData=progress.getPlayData();
    }
}
