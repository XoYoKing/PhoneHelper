package com.jrh.project.phonehelper.common.event;

import java.io.Serializable;

/**
 * Description: 发送通知，用不同的type 标识
 * Created by JiaRH on 2016/1/5.16:20
 */
public class EventMsg<T> implements Serializable{
    /**
     * 添加联系人
     */
    public static final int ADD_USER=1<<1;
    private int type;
    private String msg;
    private T t;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
