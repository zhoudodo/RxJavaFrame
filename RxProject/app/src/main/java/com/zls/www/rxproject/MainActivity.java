package com.zls.www.rxproject;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

/**
 * Created by dodo on 2018/6/15.
 */

public class MainActivity extends AppCompatActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        AssetManager am = getAssets();
        Object o;
        try {
            o = gson.toJson( am.open("DataBridge_10.94.2.41_20180612110207_cfg.en"), Activity.class);
            Log.d("dodoT", "o = isSuccess" );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
