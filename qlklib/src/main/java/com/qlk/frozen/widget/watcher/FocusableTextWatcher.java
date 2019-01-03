package com.qlk.frozen.widget.watcher;

import android.support.annotation.Nullable;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/10 09:19
 */
public abstract class FocusableTextWatcher extends SimpleTextWatcher {
    private WeakReference<View> mTarget;

    public FocusableTextWatcher() {
        this(null);
    }

    public FocusableTextWatcher(@Nullable View hostView) {
        setHostView(hostView);
    }

    @Nullable
    public View getHostView() {
        if (mTarget != null) {
            return mTarget.get();
        }
        return null;
    }

    public void setHostView(@Nullable View hostView) {
        mTarget = null;
        if (hostView != null) {
            mTarget = new WeakReference<>(hostView);
        }
    }

    public abstract void onFocusTextChanged(CharSequence s);

    @Override
    public void onSimpleTextChanged(CharSequence s) {
        View hostView = getHostView();
        if (hostView == null || hostView.hasFocus()) {
            onFocusTextChanged(s);
        }
    }
}
