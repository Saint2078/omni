package com.tenke.music;


public class SearchResponse extends Bean {
    private int code;

    private Data data;

    private String message;

    private String notice;

    private int subcode;

    private long time;

    private String tips;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return this.data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getNotice() {
        return this.notice;
    }

    public void setSubcode(int subcode) {
        this.subcode = subcode;
    }

    public int getSubcode() {
        return this.subcode;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return this.time;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTips() {
        return this.tips;
    }

}
