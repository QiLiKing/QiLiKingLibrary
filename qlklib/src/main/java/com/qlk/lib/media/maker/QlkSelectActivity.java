package com.qlk.lib.media.maker;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.qlk.lib.QlkSelector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/14 11:27
 */
public class QlkSelectActivity extends AppCompatActivity implements ISelectCallback {
    public static final String KEY_MAX_SELECT_COUNT = "max_select_count";
    public static final String KEY_SELECTED_LIST = "selected_list";
    public static final String KEY_SELECT_TYPE = "select_type";
    public static final String KEY_SELECT_MODE = "select_mode";
    public static final int TYPE_ALL = 0;
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_VIDEO = 3;
    public static final int TYPE_FILE = 4;

    @IntDef({TYPE_ALL, TYPE_IMAGE, TYPE_AUDIO, TYPE_VIDEO, TYPE_FILE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Type {
    }

    private static final int MAX_SELECT_COUNT_DEFAULT = 9;

    private int mType;
    private QlkSelector.Mode mMode;
    private QlkSelector<String> mSelector = new QlkSelector<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public boolean checkSelect(String path) {
        return mSelector.contains(path);
    }

    @Override
    public boolean switchSelect(String path) {
        return mSelector.switchSelect(path);
    }

    @Override
    public ArrayList<String> getSelections() {
        return mSelector.getSelections();
    }

    public static class Builder {
        private Context context;
        private int max;
        private int type;
        private QlkSelector.Mode mode;
        private ArrayList<String> selected;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder(Context context, int type, QlkSelector.Mode mode) {
            this.context = context;
            this.type = type;
            this.mode = mode;
        }

        public Builder setMaxSelectCount(int max) {
            this.max = max;
            return this;
        }

        public Builder setSelectType(@Type int type) {
            this.type = type;
            return this;
        }

        public Builder setSelectMode(QlkSelector.Mode mode) {
            this.mode = mode;
            return this;
        }

        public Builder setSelectedFiles(ArrayList<String> selected) {
            this.selected = selected;
            return this;
        }

        public Intent build() {
            Intent intent = new Intent(context, QlkSelectActivity.class);
            if (max > 0) {
                intent.putExtra(KEY_MAX_SELECT_COUNT, max);
            }
            intent.putExtra(KEY_SELECT_TYPE, type);
            if (mode != null) {
                intent.putExtra(KEY_SELECT_MODE, mode);
            }
            if (selected != null && !selected.isEmpty()) {
                intent.putExtra(KEY_SELECTED_LIST, selected);
            }
            return intent;
        }

        public Intent defaultBuild() {
            Intent intent = new Intent(context, QlkSelectActivity.class);
            intent.putExtra(KEY_MAX_SELECT_COUNT, MAX_SELECT_COUNT_DEFAULT);
            intent.putExtra(KEY_SELECT_TYPE, TYPE_ALL);
            intent.putExtra(KEY_SELECT_MODE, QlkSelector.Mode.Single);
            return intent;
        }
    }
}
