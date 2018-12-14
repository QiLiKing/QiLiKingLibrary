package com.qlk.frozen.widget.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.esuyuan.R;

/**
 * QQ:1055329812
 * Created by QiLiKing on 2018/11/18.
 */
public class CounterLayout extends CounterInputLayout {
    public CounterLayout(Context context) {
        this(context, null);
    }

    public CounterLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (getId() == NO_ID) {
            setId(R.id.counter_layout);
        }
    }

    @Override
    protected boolean countable() {
        return true;
    }

    @Override
    protected boolean editable() {
        return false;
    }

    @Override
    protected boolean selectable() {
        return false;
    }
}
