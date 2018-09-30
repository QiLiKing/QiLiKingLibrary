package com.qlk.lib.recycler.cell;

import android.view.View;

import com.qlk.lib.recycler.IFrzRefreshable;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/30 10:49
 */
public interface IFrzItemView extends IFrzRefreshable {
    View getView();
}
