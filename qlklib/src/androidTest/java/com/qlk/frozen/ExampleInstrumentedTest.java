package com.qlk.frozen;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.Toast;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.qlk.lib.test", appContext.getPackageName());
        Looper.prepare();
        Toast.makeText(appContext, "hhfdfsdfsdfsfsfsfdsfh", 0).show();
        String str = Environment.getExternalStorageDirectory().getAbsolutePath();
        System.out.println("nnn");
        System.out.println(str);
        Toast.makeText(appContext, "hhfdfsdfsdfsfsfsfdsfh" + str, 0).show();
        Log.i("ExampleInstrumentedTest", "useAppContext: str:" + str);
        assert str == null;
        Looper.loop();
    }
}
