package com.tenke.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.orhanobut.logger.Logger;
import com.tenke.baselibrary.generated.callback.OnClickListener;
import com.tenke.voice.asr.baidu.control.BaiduASRManager;
import com.tenke.voice.tts.TTSManager;

import org.json.JSONException;
import org.json.JSONObject;

public class QAActivity extends AppCompatActivity {

    private static final String TAG = "QAActivity";
    EditText question ;
    TextView answer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qa);
        question = findViewById(R.id.question);
        question.setMovementMethod(ScrollingMovementMethod.getInstance());

        answer = findViewById(R.id.answer);
        answer.setMovementMethod(ScrollingMovementMethod.getInstance());

        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaiduASRManager.getInstance().startASRSession(false);
            }
        });

        findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaiduASRManager.getInstance().stopASRSession();
                question.setText("");
                answer.setText("");
            }
        });

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (question.getText().toString().contains("Rewind") || question.getText().toString().contains("倒带")){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {

                    }
                    answer.setText("帮助您搜索到以下记录:要是回去能有止痛药水：评 Rewind[https://ios.sspai.com/post/77790],人生搜索引擎Rewind使用体验怎么样？[https://www.huxiu.com/article/1896776.html]");
                }
                if (question.getText().toString().contains("分析")){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {

                    }
                    answer.setText("以下是根据链接中的内容分析得出的Rewind的缺陷：\n" +
                            "1.功能限制：Rewind在iPhone端主要支持屏幕截图和Safari浏览记录的回溯，与Mac端相比，后者能记录并回溯用户几乎所有的屏幕活动，包括麦克风录音，功能上存在较大差距。\n" +
                            "2.用户习惯：不是所有用户都有截屏或使用Safari的习惯，这可能限制了Rewind的实用性。\n" +
                            "3.浏览器兼容性：Rewind在iPhone上主要作为Safari的扩展程序，不支持其他浏览器如Chrome，这限制了其在不同用户群体中的普及。\n" +
                            "4.搜索效率：一些用户认为使用Rewind查询可能不如在单个应用内搜索历史记录来得高效。\n" +
                            "5.数据存储和隐私：尽管Rewind声称有高压缩率，但实际使用中可能产生大量数据，对手机存储空间造成压力。同时，用户对隐私保护表示担忧。\n" +
                            "6.电量消耗：有分析认为Rewind可能对老款iPhone的电量消耗较大。\n" +
                            "7.应用稳定性：在iPhone 13上体验Rewind时，出现了闪退问题，表明应用可能存在稳定性问题。\n" +
                            "8.成本问题：Rewind为用户提供有限的免费体验次数，之后需要支付月费，这可能会让一些用户望而却步。\n" +
                            "9.隐私政策疑虑：尽管Rewind强调了对用户隐私的保护，但仍有用户对于数据的安全性表示担忧。\n" +
                            "10.产品定位变化：Rewind最初的宣传语为“你的人生搜索引擎”，后来转向强调“办公助手”功能，这可能表明产品在市场定位上有所调整。");
                }

                if (question.getText().toString().contains("分析")){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {

                    }
                    answer.setText("以下是根据链接中的内容分析得出的Rewind的缺陷：\n" +
                            "1.功能限制：Rewind在iPhone端主要支持屏幕截图和Safari浏览记录的回溯，与Mac端相比，后者能记录并回溯用户几乎所有的屏幕活动，包括麦克风录音，功能上存在较大差距。\n" +
                            "2.用户习惯：不是所有用户都有截屏或使用Safari的习惯，这可能限制了Rewind的实用性。\n" +
                            "3.浏览器兼容性：Rewind在iPhone上主要作为Safari的扩展程序，不支持其他浏览器如Chrome，这限制了其在不同用户群体中的普及。\n" +
                            "4.搜索效率：一些用户认为使用Rewind查询可能不如在单个应用内搜索历史记录来得高效。\n" +
                            "5.数据存储和隐私：尽管Rewind声称有高压缩率，但实际使用中可能产生大量数据，对手机存储空间造成压力。同时，用户对隐私保护表示担忧。\n" +
                            "6.电量消耗：有分析认为Rewind可能对老款iPhone的电量消耗较大。\n" +
                            "7.应用稳定性：在iPhone 13上体验Rewind时，出现了闪退问题，表明应用可能存在稳定性问题。\n" +
                            "8.成本问题：Rewind为用户提供有限的免费体验次数，之后需要支付月费，这可能会让一些用户望而却步。\n" +
                            "9.隐私政策疑虑：尽管Rewind强调了对用户隐私的保护，但仍有用户对于数据的安全性表示担忧。\n" +
                            "10.产品定位变化：Rewind最初的宣传语为“你的人生搜索引擎”，后来转向强调“办公助手”功能，这可能表明产品在市场定位上有所调整。");
                }


            }



        });

        BaiduASRManager.getInstance().mAsr.registerListener(new EventListener() {
            @Override
            public void onEvent(String name, String params, byte[] data, int offset, int length) {
                Logger.t(TAG).d("dispatchEvent: " + name + " | " + params);
//                if ("asr.finish".equals(name)){
//                    mTextView.append(mText);
//                }
//
                if("asr.partial".equals(name)) {
                    try {
                        JSONObject jsonObject = new JSONObject(params);
                        if (jsonObject.getString("best_result")!=null && !jsonObject.getString("best_result").equals("") )  question.setText(jsonObject.getString("best_result"));
                    } catch (JSONException e) {
                        question.append("出现异常");
                    }
                }
            }
        });
    }

}
