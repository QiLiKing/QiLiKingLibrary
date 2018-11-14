package com.qlk.frozen.utils;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/14 14:16
 */
public class QlkFormatUtilTest {
    private static final long[] TIMES = {Long.MIN_VALUE, -235354, -1, 0, 1, 235354, 1534227651544L, System.currentTimeMillis(), Long.MAX_VALUE};

    @Test
    public void formatDate() {
        final long today = 1534227651544L;

        System.out.println("\nYear Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year :%s", time, FrzFormatUtil.formatY(time)));
        }
        assertEquals("2018", FrzFormatUtil.formatY(today));

        System.out.println("\nYear-Month Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month :%s", time, FrzFormatUtil.formatYM(time)));
        }
        assertEquals("2018-08", FrzFormatUtil.formatYM(today));

        System.out.println("\nYear-Month-Day Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month-day :%s", time, FrzFormatUtil.formatYMD(time)));
        }
        assertEquals("2018-08-14", FrzFormatUtil.formatYMD(today));

        System.out.println("\nHour:Minute Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---hour:minute :%s", time, FrzFormatUtil.formatHm(time)));
        }
        assertEquals("14:20", FrzFormatUtil.formatHm(today));

        System.out.println("\nHour:Minute:Second Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---hour:minute:second :%s", time, FrzFormatUtil.formatHms(time)));
        }
        assertEquals("14:20:51", FrzFormatUtil.formatHms(today));

        System.out.println("\nYear-Month-Day Hour:Minute Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month-day hour:minute :%s", time, FrzFormatUtil.formatYMDHm(time)));
        }
        assertEquals("2018-08-14 14:20", FrzFormatUtil.formatYMDHm(today));

        System.out.println("\nYear-Month-Day Hour:Minute:Second Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month-day hour:minute:second :%s", time, FrzFormatUtil.formatYMDHms(time)));
        }
        assertEquals("2018-08-14 14:20:51", FrzFormatUtil.formatYMDHms(today));
    }

    @Test
    public void formStr() {
        final String[] strs = {"a", "bc", "cef", null, "", " ", "g", "hij"};
        assertEquals("abccef ghij", FrzFormatUtil.formatStr(strs));
    }

    @Test
    public void formatCountdownTime() {
        final long today = 1534227651544L;
        System.out.println("\nFormat Count Down Time");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---format:%s", time, FrzFormatUtil.formatCountdownTime(time)));
        }
        assertEquals("00:00", FrzFormatUtil.formatCountdownTime(-1));
        assertEquals("00:01", FrzFormatUtil.formatCountdownTime(1000));
        assertEquals("00:10", FrzFormatUtil.formatCountdownTime(10000));
        assertEquals("01:01", FrzFormatUtil.formatCountdownTime(61000));
        assertEquals("01:01:01", FrzFormatUtil.formatCountdownTime(3661000));
        assertEquals("01:11:01", FrzFormatUtil.formatCountdownTime(4261000));
        assertEquals("17757天 06:20:51", FrzFormatUtil.formatCountdownTime(1534227651544L));
    }

    @Test
    public void formatTimeToDescribe() {
        System.out.println("\nFormat Time Describe");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---describe:%s", time, FrzFormatUtil.formatTimeToDescribe(time)));
        }
        final long day = 24 * 60 * 60 * 1000;
        final long now = System.currentTimeMillis();
        long date = now + 1000;
        assertEquals(FrzFormatUtil.formatDate(date, "MM-dd HH:mm"), FrzFormatUtil.formatTimeToDescribe(date));

        date = now + day;
        assertEquals(FrzFormatUtil.formatDate(date, "MM-dd HH:mm"), FrzFormatUtil.formatTimeToDescribe(date));

        date = now + 367 * day;
        assertEquals(FrzFormatUtil.formatDate(date, "yyyy-MM-dd HH:mm"), FrzFormatUtil.formatTimeToDescribe(date));

        date = now - 367 * day;
        assertEquals(FrzFormatUtil.formatDate(date, "yyyy-MM-dd HH:mm"), FrzFormatUtil.formatTimeToDescribe(date));


        assertEquals("", FrzFormatUtil.formatTimeToDescribe(0));
        assertEquals("", FrzFormatUtil.formatTimeToDescribe(-1));
        assertEquals("1970-01-01 08:00", FrzFormatUtil.formatTimeToDescribe(1));
        assertEquals("刚刚", FrzFormatUtil.formatTimeToDescribe(now));
        assertEquals("刚刚", FrzFormatUtil.formatTimeToDescribe(now - 1));
        assertEquals("1分钟前", FrzFormatUtil.formatTimeToDescribe(now - 60 * 1000));
        assertEquals("59分钟前", FrzFormatUtil.formatTimeToDescribe(now - 59 * 60 * 1000));
        assertEquals("1小时前", FrzFormatUtil.formatTimeToDescribe(now - 60 * 60 * 1000));
        assertEquals("23小时前", FrzFormatUtil.formatTimeToDescribe(now - 23 * 60 * 60 * 1000));
    }

    @Test
    public void formatDecimal() {
        System.out.println("\nFormat Decimal");
        assertEquals("0%", FrzFormatUtil.formatDecimal(Float.MIN_VALUE));
        assertEquals("0%", FrzFormatUtil.formatDecimal(-1));
        assertEquals("0%", FrzFormatUtil.formatDecimal(0));
        assertEquals("1%", FrzFormatUtil.formatDecimal(0.01f));
        assertEquals("11%", FrzFormatUtil.formatDecimal(0.11f));
        assertEquals("11.1%", FrzFormatUtil.formatDecimal(0.111f));
        assertEquals("11.11%", FrzFormatUtil.formatDecimal(0.1111f));
        assertEquals("80%", FrzFormatUtil.formatDecimal(0.80f));
        assertEquals("100%", FrzFormatUtil.formatDecimal(1f));
        assertEquals("110%", FrzFormatUtil.formatDecimal(1.1f));
        assertEquals("1100.1%", FrzFormatUtil.formatDecimal(11.001f));
        assertEquals("∞%", FrzFormatUtil.formatDecimal(Float.MAX_VALUE));
    }
}