package com.zls.www.rxlib.fragments.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Created by dodo on 2018/6/20.
 */

public  abstract  class BaseUiFragment extends BaseFunctionFragment {

    protected View viewRoot;

    public static <T extends BaseUiFragment> T newInstance(Class<T> clazz) {
        T fragment = null;
        try {
            fragment = clazz.newInstance();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fragment;
    }

    protected abstract View inflaterView(LayoutInflater inflater,
                                         ViewGroup container, Bundle bundle);
    protected void initViews() {
    }


    protected void initData() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflaterView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, viewRoot);
        initViews();
        initData();
        return viewRoot;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint() && !isHidden()) {
            onVisibilityChanged(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint() && !isHidden()) {
            onVisibilityChanged(false);
        }
    }


    public void onVisibilityChanged(boolean visible) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindDrawables(viewRoot);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViews(View convert, int ids) {
        return (T) convert.findViewById(ids);
    }

    public <T extends View> T findViews(int ids) {
        return findViews(viewRoot, ids);
    }

    //解除绑定
    private void unbindDrawables(View view) {
        try {
            if (view == null) return;
            if (view.getBackground() != null) {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
                }
                ((ViewGroup) view).removeAllViews();
            }
        } catch (Exception ignore) {
        }
    }
}
