package com.qlk.lib.recycler;

import android.support.v4.widget.SwipeRefreshLayout;

import com.qlk.lib.recycler.IFrzCancelable;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/30 10:24
 */
public interface IFrzRefreshable extends SwipeRefreshLayout.OnRefreshListener, IFrzCancelable {
    boolean isRefreshing();
}
