package com.tenke.music;

/**
 * Created by wxxin on 10/11/2018.
 */

public class Singer extends Bean {
    private long id;

    private String mid;

    private String name;

    private String title;

    private String name_hilight;

    private String title_hilight;

    private long type;

    private long uin;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMid() {
        return this.mid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public String getName_hilight() {
        return name_hilight;
    }

    public void setName_hilight(String name_hilight) {
        this.name_hilight = name_hilight;
    }

    public String getTitle_hilight() {
        return title_hilight;
    }

    public void setTitle_hilight(String title_hilight) {
        this.title_hilight = title_hilight;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getType() {
        return this.type;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public long getUin() {
        return this.uin;
    }
}
