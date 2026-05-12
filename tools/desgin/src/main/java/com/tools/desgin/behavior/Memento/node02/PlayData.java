package com.tools.desgin.behavior.Memento.node02;

/**
 * @author shenlx
 * @description
 * @date 2024/10/10 16:43
 */
public class PlayData {
    private String videoName;
    private String plateTime;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getPlateTime() {
        return plateTime;
    }

    public void setPlateTime(String plateTime) {
        this.plateTime = plateTime;
    }

    public PlayData(String videoName, String plateTime) {
        this.videoName = videoName;
        this.plateTime = plateTime;
    }
}
