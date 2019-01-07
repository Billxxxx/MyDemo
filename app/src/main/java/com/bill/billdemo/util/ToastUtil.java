package com.bill.billdemo.util;

import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.bill.billdemo.App;

public class ToastUtil {
    private static Toast toast;

    public static void toastShow(String showContent) {
        if (TextUtils.isEmpty(showContent)) return;
        if (showContent.contains("com.android.volley")) return;
        if (toast == null) {
            toast = Toast.makeText(App.Companion.getContext(), showContent, Toast.LENGTH_LONG);
        } else {
            toast.setText(showContent);
        }
        toast.setGravity(Gravity.CENTER, 0, 20);
        toast.show();
    }

    public static void toastShowShort(String showContent) {
        if (TextUtils.isEmpty(showContent)) return;
        if (showContent.contains("com.android.volley")) return;
        if (toast == null) {
            toast = Toast.makeText(App.Companion.getContext(), showContent, Toast.LENGTH_SHORT);
        } else {
            toast.setText(showContent);
        }
        toast.setGravity(Gravity.CENTER, 0, 20);
        toast.show();
    }

    public static void toastShow(int showContent) {
        if (toast == null) {
            toast = Toast.makeText(App.Companion.getContext(), showContent, Toast.LENGTH_LONG);
        } else {
            toast.setText(showContent);
        }
        toast.setGravity(Gravity.CENTER, 0, 20);
        toast.show();
    }
}