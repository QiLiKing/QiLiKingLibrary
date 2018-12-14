package com.qlk.frozen.utils.formatter.number;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.qlk.frozen.utils.formatter.FormatPattern;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/14 11:23
 */
public class DoubleFormatter extends NumberFormatter<Double> {

    /**
     * @return true 是严格的浮点数，允许科学技术法形式
     */
    @Override
    public boolean isLawful(@Nullable CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return true;
        }
        try {
            double d = Double.parseDouble(text.toString());    //Out of range: -Infinity or +Infinity
            return Math.abs(d) <= Double.MAX_VALUE;
        } catch (Exception e) {
            return false;
        }
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
                } else if (d < -Double.MAX_VALUE) {
                    d = -Double.MAX_VALUE;
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
