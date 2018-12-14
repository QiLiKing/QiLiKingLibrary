package com.qlk.frozen.utils.formatter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/14 17:43
 */
public class FormatterPattern {
    /**
     * @return true regular empty or isLawful; false text empty or not isLawful
     */
    public static boolean matches(@Nullable CharSequence text, @Nullable String regular) {
        if (TextUtils.isEmpty(regular)) {
            return true;
        } else if (TextUtils.isEmpty(text)) {
            return false;
        } else {
            return Pattern.matches(regular, text);
        }
    }

    /**
     * @return true regular empty or isLawful; false text empty or not isLawful
     */
    public static boolean contains(@Nullable CharSequence text, @Nullable String regular) {
        if (TextUtils.isEmpty(regular)) {
            return true;
        } else if (TextUtils.isEmpty(text)) {
            return false;
        } else {
            return Pattern.compile(regular).matcher(text).find();
        }
    }

    /**
     * @return true regular empty or isLawful; false text empty or not isLawful
     */
    @NonNull
    public static CharSequence findFirst(@Nullable CharSequence text, @Nullable String regular) {
        if (TextUtils.isEmpty(regular) && text != null) {
            return text;
        } else if (TextUtils.isEmpty(text)) {
            return "";
        } else {
            Matcher matcher = Pattern.compile(regular).matcher(text);
            if (matcher.find()) {
                return matcher.group();
            }
            return "";
        }
    }
}
