package com.qlk.lib.recycler.vh;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/29 09:54
 */
public class FrzBindingVH extends FrzSimpleVH {
    private ViewDataBinding bindingView;

    public FrzBindingVH(View itemView) {
        super(itemView);
        bindingView = DataBindingUtil.bind(itemView);
    }

    public ViewDataBinding getBindingView() {
        return bindingView;
    }

    public void setBindingVariable(int variableId, @Nullable Object value) {
        bindingView.setVariable(variableId, value);
        bindingView.executePendingBindings();
    }
}
