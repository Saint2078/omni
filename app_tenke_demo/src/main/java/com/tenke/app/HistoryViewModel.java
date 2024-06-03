package com.tenke.app;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

public class HistoryViewModel{
    public final ObservableList<String> histoy = new ObservableArrayList<>();


}
