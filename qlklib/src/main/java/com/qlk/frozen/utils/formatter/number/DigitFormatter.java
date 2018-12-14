package com.qlk.frozen.utils.formatter.number;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.qlk.frozen.utils.formatter.FormatterPattern;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/14 11:23
 */
public class DigitFormatter extends DoubleFormatter implements IDigitFormatter {

    /* 308,324：double 的最大小数位 */
    @IntRange(from = 1, to = 325)
    public @interface DigitalRange {
    }

    private final Pattern DigitPattern;
    private final int digit;

    public DigitFormatter(@DigitalRange int digit) {
        this.digit = digit;
        DigitPattern = Pattern.compile(NumberFormatterPattern.getDigitRegular(digit));
    }

    /**
     * @return true 是严格的浮点数，允许科学技术法形式
     */
    @Override
    public boolean isLawful(@Nullable CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return true;
        }
        if (super.isLawful(text)) { //不能越界
            String non = text.toString();
            if (non.contains("E") || non.contains("e")) {   //大多数情况是不需要new一个BigDecimal的
                try {
                    String plain = new BigDecimal(text.toString()).toPlainString();
                    return DigitPattern.matcher(plain).matches();
                } catch (Exception e) {
                    //
                }
            } else {
                return DigitPattern.matcher(non).matches();
            }
        }
        return false;
    }

    /**
     * 四舍五入
     *
     * @return "" 或者最多三个小数点的浮点数
     */
    @NonNull
    @Override
    public CharSequence format(@Nullable CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        String non = super.format(text).toString(); //trim过的，并且是plain-text
        int dot = non.indexOf(".");
        if (dot != -1) {
            if (1 + dot + digit >= non.length()) {
                return non; //符合条件，不用截取
            } else {
                try {
                    return new BigDecimal(non).setScale(digit, RoundingMode.HALF_UP).toPlainString();   //四舍五入
                } catch (Exception e) {
                    return "";
                }
            }
        } else {
            return non; //整数或者“”
        }
    }

    /**
     * @return 如果要返回精确地位数，请使用format()
     */
    @NonNull
    @Override
    public Double toNumber(@Nullable CharSequence text) {
        CharSequence s = super.format(text);
        return TextUtils.isEmpty(s) ? 0 : Double.valueOf(s.toString());
    }

    @Override
    public double toDigit(@Nullable CharSequence text) {
        CharSequence s = format(text);
        return TextUtils.isEmpty(s) ? 0 : Double.parseDouble(s.toString());
    }

    @Override
    public double toDigit(@Nullable CharSequence text, int digit) {
        if (digit == this.digit) {
            return toDigit(text);
        } else if (digit < this.digit) {
            CharSequence find = FormatterPattern.findFirst(format(text), NumberFormatterPattern.getDigitRegular(digit));
            return TextUtils.isEmpty(find) ? 0 : Double.parseDouble(find.toString());
        } else {
            return new com.qlk.frozen.utils.formatter.number.DigitFormatter(digit).toDigit(text); //需要重新format
        }
    }

    public static class WeightFormatter extends DigitFormatter {
        public WeightFormatter() {
            super(3);
        }
    }

    public static class PriceFormatter extends DigitFormatter {
        public PriceFormatter() {
            super(2);
        }
    }
}
