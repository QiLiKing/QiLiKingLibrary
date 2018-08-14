package com.qlk.lib.utils;

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
            System.out.println(String.format(Locale.US, "time:%d---year :%s", time, QlkFormatUtil.formatY(time)));
        }
        assertEquals("2018", QlkFormatUtil.formatY(today));

        System.out.println("\nYear-Month Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month :%s", time, QlkFormatUtil.formatYM(time)));
        }
        assertEquals("2018-08", QlkFormatUtil.formatYM(today));

        System.out.println("\nYear-Month-Day Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month-day :%s", time, QlkFormatUtil.formatYMD(time)));
        }
        assertEquals("2018-08-14", QlkFormatUtil.formatYMD(today));

        System.out.println("\nHour:Minute Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---hour:minute :%s", time, QlkFormatUtil.formatHm(time)));
        }
        assertEquals("14:20", QlkFormatUtil.formatHm(today));

        System.out.println("\nHour:Minute:Second Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---hour:minute:second :%s", time, QlkFormatUtil.formatHms(time)));
        }
        assertEquals("14:20:51", QlkFormatUtil.formatHms(today));

        System.out.println("\nYear-Month-Day Hour:Minute Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month-day hour:minute :%s", time, QlkFormatUtil.formatYMDHm(time)));
        }
        assertEquals("2018-08-14 14:20", QlkFormatUtil.formatYMDHm(today));

        System.out.println("\nYear-Month-Day Hour:Minute:Second Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month-day hour:minute:second :%s", time, QlkFormatUtil.formatYMDHms(time)));
        }
        assertEquals("2018-08-14 14:20:51", QlkFormatUtil.formatYMDHms(today));
    }

    @Test
    public void formStr() {
        final String[] strs = {"a", "bc", "cef", null, "", " ", "g", "hij"};
        assertEquals("abccef ghij", QlkFormatUtil.formatStr(strs));
    }

    @Test
    public void formatCountdownTime() {
        final long today = 1534227651544L;
        System.out.println("\nFormat Count Down Time");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---format:%s", time, QlkFormatUtil.formatCountdownTime(time)));
        }
        assertEquals("00:00", QlkFormatUtil.formatCountdownTime(-1));
        assertEquals("00:01", QlkFormatUtil.formatCountdownTime(1000));
        assertEquals("00:10", QlkFormatUtil.formatCountdownTime(10000));
        assertEquals("01:01", QlkFormatUtil.formatCountdownTime(61000));
        assertEquals("01:01:01", QlkFormatUtil.formatCountdownTime(3661000));
        assertEquals("01:11:01", QlkFormatUtil.formatCountdownTime(4261000));
        assertEquals("17757天 06:20:51", QlkFormatUtil.formatCountdownTime(1534227651544L));
    }

    @Test
    public void formatTimeToDescribe() {
        System.out.println("\nFormat Time Describe");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---describe:%s", time, QlkFormatUtil.formatTimeToDescribe(time)));
        }
        final long day = 24 * 60 * 60 * 1000;
        final long now = System.currentTimeMillis();
        long date = now + 1000;
        assertEquals(QlkFormatUtil.formatDate(date, "MM-dd HH:mm"), QlkFormatUtil.formatTimeToDescribe(date));

        date = now + day;
        assertEquals(QlkFormatUtil.formatDate(date, "MM-dd HH:mm"), QlkFormatUtil.formatTimeToDescribe(date));

        date = now + 367 * day;
        assertEquals(QlkFormatUtil.formatDate(date, "yyyy-MM-dd HH:mm"), QlkFormatUtil.formatTimeToDescribe(date));

        date = now - 367 * day;
        assertEquals(QlkFormatUtil.formatDate(date, "yyyy-MM-dd HH:mm"), QlkFormatUtil.formatTimeToDescribe(date));


        assertEquals("", QlkFormatUtil.formatTimeToDescribe(0));
        assertEquals("", QlkFormatUtil.formatTimeToDescribe(-1));
        assertEquals("1970-01-01 08:00", QlkFormatUtil.formatTimeToDescribe(1));
        assertEquals("刚刚", QlkFormatUtil.formatTimeToDescribe(now));
        assertEquals("刚刚", QlkFormatUtil.formatTimeToDescribe(now - 1));
        assertEquals("1分钟前", QlkFormatUtil.formatTimeToDescribe(now - 60 * 1000));
        assertEquals("59分钟前", QlkFormatUtil.formatTimeToDescribe(now - 59 * 60 * 1000));
        assertEquals("1小时前", QlkFormatUtil.formatTimeToDescribe(now - 60 * 60 * 1000));
        assertEquals("23小时前", QlkFormatUtil.formatTimeToDescribe(now - 23 * 60 * 60 * 1000));
    }

    @Test
    public void formatDecimal() {
        System.out.println("\nFormat Decimal");
        assertEquals("0%", QlkFormatUtil.formatDecimal(Float.MIN_VALUE));
        assertEquals("0%", QlkFormatUtil.formatDecimal(-1));
        assertEquals("0%", QlkFormatUtil.formatDecimal(0));
        assertEquals("1%", QlkFormatUtil.formatDecimal(0.01f));
        assertEquals("11%", QlkFormatUtil.formatDecimal(0.11f));
        assertEquals("11.1%", QlkFormatUtil.formatDecimal(0.111f));
        assertEquals("11.11%", QlkFormatUtil.formatDecimal(0.1111f));
        assertEquals("80%", QlkFormatUtil.formatDecimal(0.80f));
        assertEquals("100%", QlkFormatUtil.formatDecimal(1f));
        assertEquals("110%", QlkFormatUtil.formatDecimal(1.1f));
        assertEquals("1100.1%", QlkFormatUtil.formatDecimal(11.001f));
        assertEquals("∞%", QlkFormatUtil.formatDecimal(Float.MAX_VALUE));
    }
}