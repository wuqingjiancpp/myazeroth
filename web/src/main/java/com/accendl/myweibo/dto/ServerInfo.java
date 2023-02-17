package com.accendl.myweibo.dto;

public class ServerInfo {
    private int player;
    private int character;
    private int peak;
    private String uptime;
    private int updateTimeDiff;
    private int averageDelay;

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter(int character) {
        this.character = character;
    }

    public int getPeak() {
        return peak;
    }

    public void setPeak(int peak) {
        this.peak = peak;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public int getUpdateTimeDiff() {
        return updateTimeDiff;
    }

    public void setUpdateTimeDiff(int updateTimeDiff) {
        this.updateTimeDiff = updateTimeDiff;
    }

    public int getAverageDelay() {
        return averageDelay;
    }

    public void setAverageDelay(int averageDelay) {
        this.averageDelay = averageDelay;
    }
}
