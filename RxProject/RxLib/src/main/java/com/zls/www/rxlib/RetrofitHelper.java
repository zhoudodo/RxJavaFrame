package com.zls.www.rxlib;

/**
 * retrofit工具类
 */

public class RetrofitHelper {

    private static RetrofitHelper INSTANCE;

    //获取单例
    public RetrofitHelper getInstance(){
        if(INSTANCE == null){
            synchronized (RetrofitHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new RetrofitHelper();
                }
            }
        }
        return INSTANCE;
    }


    private RetrofitHelper(){
        //构造器

    }

}
