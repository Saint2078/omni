package com.tenke.voicesdk.asr;


public interface BaiduASRAction {

    void startASRSession();

    void stopASRSession();

    void cancelASRSession();

    void destroyASR();
}
