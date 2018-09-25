package com.zm.androidUnitTest;

import android.app.Application;
import android.content.Context;

/**
 * Created by qktang on 2018/2/24.
 */

public class App extends Application {

    protected static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Context getAppContext() {
        return mApp;
    }
}
