package com.tenke.music;

import java.util.List;


public class Albums extends Bean {
    private int curnum;

    private int curpage;

    private List<AlbumDetail> list;

    private int totalnum;

    public void setCurnum(int curnum) {
        this.curnum = curnum;
    }

    public int getCurnum() {
        return this.curnum;
    }

    public void setCurpage(int curpage) {
        this.curpage = curpage;
    }

    public int getCurpage() {
        return this.curpage;
    }

    public void setList(List<AlbumDetail> list) {
        this.list = list;
    }

    public List<AlbumDetail> getList() {
        return this.list;
    }

    public void setTotalnum(int totalnum) {
        this.totalnum = totalnum;
    }

    public int getTotalnum() {
        return this.totalnum;
    }

}
