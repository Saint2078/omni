package com.tenke.test.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tenke.test.R;
import com.tenke.voice.asr.baidu.control.BaiduASRManager;
import com.tenke.voice.asr.common.ASREvent;

import org.json.JSONObject;


public class AsrActivity extends AppCompatActivity {
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asr);

        mTextView=findViewById(R.id.result);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        mTextView.setOnLongClickListener(v -> {
            mTextView.setText("");
            return true;
        });



        BaiduASRManager.getInstance().registerListener("wp.data", asrEvent -> {
            mTextView.append("\n"+asrEvent.getType());
            BaiduASRManager.getInstance().startASRSession(false);
        });
        BaiduASRManager.getInstance().registerListener("asr.partial",this::addContent);
    }

    @Override
    protected void onStop() {
        BaiduASRManager.getInstance().stopASRSession();
        super.onStop();
    }
    private void addContent(ASREvent asrEvent){
        try {
            JSONObject jsonObject = new JSONObject(asrEvent.getType());
            Logger.d(jsonObject.toString());
            mTextView.append("\n"+jsonObject.getString("best_result"));
            mTextView.append("\n"+asrEvent.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
