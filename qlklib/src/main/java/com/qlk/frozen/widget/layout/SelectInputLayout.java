package com.qlk.frozen.widget.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.esuyuan.R;

/**
 * QQ:1055329812
 * Created by QiLiKing on 2018/11/10.
 */
public class SelectInputLayout extends SelectLayout {

    public SelectInputLayout(Context context) {
        this(context, null);
    }

    public SelectInputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getDefaultID() {
        return R.id.select_input_layout;
    }

    @Override
    protected boolean editable() {
        return true;
    }

    @Override
    protected boolean countable() {
        return false;
    }

    @Override
    protected boolean selectable() {
        return true;
    }

    public void setContentEditable(boolean editable) {
        mContentEdit.setFocusable(editable);
        mContentEdit.setFocusableInTouchMode(editable);
        mContentEdit.setOnClickListener(editable ? null : v -> performClick());
    }
}
