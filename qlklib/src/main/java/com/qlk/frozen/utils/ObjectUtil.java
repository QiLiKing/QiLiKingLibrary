package com.qlk.frozen.utils;

import android.support.annotation.Nullable;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/14 11:09
 */
public class ObjectUtil {
    public static String checkNull(@Nullable Object o) {
        return o == null ? "" : o.toString();
    }
}
