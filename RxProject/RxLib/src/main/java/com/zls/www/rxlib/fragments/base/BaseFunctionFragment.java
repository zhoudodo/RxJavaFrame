package com.zls.www.rxlib.fragments.base;

import android.content.Intent;
import android.os.Bundle;

/**
 * Created by dodo on 2018/6/20.
 */

public abstract class BaseFunctionFragment extends BaseNetworkFragment {




    /**
     * 通过Class跳转界面 *
     */
    public void startAct(Class<?> cls) {
        startAct(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面 *
     */
    public void startAct(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


}
