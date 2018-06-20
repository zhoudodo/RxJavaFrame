package com.zls.www.rxlib.rxframe.retrofit_api;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by dodo on 2018/6/20.
 */

public interface Common {

    @GET("{pathUrl}")//动态路径get请求  @QueryMAp使用
    public Observable<String> getCommonApi(@Path("pathUrl") String url, @QueryMap Map<String, Object> paramsMap);


    @POST("{url}")
    public Call<String> postComonApi(@Path(value = "url" ,encoded = true) String url, @QueryMap Map<String, Object> paramsMap,
                                     @Body RequestBody body);
}
