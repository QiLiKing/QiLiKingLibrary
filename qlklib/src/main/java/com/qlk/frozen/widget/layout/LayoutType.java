package com.qlk.frozen.widget.layout;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

import com.esuyuan.R;

/**
 * QQ:1055329812
 * Created by QiLiKing on 2018/12/13.
 */
public enum LayoutType {
    Default(0, false, false, false),
    Display(R.id.display_layout, false, false, false),
    Select(R.id.select_layout, false, true, false),
    Input(R.id.input_layout, false, false, true),
    Counter(R.id.counter_layout, true, false, false),
    SelectInput(R.id.select_layout, false, true, true),
    CounterInput(R.id.select_layout, true, false, true);

    public boolean countable;
    public boolean selectable;
    public boolean editable;
    @IdRes
    public int dftId;   //当用户没有指定Root的ID时，启用此ID

    LayoutType(@IdRes int dftId, boolean countable, boolean selectable, boolean editable) {
        this.countable = countable;
        this.selectable = selectable;
        this.editable = editable;
        this.dftId = dftId;
    }

    @Nullable
    public static LayoutType obtain(int ordinal) {
        if (ordinal == Display.ordinal()) {
            return Display;
        } else if (ordinal == Select.ordinal()) {
            return Select;
        } else if (ordinal == Input.ordinal()) {
            return Input;
        } else if (ordinal == Counter.ordinal()) {
            return Counter;
        } else if (ordinal == SelectInput.ordinal()) {
            return SelectInput;
        } else if (ordinal == CounterInput.ordinal()) {
            return CounterInput;
        } else {
            return null;
        }
    }
}
