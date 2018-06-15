package com.zls.www.rxlib;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by dodo on 2018/6/15.
 */
public class FrameApplication extends MultiDexApplication {

    protected static Context ctx;


    @Override
    public void onCreate() {
        super.onCreate();
        ctx = this.getApplicationContext();

    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level >= ComponentCallbacks2.TRIM_MEMORY_MODERATE) {
            //清理Glide内存
        }
    }
    public static Context getFrameContext() {
        return ctx;
    }

}