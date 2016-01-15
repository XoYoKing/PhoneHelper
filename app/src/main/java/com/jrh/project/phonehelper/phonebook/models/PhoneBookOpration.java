package com.jrh.project.phonehelper.phonebook.models;

/**
 * Description:
 * Created by JiaRH on 2015/12/29.17:39
 */
public enum PhoneBookOpration {
    CALL(0, "打电话"),
//    SEND_MSG(1,"发短信"),
    //UPDATE_USER(2,"修改"),
    DELETE(3,"删除"),
    CANCLE(4,"取消");
    private String opName;
    private int index;

     PhoneBookOpration(int index, String opName) {

        this.index=index;
        this.opName = opName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }
}
