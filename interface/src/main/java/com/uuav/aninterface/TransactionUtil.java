package com.uuav.aninterface;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.uuav.entry.Constants;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 数据请求主方法
 * Created by Administrator on 2017/6/29.
 */

public class TransactionUtil {
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");//mdiatype 这个需要和服务端保持一致
    public static OkHttpClient mOkHttpClient;//okHttpClient 实例
    public static Handler okHttpHandler;//全局处理子线程和M主线程通信
    public static final String TAG = "zpf";
    public static InternetProgressDialog progressDialog;

    /**
     * 初始化RequestManager
     */
    public static void initInternet(Context context) {
        //初始化OkHttpClient
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //初始化Handler
        okHttpHandler = new Handler(context.getMainLooper());
    }

    /**
     * okHttp post异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @return
     */
    public static void requestPostByAsyn(Context context, String actionUrl, Map<String, String> paramsMap, final TransactionCallback callBack) {
        initInternet(context);
        try {
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            String params = tempParams.toString();
            Log.i(TAG, "PARAMS::" + params);
            RequestBody body = RequestBody.create(MEDIA_TYPE_MARKDOWN, params);
            String requestUrl = String.format("%s/%s", Constants.Http_Url, actionUrl);
            Log.i(TAG, "URL::" + requestUrl);
            requestUrl = "http://blog.csdn.net/jupiter37/article/details/8770729";
            final Request request = addHeaders().url(requestUrl).post(body).build();
            final Call call = mOkHttpClient.newCall(request);
            final InternetProgressDialog dialog = createDialog(context);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    failedCallBack("访问失败", callBack);
                    Log.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    SystemClock.sleep(2000);
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        Log.e(TAG, "response ----->" + string);
//                        successCallBack(getJsonObject(string), callBack);
                    } else {
                        failedCallBack("服务器错误", callBack);
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * okHttp post异步请求表单提交
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @return
     */
    public static void requestPostByAsynWithForm(Context context, String actionUrl, HashMap<String, String> paramsMap, final TransactionCallback callBack) {
        initInternet(context);
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for (String key : paramsMap.keySet()) {
                builder.add(key, paramsMap.get(key));
            }
            RequestBody formBody = builder.build();
            String requestUrl = String.format("%s/%s", Constants.Http_Url, actionUrl);
            requestUrl = "http://blog.csdn.net/jupiter37/article/details/8770729";
            final Request request = addHeaders().url(requestUrl).post(formBody).build();
            final Call call = mOkHttpClient.newCall(request);
            final InternetProgressDialog dialog = createDialog(context);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    failedCallBack("访问失败", callBack);
                    Log.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        Log.e(TAG, "response ----->" + string);
//                        successCallBack(getJsonObject(string), callBack);
                    } else {
                        failedCallBack("服务器错误", callBack);
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * okHttp get异步请求
     *
     * @param actionUrl 接口地址
     * @param paramsMap 请求参数
     * @param callBack  请求返回数据回调
     * @return
     */
    public static void requestGetByAsyn(Context context, String actionUrl, Map<String, String> paramsMap, final TransactionCallback callBack) {
        initInternet(context);
        StringBuilder tempParams = new StringBuilder();
        try {
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key, URLEncoder.encode(paramsMap.get(key), "utf-8")));
                pos++;
            }
            String requestUrl = String.format("%s/%s?%s", Constants.Http_Url, actionUrl, tempParams.toString());
            final Request request = addHeaders().url(requestUrl).build();
            final Call call = mOkHttpClient.newCall(request);
            final InternetProgressDialog dialog = createDialog(context);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    failedCallBack("访问失败", callBack);
                    Log.e(TAG, e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    if (response.isSuccessful()) {
                        String string = response.body().string();
                        Log.e(TAG, "response ----->" + string);
                        //将字符串变为json
                        JsonObject jsonObject = getJsonObject(string);
                        successCallBack(jsonObject, callBack);
                    } else {
                        failedCallBack("服务器错误", callBack);
                    }
                }
            });
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * 统一处理成功信息
     *
     * @param result
     * @param callBack
     */
    public static void successCallBack(final JsonObject result, final TransactionCallback callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onSuccess(result);
                }
            }
        });
    }

    /**
     * 统一处理失败信息
     *
     * @param result
     * @param callBack
     */
    public static void failedCallBack(final String result, final TransactionCallback callBack) {
        okHttpHandler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onFail(result);
                }
            }
        });
    }

    /**
     * 统一为请求添加头信息
     *
     * @return
     */
    public static Request.Builder addHeaders() {
        Request.Builder builder = new Request.Builder()
                .addHeader("Connection", "keep-alive")
                .addHeader("platform", "2")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE)
                .addHeader("appVersion", "1.0.0");
        return builder;
    }

    /**
     * 将json的字符串变为json对象
     *
     * @param json
     * @return
     */
    public static JsonObject getJsonObject(String json) {
        JsonObject returnData = new JsonParser().parse(json).getAsJsonObject();
        return returnData;
    }

    public static InternetProgressDialog createDialog(Context context) {
        progressDialog = InternetProgressDialog.createProgress(context);
        progressDialog.setBottomText("拼命加载中...");
        progressDialog.show();
        return progressDialog;
    }
}
