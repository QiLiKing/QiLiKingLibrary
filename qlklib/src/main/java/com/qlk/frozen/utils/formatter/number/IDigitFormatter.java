package com.qlk.frozen.utils.formatter.number;

import android.support.annotation.IntRange;
import android.support.annotation.Nullable;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/14 10:39
 */
public interface IDigitFormatter {
    /* 308,324：double 的最大小数位 */
    @IntRange(from = 1, to = 325)
    @interface DigitalRange {
    }

    /**
     * @return 返回当前使用的位数
     */
    double toDigit(@Nullable CharSequence text);

    /**
     * @return 自定义返回小数点位数
     */
    double toDigit(@Nullable CharSequence text, @DigitalRange int digit);

}
