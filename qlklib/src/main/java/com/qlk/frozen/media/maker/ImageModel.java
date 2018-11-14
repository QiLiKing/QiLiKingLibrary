package com.qlk.frozen.media.maker;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/14 17:43
 */
public class ImageModel extends AndroidViewModel {
    private final MutableLiveData<Image> image = new MutableLiveData<>();

    public ImageModel(@NonNull Application application) {
        super(application);
    }
}
