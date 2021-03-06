package com.qlk.frozen.utils;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/13 15:37
 */
public class QlkDisplayUtilTest {

    //720P---2.0x; 1080---2.625x
    @Test
    public void testPxDpSp() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Log.i("QlkDisplayUtilTest", "testPxDpSp: density=" + appContext.getResources().getDisplayMetrics().density);
        Log.i("QlkDisplayUtilTest", "testPxDpSp: scaledDensity=" + appContext.getResources().getDisplayMetrics().scaledDensity);
        Log.i("QlkDisplayUtilTest", "testPxDpSp: 38dp=" + DisplayUtil.dp2px(appContext, 38));
        Log.i("QlkDisplayUtilTest", "testPxDpSp: 100px=" + DisplayUtil.px2dp(appContext, 100));
        Log.i("QlkDisplayUtilTest", "testPxDpSp: 100px=" + DisplayUtil.px2sp(appContext, 100));
        Log.i("QlkDisplayUtilTest", "testPxDpSp: 38sp=" + DisplayUtil.sp2px(appContext, 38));

        int value = 40;
        assertEquals(value, DisplayUtil.px2dp(appContext, DisplayUtil.dp2px(appContext, value)));
        assertEquals(value, DisplayUtil.px2sp(appContext, DisplayUtil.sp2px(appContext, value)));
    }

    @Test
    public void testLandscape() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int width = DisplayUtil.getDisplayWidth(appContext);
        int realWidth = DisplayUtil.getScreenWidth(appContext);
        int height = DisplayUtil.getDisplayHeight(appContext);
        int realHeight = DisplayUtil.getScreenHeight(appContext);
        int statusBarHeight = DisplayUtil.getStatusBarHeight(appContext);
        int virtualBarHeight = DisplayUtil.getVirtualBarHeight(appContext);
        Log.i("QlkDisplayUtilTest", "testLandscape: width=" + width);
        Log.i("QlkDisplayUtilTest", "testLandscape: height=" + height);
        Log.i("QlkDisplayUtilTest", "testLandscape: realWidth=" + realWidth);
        Log.i("QlkDisplayUtilTest", "testLandscape: realHeight=" + realHeight);
        Log.i("QlkDisplayUtilTest", "testLandscape: statusBarHeight=" + statusBarHeight);
        Log.i("QlkDisplayUtilTest", "testLandscape: virtualBarHeight=" + virtualBarHeight);
        assertEquals(realHeight, height);
        assertEquals(realWidth, width + virtualBarHeight);
    }

    @Test
    public void testPortrait() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int width = DisplayUtil.getDisplayWidth(appContext);
        int realWidth = DisplayUtil.getScreenWidth(appContext);
        int height = DisplayUtil.getDisplayHeight(appContext);
        int realHeight = DisplayUtil.getScreenHeight(appContext);
        int statusBarHeight = DisplayUtil.getStatusBarHeight(appContext);
        int virtualBarHeight = DisplayUtil.getVirtualBarHeight(appContext);
        Log.i("QlkDisplayUtilTest", "testPortrait: width=" + width);
        Log.i("QlkDisplayUtilTest", "testPortrait: height=" + height);
        Log.i("QlkDisplayUtilTest", "testPortrait: realWidth=" + realWidth);
        Log.i("QlkDisplayUtilTest", "testPortrait: realHeight=" + realHeight);
        Log.i("QlkDisplayUtilTest", "testPortrait: statusBarHeight=" + statusBarHeight);
        Log.i("QlkDisplayUtilTest", "testPortrait: virtualBarHeight=" + virtualBarHeight);
        assertEquals(width, realWidth);
        assertEquals(realHeight, height + virtualBarHeight);
    }

}