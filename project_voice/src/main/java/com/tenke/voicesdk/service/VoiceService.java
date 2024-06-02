package com.tenke.voicesdk.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.baidu.speech.asr.SpeechConstant;
import com.orhanobut.logger.Logger;
import com.tenke.voicesdk.asr.ASREvent;
import com.tenke.voicesdk.asr.BaiduASR;

import org.json.JSONObject;

import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * 对外输出的应该是最后的结果
 * 同时AIDL+Binder对外提供操控Asr的方法
 * 所有的错误都在内部处理吧，外部处理的错误先不管
 * 语义理解也放在这里吧
 */
public class VoiceService extends Service {
    private static final String TAG = "VoiceService";

    public enum VoiceState {
        IDLE,
        WAKEUP,
        LISTEN_START,
        LISTENING,
        LISTEN_END,
        UNDERSTANDING,

        SPEAKING,
        RETRYING,

        UNDEFINED,
    }

    private final BaiduASR mBaiduASR;
    private VoiceState state = VoiceState.IDLE;

    public VoiceService() {
        mBaiduASR = new BaiduASR();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mBaiduASR.startWakeUpSession();
        mBaiduASR.getASREventObservable()
                .filter(new Predicate<ASREvent>() {
                    @Override
                    public boolean test(ASREvent asrEvent) throws Exception{
                        String name = asrEvent.getName();
                        if("wp.data".equals(name)){
                            state = VoiceState.WAKEUP;
                            return true;
                        }else if ("wp.exit".equals(name)){
                            state = VoiceState.IDLE;
                            return false;
                        }else if("wp.ready".equals(name)){
                            state = VoiceState.IDLE;
                            return false;
                        }else if("wp.error".equals(name)){
                            state = VoiceState.IDLE;
                            JSONObject json = new JSONObject(asrEvent.getParams());
                            // TODO: 2019/5/18
                            throw new Exception("unhandle wakeup error");
                        }
                        switch (name) {
                            case SpeechConstant.CALLBACK_EVENT_ASR_LOADED:
                                Logger.t(TAG).d("onOfflineLoaded ");
                                return false;
                            case SpeechConstant.CALLBACK_EVENT_ASR_UNLOADED:
                                Logger.t(TAG).d("onOfflineUnLoaded ");
                                return false;
                            case SpeechConstant.CALLBACK_EVENT_ASR_READY:
                                state = VoiceState.LISTEN_START;
                                return false;
                            case SpeechConstant.CALLBACK_EVENT_ASR_BEGIN:
                                Logger.t(TAG).d("onAsrBegin ");
                                return false;
                            case SpeechConstant.CALLBACK_EVENT_ASR_END:
                                state = VoiceState.LISTEN_END;
                                return false;
                            case SpeechConstant.CALLBACK_EVENT_ASR_PARTIAL: {
                                // TODO: 2019/5/18
//                                BaiduLocalAsrResult baiduLocalAsrResult = BaiduLocalAsrResult.parseJson(params);
//                                String[] results = baiduLocalAsrResult.getResultsRecognition();
//                                if (baiduLocalAsrResult.isFinalResult()) {
//                                    listener.onAsrFinalResult(results, baiduLocalAsrResult);
//                                } else if (baiduLocalAsrResult.isPartialResult()) {
//                                    listener.onAsrPartialResult(results, baiduLocalAsrResult);
//                                } else if (baiduLocalAsrResult.isNluResult()) {
//                                    listener.onAsrOnlineNluResult(new String(data, offset, length));
//                                }
                                return true;
                            }
                            case SpeechConstant.CALLBACK_EVENT_ASR_FINISH: {
                                // TODO: 2019/5/18
//                                BaiduLocalAsrResult baiduLocalAsrResult = BaiduLocalAsrResult.parseJson(params);
//                                if (baiduLocalAsrResult.hasError()) {
//                                    int errorCode = baiduLocalAsrResult.getError();
//                                    int subErrorCode = baiduLocalAsrResult.getSubError();
//                                    Logger.t(TAG).d("asr error:" + params);
//                                    listener.onAsrFinishError(errorCode, subErrorCode, baiduLocalAsrResult.getDesc(), baiduLocalAsrResult);
//                                } else {
//                                    listener.onAsrFinish(baiduLocalAsrResult);
//                                }
                                return true;
                            }
                            case SpeechConstant.CALLBACK_EVENT_ASR_LONG_SPEECH:
                                Logger.t(TAG).d("onAsrLongFinish ");
                                return false;
                            case SpeechConstant.CALLBACK_EVENT_ASR_EXIT:
                                Logger.t(TAG).d("onAsrExit ");
                                return false;
                            case SpeechConstant.CALLBACK_EVENT_ASR_VOLUME:
                                Logger.t(TAG).d("onAsrVolume ");
//                                // Logger.info(TAG, "asr volume event:" + params);
//                                Volume vol = parseVolumeJson(params);
//                                listener.onAsrVolume(vol.volumePercent, vol.volume);
                                return true;
                            case SpeechConstant.CALLBACK_EVENT_ASR_AUDIO:
                                Logger.t(TAG).d("onAsrAudio ");
                                return false;
                        }
                        throw new Exception("can't match name");
                    }
                }).map(new Function<ASREvent, JSONObject>() {
                    @Override
                    public JSONObject apply(ASREvent asrEvent) throws Exception {
                        JSONObject json = new JSONObject(asrEvent.getParams());
                        json.put("name",asrEvent.getName());
                        return json;
                    }
                });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
