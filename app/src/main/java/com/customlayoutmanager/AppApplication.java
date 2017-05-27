package com.customlayoutmanager;

import android.app.Application;

import com.customlayoutmanager.util.FishLog;

/**
 * Created by Fishy on 2017/4/10.
 */

public class AppApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FishLog.enableLog(true);
    }
}
