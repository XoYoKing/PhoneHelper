package com.jrh.project.phonehelper;

import android.content.Context;

/**
 * Description:
 * Created by JiaRH on 2015/12/28.17:07
 */
public abstract class BasePresenter {
    public Context mContext;

    public BasePresenter(Context mContext) {
        this.mContext = mContext;
    }
}
