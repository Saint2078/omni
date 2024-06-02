package com.tenke.voicesdk.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.baidu.android.common.logging.Log;
import com.orhanobut.logger.Logger;
import com.tenke.voicesdk.R;
import com.tenke.voicesdk.service.VoiceService;

public class DebugActivity extends FragmentActivity {
    TextView mTextView;
    StringBuilder stringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        mTextView=findViewById(R.id.text);
        stringBuilder =new StringBuilder();
        Intent intent =new Intent(this,VoiceService.class);
//        bindService(intent, new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName name, IBinder service) {
//                Logger.d("建立连接");
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName name) {
//                Logger.d("连接断开");
//            }
//        },BIND_AUTO_CREATE);
        startService(intent);
    }


    //class VoiceServiceConnection

}
