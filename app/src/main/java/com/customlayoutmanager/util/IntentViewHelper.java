package com.customlayoutmanager.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Fishy on 2017/4/10.
 *
 */

public class IntentViewHelper {
    /**
     * 调用系统拨打电话
     *
     * @param phoneNumber
     * @param context
     */
    public static void callPhone(String phoneNumber, Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        //数据都是以uri的方式传递
        Uri uri = Uri.parse("tel:" + phoneNumber);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /**
     * 调用系统弹出拨打电话界面
     *
     * @param phoneNumber
     * @param context
     */
    public static void dialPhone(String phoneNumber, Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        Uri uri = Uri.parse("tel:" + phoneNumber);
        intent.setData(uri);
        context.startActivity(intent);
    }

    /**
     * 发送短信
     *
     * @param to
     * @param context
     */
    public static void smsMsg(String to, Context context) {
        smsMsg(to, "", context);
    }

    /**
     * 发送短信
     *
     * @param to
     * @param msg
     * @param context
     */
    public static void smsMsg(String to, String msg, Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        Uri uri = Uri.parse("smsto:" + to);
        intent.setData(uri);
        intent.putExtra("sms_body", msg);
        context.startActivity(intent);
    }

    /**
     * 打开网页链接
     * @param url
     * @param context
     */
    public static void openWebLink(String url,Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        if (!url.contains("http://")) {
            url = "http://" + url;
        }
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        context.startActivity(intent);
    }

}
