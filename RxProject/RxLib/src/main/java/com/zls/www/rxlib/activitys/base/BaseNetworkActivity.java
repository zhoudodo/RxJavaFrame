package com.zls.www.rxlib.activitys.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zls.www.rxlib.rxframe.RetrofitHelper;

/**
 * 网络层封装
 */

public abstract class BaseNetworkActivity extends BaseAutoLayoutActivity {


    protected RetrofitHelper mRetrofitHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNetwork();
    }

    private void initNetwork(){
        if(mRetrofitHelper == null){
            mRetrofitHelper = RetrofitHelper.getInstance();
        }
    }
}
