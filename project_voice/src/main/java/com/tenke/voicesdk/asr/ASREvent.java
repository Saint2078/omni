package com.tenke.voicesdk.asr;

public class ASREvent {
    private final String name;
    private final String params;
    private final byte[] data;
    private final int offset;
    private final int length;

    ASREvent(String name, String params, byte[] data, int offset, int length) {
        this.name = name;
        this.params = params;
        this.data = data;
        this.offset = offset;
        this.length = length;
    }

    @Override
    public String toString() {
        return "name="+name+"\tparams="+params;
    }

    public String getName() {
        return name;
    }

    public String getParams() {
        return params;
    }

    public byte[] getData() {
        return data;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }
}
