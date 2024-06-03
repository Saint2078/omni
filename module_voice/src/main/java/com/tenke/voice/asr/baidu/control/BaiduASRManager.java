package com.tenke.voice.asr.baidu.control;

import static com.baidu.speech.asr.SpeechConstant.BDS_ASR_ENABLE_LONG_SPEECH;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.orhanobut.logger.Logger;
import com.tenke.baselibrary.ApplicationContextLink;
import com.tenke.voice.R;
import com.tenke.voice.asr.baidu.start.WPListener;
import com.tenke.voice.asr.common.ASREventDispatcher;
import com.tenke.voice.asr.common.ASREventReceiver;
import com.tenke.voice.asr.common.ASRState;
import com.tenke.voice.asr.common.ASRStatusController;
import com.tenke.voice.asr.baidu.start.ASRListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.schedulers.Schedulers;

public class BaiduASRManager {
    private static final String TAG= "BaiduASRManager";
    private static EventManager mWakeUpManager = null;
    public final EventManager mAsr;

    private BaiduASRManager(){
        mWakeUpManager = EventManagerFactory.create(ApplicationContextLink.LinkToApplication(),"wp");
//        mWakeUpManager.registerListener(ASRListener.getInstance());

        HashMap<String, Object> params = new HashMap<>();
        params.put(SpeechConstant.WP_WORDS_FILE, "assets:///会议纪要，开始演示，记忆搜索.bin");
        params.put(SpeechConstant.APP_ID, "70318835"); // 添加appId
        params.put(SpeechConstant.APP_KEY, "m9rknfytcvysiyMXVWc7r3c7"); // 添加apiKey
        params.put(SpeechConstant.SECRET, "7qW4u8hp0cxcpFLfe0KFKzlLH6a9YEMg"); // 添加secretKey
        mWakeUpManager.send(SpeechConstant.WAKEUP_START, new JSONObject(params).toString(), null, 0, 0);

        mAsr = EventManagerFactory.create(ApplicationContextLink.LinkToApplication(), "asr");
//        mAsr.registerListener(ASRListener.getInstance());
        //loadOfflineEngine();
    }

    public static BaiduASRManager getInstance(){
        return Holder.INSTANCE;
    }

    public static void registerListener(EventListener eventListener){
        mWakeUpManager.registerListener(eventListener);
    }

    private static final class Holder{
        private static final BaiduASRManager INSTANCE = new BaiduASRManager();
    }

    public void startASRSession(boolean isLong) {
        Schedulers.io().scheduleDirect(new Runnable() {
            @Override
            public void run() {
                MediaPlayer mediaPlayer = MediaPlayer.create(ApplicationContextLink.LinkToApplication(), R.raw.bdspeech_recognition_start);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
            }
        });
        HashMap<String,Object> params = (HashMap<String, Object>) getParams();

        if (isLong){
            params.put(SpeechConstant.BDS_ASR_ENABLE_LONG_SPEECH,true);
        }else {
            params.put(SpeechConstant.BDS_ASR_ENABLE_LONG_SPEECH,false);
        }
        String json = new JSONObject(params).toString();
        mAsr.send(SpeechConstant.ASR_START, json, null, 0, 0);
    }
    public void stopASRSession() {
        mAsr.send(SpeechConstant.ASR_STOP, "{}", null, 0, 0);
    }
    public void cancelASRSession() {
        mAsr.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
    }
    public void destroyASR() {
        if (mAsr != null) mAsr.unregisterListener(ASRListener.getInstance());
    }

    public void startWakeUpSession(String wakeup) {
        HashMap<String, Object> params = new HashMap<>();
        params.put(SpeechConstant.WP_WORDS_FILE, wakeup);
        params.put(SpeechConstant.APP_ID, "77648751"); // 添加appId
        params.put(SpeechConstant.APP_KEY, "pIXRxD3e97s1qVgGf6tF7z6f"); // 添加apiKey
        params.put(SpeechConstant.SECRET, "VCtIgOijC0vvK4P3H5XirVf3hqDmKseU"); // 添加secretKey
        mWakeUpManager.send(SpeechConstant.WAKEUP_START, new JSONObject(params).toString(), null, 0, 0);
    }

    public void stopWakeUpSession() {
        mWakeUpManager.send(SpeechConstant.WAKEUP_STOP, null, null, 0, 0);
    }
    public void destoryWakeUp() {

    }

    private Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
//        params.put(SpeechConstant.SOUND_START, R.raw.bdspeech_recognition_start);
//        params.put(SpeechConstant.SOUND_END, R.raw.bdspeech_speech_end);
//        params.put(SpeechConstant.SOUND_SUCCESS, R.raw.bdspeech_recognition_success);
        params.put(SpeechConstant.SOUND_ERROR, R.raw.bdspeech_recognition_error);
        params.put(SpeechConstant.SOUND_CANCEL, R.raw.bdspeech_recognition_cancel);
        params.put("pid", "1537");
        params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 0);
        params.put(SpeechConstant.VAD, SpeechConstant.VAD_DNN);
        params.put(SpeechConstant.DECODER, 0);
        params.put(SpeechConstant.ASR_OFFLINE_ENGINE_GRAMMER_FILE_PATH, "assets:///baidu_speech_grammar.bsg");
        params.put(SpeechConstant.NLU, "enable");
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);
        params.put(SpeechConstant.APP_ID, "77648751"); // 添加appId
        params.put(SpeechConstant.APP_KEY, "pIXRxD3e97s1qVgGf6tF7z6f"); // 添加apiKey
        params.put(SpeechConstant.SECRET, "VCtIgOijC0vvK4P3H5XirVf3hqDmKseU"); // 添加secretKey
//        if (!mVoiceServiceProvider.isSessionStarted()) {
//            params.put(SpeechConstant.AUDIO_MILLS, System.currentTimeMillis() - BACK_TRACK_IN_MILLISECOND);
//        }
        return params;
    }

    private void loadOfflineEngine() {
        Map<String, Object> params = new HashMap<>();
        params.put(SpeechConstant.DECODER, 2);
        params.put(SpeechConstant.ASR_OFFLINE_ENGINE_GRAMMER_FILE_PATH, "assets:///baidu_speech_grammar.bsg");
        String json = new JSONObject(params).toString();
        mAsr.send(SpeechConstant.ASR_KWS_LOAD_ENGINE, json, null, 0, 0);
    }

    public void registerListener(String type, ASREventReceiver receiver){
        ASREventDispatcher.getInstance().registerReceiver(receiver);
    }

    public ASRState getState(){
        return ASRStatusController.getInstance().getASRStatus();
    }
}

