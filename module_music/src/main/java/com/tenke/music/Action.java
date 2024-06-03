package com.tenke.music;

import com.google.gson.annotations.SerializedName;

public class Action extends Bean {
    private int alert;

    private int icons;

    private int msg;

    @SerializedName("switch")
    private int _switch;

    public void setAlert(int alert) {
        this.alert = alert;
    }

    public int getAlert() {
        return this.alert;
    }

    public void setIcons(int icons) {
        this.icons = icons;
    }

    public int getIcons() {
        return this.icons;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public int getMsg() {
        return this.msg;
    }

    public void setSwitch(int _switch) {
        this._switch = _switch;
    }

    public int getSwitch() {
        return this._switch;
    }

}
