package com.qlk.lib.recycler;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/28 16:11
 */
public interface IFrzAdapter {
    @NonNull
    FrzViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    void onBindViewHolder(@NonNull FrzViewHolder holder, int position);
}
