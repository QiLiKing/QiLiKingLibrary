package com.qlk.lib.recycler.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/29 10:06
 */
public class FrzAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected static final int TYPE_HEADER_REFRESH = 0x1981;
    protected static final int TYPE_HEADER_NORMAL = 0x1982;
    protected static final int TYPE_FOOTER_LOAD = 0x1983;
    protected static final int TYPE_FOOTER_NORMAL = 0x1984;

    @NonNull
    @Override
    public final VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public final void onBindViewHolder(@NonNull VH holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
