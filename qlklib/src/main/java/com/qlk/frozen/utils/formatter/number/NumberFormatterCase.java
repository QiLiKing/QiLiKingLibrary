package com.qlk.frozen.utils.formatter.number;

import android.support.annotation.Nullable;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/12 12:11
 */
public enum NumberFormatterCase {
    ShortCase(new ShortFormatter(), false), IntegerCase(new IntegerFormatter(), false), LongCase(new LongFormatter(), false), FloatCase(new FloatFormatter(), true), DoubleCase(new DoubleFormatter(), true), PriceCase(new DigitFormatter.PriceFormatter(), true), WeightCase(new DigitFormatter.WeightFormatter(), true);

    public boolean isDecimal;
    public final NumberFormatter formatter;

    NumberFormatterCase(NumberFormatter<? extends Number> formatter, boolean isDecimal) {
        this.formatter = formatter;
        this.isDecimal = isDecimal;
    }

    @Nullable
    public static NumberFormatterCase obtain(int ordinal) {
        if (ordinal == ShortCase.ordinal()) {
            return ShortCase;
        } else if (ordinal == IntegerCase.ordinal()) {
            return IntegerCase;
        } else if (ordinal == LongCase.ordinal()) {
            return LongCase;
        } else if (ordinal == FloatCase.ordinal()) {
            return FloatCase;
        } else if (ordinal == DoubleCase.ordinal()) {
            return DoubleCase;
        } else if (ordinal == PriceCase.ordinal()) {
            return PriceCase;
        } else if (ordinal == WeightCase.ordinal()) {
            return WeightCase;
        } else {
            return null;
        }
    }

    @Nullable
    public static NumberFormatter getStandardFormatter(@Nullable Class<? extends Number> cls) {
        if (cls == Short.class) {
            return ShortCase.formatter;
        } else if (cls == Integer.class) {
            return IntegerCase.formatter;
        } else if (cls == Long.class) {
            return LongCase.formatter;
        } else if (cls == Float.class) {
            return FloatCase.formatter;
        } else if (cls == Double.class) {
            return DoubleCase.formatter;
        } else {
            return null;
        }
    }

    @Nullable
    public static <T extends Number> NumberFormatter getStandardFormatter(@Nullable T t) {
        return t == null ? null : getStandardFormatter(t.getClass());
    }

    @Nullable
    public static DigitFormatter getDigitalFormatter(@DigitFormatter.DigitalRange int digital) {
        if (digital == 2) {
            return (DigitFormatter) PriceCase.formatter;
        } else if (digital == 3) {
            return (DigitFormatter) WeightCase.formatter;
        } else {
            return new DigitFormatter(digital);
        }
    }

    public static DigitFormatter.PriceFormatter getPriceFormatter() {
        return (DigitFormatter.PriceFormatter) PriceCase.formatter;
    }

    public static DigitFormatter.WeightFormatter getWeightFormatter() {
        return (DigitFormatter.WeightFormatter) WeightCase.formatter;
    }
}
