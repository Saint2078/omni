package com.tenke.voice.asr.common;

import java.util.HashSet;

public class ASRStatusController {

    private ASRState mASRState = ASRState.IDLE;

    private ASRStatusController(){

    }

    public static ASRStatusController getInstance(){
        return Holder.INSTANCE;
    }

    private static final class Holder{
        private static final ASRStatusController INSTANCE = new ASRStatusController();
    }

    public void setState(ASRState state){
        mASRState = state;
    }

    public ASRState getASRStatus(){
        return mASRState;
    }




}
