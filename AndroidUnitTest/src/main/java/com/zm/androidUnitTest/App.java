package com.zm.androidUnitTest;

import android.app.Application;
import android.content.Context;

/**
 *
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
