package com.qlk.lib.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Note that: we get the width and height with a normal mode.<br/>
 * If you set the display size using adb shell command, here may be an error.
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/13 14:49
 */
public class QlkDisplayUtil {
    /**
     * transfer px to dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);  //add 0.5f for Rounding Algorithm. see https://blog.csdn.net/changcsw/article/details/52440543
    }

    /**
     * transfer dp to px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * transfer px to sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * transfer sp to px
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * Its location does not change whether the screen in portrait or landscape mode.
     */
    public static int getVirtualBarHeight(Context context) {
        int height = getScreenHeight(context) - getDisplayHeight(context);
        if (height <= 0) {
            height = getScreenWidth(context) - getDisplayWidth(context);
        }
        return height;
    }

    /**
     * the full screen width<br/>
     * portrait: include app container<br/>
     * landscape: include status-bar and app container<br/>
     *
     * @return 0 failure
     */
    @SuppressLint("ObsoleteSdkInt")
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.getDefaultDisplay().getRealMetrics(metrics); //Added in Api 17
                return metrics.widthPixels;
            } else {
                return getDisplayWidth(context);
            }
        }
        return 0;
    }

    /**
     * the width in use<br/>
     * portrait: include app container<br/>
     * landscape: include app container<br/>
     *
     * @return 0 failure
     */
    public static int getDisplayWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(metrics);
            return metrics.widthPixels;
        }
        return 0;
    }

    /**
     * the full screen height<br/>
     * portrait: include virtual-bar, status-bar and app container<br/>
     * landscape: include status-bar and app container<br/>
     *
     * @return 0 failure
     */
    @SuppressLint("ObsoleteSdkInt")
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.getDefaultDisplay().getRealMetrics(metrics); //Added in Api 17
                return metrics.heightPixels;
            } else {
                return getDisplayHeight(context);
            }
        }
        return 0;
    }

    /**
     * the height in use<br/>
     * portrait: include status-bar and app container<br/>
     * landscape: include status-bar and app container<br/>
     *
     * @return 0 failure
     */
    public static int getDisplayHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            DisplayMetrics metrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(metrics);
            return metrics.heightPixels;
        }
        return 0;
    }
}
