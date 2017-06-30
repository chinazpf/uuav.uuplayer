package com.uuav.aninterface;

import com.google.gson.JsonObject;

/**
 * Created by Administrator on 2017/6/29.
 *  数据请求回调接口
 */

public interface TransactionCallback {
    /**
     * 请求数据成功
     * @param jsonObject 返回数据
     */
    public void onSuccess(JsonObject jsonObject);

    /**
     * 请求数据失败
     * @param jsonObject 返回的失败原因
     */
    public void  onFail(String jsonObject);
}
