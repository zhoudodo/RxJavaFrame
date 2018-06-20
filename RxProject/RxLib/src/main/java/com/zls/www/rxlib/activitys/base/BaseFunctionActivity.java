package com.zls.www.rxlib.activitys.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by dodo on 2018/6/20.
 */

public abstract class BaseFunctionActivity extends BaseNetworkActivity {

    /**
     * 通过Class跳转界面 *
     */
    public void startAct(Class<? extends Activity> cls) {
        startAct(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面 *
     */
    public void startAct(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivityForResult(intent, requestCode);
    }

    public void startActForResultBundle(Class<?> cls, Bundle mBundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (mBundle != null) {
            intent.putExtras(mBundle);
        }
        startActivityForResult(intent, requestCode);
    }


}
