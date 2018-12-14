package com.qlk.frozen.utils.formatter.number;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.qlk.frozen.utils.formatter.FormatPattern;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/14 11:23
 */
public class FloatFormatter extends NumberFormatter<Float> {

    /**
     * @return true 是严格的浮点数，允许科学技术法形式
     */
    @Override
    public boolean isLawful(@Nullable CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return true;
        }
        try {
            float f = Float.parseFloat(text.toString());    //Out of range: -Infinity or +Infinity
            return f <= Math.abs(f);
        } catch (Exception e) {
            return false;
        }
    }

    @NonNull
    @Override
    public CharSequence format(@Nullable CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            try {
                float f = Float.parseFloat(text.toString());    //Out of range: -Infinity or +Infinity
                if (f > Float.MAX_VALUE) {
                    f = Float.MAX_VALUE;
                } else if (f < -Float.MAX_VALUE) {
                    f = -Float.MAX_VALUE;
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
