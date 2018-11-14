package com.qlk.frozen.media.maker;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/14 17:40
 */
public class FolderModel extends AndroidViewModel {
    private final MutableLiveData<Folder> folder = new MutableLiveData<>();

    public FolderModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<Folder> getFolder() {
        return folder;
    }
}
