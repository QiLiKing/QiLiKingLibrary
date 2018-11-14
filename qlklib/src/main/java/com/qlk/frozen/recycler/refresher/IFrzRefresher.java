package com.qlk.frozen.recycler.refresher;

import android.support.annotation.Nullable;

import com.qlk.frozen.recycler.IFrzLoadable;
import com.qlk.frozen.recycler.IFrzRefreshable;

import java.util.List;

/**
 * Only manage the refreshing logic, not care how the UI displays.
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/28 14:58
 */
public interface IFrzRefresher extends IFrzRefreshable, IFrzLoadable {
    int STATUS_REFRESH_FAILURE = -1;
    int STATUS_LOAD_MORE_FAILURE = -2;
    int STATUS_RELOAD_FAILURE = -3;
    int STATUS_IDLE = 0;
    int STATUS_REFRESHING = 1;
    int STATUS_LOADING_MORE = 2;
    int STATUS_RELOADING = 3;
    int STATUS_REFRESH_SUCCESS = 4;
    int STATUS_LOAD_MORE_SUCCESS = 5;
    int STATUS_RELOAD_SUCCESS = 6;

    /**
     * @param index the position to reload
     */
    void onReload(int index);

    /**
     * @return refresh status
     */
    int onFinished(@Nullable List peerData);
}
