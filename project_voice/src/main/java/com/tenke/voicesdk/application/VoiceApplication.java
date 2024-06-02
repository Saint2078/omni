package com.tenke.voicesdk.application;

import android.app.Application;

import com.tenke.baselibrary.ApplicationContextLink;
import com.tenke.baselibrary.LoggerUtil;

public class VoiceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationContextLink.init(this);
        LoggerUtil.init("VoiceSDK",true,false);
    }
}
