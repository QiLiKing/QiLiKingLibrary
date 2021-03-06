package com.qlk.frozen.utils;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        double d = Double.valueOf(".13e01");
        System.out.println(d);
//        "[+-]?(\\d*.\\d+|\\d+\\.?\\d*)"
        Matcher matcher = Pattern.compile("[+,-]?(\\d+\\.?\\d*|\\d*\\.\\d+)([e,E][+,-]?[1-9]\\d{0,2})").matcher("-1.1e");
        int i = 0;
        while (matcher.find(i)) {
            System.out.println(matcher.group());
            i = matcher.end();
        }


        IOUtil.close();
//        IOUtil.close(null); //error
        IOUtil.close(null, null);

        InputStream is = null;
        IOUtil.close(is);
    }

    @Test
    public void toByteArray() {
        InputStream is = new ByteArrayInputStream(BYTES);
        try {
            byte[] b = IOUtil.toByteArray(is);
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
            long len = IOUtil.copy(is, os);
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
            assertEquals("b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`b\u001A&18`", IOUtil.toString(is));
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