package com.tenke.voicesdk;

/**
 * Created by marshall on 07/02/2017.
 */

public enum VoiceIntent {

    //common voice control intent

    COMMON_NONE,
    COMMON_EXIT_OR_CANCEL,
    COMMON_WAKEUP,
    COMMON_IDLE,

    //nav related voice control intent
    NAV_SEARCH_COMMON,
    NAV_SEARCH_FOOD,
    NAV_SEARCH_COFFEE,
    NAV_SEARCH_PARKING,
    NAV_SEARCH_GAS,

    NAV_SELECT_INDEX,
    NAV_NEXT_PAGE,
    NAV_PREVIOUS_PAGE,

    NAV_START_NAVI,
    NAV_CANCEL_NAVI,//not start navi in detail page
    NAV_STOP_NAVI,
    NAV_DRIVE_TO,
    NAV_ROUTE_OVERVIEW,
    NAV_ROUTE_CONTINUE,

    NAV_BACK,

    NAV_LANGUAGE_MANDARIN,
    NAV_LANGUAGE_SICHUANESE,
    NAV_LANGUAGE_CANTONESE,

    NAV_AUDIO_CONTROL,

    NAV_DRIVE_HOME,
    NAV_DRIVE_WORK,

    //music related voice control intent
    MUSIC_PLAY,
    MUSIC_NEXT_SONG,
    MUSIC_PREVIOUS_SONG,
    MUSIC_PAUSE,
    MUSIC_RESUME,
    MUSIC_STOP,

    MUSIC_LOGIN_QQ_MUSIC,
    MUSIC_LOGIN_NETEASE_MUSIC,

    //radio intent
    RADIO_PLAY,

    //phone intent
    PHONE_CALL,
    PHONE_ACCEPT,
    PHONE_REJECT,
    PHONE_CONFIRM_FUZZY_MATCH,

    //wechat intent
    WECHAT_LOGIN,
    WECHAT_LOGOUT,
    WECHAT_SEND_MESSAGE,
    WECHAT_SEND_MESSAGE_CONFIRM,
    WECHAT_VALIDATE_RECEIVER,
    WECHAT_LOGIN_VALIDATION,
    WECHAT_STOP_AUTO_PLAY,
    WECHAT_REPLY_MESSAGE,

    //system setting intent
    SETTINGS_VOLUME_UP,
    SETTINGS_VOLUME_DOWN,

    AVOID_TYPE,

    CONTROL_EXIT,
    COMMON_ERROR,
}
