package com.qlk.frozen.recycler.refresher;

import android.support.annotation.UiThread;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/28 15:00
 */
public interface FrzOnRefreshListener {
    /**
     * @param page  >=1
     * @param start >=0
     * @param size  total of peer page; or number of distance starting positions
     * @return the count of loading threads
     */
    @UiThread
    void onRefresh(int page, int start, int size);
}
