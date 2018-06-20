package com.zls.www.rxproject;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.zls.www.rxlib.activitys.base.BaseUiActivity;

import java.io.File;
import java.io.IOException;

/**
 * Created by dodo on 2018/6/15.
 */

public class MainActivity extends BaseUiActivity {

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_main);
    }
}
