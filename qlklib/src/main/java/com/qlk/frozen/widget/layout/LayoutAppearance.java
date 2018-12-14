package com.qlk.frozen.widget.layout;

import android.support.annotation.DimenRes;

import com.esuyuan.R;

/**
 * QQ:1055329812
 * Created by QiLiKing on 2018/12/13.
 */
public enum LayoutAppearance {
    Small(R.dimen.xhpi_50px, R.dimen.size_small), Normal(R.dimen.xhpi_90px, R.dimen.size_normal), Big(R.dimen.xhpi_115px, R.dimen.size_big);

    public @DimenRes
    int height;
    public @DimenRes
    int size;

    LayoutAppearance(int height, int size) {
        this.height = height;
        this.size = size;
    }

    public static LayoutAppearance obtain(int style) {
        switch (style) {
            case 0:
                return Small;

            case 1:
                return Normal;

            case 2:
                return Big;

            default:
                return null;
        }
    }
}
