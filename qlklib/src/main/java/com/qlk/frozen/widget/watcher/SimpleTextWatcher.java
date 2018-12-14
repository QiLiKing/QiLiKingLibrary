package com.qlk.frozen.widget.watcher;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

/**
 * 只有在text确实变化的时候才会调用onSimpleTextChanged()
 * QQ:1055329812
 * Created by QiLiKing on 2018/11/16.
 */
public abstract class SimpleTextWatcher implements TextWatcher {
    private String previous;    //注意不能用CharSequence，他是同一个对象

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.equals(previous, s)) {
            previous = s.toString();
            onSimpleTextChanged(s);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    protected abstract void onSimpleTextChanged(CharSequence s);

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
