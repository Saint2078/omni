package com.tenke.voicesdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VoiceProxy extends Observable {
    private final static String TAG = "VoiceProxy";
    private final static VoiceProxy instance = new VoiceProxy();
    private Context mContext;

    public static VoiceProxy getInstance() {
        return instance;
    }

    private VoiceProxy() {
    }

    public void check2Init(Context context) {
        mContext = context;

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ProtocolConstants.ACTION_VUI_COMMAND_OUT);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(mVoiceBroadcastReceiver, intentFilter);
    }

    private BroadcastReceiver mVoiceBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ProtocolConstants.ACTION_VUI_COMMAND_OUT.equals(action)) {
                Bundle voiceMessage = intent.getParcelableExtra(ProtocolConstants.KEY_VOICE_COMMAND_BUNDLE);
                onVoiceMessageUpdate(voiceMessage);
            }
        }
    };

    private void onVoiceMessageUpdate(Bundle voiceMessage) {
        String value = voiceMessage.getString(ProtocolConstants.KEY_VOICE_INTENT);
        if (value != null) {
            VoiceIntent voiceIntent = VoiceIntent.valueOf(value);
            switch (voiceIntent) {
                case WECHAT_VALIDATE_RECEIVER:
                case WECHAT_SEND_MESSAGE_CONFIRM:
                case WECHAT_LOGIN_VALIDATION:
                case WECHAT_LOGOUT:
                case WECHAT_STOP_AUTO_PLAY:
                case COMMON_EXIT_OR_CANCEL:
                case PHONE_CALL:
                case NAV_SELECT_INDEX:
                case RADIO_PLAY:
                case PHONE_CONFIRM_FUZZY_MATCH:
                case NAV_DRIVE_HOME:
                case NAV_DRIVE_WORK:
                    setChanged();
                    notifyObservers(voiceMessage);
                    break;
            }
        }
    }

    public void receiveWeChatMessage(Bundle bundle) {
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.WECHAT_RECEIVE_MESSAGE);
        feedVoiceAssistant(bundle);
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    public synchronized void deleteObserver(Observer o) {
        super.deleteObserver(o);
    }

    public void loginValidationDone(boolean isLogin, String fromIntent) {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.WECHAT_LOGIN_STATUS);
        bundle.putBoolean(ProtocolConstants.WECHAT_DATA_KEY_IS_LOGIN, isLogin);
        bundle.putString(ProtocolConstants.WECHAT_DATA_KEY_FROM_INTENT, fromIntent);
        feedVoiceAssistant(bundle);
    }

    public void findWechatCandidatesDone(ArrayList<Parcelable> candidatesIds) {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.WECHAT_FIND_CANDIDATES_DONE);
        bundle.putParcelableArrayList(ProtocolConstants.WECHAT_DATA_KEY_CANDIDATES_IDS, candidatesIds);
        feedVoiceAssistant(bundle);
    }

    public void mediaSearchNotFound() {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.MUSIC_SEARCH_NOT_FOUND);
        feedVoiceAssistant(bundle);
    }

    public void mediaByNameAndArtist(String songName, @NonNull ArrayList<String> performerNames) {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.MUSIC_BY_SONG_AND_ARTIST);
        bundle.putString(ProtocolConstants.MUSIC_DATA_KEY_SONG_NAME, songName);
        bundle.putStringArrayList(ProtocolConstants.MUSIC_DATA_KEY_ARTIST_NAME, performerNames);
        feedVoiceAssistant(bundle);
    }

    public void mediaByCategory(String category) {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.MUSIC_BY_CATEGORY);
        bundle.putString(ProtocolConstants.MUSIC_DATA_KEY_CATEGORY_NAME, category);
        feedVoiceAssistant(bundle);
    }

    public void mediaByArtist(String artist) {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.MUSIC_BY_ARTIST);
        bundle.putString(ProtocolConstants.MUSIC_DATA_KEY_ARTIST_NAME, artist);
        feedVoiceAssistant(bundle);
    }

    private void feedVoiceAssistant(Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(ProtocolConstants.ACTION_VUI_COMMAND_IN);
        intent.putExtra(ProtocolConstants.KEY_VOICE_COMMAND_BUNDLE, bundle);
        mContext.sendBroadcast(intent);
    }


    public void phoneReadyToCall(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.PHONE_READY_TO_CALL);
        bundle.putString(ProtocolConstants.PHONE_DATA_KEY_CONTACT_NAME, name);
        feedVoiceAssistant(bundle);
    }

    public void confirmFuzzyMatch(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.PHONE_CONFIRM_FUZZY_MATCH);
        bundle.putString(ProtocolConstants.PHONE_DATA_KEY_CONTACT_NAME, name);
        bundle.putString(ProtocolConstants.PHONE_DATA_KEY_CONTACT_NUMBER, name);
        feedVoiceAssistant(bundle);
    }

    public void phoneNameNotFound() {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.PHONE_NAME_NOT_FOUND);
        feedVoiceAssistant(bundle);
    }

    public void phoneIsNotAvailable() {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.PHONE_IS_NOT_AVAILABLE);
        feedVoiceAssistant(bundle);
    }

    public void phoneBookIsNotAvailable() {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.PHONE_BOOK_IS_NOT_AVAILABLE);
        feedVoiceAssistant(bundle);
    }

    public void findContactListDone(int candidatesCount) {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.PHONE_FIND_CANDIDATES_DONE);
        bundle.putInt(ProtocolConstants.PHONE_DATA_KEY_CONTACT_LIST_COUNT, candidatesCount);
        feedVoiceAssistant(bundle);
    }

    public void quitVUI() {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.COMMON_QUIT);
        feedVoiceAssistant(bundle);
    }

    public void wechatMessageSendFail() {
        Bundle bundle = new Bundle();
        bundle.putString(ProtocolConstants.IN_EVENT_TYPE_KEY, ProtocolConstants.WECHAT_MESSAGE_SEND_FAIL);
        feedVoiceAssistant(bundle);
    }

}
