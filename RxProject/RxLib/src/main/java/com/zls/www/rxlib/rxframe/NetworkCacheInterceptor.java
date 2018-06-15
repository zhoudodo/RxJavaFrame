package com.zls.www.rxlib.rxframe;

import com.orhanobut.logger.Logger;
import com.zls.www.rxlib.rxframe.util.NetUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 如果有网络直接获取数据，如果没有网络的话
 */

public class NetworkCacheInterceptor implements Interceptor {

    public static String TAG = NetworkCacheInterceptor.class.getSimpleName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtil.isNetworkAvailable()) {
            //无网络情况
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Logger.d(TAG, "no network");
        }

        Response response = chain.proceed(request);

        if (NetUtil.isNetworkAvailable()) {
            int maxAge = 0 * 60; // 有网络时 设置缓存超时时间为0;
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        } else {
            int maxStale = 60 * 60 * 24; // 无网络时，设置超时为1天
            Logger.d(TAG, "has maxStale=" + maxStale);
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
        return response;
    }
}
