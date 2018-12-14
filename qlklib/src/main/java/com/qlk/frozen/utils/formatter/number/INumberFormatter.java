package com.qlk.frozen.utils.formatter.number;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.qlk.frozen.utils.formatter.ITextFormatter;

/**
 * QQ:1055329812
 * Created by QiLiKing on 2018/12/11.
 */
public interface INumberFormatter<T extends Number> extends ITextFormatter {
    /**
     * @return value 0 if text is null or not a number
     */
    @NonNull
    T toNumber(@Nullable CharSequence text);
}
