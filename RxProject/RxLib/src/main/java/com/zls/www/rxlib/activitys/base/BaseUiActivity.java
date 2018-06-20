package com.zls.www.rxlib.activitys.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.zls.www.rxlib.R;
import com.zls.www.rxlib.utils.MaterialDialogUtils;

import butterknife.ButterKnife;

/**
 * activity基类
 */

public abstract class BaseUiActivity extends BaseFunctionActivity {

    protected MaterialDialog mProgressDialog;//进度条

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRootView();
        ButterKnife.bind(this);
        if (initBaseParams(savedInstanceState)) {
            initViews();
            initData();
        }
    }
    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    protected abstract void setRootView();

    public void initData() {
    }

    public void initViews() {
    }


    //activity重置  数据重置
    protected boolean initBaseParams(Bundle savedInstanceState) {
        if (savedInstanceState != null) {

        }
        return true;
    }

    public void showProgressDialog() {
        showProDialog("数据请求中...");
    }

    public void showProDialog(CharSequence content) {
        if (mProgressDialog != null) {
            mProgressDialog.show();
            mProgressDialog.setContent(content);
        } else {
            MaterialDialog.Builder builder = MaterialDialogUtils.showIndeterminateProgressDialog(BaseUiActivity.this, content, false);
            mProgressDialog = builder.show();
        }
    }

    //带dismissListener
    public void showProDialog(CharSequence content, DialogInterface.OnDismissListener dismissListener) {
        showProDialog(content);
        mProgressDialog.setOnDismissListener(dismissListener);
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }


    /**
     * 频繁的toast *
     */
    private Toast mToast2;

    //自定义 toast
    public void showWarmToast(String text) {
        if (TextUtils.isEmpty(text))
            return;
        if (mToast2 == null) {
            mToast2 = new Toast(getApplicationContext());
            mToast2.setGravity(Gravity.CENTER, 0, 0);
            mToast2.setView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.toast_photo_center_layout, null));
        }
        ((TextView) mToast2.getView().findViewById(R.id.tv_toast_warm_str)).setText(text);
        mToast2.setDuration(Toast.LENGTH_SHORT);
        mToast2.show();
    }

    //自定义 res  icon_toast_success_warm
    public void showWarmToast(String text, int resId) {
        if (TextUtils.isEmpty(text))
            return;
        if (mToast2 == null) {
            mToast2 = new Toast(getApplicationContext());
            mToast2.setGravity(Gravity.CENTER, 0, 0);
        }
        mToast2.setView(LayoutInflater.from(this).inflate(R.layout.toast_photo_center_layout, null));
        ((ImageView) mToast2.getView().findViewById(R.id.iv_toast_warm)).setImageResource(resId);
        ((TextView) mToast2.getView().findViewById(R.id.tv_toast_warm_str)).setText(text);
        mToast2.setDuration(Toast.LENGTH_SHORT);
        mToast2.show();
    }

}
