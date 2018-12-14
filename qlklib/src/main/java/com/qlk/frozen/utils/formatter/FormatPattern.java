package com.qlk.frozen.utils.formatter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qlk.frozen.utils.ObjectUtil.checkNull;

/**
 * QQ:1055329812
 * Created by QiLiKing on 2018/12/12.
 */
public final class FormatPattern {

    /* 谨记科学计数法的形式 */

    /**
     * 数字（包括整数和小数）
     */
    public static final String NumberRegular = "[+,-]?(\\d+\\.?\\d*|\\d*\\.\\d+)([e,E][+,-]?\\d*|\\d*)"; //科学计数法
    public static final Pattern NumberPattern = Pattern.compile(NumberRegular);

    /**
     * 0~∞位小数
     */
    //注意这里的E,e匹配，要放在\d*之前，否则会导致匹配失败，比如“-1.13e4”
    public static final String DecimalRegular = "[+,-]?(\\d+\\.?\\d*|\\d*\\.\\d+)([e,E][+,-]?\\d{0,3}|\\d*)";    //Double最大到E302,最小E324,但不知道怎么限制，就匹配三位数吧。
    public static final Pattern DecimalPattern = Pattern.compile(DecimalRegular);

    /**
     * 0~n位小数
     */
    public static final String DigitRegular = "[+,-]?(\\d+\\.?\\d{0,%d}|\\d*\\.\\d{1,%d})";

    /**
     * 整数（至少一位整数，不允许有小数）
     */
    public static final String IntegerRegular = "[+,-]?\\d+";

    public static final String PriceRegular = "[+,-]?(\\d+\\.?\\d{0,2}|\\d*\\.\\d{1,2})";
    public static final Pattern PricePattern = Pattern.compile(PriceRegular);

    public static final String WeightRegular = "[+,-]?(\\d+\\.?\\d{0,3}|\\d*\\.\\d{1,3})";   //最多三个小数点
    public static final Pattern WeightPattern = Pattern.compile(WeightRegular);

    public static String getDigitRegular(int digit) {
        return String.format(Locale.US, DigitRegular, digit, digit);
    }

    /**
     * @return true regular empty or isLawful; false text empty or not isLawful
     */
    public static boolean matches(@Nullable CharSequence text, @Nullable String regular) {
        if (TextUtils.isEmpty(regular)) {
            return true;
        } else if (TextUtils.isEmpty(text)) {
            return false;
        } else {
            return Pattern.matches(regular, text);
        }
    }

    /**
     * @return true regular empty or isLawful; false text empty or not isLawful
     */
    public static boolean contains(@Nullable CharSequence text, @Nullable String regular) {
        if (TextUtils.isEmpty(regular)) {
            return true;
        } else if (TextUtils.isEmpty(text)) {
            return false;
        } else {
            return Pattern.compile(regular).matcher(text).find();
        }
    }

    /**
     * @return true regular empty or isLawful; false text empty or not isLawful
     */
    @NonNull
    public static CharSequence findFirst(@Nullable CharSequence text, @Nullable String regular) {
        if (TextUtils.isEmpty(regular) && text != null) {
            return text;
        } else if (TextUtils.isEmpty(text)) {
            return "";
        } else {
            Matcher matcher = Pattern.compile(regular).matcher(text);
            if (matcher.find()) {
                return matcher.group();
            }
            return "";
        }
    }

    /**
     * @return 是否为数字形式
     */
    public static boolean matchesNumber(@Nullable CharSequence text) {
        return matches(text, NumberRegular);
    }

    /**
     * @return 是否为严格的小数点形式
     */
    public static boolean matchesDigit(@Nullable CharSequence text, int digit) {
        return matches(text, String.format(Locale.US, DigitRegular, digit));
    }

    public static boolean matchesInteger(@Nullable CharSequence text) {
        return matches(text, IntegerRegular);
    }

    public static boolean matchesDecimal(@Nullable CharSequence text) {
        return matches(text, DecimalRegular);
    }

    public static boolean matchesPrice(@Nullable CharSequence text) {
        return PricePattern.matcher(checkNull(text)).matches();
    }

    public static boolean matchesWeight(@Nullable CharSequence text) {
        return WeightPattern.matcher(checkNull(text)).matches();
    }
}
