package com.uuav.aninterface;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/6/29.
 */

public class InternetProgressDialog extends Dialog {
    private Context context = null;
    private static InternetProgressDialog internetProgressDialog = null;
    public InternetProgressDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public InternetProgressDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected InternetProgressDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static InternetProgressDialog createProgress(Context context){
        if (internetProgressDialog == null) {
            internetProgressDialog = new InternetProgressDialog(context, R.style.InternetProgressDialog);
            internetProgressDialog.setContentView(R.layout.internet_progress_dialog);
            internetProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        }
        return internetProgressDialog;
    }

    /**
     * 设置底部显示的文字
     * @param msg
     */
    public void  setBottomText(String msg){
        if (internetProgressDialog == null&&msg.length()>0) {
            TextView textView = (TextView) internetProgressDialog.findViewById(R.id.tv_internet_dec);
            textView.setText(msg);
        }
    }
}
