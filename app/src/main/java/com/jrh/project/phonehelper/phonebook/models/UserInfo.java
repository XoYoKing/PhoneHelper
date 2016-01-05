package com.jrh.project.phonehelper.phonebook.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description:
 * Created by JiaRH on 2015/12/28.
 */
public class UserInfo implements Parcelable {
    public static final String USER_INFO="userinfo";
    private String id;
    private String name;
    private String telNum;
    /**地址*/
    private String address;
    /**常用等级，使用频次越高，数字越大*/
    private String level;
    /** 是否是亲情号码*/
    private boolean isReleative;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isReleative() {
        return isReleative;
    }

    public void setReleative(boolean releative) {
        isReleative = releative;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public UserInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.telNum);
        dest.writeString(this.address);
        dest.writeString(this.level);
        dest.writeByte(isReleative ? (byte) 1 : (byte) 0);
    }

    protected UserInfo(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.telNum = in.readString();
        this.address = in.readString();
        this.level = in.readString();
        this.isReleative = in.readByte() != 0;
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
