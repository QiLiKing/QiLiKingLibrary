package com.qlk.frozen.media.maker;

import java.util.ArrayList;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/15 11:43
 */
public interface ISelectCallback {
    boolean checkSelect(String path);

    boolean switchSelect(String path);

    ArrayList<String> getSelections();
}
