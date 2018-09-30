package com.qlk.lib.recycler.refresher;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/28 15:11
 */
public class FrzRefresher implements IFrzRefresher {
    private static final int DEFAULT_SIZE = 20;

    private final int mPeerSize;
    private int mStart = 0;
    private int mCurPage = 1;

    private boolean hasMoreData = true;

    private int mStatus = STATUS_IDLE;
    private FrzOnRefreshListener mRefreshListener;

    public FrzRefresher(@NonNull FrzOnRefreshListener refreshListener) {
        this(refreshListener, DEFAULT_SIZE);
    }

    public FrzRefresher(@NonNull FrzOnRefreshListener refreshListener, int peerSize) {
        this.mRefreshListener = refreshListener;
        this.mPeerSize = peerSize;
    }

    public int getStatus() {
        return mStatus;
    }

    // FIXME: 2018/9/30 how to deal with this method
    @Override
    public void onReload(int index) {
        int page = mCurPage;
        int start = mStart;
        if (index >= 0 && index < start) {
            page = index / mPeerSize + 1;
            start = index - index % mPeerSize;
        }
        mStatus = STATUS_RELOADING;
        mRefreshListener.onRefresh(page, start, mPeerSize);
    }

    @Override
    public int onFinished(@Nullable List peerData) {
        if (peerData == null) {
            if (mStatus == STATUS_REFRESHING) {
                mStatus = STATUS_REFRESH_FAILURE;
            } else if (mStatus == STATUS_LOADING_MORE) {
                mStatus = STATUS_LOAD_MORE_FAILURE;
            } else if (mStatus == STATUS_RELOADING) {
                mStatus = STATUS_RELOAD_FAILURE;
            }
        } else {
            hasMoreData = peerData.size() >= mPeerSize;
            if (mStatus == STATUS_REFRESHING) {
                mStatus = STATUS_REFRESH_SUCCESS;
                mCurPage = 1;
                mStart = 0;
            } else if (mStatus == STATUS_LOADING_MORE) {
                mStatus = STATUS_LOAD_MORE_SUCCESS;
                if (peerData.size() > 0) {
                    mCurPage++;
                    mStart += mPeerSize;
                }
            } else if (mStatus == STATUS_RELOADING) {
                mStatus = STATUS_RELOAD_SUCCESS;
                if (peerData.isEmpty()) {
                    // TODO: 2018/9/30 how to do it
//                    mCurPage--;
//                    mStart -= mPeerSize;
                }
            }
        }
        return mStatus;
    }

    @Override
    public void onRefresh() {
        mStatus = STATUS_REFRESHING;
        mRefreshListener.onRefresh(1, 0, mPeerSize);
    }

    @Override
    public boolean isLoadingMore() {
        return mStatus == STATUS_LOADING_MORE;
    }

    @Override
    public boolean canLoadMore() {
        return hasMoreData;
    }

    @Override
    public void onLoadMore() {
        mStatus = STATUS_LOADING_MORE;
        mRefreshListener.onRefresh(mCurPage + 1, mStart + mPeerSize, mPeerSize);
    }

    @Override
    public boolean isRefreshing() {
        return mStatus == STATUS_REFRESHING;
    }

    @Override
    public void onCancel() {
        //not support
    }
}
