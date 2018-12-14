package com.qlk.frozen.widget.layout;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.esuyuan.v4.template.formatter.ITextFormatter;
import com.esuyuan.v4.template.watcher.ControllableTextWatcher;
import com.esuyuan.v4.template.watcher.FocusableTextWatcher;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/12/10 12:06
 */
public class FormatterEditText extends AppCompatEditText {
    private ITextFormatter mTextFormatter;
    private ControllableTextWatcher mCustomerWatcher;

    public FormatterEditText(Context context) {
        super(context);
    }

    public FormatterEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTextFormatter(ITextFormatter formatter) {
        mTextFormatter = formatter;
        if (formatter != null && !isFocused()) {
            setText(getText().toString());  //主要用于初始化数据
        }
        if (mCustomerWatcher != null) {
            mCustomerWatcher.bindFormatter(mTextFormatter);
        }
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        super.addTextChangedListener(watcher);
        if (watcher instanceof ControllableTextWatcher) {
            if (mCustomerWatcher != null) {
                removeTextChangedListener(mCustomerWatcher);
            }
            mCustomerWatcher = (ControllableTextWatcher) watcher;
            if (mTextFormatter != null) {
                mCustomerWatcher.bindFormatter(mTextFormatter);
            }
        } else if (watcher instanceof com.esuyuan.v4.template.watcher.FocusableTextWatcher) {
            if (((com.esuyuan.v4.template.watcher.FocusableTextWatcher) watcher).getHostView() != this) {
                ((FocusableTextWatcher) watcher).setHostView(this);
            }
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        try {
            if (TextUtils.equals(text, getText().toString())) {
                return;
            }
            if (isFocused()) {
                int selection = getSelectionEnd();
                super.setText(text, type);
                if (selection != -1) {
                    setSelection(Math.min(selection, getText().length()));
                }
            } else {
                if (mTextFormatter != null) {
                    text = mTextFormatter.format(text);
                }
                super.setText(text, type);
                setSelection(getText().length());
            }
        } catch (Exception e) {
            super.setText(text, type);
        }
    }


}
