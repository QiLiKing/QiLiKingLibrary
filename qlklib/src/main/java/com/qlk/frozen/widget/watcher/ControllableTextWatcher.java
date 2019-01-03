package com.qlk.frozen.widget.watcher;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qlk.frozen.utils.formatter.ITextFormatter;

public abstract class ControllableTextWatcher extends FocusableTextWatcher {
    private String previous;    //注意不能用CharSequence存储，他是一个对象！
    private int selection = -1;

    public ControllableTextWatcher() {
        this(null);
    }

    public ControllableTextWatcher(@Nullable View hostView) {
        super(hostView);
    }

    protected abstract void onControlTextChanged(CharSequence s);

    /**
     * 如果构造时，未传入EditText，这需要调用方手动Reset
     *
     * @param s 需要重置TextView为该值
     * @see ControllableTextWatcher#keepSelection(EditText, CharSequence)
     */
    protected void onResetRequest(CharSequence s) {
    }

    /**
     * @return false 无效值，此时会发起重置请求
     * @see ControllableTextWatcher#onResetRequest(CharSequence)
     */
    public boolean onChangeRequest(CharSequence desire) {
        return mFormatter == null || mFormatter.isLawful(desire);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        super.beforeTextChanged(s, start, count, after);
        if (selection == -1) {  //在修正的时候，会再次调用，此时的start是0，因为setText了
            selection = start;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        super.afterTextChanged(s);
        selection = -1;
    }

    @Override
    public void onFocusTextChanged(CharSequence s) {
        if (onChangeRequest(s)) {
            setPrevious(s.toString());
            onControlTextChanged(s);
        } else {
            setPrevious(previous);  //更改父类的字段
            View hostView = getHostView();
            if (hostView instanceof EditText) {
                keepSelection((EditText) hostView, previous);
                onControlTextChanged(previous);
            } else if (hostView instanceof TextView) {
                ((TextView) hostView).setText(previous);
                onControlTextChanged(previous);
            } else {
                onResetRequest(previous);
                onControlTextChanged(previous);
            }
        }
    }

    protected void keepSelection(EditText targetView, CharSequence keepText) {
        keepSelection(targetView, keepText, this.selection);
    }

    protected void keepSelection(EditText targetView, CharSequence keepText, int selection) {
        targetView.setText(keepText);   //这里会直接把beforeTextChanged->afterTextChanged流程走完，只能缓存下保存的selection了
        if (selection >= 0 && selection <= targetView.getText().length()) {
            targetView.setSelection(selection);
        }
    }

    @Override
    public void setPrevious(String previous) {
        super.setPrevious(previous);
        this.previous = previous;
    }

    /* Formatter */
    private ITextFormatter mFormatter;

    public void bindFormatter(ITextFormatter formatter) {
        mFormatter = formatter;
    }
}