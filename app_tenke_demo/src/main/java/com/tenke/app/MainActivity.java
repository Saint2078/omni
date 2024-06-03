package com.tenke.app;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.tenke.voice.asr.baidu.control.BaiduASRManager;
import com.tenke.voice.asr.common.ASREvent;
import com.tenke.voice.tts.TTSManager;
import com.yhao.floatwindow.FloatWindow;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView mTextView ;

    private MediaProjection mMediaProjection;
    private MediaProjectionManager mMediaProjectionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.text);
        mTextView.bringToFront();

        BaiduASRManager.getInstance().registerListener(VoiceListener.getInstance());
        VoiceListener.setActivity(this);


//        FloatWindow.with(getApplicationContext())
//                .setView(R.layout.float_voice)
//                .setWidth(1080)
//                .setHeight(400)
//                .setY(1880)
//                .setTag("voice")
//                .build();
//        mTextView = FloatWindow.get("voice").getView().findViewById(R.id.voice_text);
//        mScrollView = FloatWindow.get("voice").getView().findViewById(R.id.textScrollView);
//        mScrollView.fullScroll(View.FOCUS_DOWN);
    }



    private void addContent(ASREvent asrEvent){
        try {
            JSONObject jsonObject = new JSONObject(asrEvent.getType());
            Logger.d(jsonObject.toString());
            mTextView.append("\n"+jsonObject.getString("best_result"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
