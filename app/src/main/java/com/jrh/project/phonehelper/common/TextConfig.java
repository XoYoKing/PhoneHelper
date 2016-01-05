package com.jrh.project.phonehelper.common;

/**
 * Description: 字体控制
 * Created by JiaRH on 2015/12/29.17:27
 */
public class TextConfig {
    private  static TextConfig instance=null;
    private TextConfig(){}
    public static TextConfig getInstance(){
        if (instance==null){
            instance = new TextConfig();
        }
        return  instance;
    }

    private int textSize;
    private String textColor;

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
}
