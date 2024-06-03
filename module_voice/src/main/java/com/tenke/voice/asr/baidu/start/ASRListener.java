package com.tenke.voice.asr.baidu.start;

import com.baidu.speech.EventListener;
import com.orhanobut.logger.Logger;
import com.tenke.voice.asr.algorithm.AlgorithmManager;
import com.tenke.voice.asr.common.ASREvent;
import com.tenke.voice.asr.common.ASREventDispatcher;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.MaybeObserver;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class ASRListener implements EventListener {
    private static final String TAG = "ASRListener";

    private ASRListener() {
    }

    public static ASRListener getInstance() {
        return HOLDER.INSTANCE;
    }

    private static final class HOLDER {
        private static final ASRListener INSTANCE = new ASRListener();
    }

    @Override
    public void onEvent(String name, String params, byte[] data, int offset, int length) {
        if (params == null) return;
        //params : {"errorDesc":"wakup success","errorCode":0,"word":"调试模式"}
        //{"results_recognition":["播放周杰伦的七里香"],
        // "result_type":"final_result",
        // "best_result":"播放周杰伦的七里香",
        // "origin_result":{"corpus_no":6730202075279856211,"err_no":0,
        // "result":{"word":["播放周杰伦的七里香"]},"sn":"3f1cbe44-125f-482d-8e94-123225bb8def_s-2","voice_energy":34121.28125},
        // "error":0}
        Logger.t(TAG).d("dispatchEvent: " + name + " | " + params);
        String nlu = null;
        if(length != 0){
            nlu = new String(data);
            //name:asr.partial
            //{"result_type":"nlu_result","best_result":"","origin_result":""}
            //{"appid":15363,"encoding":"UTF-8","results":[{"domain":"telephone","intent":"PHONE_CALL","score":100.0,"slots":{"user_call_target":[{"word":"孙杰","norm":"孙杰"}]}},{"domain":"contact","intent":"CALL","score":100.0,"slots":{"user_contact":[{"word":"孙杰","norm":"孙杰"}]}}],"err_no":0,"parsed_text":"打 电话 给 孙杰 。","raw_text":"打电话给孙杰。"}
        }

        String type = null;
        try {
            JSONObject jsonObject = new JSONObject(params);
            type=jsonObject.getString("result_type");
        } catch (JSONException e) {
            Logger.e("",e);
        }
        ASREvent event = new ASREvent(name, type,nlu);


        Single.just(event).filter(new Predicate<ASREvent>() {
            @Override
            public boolean test(ASREvent asrEvent) throws Exception {
                JSONObject jsonObject = new JSONObject(asrEvent.getType());
                if(asrEvent.getName().equals("asr.partial")){
                    if(("final_result".equals(jsonObject.get("result_type")) || "nlu_result".equals(jsonObject.get("result_type")))){
                        return  true;
                    }
                }
                return false;
            }
        }).map(new Function<ASREvent, ASREvent>() {
            @Override
            public ASREvent apply(ASREvent asrEvent) throws Exception {
                return AlgorithmManager.getInstance().excute(asrEvent);
            }
        }).subscribe(new MaybeObserver<ASREvent>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ASREvent asrEvent) {
                ASREventDispatcher.getInstance().dispatchEvent(asrEvent);
            }

            @Override
            public void onError(Throwable e) {
                Logger.e(TAG, e);
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
