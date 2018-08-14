package com.qlk.lib.utils;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.SparseArray;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/14 11:41
 */
public class QlkFormatUtil {
    public static final long SECOND = 1000;
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;

    public static String formatTimeToDescribe(Date date) {
        return formatTimeToDescribe(date.getTime());
    }

    public static String formatTimeToDescribe(long date) {
        final long curTime = System.currentTimeMillis();
        if (date <= curTime) {
            long time = curTime - date;
            time /= 60000; //转换成分钟
            if (time < 1)    //少于1分钟
            {
                return "刚刚";
            } else if (time < 60) //一小时之内
            {
                return time + "分钟前";
            } else if (time < 24 * 60)    //一天之内
            {
                return Math.round(1.0f * time / 60) + "小时前";
            }
        }

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.setTimeInMillis(date);
        if (calendar.get(Calendar.YEAR) == year) {
            return formatDate(date, "MM-dd HH:mm"); //今年
        } else {
            return formatDate(date, "yyyy-MM-dd HH:mm");
        }
    }

    public static String formatY(long millTime) {
        return formatDate(millTime, "yyyy");
    }

    public static String formatY(Date millTime) {
        return formatY(millTime.getTime());
    }

    public static String formatYM(long millTime) {
        return formatDate(millTime, "yyyy-MM");
    }

    public static String formatYM(Date millTime) {
        return formatYM(millTime.getTime());
    }

    public static String formatYMD(long millTime) {
        return formatDate(millTime, "yyyy-MM-dd");
    }

    public static String formatYMD(Date millTime) {
        return formatYMD(millTime.getTime());
    }

    public static String formatHm(long millTime) {
        return formatDate(millTime, "HH:mm");
    }

    public static String formatHm(Date millTime) {
        return formatHm(millTime.getTime());
    }

    public static String formatHms(long millTime) {
        return formatDate(millTime, "HH:mm:ss");
    }

    public static String formatHms(Date millTime) {
        return formatHms(millTime.getTime());
    }

    public static String formatYMDHm(long millTime) {
        return formatDate(millTime, "yyyy-MM-dd HH:mm");
    }

    public static String formatYMDHm(Date millTime) {
        return formatYMDHm(millTime.getTime());
    }

    public static String formatYMDHms(long millTime) {
        return formatDate(millTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatYMDHms(Date millTime) {
        return formatYMDHms(millTime.getTime());
    }

    /**
     * @param millTime 微秒
     * @param format   默认格式 yyyy-MM-dd HH:mm:ss
     */
    public static String formatDate(long millTime, String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (millTime <= 0) {
            return "";
        }
        FORMATTER.applyPattern(format);
        return FORMATTER.format(millTime);
    }

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    public static String formatCountdownTime(long milliseconds) {
        if (milliseconds > DAY) {
            return String.format(Locale.CHINA, "%d天 %02d:%02d:%02d", milliseconds / DAY, milliseconds / HOUR %
                    24, milliseconds / MINUTE % 60, milliseconds / SECOND % 60);
        } else if (milliseconds > HOUR) {
            return String.format(Locale.CHINA, "%02d:%02d:%02d", milliseconds / HOUR % 24, milliseconds / MINUTE
                    % 60, milliseconds / SECOND % 60);
        } else {
            if (milliseconds < 0) {
                milliseconds = 0;
            }
            return String.format(Locale.CHINA, "%02d:%02d", milliseconds / MINUTE % 60, milliseconds / SECOND % 60);
        }
    }

    /**
     * Decimal to Percent
     *
     * @return 0% for a negative value
     */
    public static String formatDecimal(float num) {
        return num < 0 ? "0%" : new DecimalFormat("##.##").format(num * 100) + "%";
    }

    public static String formatStr(Object... formats) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : formats) {
            if (obj != null) {
                sb.append(obj.toString());
            }
        }
        return sb.toString().trim();
    }
}
