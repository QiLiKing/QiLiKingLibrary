package com.qlk.frozen.widget.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.esuyuan.BR;
import com.esuyuan.R;
import com.esuyuan.v4.template.formatter.ITextFormatter;
import com.esuyuan.v4.template.formatter.NumberFormatterCase;
import com.esuyuan.v4.template.watcher.SimpleTextWatcher;

/**
 * QQ:1055329812
 * Created by QiLiKing on 2018/11/10.
 */
public class SelectLayout extends InputLayout {

    protected View mRightLayout;
    protected FormatterEditText mContentEdit;
    protected TextView mRightText;
    protected TextView mTitleText;
    protected View mRightDivider;
    @Nullable
    protected ImageView mRightImage;
    protected ImageView mTitleLeftImage;
    protected ImageView mContentLeftImage;
    private View mItemDivider;
    @NonNull
    private LayoutType mLayoutType;

    public SelectLayout(Context context) {
        this(context, null);
    }

    public SelectLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (getId() == NO_ID) {
            setId(getDefaultID());
        }

        inflateLayout(context);
        initViews();
        addActions();

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SelectLayout);
        setTextFormatter(ta.getInt(R.styleable.DisplayLayout_select_formatter_type, DisplayLayout.FORMAT_NUMBER));
        if (ta.hasValue(R.styleable.SelectLayout_select_title)) {
            setTitle(ta.getString(R.styleable.SelectLayout_select_title));
        }
        setTitleColor(ta.getResourceId(R.styleable.SelectLayout_select_title_color, 0));
        setTitleSize(ta.getResourceId(R.styleable.SelectLayout_select_title_size, 0));
        if (ta.hasValue(R.styleable.SelectLayout_select_content)) {
            setContent(ta.getString(R.styleable.SelectLayout_select_content));
        }
        setContentSize(ta.getResourceId(R.styleable.SelectLayout_select_content_size, 0));
        if (ta.hasValue(R.styleable.SelectLayout_select_contentHint)) {
            setContentHint(ta.getString(R.styleable.SelectLayout_select_contentHint));
        }
        if (ta.hasValue(R.styleable.SelectLayout_select_rightText)) {
            setRightText(ta.getString(R.styleable.SelectLayout_select_rightText));
        }
        setTitleIcon(ta.getResourceId(R.styleable.SelectLayout_select_titleLeftIcon, 0), ta.getResourceId(R.styleable.SelectLayout_select_titleRightIcon, 0));
        setTitleLeftImage(ta.getResourceId(R.styleable.SelectLayout_select_titleLeftImage, 0));
        setContentLeftImage(ta.getResourceId(R.styleable.SelectLayout_select_contentLeftImage, 0));
        setContentTextStyle(ta.getInt(R.styleable.SelectLayout_select_content_text_style, Typeface.NORMAL));
        showItemDivider(ta.getBoolean(R.styleable.SelectLayout_select_show_item_divider, true));
        setLayoutStyle(ta.getInt(R.styleable.SelectLayout_select_layout_style, -1));
        setRightImage(ta.getResourceId(R.styleable.SelectLayout_select_right_image, 0));
        setRightImageVisible(ta.getInt(R.styleable.SelectLayout_select_right_image_visibility, -1));
        ta.recycle();
    }

    protected LayoutType getLayoutType() {
        return LayoutType.Display;
    }

    protected int getDefaultID() {
        return R.id.select_layout;
    }

    protected void inflateLayout(Context context) {
        inflate(context, R.layout.template_input_layout, this);
    }

    protected void initViews() {
        mTitleText = findViewById(R.id.title);
        mContentEdit = findViewById(R.id.content);
        mTitleLeftImage = findViewById(R.id.title_left_image);
        mContentLeftImage = findViewById(R.id.content_left_image);
        mItemDivider = findViewById(R.id.item_divider);
        mRightDivider = findViewById(R.id.right_divider);

        DataBindingUtil.bind(findViewById(R.id.root)).setVariable(BR.editable, editable());
        DataBindingUtil.bind(findViewById(R.id.root)).setVariable(BR.countable, countable());
        DataBindingUtil.bind(findViewById(R.id.root)).setVariable(BR.selectable, selectable());

        ViewStub stub = findViewById(R.id.right_layout_stub);
        stub.setLayoutResource(getRightLayout());
        mRightLayout = stub.inflate();

        mRightText = mRightLayout.findViewById(R.id.right_text);
        mRightImage = mRightLayout.findViewById(R.id.right_arrow);
        if (!selectable()) {
            if (mRightImage != null) {
                mRightImage.setVisibility(GONE);
            }
        }
        if (!editable()) {
            mContentEdit.setOnClickListener(v -> performClick());   //Handle down the click event to ItemView
        }
    }

    protected void addActions() {

    }

    protected void setLayoutStyle(int style) {
        LayoutAppearance lytStyle = LayoutAppearance.obtain(style);
        if (lytStyle != null) {
            View rootView = findViewById(R.id.root);
            ViewGroup.LayoutParams params = rootView.getLayoutParams();
            if (params == null) {
                params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            params.height = getResources().getDimensionPixelSize(lytStyle.height);
            rootView.requestLayout();
            setTitleSize(lytStyle.size);
            setContentSize(lytStyle.size);
        }
    }

    protected int getRightLayout() {
        return countable() ? R.layout.template_layout_counter : R.layout.template_layout_select;
    }

    protected boolean editable() {
        return false;
    }

    protected boolean countable() {
        return false;
    }

    protected boolean selectable() {
        return true;
    }

    public EditText getContentView() {
        return mContentEdit;
    }

    public void setTitle(CharSequence title) {
        mTitleText.setText(title);
    }

    public void setTitleColor(@ColorRes int color) {
        if (color != 0) {
            mTitleText.setTextColor(ContextCompat.getColor(getContext(), color));
        }
    }

    public void setTitleSize(@DimenRes int size) {
        if (size != 0) {
            mTitleText.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(size));
        }
    }

    public void setTitleIcon(@DrawableRes int leftIcon, @DrawableRes int rightIcon) {
        mTitleText.setCompoundDrawablesWithIntrinsicBounds(leftIcon, 0, rightIcon, 0);
    }

    public void setTitleIconPadding(int padding) {
        mTitleText.setCompoundDrawablePadding(padding);
    }

    public void setContent(CharSequence content) {
        mContentEdit.setText(content);
    }

    public void setContentSize(@DimenRes int size) {
        if (size != 0) {
            mContentEdit.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(size));
        }
    }

    public void setContentColor(@ColorRes int color) {
        mContentEdit.setTextColor(ContextCompat.getColor(getContext(), color));
    }

    public void setContentIcon(@DrawableRes int leftIcon, @DrawableRes int rightIcon) {
        mContentEdit.setCompoundDrawablesWithIntrinsicBounds(leftIcon, 0, rightIcon, 0);
    }

    public void setInputType(int type) {
        mContentEdit.setInputType(type);
    }

    public void setContentTextStyle(@IntRange(from = Typeface.NORMAL, to = Typeface.BOLD_ITALIC) int style) {
        mContentEdit.setTypeface(null, style);
    }

    public void setRightImage(@DrawableRes int res) {
        if (mRightImage != null && res != 0) {
            mRightImage.setImageResource(res);
        }
    }

    public void setTitleLeftImage(@DrawableRes int image) {
        if (image != 0) {
            mTitleLeftImage.setImageResource(image);
        }
    }

    public void setContentLeftImage(@DrawableRes int image) {
        if (image != 0) {
            mContentLeftImage.setImageResource(image);
        }
    }

    @NonNull
    public String getContent() {
        return mContentEdit.getText().toString().trim();
    }

    public void setContentHint(CharSequence hint) {
        mContentEdit.setHint(hint);
    }

    public void setRightText(CharSequence right) {
        if (mRightText != null) {
            mRightText.setText(right);
        }
    }

    public void setRightImageVisible(int visibility) {
        if (visibility == -1) {
            return;
        }
        if (mRightImage != null) {
            mRightImage.setVisibility(visibility);
        }
        if (visibility != VISIBLE && editable() && mRightDivider != null) {
            mRightDivider.setVisibility(visibility);
        }
    }

    public void setRightColor(@ColorRes int color) {
        if (mRightText != null && color != 0) {
            mRightText.setTextColor(ContextCompat.getColor(getContext(), color));
        }
    }

    public void showItemDivider(boolean show) {
        mItemDivider.setVisibility(show ? VISIBLE : GONE);
    }

    public void enableRightClick(boolean enable) {
        mRightLayout.setClickable(enable);
    }

    public void setOnRightClickListener(View.OnClickListener listener) {
        mRightLayout.setOnClickListener(listener);
    }

    public void setTextFormatter(ITextFormatter formatter) {
        mContentEdit.setTextFormatter(formatter);
    }

    public void setOnItemClickListener(View.OnClickListener listener) {
        setOnClickListener(listener);
    }

    public void setContentWatcher(SimpleTextWatcher watcher) {
        mContentEdit.addTextChangedListener(watcher);
    }

    public void setTextFormatter(@IntRange(from = DisplayLayout.FORMAT_NUMBER, to = DisplayLayout.FORMAT_WEIGHT) int formatType) {
        switch (formatType) {
            case DisplayLayout.FORMAT_NUMBER:
                setInputType(InputType.TYPE_CLASS_NUMBER);
                break;

            case DisplayLayout.FORMAT_SHORT:
                setTextFormatter(NumberFormatterCase.ShortCase.formatter);
                setInputType(InputType.TYPE_CLASS_NUMBER);
                break;

            case DisplayLayout.FORMAT_INTEGER:
                setTextFormatter(NumberFormatterCase.IntegerCase.formatter);
                setInputType(InputType.TYPE_CLASS_NUMBER);
                break;

            case DisplayLayout.FORMAT_LONG:
                setTextFormatter(NumberFormatterCase.LongCase.formatter);
                setInputType(InputType.TYPE_CLASS_NUMBER);
                break;

            case DisplayLayout.FORMAT_FLOAT:
                setTextFormatter(NumberFormatterCase.FloatCase.formatter);
                setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;

            case DisplayLayout.FORMAT_DOUBLE:
                setTextFormatter(NumberFormatterCase.DoubleCase.formatter);
                setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;

            case DisplayLayout.FORMAT_PRICE:
                setTextFormatter(NumberFormatterCase.PriceCase.formatter);
                setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;

            case DisplayLayout.FORMAT_WEIGHT:
                setTextFormatter(NumberFormatterCase.WeightCase.formatter);
                setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;

            default:
                setTextFormatter(null);
                setInputType(InputType.TYPE_MASK_CLASS);
                break;
        }

    }
}