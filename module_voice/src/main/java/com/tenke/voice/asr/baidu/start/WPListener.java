package com.tenke.voice.asr.baidu.start;

import com.baidu.speech.EventListener;
import com.orhanobut.logger.Logger;
import com.tenke.voice.asr.algorithm.AlgorithmManager;
import com.tenke.voice.asr.baidu.control.BaiduASRManager;
import com.tenke.voice.asr.common.ASREvent;
import com.tenke.voice.asr.common.ASREventDispatcher;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class WPListener implements EventListener {
    private static final String TAG = "WPListener";

    private WPListener() {
    }

    public static WPListener getInstance() {
        return HOLDER.INSTANCE;
    }

    private static final class HOLDER {
        private static final WPListener INSTANCE = new WPListener();
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        Logger.t(TAG).d("dispatchEvent: " + name + " | " + params);
        if ("wp.data".equals(name)){
            String word = null;
            try {
                JSONObject jsonObject = new JSONObject(params);
                word=jsonObject.getString("word");
                if (word.equals("开始演示")){
                    BaiduASRManager.getInstance().startASRSession(false);
                    Logger.t(TAG).d("开始ASR");
                }
            } catch (JSONException e) {
                Logger.e("",e);
            }
        }
    }
}
