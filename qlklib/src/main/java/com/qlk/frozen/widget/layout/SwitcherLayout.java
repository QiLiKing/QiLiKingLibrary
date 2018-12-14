package com.qlk.frozen.widget.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.esuyuan.R;

/**
 * QQ:1055329812
 * Created by QiLiKing on 2018/12/5.
 */
public class SwitcherLayout extends SelectLayout {
    private Switch mSwitchView;

    public SwitcherLayout(Context context) {
        this(context, null);
    }

    public SwitcherLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SwitcherLayout);
        setChecked(ta.getBoolean(R.styleable.SwitcherLayout_select_checked, false));
        ta.recycle();
    }

    @Override
    protected void initViews() {
        super.initViews();
        mSwitchView = (Switch) mRightLayout;
    }

    @Override
    protected void addActions() {
        super.addActions();
        setOnItemClickListener(view -> mSwitchView.performClick());
    }

    @Override
    protected int getRightLayout() {
        return R.layout.template_layout_switcher;
    }

    @Override
    protected boolean editable() {
        return false;
    }

    @Override
    protected boolean selectable() {
        return false;
    }

    @Override
    protected boolean countable() {
        return false;
    }

    public void setChecked(boolean checked) {
        if (mSwitchView != null) {
            mSwitchView.setChecked(checked);
        }
    }

    public boolean isChecked() {
        if (mSwitchView != null) {
            return mSwitchView.isChecked();
        }
        return false;
    }

    public void setOnSwitchChangedListener(CompoundButton.OnCheckedChangeListener listener) {
        if (mSwitchView != null) {
            mSwitchView.setOnCheckedChangeListener(listener);
        }
    }
}
