package com.qlk.frozen.utils.formatter.number;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.qlk.frozen.utils.formatter.FormatManager;

import java.math.BigDecimal;

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
            return FormatManager.trimDot(new BigDecimal(text.toString()).toPlainString());
        } catch (Exception e) {
            return "";
        }
    }

}
