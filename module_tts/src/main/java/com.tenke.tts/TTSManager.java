package com.tenke.tts;

import android.media.AudioManager;

import com.jd.ai.manager.SpeechEvent;
import com.jd.ai.manager.SpeechListener;
import com.jd.ai.manager.SpeechManager;
import com.jd.ai.manager.SpeechUtility;
import com.jd.ai.tts.TTSContent;
import com.jd.ai.tts.TTSOperator;
import com.tenke.baselibrary.ApplicationContextLink;

import org.json.JSONException;
import org.json.JSONObject;

import static com.jd.ai.manager.EngineType.TTS;

public class TTSManager {
    //京东的tts居然不支持，shit，要么换手机，要么换tts
    //怎么办？

    private enum TTSStatus{
        STOP,
        START,
    }
    private final SpeechManager mSpeechManager;
    private final TTSStatusListener mTTSStatusListener;
    private TTSManager(){
        mSpeechManager = SpeechUtility.create(ApplicationContextLink.LinkToApplication(), TTS);
        mTTSStatusListener = new TTSStatusListener();
        mSpeechManager.setListener(mTTSStatusListener);
    }
    private static final class Holder{ private static final TTSManager INSTANCE = new TTSManager();}
    public static TTSManager getIntance(){
        return Holder.INSTANCE;
    }

    public void speak(String text) throws JSONException {
        if (mTTSStatusListener.getStatus() == TTSStatus.START) {
            stop();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(TTSContent.APPKEY, "b399d02131515930e3d173425ae32720");
        jsonObject.put(TTSContent.SECRETKEY, "edfad37deee18ab30a1a69105be0af56");
        jsonObject.put(TTSContent.TEXT, text);
        jsonObject.put(TTSContent.STREAME, AudioManager.STREAM_MUSIC);
        jsonObject.put(TTSContent.SAMPLE, 16000);
        jsonObject.put(TTSContent.TIM,1);
        jsonObject.put(TTSContent.SPEED,1);

        mSpeechManager.send(TTSOperator.START, jsonObject.toString());
    }

    public void stop(){
        if (mTTSStatusListener.getStatus() == TTSStatus.START) {
            mSpeechManager.send(TTSOperator.STOP, "");
        }
    }

    private final class TTSStatusListener implements SpeechListener{
        TTSStatus status = TTSStatus.STOP;

        @Override
        public void onEvent(SpeechEvent speechEvent, String s, byte[] bytes) {
            switch (speechEvent){
                case TTS_PLAY_START:
                    status = TTSStatus.START;
                    break;
                case TTS_PLAY_END:
                    status = TTSStatus.STOP;
                    break;
            }
        }

        private TTSStatus getStatus(){
            return status;
        }
    }
}
