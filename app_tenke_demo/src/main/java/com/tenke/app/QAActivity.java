package com.tenke.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tenke.voice.tts.TTSManager;

public class QAActivity extends AppCompatActivity {

    private static final String TAG = "QAActivity";
    TextView question ;
    TextView answer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qa);
        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
    }



}
