package com.qlk.lib.utils;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.Formatter;

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

    public static String formatTimeToDescribe(Date date) {
        return formatTimeToDescribe(date.getTime());
    }

    public static String formatTimeToDescribe(long date) {
        final long curTime = System.currentTimeMillis();
        long time = curTime - date;
        time /= 60000; //转换成分钟
        if (time < 0) {
            return "未来某时刻";
        } else if (time < 1)    //少于1分钟
        {
            return "刚刚";
        } else if (time < 60) //一小时之内
        {
            return time + "分钟前";
        } else if (time < 24 * 60)    //一天之内
        {
            return Math.round(1.0f * time / 60) + "小时前";
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(date);
            if (calendar.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
                return formatDate(date, "MM-dd HH:mm"); //今年
            } else {
                return formatDate(date, "yyyy-MM-dd HH:mm");
            }
        }
    }

    public static String formatSize(Context context, long size) {
        return size < 0 ? "0.00 B" : Formatter.formatFileSize(context, size);
    }

    public static String formatDecimal(float num) {
        return num < 0 ? "0%" : new DecimalFormat("##.##").format(num * 100) + "%";
    }

    public static String formatFullTime(int hour, int min, int second) {
        return String.format(Locale.US, "%02d:%02d:%02d", hour, min, second);
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

    /**
     * 名称是否合法
     */
    public static boolean isNameValid(String name) {
        final String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\r\n\f\b\t]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(name);
        if (!m.find()) {
            if (containsEmoji(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测是否有emoji表情
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >=
                0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >=
                0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * @return 返回正数
     */
    public static int formatToPositiveDigital(String format) {
        if (TextUtils.isEmpty(format)) {
            return -1;
        }
        format = format.replaceAll("[^\\d]", "");
        if (TextUtils.isEmpty(format)) {
            return -1;
        }
        //        if (format.startsWith("0"))
        //        {
        //            char[] ary = format.toCharArray();
        //            for (int i = 0; i < ary.length; i++)
        //            {
        //                if (ary[i] != '0')
        //                {
        //                    format = format.substring(i);
        //                    return Integer.parseInt(format);
        //                }
        //            }
        //            return -1;
        //        }
        //        else
        //        {
        return Integer.parseInt(format);
        //        }
    }

    /**
     * 返回11位手机号，如果为null，则表示不是11位的
     */
    public static String toCorrectPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return null;
        }
        phone = phone.replaceAll("\\+\\d*", "");    //去掉“＋86”等
        phone = phone.replaceAll("[ _-]", "");
        if (phone.length() < 11) {
            return null;
        }
        if (isPhoneNumberValid(phone)) {
            return phone;
        }

        return null;
    }

    public static boolean isPasswordValid(String password) {
        return password.length() >= 4 && password.length() <= 20;
        //        return true;
    }

    public static boolean isPhoneNumberValid(String phoneNum) {
        //Fixme 测试
        return phoneNum.matches("1\\d{10}");
        //        return true;
    }

    /**
     * @param millTime 微秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatFullDateWithoutSeconds(long millTime) {
        return formatDate(millTime, "yyyy-MM-dd HH:mm");
    }

    public static String formatFullDateWithoutSeconds(Date millTime) {
        if (millTime == null) {
            return null;
        }
        return formatDate(millTime.getTime(), "yyyy-MM-dd HH:mm");
    }

    /*-***************************************************
     * TODO QlkNote: 具体格式化
     ****************************************************/

    public static String formatY(long millTime) {
        return formatDate(millTime, "yyyy");
    }

    public static String formatY(Date millTime) {
        if (millTime == null) {
            return null;
        }
        return formatY(millTime.getTime());
    }

    public static String formatYM(long millTime) {
        return formatDate(millTime, "yyyy-MM");
    }

    public static String formatYM(Date millTime) {
        if (millTime == null) {
            return null;
        }
        return formatYM(millTime.getTime());
    }

    public static String formatYMD(long millTime) {
        return formatDate(millTime, "yyyy-MM-dd");
    }

    public static String formatYMD(Date millTime) {
        if (millTime == null) {
            return null;
        }
        return formatYMD(millTime.getTime());
    }

    public static String formatHm(long millTime) {
        return formatDate(millTime, "HH:mm");
    }

    public static String formatHm(Date millTime) {
        if (millTime == null) {
            return null;
        }
        return formatHm(millTime.getTime());
    }

    public static String formatHms(long millTime) {
        return formatDate(millTime, "HH:mm:ss");
    }

    public static String formatHms(Date millTime) {
        if (millTime == null) {
            return null;
        }
        return formatHms(millTime.getTime());
    }

    public static String formatYMDHm(long millTime) {
        return formatDate(millTime, "yyyy-MM-dd HH:mm");
    }

    public static String formatYMDHm(Date millTime) {
        if (millTime == null) {
            return null;
        }
        return formatYMDHm(millTime.getTime());
    }

    public static String formatYMDHms(long millTime) {
        return formatDate(millTime, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatYMDHms(Date millTime) {
        if (millTime == null) {
            return null;
        }
        return formatYMDHms(millTime.getTime());
    }

    /**
     * @param millTime 微秒
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatFullDate(long millTime) {
        return formatYMDHms(millTime);
    }


    public static String formatFullDate(Date millTime) {
        return formatYMDHms(millTime);
    }

    public static String formatWeekday(long millTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millTime);
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return String.valueOf(week);
    }

    /**
     * @param millTime 微秒
     * @return yyyy-MM-dd
     */
    public static String formatSimpleDate(long millTime) {
        return formatYMD(millTime);
    }

    /**
     * @param millTime 微秒
     * @return yyyy-MM-dd
     */
    public static String formatSimpleDate(Date millTime) {
        return formatYMD(millTime);
    }


    /**
     * @param millTime 微秒
     * @return yyyy-MM-dd
     */
    public static String formatTime(long millTime) {
        return formatDate(millTime, "HH:mm:ss");
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
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
        return sdf.format(millTime);
    }

    public static String formatMonthDay(long millTime) {
        return formatDate(millTime, "MM-dd");
    }

    public static String formatMonthDay(Date date) {
        if (date == null) {
            return null;
        }
        return formatDate(date.getTime(), "MM-dd");
    }

    private static final int DAY_PEER = 24 * 60 * 60;
    private static final int HOUR_PEER = 60 * 60;
    private static final int MINUTE_PEER = 60;

    public static String formatCountdownTime(long leftMills) {
        leftMills /= 1000;
        if (leftMills > DAY_PEER) {
            return String.format(Locale.CHINA, "%d天 %02d:%02d:%02d", leftMills / DAY_PEER, leftMills / HOUR_PEER %
                    24, leftMills / MINUTE_PEER % 60, leftMills % 60);
        } else if (leftMills > HOUR_PEER) {
            return String.format(Locale.CHINA, "%02d:%02d:%02d", leftMills / HOUR_PEER % 24, leftMills / MINUTE_PEER
                    % 60, leftMills % 60);
        } else {
            return String.format(Locale.CHINA, "%02d:%02d", leftMills / MINUTE_PEER % 60, leftMills % 60);
        }
    }
}
