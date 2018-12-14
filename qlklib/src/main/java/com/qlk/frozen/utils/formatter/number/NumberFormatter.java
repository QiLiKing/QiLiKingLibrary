package com.qlk.frozen.utils.formatter.number;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.qlk.frozen.utils.formatter.FormatPattern;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Pattern;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/12 09:07
 */
public abstract class NumberFormatter<T extends Number> implements INumberFormatter<T> {

    @Override
    public boolean isLawful(@Nullable CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return true;
        }
        try {
            new BigDecimal(text.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return 如果text是科学计数法，则转为正常数字形式
     */
    @NonNull
    @Override
    public CharSequence format(@Nullable CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return "";
        }
        try {
            return new BigDecimal(text.toString()).toPlainString();
        } catch (Exception e) {
            return "";
        }
    }

    public static class DoubleFormatter extends NumberFormatter<Double> {

        /**
         * @return true 是严格的浮点数，允许科学技术法形式
         */
        @Override
        public boolean isLawful(@Nullable CharSequence text) {
            if (TextUtils.isEmpty(text)) {
                return true;
            }
            boolean matches = false;
            try {
                double d = Double.parseDouble(text.toString());    //Out of range: -Infinity or +Infinity
                matches = d >= Double.MIN_VALUE && d <= Double.MAX_VALUE;
            } catch (Exception e) {
                //
            }
            return matches;
        }

        /**
         * @return 如果超过Double范围，返回最大或最小值；如果是数字文字混合，则取第一个符合规范的数字（如果混合的是科学技术法，则无法识别）。
         */
        @NonNull
        @Override
        public CharSequence format(@Nullable CharSequence text) {
            if (!TextUtils.isEmpty(text)) {
                try {
                    double d = Double.parseDouble(text.toString());    //Out of range: -Infinity or +Infinity
                    if (d > Double.MAX_VALUE) {
                        d = Double.MAX_VALUE;
                    } else if (d < Double.MIN_VALUE) {
                        d = Double.MIN_VALUE;
                    }
                    return super.format(String.valueOf(d));
                } catch (Exception e) {
                    return format(FormatPattern.findFirst(text, FormatPattern.DecimalRegular));
                }
            }
            return "";
        }

        /**
         * 有可能返回可续计数法，如果不希望系统自动转换，请使用format
         */
        @NonNull
        @Override
        public Double toNumber(@Nullable CharSequence text) {
            CharSequence non = format(text);
            return TextUtils.isEmpty(non) ? 0 : Double.valueOf(non.toString());
        }
    }

    public static class FloatFormatter extends NumberFormatter<Float> {

        /**
         * @return true 是严格的浮点数，允许科学技术法形式
         */
        @Override
        public boolean isLawful(@Nullable CharSequence text) {
            if (TextUtils.isEmpty(text)) {
                return true;
            }
            boolean matches = false;
            try {
                float f = Float.parseFloat(text.toString());    //Out of range: -Infinity or +Infinity
                matches = f >= Float.MIN_VALUE && f <= Float.MAX_VALUE;
            } catch (Exception e) {
                //
            }
            return matches;
        }

        @NonNull
        @Override
        public CharSequence format(@Nullable CharSequence text) {
            if (!TextUtils.isEmpty(text)) {
                try {
                    float f = Float.parseFloat(text.toString());    //Out of range: -Infinity or +Infinity
                    if (f > Float.MAX_VALUE) {
                        f = Float.MAX_VALUE;
                    } else if (f < Float.MIN_VALUE) {
                        f = Float.MIN_VALUE;
                    }
                    return super.format(String.valueOf(f));
                } catch (Exception e) {
                    return format(FormatPattern.findFirst(text, FormatPattern.DecimalRegular));
                }
            }
            return "";
        }

        @NonNull
        @Override
        public Float toNumber(@Nullable CharSequence text) {
            CharSequence non = format(text);
            return TextUtils.isEmpty(non) ? 0 : Float.valueOf(non.toString());
        }
    }

    public static class DigitFormatter extends DoubleFormatter implements IDigitFormatter {

        /* 325：double 的最大小数位 */
        @IntRange(from = 1, to = 325)
        public @interface DigitalRange {
        }

        private final Pattern DigitPattern;
        private final int digit;

        public DigitFormatter(@DigitalRange int digit) {
            this.digit = digit;
            DigitPattern = Pattern.compile(FormatPattern.getDigitRegular(digit));
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
            CharSequence non = super.format(text);    //杜绝科学技术形式和越界
            if (TextUtils.isEmpty(non)) {
                return "";
            }
            try {
                return new BigDecimal(non.toString()).setScale(digit, RoundingMode.HALF_UP).toPlainString();
            } catch (Exception e) {
                return "";
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
                CharSequence find = FormatPattern.findFirst(format(text), FormatPattern.getDigitRegular(digit));
                return TextUtils.isEmpty(find) ? 0 : Double.parseDouble(find.toString());
            } else {
                return new DigitFormatter(digit).toDigit(text); //需要重新format
            }
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

    public static class LongFormatter extends NumberFormatter<Long> {

        @Override
        public boolean isLawful(@Nullable CharSequence text) {
            if (TextUtils.isEmpty(text)) {
                return true;
            }
            try {
                Long.valueOf(text.toString());  //超过范围直接Exception，跟浮点型的不一样
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        /**
         * @return 允许浮点型、超大型、数文混合型
         */
        @NonNull
        @Override
        public CharSequence format(@Nullable CharSequence text) {
            if (!TextUtils.isEmpty(text)) {
                long l;
                try {
                    l = Long.parseLong(text.toString());    //Out of range: NumberFormatException
                } catch (Exception e) {
                    try {
                        Double d = Double.valueOf(text.toString());
                        if (d > Long.MAX_VALUE) {
                            l = Long.MAX_VALUE;
                        } else if (d < Long.MIN_VALUE) {
                            l = Long.MIN_VALUE;
                        } else {
                            return "";
                        }
                    } catch (Exception e1) {
                        //图文混合
                        return format(FormatPattern.findFirst(text, FormatPattern.DecimalRegular));
                    }
                }
                return super.format(String.valueOf(l)); //防止科学计数法
            }
            return "";
        }

        @NonNull
        @Override
        public Long toNumber(@Nullable CharSequence text) {
            CharSequence non = format(text);
            return TextUtils.isEmpty(non) ? 0 : Long.valueOf(non.toString());
        }
    }

    public static class IntegerFormatter extends NumberFormatter<Integer> {

        @Override
        public boolean isLawful(@Nullable CharSequence text) {
            if (TextUtils.isEmpty(text)) {
                return true;
            }
            try {
                Integer.valueOf(text.toString());
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @NonNull
        @Override
        public CharSequence format(@Nullable CharSequence text) {
            if (!TextUtils.isEmpty(text)) {
                int i;
                try {
                    i = Integer.parseInt(text.toString());    //Out of range: NumberFormatException
                } catch (Exception e) {
                    try {
                        Double d = Double.valueOf(text.toString());
                        if (d > Integer.MAX_VALUE) {
                            i = Integer.MAX_VALUE;
                        } else if (d < Integer.MIN_VALUE) {
                            i = Integer.MIN_VALUE;
                        } else {
                            return "";
                        }
                    } catch (Exception e1) {
                        //图文混合
                        return format(FormatPattern.findFirst(text, FormatPattern.DecimalRegular));
                    }
                }
                return super.format(String.valueOf(i));
            }
            return "";
        }

        @NonNull
        @Override
        public Integer toNumber(@Nullable CharSequence text) {
            CharSequence non = format(text);
            return TextUtils.isEmpty(non) ? 0 : Integer.valueOf(non.toString());
        }
    }

    public static class ShortFormatter extends NumberFormatter<Short> {

        @Override
        public boolean isLawful(@Nullable CharSequence text) {
            if (TextUtils.isEmpty(text)) {
                return true;
            }
            try {
                Short.valueOf(text.toString());
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @NonNull
        @Override
        public CharSequence format(@Nullable CharSequence text) {
            if (!TextUtils.isEmpty(text)) {
                short s;
                try {
                    s = Short.parseShort(text.toString());    //Out of range: NumberFormatException
                } catch (Exception e) {
                    try {
                        Double d = Double.valueOf(text.toString());
                        if (d > Short.MAX_VALUE) {
                            s = Short.MAX_VALUE;
                        } else if (d < Short.MIN_VALUE) {
                            s = Short.MIN_VALUE;
                        } else {
                            return "";
                        }
                    } catch (Exception e1) {
                        //图文混合
                        return format(FormatPattern.findFirst(text, FormatPattern.DecimalRegular));
                    }
                }
                return super.format(String.valueOf(s));
            }
            return "";
        }

        @NonNull
        @Override
        public Short toNumber(@Nullable CharSequence text) {
            CharSequence non = format(text);
            return TextUtils.isEmpty(non) ? 0 : Short.valueOf(non.toString());
        }
    }

}
