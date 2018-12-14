package com.qlk.frozen.utils.formatter;

import android.support.annotation.Nullable;

/**
 * QQ:1055329812
 * Created by QiLiKing on 2018/11/18.
 */
public interface ITextFormatter {

    CharSequence format(@Nullable CharSequence text);

    boolean isLawful(@Nullable CharSequence text);
}
