package com.qlk.frozen.utils;

import com.qlk.frozen.utils.formatter.FormatUtil;

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
            System.out.println(String.format(Locale.US, "time:%d---year :%s", time, FormatUtil.formatY(time)));
        }
        assertEquals("2018", FormatUtil.formatY(today));

        System.out.println("\nYear-Month Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month :%s", time, FormatUtil.formatYM(time)));
        }
        assertEquals("2018-08", FormatUtil.formatYM(today));

        System.out.println("\nYear-Month-Day Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month-day :%s", time, FormatUtil.formatYMD(time)));
        }
        assertEquals("2018-08-14", FormatUtil.formatYMD(today));

        System.out.println("\nHour:Minute Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---hour:minute :%s", time, FormatUtil.formatHm(time)));
        }
        assertEquals("14:20", FormatUtil.formatHm(today));

        System.out.println("\nHour:Minute:Second Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---hour:minute:second :%s", time, FormatUtil.formatHms(time)));
        }
        assertEquals("14:20:51", FormatUtil.formatHms(today));

        System.out.println("\nYear-Month-Day Hour:Minute Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month-day hour:minute :%s", time, FormatUtil.formatYMDHm(time)));
        }
        assertEquals("2018-08-14 14:20", FormatUtil.formatYMDHm(today));

        System.out.println("\nYear-Month-Day Hour:Minute:Second Format");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---year-month-day hour:minute:second :%s", time, FormatUtil.formatYMDHms(time)));
        }
        assertEquals("2018-08-14 14:20:51", FormatUtil.formatYMDHms(today));
    }

    @Test
    public void formStr() {
        final String[] strs = {"a", "bc", "cef", null, "", " ", "g", "hij"};
        assertEquals("abccef ghij", FormatUtil.formatStr(strs));
    }

    @Test
    public void formatCountdownTime() {
        final long today = 1534227651544L;
        System.out.println("\nFormat Count Down Time");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---format:%s", time, FormatUtil.formatCountdownTime(time)));
        }
        assertEquals("00:00", FormatUtil.formatCountdownTime(-1));
        assertEquals("00:01", FormatUtil.formatCountdownTime(1000));
        assertEquals("00:10", FormatUtil.formatCountdownTime(10000));
        assertEquals("01:01", FormatUtil.formatCountdownTime(61000));
        assertEquals("01:01:01", FormatUtil.formatCountdownTime(3661000));
        assertEquals("01:11:01", FormatUtil.formatCountdownTime(4261000));
        assertEquals("17757天 06:20:51", FormatUtil.formatCountdownTime(1534227651544L));
    }

    @Test
    public void formatTimeToDescribe() {
        System.out.println("\nFormat Time Describe");
        for (long time : TIMES) {
            System.out.println(String.format(Locale.US, "time:%d---describe:%s", time, FormatUtil.formatTimeToDescribe(time)));
        }
        final long day = 24 * 60 * 60 * 1000;
        final long now = System.currentTimeMillis();
        long date = now + 1000;
        assertEquals(FormatUtil.formatDate(date, "MM-dd HH:mm"), FormatUtil.formatTimeToDescribe(date));

        date = now + day;
        assertEquals(FormatUtil.formatDate(date, "MM-dd HH:mm"), FormatUtil.formatTimeToDescribe(date));

        date = now + 367 * day;
        assertEquals(FormatUtil.formatDate(date, "yyyy-MM-dd HH:mm"), FormatUtil.formatTimeToDescribe(date));

        date = now - 367 * day;
        assertEquals(FormatUtil.formatDate(date, "yyyy-MM-dd HH:mm"), FormatUtil.formatTimeToDescribe(date));


        assertEquals("", FormatUtil.formatTimeToDescribe(0));
        assertEquals("", FormatUtil.formatTimeToDescribe(-1));
        assertEquals("1970-01-01 08:00", FormatUtil.formatTimeToDescribe(1));
        assertEquals("刚刚", FormatUtil.formatTimeToDescribe(now));
        assertEquals("刚刚", FormatUtil.formatTimeToDescribe(now - 1));
        assertEquals("1分钟前", FormatUtil.formatTimeToDescribe(now - 60 * 1000));
        assertEquals("59分钟前", FormatUtil.formatTimeToDescribe(now - 59 * 60 * 1000));
        assertEquals("1小时前", FormatUtil.formatTimeToDescribe(now - 60 * 60 * 1000));
        assertEquals("23小时前", FormatUtil.formatTimeToDescribe(now - 23 * 60 * 60 * 1000));
    }

    @Test
    public void formatDecimal() {
        System.out.println("\nFormat Decimal");
        assertEquals("0%", FormatUtil.formatDecimal(Float.MIN_VALUE));
        assertEquals("0%", FormatUtil.formatDecimal(-1));
        assertEquals("0%", FormatUtil.formatDecimal(0));
        assertEquals("1%", FormatUtil.formatDecimal(0.01f));
        assertEquals("11%", FormatUtil.formatDecimal(0.11f));
        assertEquals("11.1%", FormatUtil.formatDecimal(0.111f));
        assertEquals("11.11%", FormatUtil.formatDecimal(0.1111f));
        assertEquals("80%", FormatUtil.formatDecimal(0.80f));
        assertEquals("100%", FormatUtil.formatDecimal(1f));
        assertEquals("110%", FormatUtil.formatDecimal(1.1f));
        assertEquals("1100.1%", FormatUtil.formatDecimal(11.001f));
        assertEquals("∞%", FormatUtil.formatDecimal(Float.MAX_VALUE));
    }
}