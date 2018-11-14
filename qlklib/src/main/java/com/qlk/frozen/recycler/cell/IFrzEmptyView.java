package com.qlk.frozen.recycler.cell;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/30 16:03
 */
public interface IFrzEmptyView {
    void onRefreshing();

    void onFailure();

    void onEmptyChanged(boolean empty);
}
