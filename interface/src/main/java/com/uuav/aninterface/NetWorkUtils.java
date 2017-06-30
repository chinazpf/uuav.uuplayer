package com.uuav.aninterface;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * Created by Administrator on 2017/6/29.
 */

public class NetWorkUtils {

    /**
     * 判断网络状态
     * @param context
     * @return
     */
    public static boolean getNetWorkStatus(final Activity context){
        boolean netSataus = false;
        ConnectivityManager cwjManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        cwjManager.getActiveNetworkInfo();

        if (cwjManager.getActiveNetworkInfo() != null) {
            netSataus = cwjManager.getActiveNetworkInfo().isAvailable();
        }

        if (!netSataus) {
            AlertDialog.Builder b = new AlertDialog.Builder(context).setTitle("没有可用的网络")
                    .setMessage("是否对网络进行设置？");
            b.setPositiveButton("是", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Intent mIntent = null;
                    if(android.os.Build.VERSION.SDK_INT>10){
                        mIntent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    }else{
                        mIntent = new Intent("/");
                        ComponentName comp = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                        mIntent.setComponent(comp);
                        mIntent.setAction("android.intent.action.VIEW");

                    }
                    context.startActivityForResult(mIntent,0);
                }
            }).setNeutralButton("否", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            }).show();
        }
        return netSataus;
    }
}
