package com.uuav.uuplayer.utils;

import android.util.Log;

/**
 * 日志工具类
 */
public class LogUtil {

    //开关
    public static boolean isDebug = true;// 是否调试
    /**
     * 鸡蛋的log方法
     * @param msg
     */
    public static void logZpfInfo(String msg){
        if(isDebug){
            Log.i("zpf",msg);
        }
    }
}
