package com.pluscubed.velociraptor.appselection;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import io.requery.Entity;
import io.requery.Key;
import io.requery.Transient;

@Entity
public class AppInfo implements Comparable<AppInfo>, Parcelable {

    public static final Creator<AppInfo> CREATOR = new Creator<AppInfo>() {
        @Override
        public AppInfo createFromParcel(Parcel source) {
            return new AppInfo(source);
        }

        @Override
        public AppInfo[] newArray(int size) {
            return new AppInfo[size];
        }
    };
    @Key
    public String packageName;
    @Transient
    public boolean enabled;
    @Transient
    public String name;

    public AppInfo() {
        name = "";
    }

    protected AppInfo(Parcel in) {
        this.packageName = in.readString();
        this.enabled = in.readByte() != 0;
        this.name = in.readString();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof AppInfo && packageName.equals(((AppInfo) o).packageName);
    }

    @Override
    public int hashCode() {
        return packageName.hashCode();
    }

    @Override
    public int compareTo(@NonNull AppInfo another) {
        return name.toLowerCase().compareTo(another.name.toLowerCase());
    }

    @Override
    public String toString() {
        return packageName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.packageName);
        dest.writeByte(enabled ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
    }
}