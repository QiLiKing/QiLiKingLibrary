package com.qlk.frozen;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final Pattern checkEmailPattern = Pattern.compile("\"([^\"]+)\"<([a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)>", Pattern.CASE_INSENSITIVE);

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

//        Matcher matcher = checkEmailPattern.matcher("\"aaade111111\"<1111@qq.com>,dfdsfdsgdg");
        Matcher matcher = checkEmailPattern.matcher("\"aaade111111\"<1111@qq.com>,dfdsfdsgdg,\"aaade22222\"<222222@qq.com>\"aaade111111\"<1111@qq.com> \"aaade111111\"<1111@qq.com>;\"aaade111111\"<1111@qq.com>]\"aaade111111\"<1111@qq.com>[oi\"aaade111111\"<1111@qq.com>");
        while (matcher.find()) {
            System.out.println("---------");
            System.out.println("" + matcher.groupCount());
            System.out.println("" + matcher.group(1));
            System.out.println("" + matcher.group(2));
        }
    }
}