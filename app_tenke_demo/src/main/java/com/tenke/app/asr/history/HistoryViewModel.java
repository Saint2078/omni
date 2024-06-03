package com.tenke.app.asr.history;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;

public class HistoryViewModel extends AndroidViewModel {
    public final ObservableArrayList<String> histories = new ObservableArrayList<>();

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        histories.add("test1");
        histories.add("test2");
    }

}
