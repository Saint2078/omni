package com.tenke.voicesdk.asr;


import android.media.AudioManager;
import android.media.MediaPlayer;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.tenke.baselibrary.ApplicationContextLink;
import com.tenke.voicesdk.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class BaiduASR implements EventListener,BaiduASRAction,BaiduWakeUpAction{
    private final EventManager mWakeUpManager;
    private final EventManager mAsr;
    private final Observable<ASREvent> mASREventObservable;
    private ObservableEmitter<ASREvent> mASREventObservableEmitter;

    public BaiduASR() {
        mWakeUpManager = EventManagerFactory.create(ApplicationContextLink.LinkToApplication(), "wp");
        // TODO: 2019/5/18  疑惑RXJAVA为什么不能单纯指定一个emitter作为发射源；
        mASREventObservable= Observable.create(new ObservableOnSubscribe<ASREvent>() {
            @Override
            public void subscribe(ObservableEmitter<ASREvent> emitter) throws Exception {
                mASREventObservableEmitter = emitter;
            }
        });
        //注册回调
        mWakeUpManager.registerListener(this);

        mAsr = EventManagerFactory.create(ApplicationContextLink.LinkToApplication(), "asr");
        mAsr.registerListener(this);

        loadOfflineEngine();
    }
    public Observable<ASREvent> getASREventObservable() {
        if(mASREventObservable ==null) return Observable.empty();
        return mASREventObservable;
    }
    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        final ASREvent asrEvent =new ASREvent(name,params,data,offset,length);
        mASREventObservableEmitter.onNext(asrEvent);
        // TODO: 2019/5/18 区分错误和正常消息
    }
    @Override
    public void startASRSession() {
        Schedulers.io().scheduleDirect(new Runnable() {
            @Override
            public void run() {
                MediaPlayer mediaPlayer = MediaPlayer.create(ApplicationContextLink.LinkToApplication(), R.raw.bdspeech_recognition_start);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
            }
        });
        String json = new JSONObject(getParams()).toString();
        mAsr.send(SpeechConstant.ASR_START, json, null, 0, 0);
    }
    @Override
    public void stopASRSession() {
        mAsr.send(SpeechConstant.ASR_STOP, "{}", null, 0, 0);
    }
    @Override
    public void cancelASRSession() {
        mAsr.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
    }
    @Override
    public void destroyASR() {
        if (mAsr != null) mAsr.unregisterListener(this);
        if (mASREventObservableEmitter != null) mASREventObservableEmitter.onComplete();
    }
    private Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
//        params.put(SpeechConstant.SOUND_START, R.raw.bdspeech_recognition_start);
//        params.put(SpeechConstant.SOUND_END, R.raw.bdspeech_speech_end);
//        params.put(SpeechConstant.SOUND_SUCCESS, R.raw.bdspeech_recognition_success);
        params.put(SpeechConstant.SOUND_ERROR, R.raw.bdspeech_recognition_error);
        params.put(SpeechConstant.SOUND_CANCEL, R.raw.bdspeech_recognition_cancel);
        params.put("pid", "15361");
        params.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 1000);
        params.put(SpeechConstant.VAD, SpeechConstant.VAD_DNN);
        params.put(SpeechConstant.DECODER, 2);
        params.put(SpeechConstant.ASR_OFFLINE_ENGINE_GRAMMER_FILE_PATH, "assets:///baidu_speech_grammar.bsg");
        params.put(SpeechConstant.NLU, "enable");
        params.put(SpeechConstant.ACCEPT_AUDIO_VOLUME, false);
        // TODO: 2019/5/18 分析这一句
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
    @Override
    public void startWakeUpSession() {
        //设置唤醒关键词文件
        HashMap<String, Object> params = new HashMap<>();
        params.put(SpeechConstant.APP_ID, "16284693");
        params.put(SpeechConstant.WP_WORDS_FILE, "assets:///WakeUp.bin");
        mWakeUpManager.send(SpeechConstant.WAKEUP_START, new JSONObject(params).toString(), null, 0, 0);
    }
    @Override
    public void stopWakeUpSession() {
        mWakeUpManager.send(SpeechConstant.WAKEUP_STOP, null, null, 0, 0);
    }
    @Override
    public void destoryWakeUp() {
    }
}
