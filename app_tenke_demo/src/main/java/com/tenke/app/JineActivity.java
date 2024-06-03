package com.tenke.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tenke.voice.tts.TTSManager;

public class JineActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TextView mTextView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_text);
        TextView textView = findViewById(R.id.content);
        textView.setText("共搜素到5起投资案例，平均投资金额 7520万\n2024-05-11\t亿铸科技\tAI大算力芯片公司\t2020-06\tA轮\t1亿\t人民币\n" +
                "2024-04-26\t沐言智语\tAIGC产品开发服务商\t2023-12\tPre-A轮\t1.2亿\t人民币\n" +
                "2024-04-24\t爱诗科技\tAIGC视觉多模态算法开发商\t2023-04\tA+轮\t1亿\t人民币\n" +
                "2024-04-08\t捏Ta\tAI驱动的次文化内容共创社区\t2022-12\tPre-A轮\t1000万\t人民币\n" +
                "2024-04-07\t幂堂科技\tAI智能拓客产品研发商\t2013-12\tA轮\t4600万\t人民币\n");
        TTSManager.getInstance().speak("处理完毕");
    }

}
