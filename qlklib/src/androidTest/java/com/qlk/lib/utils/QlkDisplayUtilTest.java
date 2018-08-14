package com.qlk.lib.utils;

import android.content.Context;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.widget.Toast;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/13 15:37
 */
public class QlkDisplayUtilTest {

    @Test
    public void testPxDpSp() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Log.i("QlkDisplayUtilTest", "testPxDpSp: " + QlkDisplayUtil.dp2px(appContext, 10));
        Log.i("QlkDisplayUtilTest", "testPxDpSp: " + QlkDisplayUtil.px2dp(appContext, 100));
        Log.i("QlkDisplayUtilTest", "testPxDpSp: " + QlkDisplayUtil.px2sp(appContext, 10));
        Log.i("QlkDisplayUtilTest", "testPxDpSp: " + QlkDisplayUtil.sp2px(appContext, 100));
    }

    @Test
    public void testLandscape() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        int width = QlkDisplayUtil.getDisplayWidth(appContext);
        int realWidth = QlkDisplayUtil.getScreenWidth(appContext);
        int height = QlkDisplayUtil.getDisplayHeight(appContext);
        int realHeight = QlkDisplayUtil.getScreenHeight(appContext);
        int statusBarHeight = QlkDisplayUtil.getStatusBarHeight(appContext);
        int virtualBarHeight = QlkDisplayUtil.getVirtualBarHeight(appContext);
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
        int width = QlkDisplayUtil.getDisplayWidth(appContext);
        int realWidth = QlkDisplayUtil.getScreenWidth(appContext);
        int height = QlkDisplayUtil.getDisplayHeight(appContext);
        int realHeight = QlkDisplayUtil.getScreenHeight(appContext);
        int statusBarHeight = QlkDisplayUtil.getStatusBarHeight(appContext);
        int virtualBarHeight = QlkDisplayUtil.getVirtualBarHeight(appContext);
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