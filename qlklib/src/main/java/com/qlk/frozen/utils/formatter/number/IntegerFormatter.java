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
public class IntegerFormatter extends NumberFormatter<Integer> {

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
