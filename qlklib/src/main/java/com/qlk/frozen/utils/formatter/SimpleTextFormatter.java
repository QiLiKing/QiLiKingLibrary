package com.qlk.frozen.utils.formatter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.qlk.frozen.utils.ObjectUtil.checkNull;

public class SimpleTextFormatter implements ITextFormatter {
    /**
     * @return empty text if null
     */
    @NonNull
    @Override
    public CharSequence format(@Nullable CharSequence text) {
        if (isLawful(text)) {
            return checkNull(text);
        } else {
            return "";
        }
    }

    @Override
    public boolean isLawful(@Nullable CharSequence text) {
        return true;
    }


    public static class EmailFormatter extends SimpleTextFormatter {

    }

    public static class PhoneFormatter extends SimpleTextFormatter {

    }

    public static class DateFormatter extends SimpleTextFormatter {

    }
}