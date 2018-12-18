package com.microsoft.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

/**
 * @author Created by huiliu on 2018/12/17.
 * @email liu594545591@126.com
 * @introduce
 */
public class DialogUtil {

    /***
     * 创建单选对话框
     *
     */
    public static Dialog createSingleDialog(Context context, String title, String[] datas,
                                                     DialogInterface.OnClickListener linister) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        } else {
            builder.setTitle("温馨提示");
        }
        builder.setItems(datas, linister);
        Dialog dialog = builder.create();
        builder.show();
        return dialog;
    }

    /**
     * 确定框
     */
    public static Dialog createNoticeDialog(Context context, String title,String message,
                                            DialogInterface.OnClickListener linister) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(message).setPositiveButton("重试", linister);
        Dialog dialog = builder.create();
        builder.setCancelable(false);
        builder.show();
        return dialog;

    }






}
