package com.qlk.frozen.utils.formatter.number;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.qlk.frozen.utils.formatter.FormatPattern;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/14 11:24
 */
public class ShortFormatter extends NumberFormatter<Short> {

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
