package com.tenke.music;

import java.util.List;


public class Song extends Bean {
    private int curnum;

    private int curpage;

    private List<SongList> list;

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

    public void setList(List<SongList> list) {
        this.list = list;
    }

    public List<SongList> getList() {
        return this.list;
    }

    public void setTotalnum(int totalnum) {
        this.totalnum = totalnum;
    }

    public int getTotalnum() {
        return this.totalnum;
    }
}
