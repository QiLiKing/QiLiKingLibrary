package com.qlk.frozen.recycler.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class FrzDividerItemDecoration extends DividerItemDecoration {

    public FrzDividerItemDecoration(Context context) {
        this(context, HORIZONTAL);
        ColorDrawable drawable = new ColorDrawable();

    }

    /**
     * Creates a divider {@link RecyclerView.ItemDecoration} that can be used with a
     * {@link LinearLayoutManager}.
     *
     * @param context     Current context, it will be used to access resources.
     * @param orientation Divider orientation. Should be {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    public FrzDividerItemDecoration(Context context, int orientation) {
        super(context, orientation);
    }
}