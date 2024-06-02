package com.tenke.voicesdk;

/**
 * Created by marshall on 13/09/2017.
 */

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
