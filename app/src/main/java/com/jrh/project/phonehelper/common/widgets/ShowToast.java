package com.jrh.project.phonehelper.common.widgets;

import android.content.Context;
import android.widget.Toast;

/**
 * Description:
 * Created by JiaRH on 2016/1/5.14:25
 */
public class ShowToast {
    public static void showLongToast(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
    public static void showShortToast(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
