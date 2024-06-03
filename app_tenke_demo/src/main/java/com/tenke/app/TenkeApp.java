package com.tenke.app;

import android.app.Application;

import com.tenke.baselibrary.ApplicationContextLink;
import com.tenke.baselibrary.LoggerUtil;
import com.tenke.voice.asr.baidu.control.BaiduASRManager;

public class TenkeApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationContextLink.init(this);
        LoggerUtil.init(true,false);
        //BaiduASRManager.getInstance().startWakeUpSession();

    }

}
