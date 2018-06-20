package com.zls.www.rxlib.rxframe;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.zls.www.rxlib.FrameApplication;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * retrofit工具类
 */

public class RetrofitHelper implements HttpLoggingInterceptor.Logger, Interceptor {

    public static final String BASE_URL = "http://xlc.gosotech.com/";

    private static RetrofitHelper INSTANCE;

    //请求日志拦截器
    protected HttpLoggingInterceptor mHttpLogInterceptor;
    private OkHttpClient mOkHttpClient;
    private static Retrofit mRetrofit;
    //缓存对象
    private final Cache cache;

    //获取单例
    public static RetrofitHelper getInstance(){
        if(INSTANCE == null){
            synchronized (RetrofitHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new RetrofitHelper();
                }
            }
        }
        return INSTANCE;
    }

    public static <T> T  create(final Class<T> service){
       return mRetrofit.create(service);
    }

    private RetrofitHelper(){

        mHttpLogInterceptor = new HttpLoggingInterceptor(this);
        //打印http的body体
        mHttpLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //创建缓存路径
        File cacheFile = new File(getContext().getCacheDir(), "HttpCache");
        Logger.d("HttpCache", "create Cache File = " + cacheFile.getAbsolutePath());
        cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(17, TimeUnit.SECONDS)
                .writeTimeout(21, TimeUnit.SECONDS)
                .readTimeout(21, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(mHttpLogInterceptor) //日志拦截器
//                .addInterceptor(new ForceCacheInterceptor())
//                .addInterceptor(new NetworkCacheInterceptor())
                .cache(cache)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //需要 加入  compile 'org.ligboy.retrofit2:converter-fastjson-android:2.0.2'
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Logger.d("Response url = " + chain.request().url());
        return null;
    }

    @Override
    public void log(String message) {
        Logger.d("HttpLoggingInterceptor log = " + message);
    }
    private Context getContext(){
        return FrameApplication.getFrameContext();
    }
}
