package com.zls.www.rxlib.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.zls.www.rxlib.rxframe.RetrofitHelper;

/**
 * Created by dodo on 2018/6/20.
 */

public abstract class BaseNetworkFragment extends RxFragment {

    protected RetrofitHelper mRetrofitHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNetwork();
    }

    private void initNetwork(){
        if(mRetrofitHelper == null){
            mRetrofitHelper = RetrofitHelper.getInstance();
        }
    }
}
