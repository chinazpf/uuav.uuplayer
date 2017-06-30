package com.uuav.code;

import android.app.Activity;

import com.uuav.aninterface.NetWorkUtils;
import com.uuav.aninterface.TransactionCallback;
import com.uuav.aninterface.TransactionUtil;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/29.
 * 请求数据
 */

public class InternetAction {
    /**
     * POST请求主方法
     * @param context
     * @param paramsMap
     * @param callBack
     */
    public static void postSbmint(Activity context, String actionUrl , Map<String, String> paramsMap, TransactionCallback callBack){
        if(NetWorkUtils.getNetWorkStatus(context)){
            TransactionUtil.requestPostByAsyn(context, actionUrl,paramsMap,callBack);
        }
    }

    /**
     * GET请求主方法
     * @param context
     * @param paramsMap
     * @param callBack
     */
    public static void getSubmit(Activity context, String actionUrl , Map<String, String> paramsMap,TransactionCallback callBack){
        if(NetWorkUtils.getNetWorkStatus(context)) {
            TransactionUtil.requestGetByAsyn(context, actionUrl, paramsMap, callBack);
        }
    }
}
