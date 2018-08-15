package com.qlk.lib;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/15 10:57
 */
public class QlkSelector<T> {
    public enum Mode {
        None(false, false, false),  //不使用选择模式
        Single(true, true, false),  //单选
        SingleOneLess(true, true, false),   //至少选择一个
        Multi(true, false, true);   //多选

        public boolean select;
        public boolean single;
        public boolean multi;

        Mode(boolean select, boolean single, boolean multi) {
            this.select = select;
            this.single = single;
            this.multi = multi;
        }
    }

    private Mode mSelectMode;
    private final HashSet<T> mSelections = new HashSet<>();
    private final HashSet<Integer> mLastSelections = new HashSet<>();
    //    private int mLastSelection = -1;    //不使用这个的原因是，如果由多选模式变成单选模式时，就不成立了


    public QlkSelector() {
    }

    public QlkSelector(Mode mode) {
        this.mSelectMode = mode;
    }

    /**
     * This operation will clear the selected data.
     *
     * @return the old select mode
     */
    public Mode resetSelectMode(Mode mode) {
        Mode oldMode = mSelectMode;
        this.mSelectMode = mode;
        clear();
        return oldMode;
    }

    public Mode getSelectMode() {
        return mSelectMode;
    }

    @Nullable
    public T getSelection() {
        return mSelections.isEmpty() ? null : mSelections.iterator().next();
    }

    @NonNull
    public ArrayList<T> getSelections() {
        return new ArrayList<>(mSelections);
    }

    public boolean contains(T t) {
        return mSelections.contains(t);
    }

    /**
     * @return if list contains t after this method
     */
    public boolean switchSelect(T t) {
        boolean containsNow;
        if (mSelections.contains(t)) {
            unSelect(t);
            containsNow = false;
        } else {
            select(t);
            containsNow = true;
        }
        return containsNow;
    }

    /**
     * add to select list
     */
    public boolean select(T t) {
        return mSelections.add(t);
    }

    public boolean unSelect(T t) {
        return mSelections.remove(t);
    }

    public void clear() {
        mSelections.clear();
    }
}
