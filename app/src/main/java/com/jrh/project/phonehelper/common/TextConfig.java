package com.jrh.project.phonehelper.common;

import android.content.Context;

import com.jrh.project.phonehelper.PhoneHelperConfigure;

/**
 * Description: 字体控制
 * Created by JiaRH on 2015/12/29.17:27
 */
public class TextConfig {
    private Context mContext;
    private  final String TEXT_SIZE="TEXT_SIZE";
    private  static TextConfig instance=null;
    private TextConfig(Context context){
        this.mContext=context;
    }
    public static TextConfig getInstance(Context context){

        if (instance==null){
            instance = new TextConfig(context);
        }
        return  instance;
    }

    private int textSize=20;
    private String textColor;

    public int getTextSize() {
        textSize=PhoneHelperConfigure.getConfigure(mContext).getInt(TEXT_SIZE,textSize);
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;

        PhoneHelperConfigure.getConfigure(mContext).put(TEXT_SIZE,textSize);
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
}
