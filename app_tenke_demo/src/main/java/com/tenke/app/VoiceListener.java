package com.tenke.app;

import android.app.Activity;
import android.content.Intent;

import com.baidu.speech.EventListener;
import com.orhanobut.logger.Logger;
import com.tenke.voice.asr.algorithm.AlgorithmManager;
import com.tenke.voice.asr.baidu.control.BaiduASRManager;
import com.tenke.voice.asr.common.ASREvent;
import com.tenke.voice.asr.common.ASREventDispatcher;
import com.tenke.voice.tts.TTSManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.MaybeObserver;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.schedulers.Schedulers;

public class VoiceListener implements EventListener {
    private static final String TAG = "ASRListener";

    private static Activity activity = null;

    private VoiceListener() {
    }

    public static VoiceListener getInstance() {
        return HOLDER.INSTANCE;
    }

    private static final class HOLDER {
        private static final VoiceListener INSTANCE = new VoiceListener();
    }

    public static void setActivity(Activity ins){
        activity = ins;
    }


    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        Logger.t(TAG).d("dispatchEvent: " + name + " | " + params);
        if ("wp.data".equals(name)){
            String word = null;
            try {
                JSONObject jsonObject = new JSONObject(params);
                word=jsonObject.getString("word");
                switch (word){
                    case "开始演示":
                        break;
                    case "会议纪要":
                        Intent intent = new Intent();
                        intent.setClass(activity,RecordActivity.class);
                        activity.startActivity(intent);
                        break;
                    case "记忆搜索":
                        break;
                    case "搜索全网":
                        Schedulers.single().scheduleDirect(new Runnable() {
                            @Override
                            public void run() {
                                gotoInfo();
                            }
                        },4,TimeUnit.SECONDS);
                        break;
                    case "软银这条":
                        Schedulers.single().scheduleDirect(new Runnable() {
                            @Override
                            public void run() {
                                gotoZhaiyao();
                            }
                        },4,TimeUnit.SECONDS);
                        break;
                    case "将此记录":
                        Schedulers.single().scheduleDirect(new Runnable() {
                            @Override
                            public void run() {
                                TTSManager.getInstance().speak("归档完成，已录入数据库");
                                BaiduASRManager.getInstance().stopWakeUpSession();
                                BaiduASRManager.getInstance().startWakeUpSession("assets:///WakeUp2.bin");
                            }
                        },4,TimeUnit.SECONDS);
                        break;
                    case "投资事件":
                        Schedulers.single().scheduleDirect(new Runnable() {
                            @Override
                            public void run() {
                                gotoTouzi();
                            }
                        },1,TimeUnit.SECONDS);
                        break;
                    case "平均金额":
                        Schedulers.single().scheduleDirect(new Runnable() {
                            @Override
                            public void run() {
                                gotoJine();
                            }
                        },2,TimeUnit.SECONDS);
                        break;
                    case "解读数据":
                        Schedulers.single().scheduleDirect(new Runnable() {
                            @Override
                            public void run() {
                                TTSManager.getInstance().speak("数据解读完成，已发送至您的云端");
                            }
                        },2, TimeUnit.SECONDS);
                        BaiduASRManager.getInstance().stopWakeUpSession();
                        BaiduASRManager.getInstance().startWakeUpSession("assets:///WakeUp1.bin");
                        break;

                }
            } catch (JSONException e) {
                Logger.e("",e);
            }
        }
    }

    private void gotoInfo(){
//        Intent intent = new Intent();
//        intent.setClass(activity,InfoActivity.class);
//        activity.startActivity(intent);
    }

    private void gotoZhaiyao(){
        Intent intent = new Intent();
        intent.setClass(activity,ZhaiyaoActivity.class);
        activity.startActivity(intent);
    }

    private void gotoTouzi(){
        Intent intent = new Intent();
        intent.setClass(activity,TouziActivity.class);
        activity.startActivity(intent);
    }

    private void gotoJine(){
//        Intent intent = new Intent();
//        intent.setClass(activity,JineActivity.class);
//        activity.startActivity(intent);
    }
}
