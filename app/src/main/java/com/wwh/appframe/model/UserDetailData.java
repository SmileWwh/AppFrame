package com.wwh.appframe.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wwh on 2016/11/29.
 */

public class UserDetailData implements Parcelable{
    String id;
    String email;
    String phone;

    public UserDetailData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserDetailData{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.email);
        dest.writeString(this.phone);
    }

    protected UserDetailData(Parcel in) {
        this.id = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
    }

    public static final Creator<UserDetailData> CREATOR = new Creator<UserDetailData>() {
        @Override
        public UserDetailData createFromParcel(Parcel source) {
            return new UserDetailData(source);
        }

        @Override
        public UserDetailData[] newArray(int size) {
            return new UserDetailData[size];
        }
    };
}
