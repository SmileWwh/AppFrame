package com.wwh.appframe.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wwh on 2016/11/29.
 */

public class UserData implements Parcelable {

    String id;
    String name;
    int age;
    UserDetailData detail;

    public UserData() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDetailData getDetail() {
        return detail;
    }

    public void setDetail(UserDetailData detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", detail=" + detail +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.age);
        dest.writeParcelable(this.detail, flags);
    }

    protected UserData(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.age = in.readInt();
        this.detail = in.readParcelable(UserDetailData.class.getClassLoader());
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel source) {
            return new UserData(source);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };
}
