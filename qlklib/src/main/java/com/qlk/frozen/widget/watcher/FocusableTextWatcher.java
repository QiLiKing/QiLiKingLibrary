package com.qlk.frozen.widget.watcher;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/10 09:19
 */
public abstract class FocusableTextWatcher extends SimpleTextWatcher {
    @Nullable
    protected View mHostView;

    public FocusableTextWatcher() {
        this(null);
    }

    // TODO: 2018/12/10 相互引用，是否会造成内存泄漏
    public FocusableTextWatcher(@NonNull View hostView) {
        this.mHostView = hostView;
    }

    public View getHostView() {
        return mHostView;
    }

    public void setHostView(View view) {
        mHostView = view;
    }

    public abstract void onFocusTextChanged(CharSequence s);

    @Override
    public void onSimpleTextChanged(CharSequence s) {
        if (mHostView == null || mHostView.hasFocus()) {
            onFocusTextChanged(s);
        }
    }
}
