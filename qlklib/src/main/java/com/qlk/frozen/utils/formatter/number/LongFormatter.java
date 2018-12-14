package com.qlk.frozen.utils.formatter.number;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.qlk.frozen.utils.formatter.FormatterPattern;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/14 11:24
 */
public class LongFormatter extends NumberFormatter<Long> {

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
                    return format(FormatterPattern.findFirst(text, NumberFormatterPattern.DecimalRegular));
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
