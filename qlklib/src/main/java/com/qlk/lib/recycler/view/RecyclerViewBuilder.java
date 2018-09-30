package com.qlk.lib.recycler.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/30 09:13
 */
public class RecyclerViewBuilder {
    private Context context;
    private RecyclerView.LayoutManager manager;
    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    private int dividerSize, dividerColor;

    public RecyclerViewBuilder(Context context) {
        this.context = context;
    }

    public RecyclerViewBuilder padding(int left, int top, int right, int bottom) {
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
        return this;
    }

    public RecyclerViewBuilder layoutManager(RecyclerView.LayoutManager manager) {
        this.manager = manager;
        return this;
    }

    public RecyclerViewBuilder divider(int size, int color) {
        dividerSize = size;
        dividerColor = color;
        return this;
    }

    public void build(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new FrzDividerItemDecoration(context));
    }

    public static void defaultBuild(Context context, RecyclerView recyclerView) {
        new RecyclerViewBuilder(context)
                .layoutManager(new LinearLayoutManager(context))
                .divider(2, Color.TRANSPARENT)
                .build(recyclerView);
    }
}
