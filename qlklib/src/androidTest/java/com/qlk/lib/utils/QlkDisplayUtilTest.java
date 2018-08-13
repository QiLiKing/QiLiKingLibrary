package com.qlk.lib.utils;

import android.content.Context;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
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
    public void getScreenWidth() {
        Context appContext = InstrumentationRegistry.getTargetContext();

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
        Looper.prepare();
        Toast.makeText(appContext, width + "---" + height + "---" + realWidth + "---" + realHeight + "---" + statusBarHeight + "---" + virtualBarHeight, Toast.LENGTH_LONG).show();
        Looper.loop();
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
        Looper.prepare();
        Toast.makeText(appContext, width + "---" + height + "---" + realWidth + "---" + realHeight + "---" + statusBarHeight + "---" + virtualBarHeight, Toast.LENGTH_LONG).show();
        Looper.loop();
    }

}