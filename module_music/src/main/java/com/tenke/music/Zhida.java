package com.tenke.music;


public class Zhida extends Bean {
    private long chinesesinger;

    private int type;

    private Zhida_album zhida_album;

    private Zhida_singer zhida_singer;

    public void setChinesesinger(long chinesesinger) {
        this.chinesesinger = chinesesinger;
    }

    public long getChinesesinger() {
        return this.chinesesinger;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public void setZhida_album(Zhida_album zhida_album) {
        this.zhida_album = zhida_album;
    }

    public Zhida_album getZhida_album() {
        return this.zhida_album;
    }

    public void setZhida_singer(Zhida_singer zhida_singer) {
        this.zhida_singer = zhida_singer;
    }

    public Zhida_singer getZhida_singer() {
        return this.zhida_singer;
    }
}
