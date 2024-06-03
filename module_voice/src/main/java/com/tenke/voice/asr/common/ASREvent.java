package com.tenke.voice.asr.common;

public class ASREvent {
    private final String name;
    private final String type;
    private final String data;

    public ASREvent(String type, String text,String data){
        this.name = type;
        this.type = text;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
