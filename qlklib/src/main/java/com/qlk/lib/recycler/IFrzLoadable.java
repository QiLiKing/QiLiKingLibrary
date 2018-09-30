package com.qlk.lib.recycler;

import com.qlk.lib.recycler.IFrzCancelable;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/30 10:27
 */
public interface IFrzLoadable extends IFrzCancelable {
    boolean isLoadingMore();

    /**
     * @return whether there is more data can load
     */
    boolean canLoadMore();

    void onLoadMore();
}
