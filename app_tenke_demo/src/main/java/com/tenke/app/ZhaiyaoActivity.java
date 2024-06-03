package com.tenke.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.tenke.voice.tts.TTSManager;

public class ZhaiyaoActivity extends AppCompatActivity {

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
        textView.setText("软银集团会长兼社长孙正义提出“AI革命”计划，旨在通过融合AI、半导体和机器人技术来革新所有产业。集团计划以AI半导体为突破口，扩展到数据中心、机器人、发电等领域，预计投资额高达10万亿日元（约合人民币4640.9亿元）。软银集团将在英国半导体设计巨头ARM内部设立新部门，专注于AI半导体开发，预计初始资金数千亿日元，并将生产委托给台积电等代工企业。市场预测显示，AI半导体市场规模将从2024年的300亿美元增长至2032年的2千亿美元以上。孙正义还计划在2026年以后建设使用自主研发半导体的数据中心，并涉足发电领域，包括核聚变和可再生能源技术。软银集团将通过并购和外部投资者募资，总计投入约10万亿日元的风险资金，标志着其业务重心从投资转向以AI为核心的领域。");
        textView.setTextSize(20);
        TTSManager.getInstance().speak("摘要完成");
    }

}
