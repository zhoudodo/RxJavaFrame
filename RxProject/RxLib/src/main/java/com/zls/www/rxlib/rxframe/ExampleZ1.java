package com.zls.www.rxlib.rxframe;

import android.support.annotation.MainThread;
import android.support.annotation.Nullable;

import com.zls.www.rxlib.rxframe.retrofit_api.Common;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.QueryMap;

/**
 * Created by dodo on 2018/6/20.
 */

public class ExampleZ1 {
    //such as a get request

    public void getReuqest(String url, @Nullable  Map<String,Object> maps) {
        if(maps == null){
            maps = new HashMap<>();
        }
        Observable call = RetrofitHelper.getInstance().create(Common.class).getCommonApi(url, maps);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
