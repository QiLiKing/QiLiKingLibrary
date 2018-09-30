package com.qlk.lib.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.qlk.lib.recycler.cell.IFrzEmptyView;
import com.qlk.lib.recycler.refresher.IFrzRefresher;
import com.qlk.lib.recycler.refresher.FrzOnRefreshListener;
import com.qlk.lib.recycler.refresher.FrzRefresher;
import com.qlk.lib.recycler.view.RecyclerViewBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/9/28 14:38
 */
public class RecyclerManager<T> {
    private final ArrayList<T> mData = new ArrayList<>();

    private IFrzRefresher mRefresher;
    private IFrzEmptyView mEmptyView;
    private Context mContext;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;

    private RecyclerManager() {
    }

    public static <K> RecyclerManager<K> with(Context context, RecyclerView recyclerView) {
        RecyclerManager<K> manager = new RecyclerManager<>();
        manager.mRecyclerView = recyclerView;
        manager.mContext = context;
        return manager;
    }

    public void refresh(List<T> data) {
        int status = mRefresher.onFinished(data);
        switch (status) {
            case IFrzRefresher.STATUS_REFRESH_FAILURE:
                
                break;

            case IFrzRefresher.STATUS_LOAD_MORE_FAILURE:

                break;

            case IFrzRefresher.STATUS_RELOAD_FAILURE:

                break;

            case IFrzRefresher.STATUS_REFRESH_SUCCESS:

                break;

            case IFrzRefresher.STATUS_LOAD_MORE_SUCCESS:

                break;

            case IFrzRefresher.STATUS_RELOAD_SUCCESS:

                break;

            default:
                break;
        }
    }

    public void start() {
        if (mRecyclerView.getLayoutManager() == null) {
            new RecyclerViewBuilder(mContext).build(mRecyclerView);
        }
        mRefresher.onRefresh();
    }

    public RecyclerManager adapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        return this;
    }

    public RecyclerManager refresher(FrzOnRefreshListener<T> listener) {
        return refresher(new FrzRefresher(), listener);
    }

    public RecyclerManager refresher(IFrzRefresher refresher, FrzOnRefreshListener listener) {
        mRefresher = refresher;
        return this;
    }

}
