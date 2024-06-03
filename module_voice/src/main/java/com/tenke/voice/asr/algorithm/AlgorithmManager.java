package com.tenke.voice.asr.algorithm;

import com.orhanobut.logger.Logger;
import com.tenke.baselibrary.DataManager.DataManager;
import com.tenke.voice.asr.common.ASREvent;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * 功能：
 */
public class AlgorithmManager {
    private static final String KEY = "AlgorithmManagerMap";
    private static final String BEST_RESULT = "best_result";

    private HashMap<String,String> algoMap = new HashMap<>();
    private boolean isEnable = true;

    public void init(){
        Object object = DataManager.getInstance().get(KEY);
        if(object != null && object instanceof HashMap){
            algoMap = (HashMap<String, String>) object;
        }
    }

    //更新
    public void update(String origin,String result){
        algoMap.put(origin,result);
        DataManager.getInstance().put(KEY,algoMap);
    }

    //获取全部结果
    public HashMap<String, String> getAlgoMap() {
        return algoMap;
    }

    //map转换
    public ASREvent excute(ASREvent asrEvent){
        String result = null;
        try {
            JSONObject jsonObject = new JSONObject(asrEvent.getType());
            String origin = jsonObject.getString(BEST_RESULT);
            if(algoMap.containsKey(origin) && isEnable){
                result = algoMap.get(origin);
            }
        } catch (JSONException e) {
            Logger.e("",e);
        }
        return new ASREvent(asrEvent.getName(),asrEvent.getType(),asrEvent.getData());
    }

    //单例
    private AlgorithmManager(){
        init();
    }
    private static final class Holder{
        private static final AlgorithmManager INSTANCE = new AlgorithmManager();
    }
    public static AlgorithmManager getInstance(){
        return Holder.INSTANCE;
    }





}
