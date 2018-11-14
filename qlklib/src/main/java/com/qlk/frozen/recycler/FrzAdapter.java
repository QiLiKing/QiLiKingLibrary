package com.qlk.frozen.recycler;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/28 16:06
 */
public abstract class FrzAdapter<T, VH extends FrzViewHolder> extends RecyclerView.Adapter<VH> {
    private final ArrayList<T> mData = new ArrayList<>();

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * @return null position is out of index
     */
    @Nullable
    public T getItem(VH vh) {
        int position = getAccuratePosition(vh);
        if (position < 0 || position > mData.size() - 1) {
            return null;
        }
        return mData.get(position);
    }

    public int getAccuratePosition(VH vh) {
        return vh.getAdapterPosition();
    }
}
