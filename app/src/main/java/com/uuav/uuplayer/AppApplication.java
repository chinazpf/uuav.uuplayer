package com.uuav.uuplayer;

import android.app.Application;

/**
 * Created by Administrator on 2017/6/30.
 */

public class AppApplication extends Application{
    public AppApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 获取全局上下文
     * @return
     */
    public AppApplication getApplication(){
        if (instance != null) {
            return instance;
        }
        return instance;
    }
}
