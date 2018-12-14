package com.qlk.frozen.utils.formatter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;

import com.qlk.frozen.utils.formatter.number.DigitFormatter;
import com.qlk.frozen.utils.formatter.number.NumberFormatterPattern;
import com.qlk.frozen.utils.formatter.number.NumberFormatter;
import com.qlk.frozen.utils.formatter.number.NumberFormatterCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.qlk.frozen.utils.ObjectUtil.checkNull;
import static com.qlk.frozen.utils.formatter.number.NumberFormatterCase.PriceCase;
import static com.qlk.frozen.utils.formatter.number.NumberFormatterCase.WeightCase;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/12 16:50
 */
public class FormatManager {

    /* Standard Number */

    @NonNull
    public static CharSequence formatShort(@Nullable Object o) {
        return formatNumber(o, Short.class);
    }

    @NonNull
    public static CharSequence formatInteger(@Nullable Object o) {
        return formatNumber(o, Integer.class);
    }

    @NonNull
    public static CharSequence formatLong(@Nullable Object o) {
        return formatNumber(o, Long.class);
    }

    @NonNull
    public static CharSequence formatFloat(@Nullable Object o) {
        return formatNumber(o, Float.class);
    }

    @NonNull
    public static CharSequence formatDouble(@Nullable Object o) {
        return formatNumber(o, Double.class);
    }

    /**
     * @return "" or a number string
     * @see FormatManager#trimDot(String)
     */
    @NonNull
    public static CharSequence formatNumber(@Nullable Object o, @NonNull Class<? extends Number> cls) {
        NumberFormatter formatter = NumberFormatterCase.getStandardFormatter(cls);
        if (formatter != null) {
            return formatter.format(checkNull(o));
        } else {
            return "";
        }
    }

    /**
     * NonNull
     *
     * @return 0 if format failed
     */
    public static Short toShort(@Nullable Object o) {
        return toNumber(o, Short.class);
    }

    /**
     * NonNull
     *
     * @return 0 if format failed
     */
    public static Integer toInteger(@Nullable Object o) {
        return toNumber(o, Integer.class);
    }

    /**
     * NonNull
     *
     * @return 0 if format failed
     */
    public static Long toLong(@Nullable Object o) {
        return toNumber(o, Long.class);
    }

    /**
     * NonNull
     *
     * @return 0 if format failed
     */
    public static Float toFloat(@Nullable Object o) {
        return toNumber(o, Float.class);
    }

    /**
     * NonNull
     *
     * @return 0 if format failed
     */
    public static Double toDouble(@Nullable Object o) {
        return toNumber(o, Double.class);
    }

    @Nullable
    public static <T extends Number> T toNumber(@Nullable Object o, @NonNull Class<T> cls) {
        NumberFormatter formatter = NumberFormatterCase.getStandardFormatter(cls);
        if (formatter != null) {
            return (T) formatter.toNumber(checkNull(o));
        } else {
            return null;
        }
    }

    /* Special Number */

    @NonNull
    public static CharSequence formatPrice(@Nullable Object o) {
        return formatDigit(o, 2);
    }

    @NonNull
    public static CharSequence formatWeight(@Nullable Object o) {
        return formatDigit(o, 3);
    }

    @NonNull
    public static CharSequence formatDigit(@Nullable Object o, @DigitFormatter.DigitalRange int digit) {
        if (digit == 2) {
            return PriceCase.formatter.format(checkNull(o));
        } else if (digit == 3) {
            return WeightCase.formatter.format(checkNull(o));
        } else {
            return new DigitFormatter(digit).format(checkNull(o));
        }
    }

    /**
     * @return 0 or price number.
     */
    @NonNull
    public static Double toPrice(@Nullable Object o) {
        return toDigit(0, 2);
    }

    /**
     * @return 0 or weight number.
     */
    @NonNull
    public static Double toWeight(@Nullable Object o) {
        return toDigit(0, 3);
    }

    /**
     * @return 0 or the specified digital number.
     */
    @NonNull
    public static Double toDigit(@Nullable Object o, @DigitFormatter.DigitalRange int digit) {
        if (digit == 2) {
            return (Double) PriceCase.formatter.toNumber(checkNull(o));
        } else if (digit == 3) {
            return (Double) WeightCase.formatter.toNumber(checkNull(o));
        } else {
            return new DigitFormatter(digit).toNumber(checkNull(o));
        }
    }

    /* Smart Decimal */

    /**
     * 将遇到的第一个SmartDecimal格式化
     */
    @NonNull
    public static CharSequence formatFirstSmartDecimal(Object o,/* px value */ int dftSize) {
        String non = checkNull(o);
        SpannableString spanString = new SpannableString(non);
        Matcher matcher = Pattern.compile(NumberFormatterPattern.NumberRegular).matcher(non);
        if (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end(1);
            AbsoluteSizeSpan span = new AbsoluteSizeSpan(dftSize);
            spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spanString;
    }

    /**
     * 将遇到的所有SmartDecimal格式化
     */
    @NonNull
    public static CharSequence formatAllSmartDecimal(Object o,/* px value */ int dftSize) {
        String non = checkNull(o);
        SpannableString spanString = new SpannableString(non);
        Matcher matcher = Pattern.compile(NumberFormatterPattern.NumberRegular).matcher(non);
        while (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end(1);
            AbsoluteSizeSpan span = new AbsoluteSizeSpan(dftSize);
            spanString.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spanString;
    }

    /**
     * @param digit 保留小数点位数
     */
    @NonNull
    public static String formatFirstDigit(Object o, int digit) {
        String non = checkNull(o);
        StringBuilder sb = new StringBuilder(non);
        Pattern pattern = Pattern.compile(NumberFormatterPattern.getDigitRegular(digit));
        Matcher matcher = pattern.matcher(sb.toString());
        if (matcher.find()) {
            int cutStart = matcher.end(1);
            int cutEnd = matcher.end(0);
            sb.delete(cutStart, cutEnd);
        }
        return sb.toString();
    }

    /**
     * @param digit 保留小数点位数
     */
    @NonNull
    public static String formatAllDigit(Object o, int digit) {
        String non = checkNull(o);
        StringBuilder sb = new StringBuilder(non);
        Pattern pattern = Pattern.compile(NumberFormatterPattern.getDigitRegular(digit));
        int cutStart = 0;
        while (true) {
            Matcher matcher = pattern.matcher(sb.toString());
            if (matcher.find(cutStart)) {
                cutStart = matcher.end(1);
                int cutEnd = matcher.end(0);
                sb.delete(cutStart, cutEnd);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    @NonNull
    public static String trimDot(@Nullable String plainNumber) {
        if (TextUtils.isEmpty(plainNumber)) {
            return "";
        } else if (plainNumber.contains(".")) {   //小数形式
            for (int end = plainNumber.length() - 1; end >= 0; end--) {
                char c = plainNumber.charAt(end);
                if (c == '.') {
                    plainNumber = plainNumber.substring(0, end);    //不保留小数点
                    break;
                }
                if (c != '0') {
                    plainNumber = plainNumber.substring(0, end + 1);
                    break;
                }
            }
            return plainNumber;
        } else {    //整数形式
            return plainNumber;
        }
    }

}
