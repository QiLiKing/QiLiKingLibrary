package com.qlk.lib.utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/9 18:04
 */
public class QlkIOUtilTest {
    private static final byte[] BYTES = {98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96, 98, 26, 38, 49, 56, 96};

    @Test
    public void close() {
        QlkIOUtil.close();
//        IOUtil.close(null); //error
        QlkIOUtil.close(null, null);

        InputStream is = null;
        QlkIOUtil.close(is);
    }

    @Test
    public void toByteArray() {
        InputStream is = new ByteArrayInputStream(BYTES);
        try {
            byte[] b = QlkIOUtil.toByteArray(is);
            assertArrayEquals(b, BYTES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void copy() {
        InputStream is = new ByteArrayInputStream(BYTES);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            long len = QlkIOUtil.copy(is, os);
            assertEquals(len, BYTES.length);
            byte[] out = os.toByteArray();
            assertArrayEquals(out, BYTES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void toStr() {
        InputStream is = new ByteArrayInputStream(BYTES);
        try {
            assertEquals("b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`", QlkIOUtil.toString(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printByteArray(byte[] bytes) {
        System.out.println(bytes);
        System.out.println(bytes.length);
        System.out.println(new String(bytes));
    }
}