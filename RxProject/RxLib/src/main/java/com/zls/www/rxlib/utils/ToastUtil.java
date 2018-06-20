package com.zls.www.rxlib.utils;

import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import com.zls.www.rxlib.FrameApplication;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


/**
 * Toast 工具类  Created by joeYu on 16/12/5.
 */

public class ToastUtil {

    public static void showToast(final String text) {
        if (TextUtils.isEmpty(text))
            return;
        if (isMainThread()) {
            Toast.makeText(FrameApplication.getFrameContext(), text, Toast.LENGTH_SHORT).show();
        } else {
            //RxJava2
            Observable.create(new ObservableOnSubscribe<String>() {
                @Override
                public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                    emitter.onComplete();
                }

            }).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String s) {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {
                            Toast.makeText(FrameApplication.getFrameContext(), text, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

}
