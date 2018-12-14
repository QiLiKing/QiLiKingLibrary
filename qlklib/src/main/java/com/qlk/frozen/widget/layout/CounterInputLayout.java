package com.qlk.frozen.widget.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.esuyuan.R;
import com.esuyuan.v4.template.formatter.ITextFormatter;
import com.esuyuan.v4.template.formatter.NumberFormatter;
import com.esuyuan.v4.template.watcher.ControllableTextWatcher;
import com.esuyuan.v4.template.watcher.SimpleTextWatcher;

/**
 * QQ:1055329812
 * Created by QiLiKing on 2018/11/18.
 */
public class CounterInputLayout extends SelectInputLayout {
    protected TextView mUnitText;
    protected ImageView mDecreaseImage;
    protected ImageView mIncreaseImage;
    protected Group mAutoCountGroup;

    private NumberFormatter mFormatter;
    private ControllableTextWatcher mWatcher;

    private double mPeer = 1;

    public CounterInputLayout(Context context) {
        this(context, null);
    }

    public CounterInputLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected int getDefaultID() {
        return R.id.counter_input_layout;
    }

    @Override
    protected boolean editable() {
        return true;
    }

    @Override
    protected boolean countable() {
        return true;
    }

    @Override
    protected boolean selectable() {
        return false;
    }

    @Override
    protected void initViews() {
        super.initViews();
        mUnitText = mRightLayout.findViewById(R.id.unit);
        mDecreaseImage = mRightLayout.findViewById(R.id.decrease);
        mIncreaseImage = mRightLayout.findViewById(R.id.increase);
        mAutoCountGroup = mRightLayout.findViewById(R.id.group_auto_count);
    }

    @Override
    protected void addActions() {
        super.addActions();

        setContentWatcher(new SimpleTextWatcher() {
            @Override
            protected void onSimpleTextChanged(CharSequence s) {
                refreshPeerState();
            }
        });

        mDecreaseImage.setOnClickListener(v -> {
            if (isAllowPeerChange(-mPeer)) {
                setContent(String.valueOf(getCurrentCount() - mPeer));
            }
        });

        mIncreaseImage.setOnClickListener(v -> {
            if (isAllowPeerChange(mPeer)) {
                setContent(String.valueOf(getCurrentCount() + mPeer));
            }
        });
    }

    private void refreshPeerState() {
        mIncreaseImage.setEnabled(isAllowPeerChange(mPeer));
        mDecreaseImage.setEnabled(isAllowPeerChange(-mPeer));
    }

    private boolean isAllowPeerChange(double peer) {
        return mWatcher == null || mWatcher.onChangeRequest(String.valueOf(peer + getCurrentCount()));
    }

    public double getCurrentCount() {
        if (mFormatter != null) {
            return mFormatter.toNumber(getContent()).doubleValue();
        } else {
            try {
                return Double.parseDouble(getContent());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    /**
     * 自己实现点击后续操作
     */
    public void setOnUnitClickListener(View.OnClickListener listener) {
        mUnitText.setOnClickListener(listener);
    }

    public String getUnit() {
        return mUnitText.getText().toString();
    }

    public void setSelfChangeEnabled(boolean enabled) {
        mContentEdit.setEnabled(enabled);
        mIncreaseImage.setEnabled(enabled);
        mDecreaseImage.setEnabled(enabled);
    }

    /**
     * default: 1
     */
    public void setPeer(double peer) {
        mPeer = peer;
    }

    public void setAutoCountEnabled(boolean decreaseEnabled, boolean increaseEnabled) {
        mIncreaseImage.setEnabled(increaseEnabled);
        mDecreaseImage.setEnabled(decreaseEnabled);
    }

    public void showAutoCount(boolean show) {
        mAutoCountGroup.setVisibility(show ? VISIBLE : GONE);
    }

    public void setUnit(String unitName) {
        mUnitText.setText(unitName);
    }

    @Override
    public void setTextFormatter(ITextFormatter formatter) {
        super.setTextFormatter(formatter);
        if (formatter instanceof NumberFormatter) {
            mFormatter = (NumberFormatter) formatter;
        }
    }

    @Override
    public void setContentWatcher(SimpleTextWatcher watcher) {
        super.setContentWatcher(watcher);
        if (watcher instanceof ControllableTextWatcher) {
            mWatcher = (ControllableTextWatcher) watcher;
        }
    }
}
