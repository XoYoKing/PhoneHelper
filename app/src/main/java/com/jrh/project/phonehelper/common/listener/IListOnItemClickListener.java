package com.jrh.project.phonehelper.common.listener;

import android.view.View;
import android.widget.AdapterView;

/**
 * Description:
 * Created by JiaRH on 2015/12/30.11:09
 */
public interface IListOnItemClickListener {
    void onItemClicked(AdapterView<?> parent, View view, int position, long id);
}
