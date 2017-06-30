package com.uuav.uuplayer.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 2017/6/30.
 */

public abstract class BaseActivity extends FragmentActivity {
    public Activity mActivity;
    public Context mContect;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        this.mActivity = this;
        this.mContect = this;
        initView();
        initData();
    }

    /**
     * 获取页面布局id
     * @return
     */
    public abstract int getLayoutID();

    /**
     * 控件初始化
     */
    protected abstract void initView();

    /**
     * 数据初始化
     */
    protected abstract void initData();



}
