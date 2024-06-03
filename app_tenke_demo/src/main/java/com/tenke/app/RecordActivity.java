package com.tenke.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.orhanobut.logger.Logger;
import com.tenke.voice.asr.baidu.control.BaiduASRManager;

import org.json.JSONException;
import org.json.JSONObject;

public class RecordActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView mTextView ;
    String mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_record);
        mTextView = findViewById(R.id.content);
        mTextView.setGravity(Gravity.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BaiduASRManager.getInstance().startASRSession(true);
        BaiduASRManager.getInstance().mAsr.registerListener(new EventListener() {
            @Override
            public void onEvent(String name, String params, byte[] data, int offset, int length) {
                Logger.t(TAG).d("dispatchEvent: " + name + " | " + params);
                if ("asr.finish".equals(name)){
                    mTextView.append(mText);
                }

                if("asr.partial".equals(name)){
                    try {
                        JSONObject jsonObject = new JSONObject(params);
                        mText = jsonObject.getString("best_result");
                    } catch (JSONException e) {
                        mTextView.append("出现异常");
                    }

                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        BaiduASRManager.getInstance().stopASRSession();
    }
}
