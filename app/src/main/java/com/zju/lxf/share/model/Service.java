package com.zju.lxf.share.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.zju.lxf.share.util.Utils;

import java.util.Date;

public class Service implements Parcelable {
    private byte status;
    private long invokeCount;

    private String id;
    private String name;
    private String description;
    private String lastInvokedTime;
    private String iconPath;

    public Service(){
        this.name = null;
        this.description = null;
        this.iconPath = null;

        this.invokeCount = 0;
        this.lastInvokedTime = Utils.invokeTimeFormat.format(new Date());
    }

    public Service(String name, String description, String iconPath){
        this.name = name;
        this.description = description;
        this.iconPath = iconPath;

        this.invokeCount = 0;
        this.lastInvokedTime = Utils.invokeTimeFormat.format(new Date());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastInvokedTime() {
        return lastInvokedTime;
    }

    public void setLastInvokedTime(String lastInvokedTime) {
        this.lastInvokedTime = lastInvokedTime;
    }

    public long getInvokeCount() {
        return invokeCount;
    }

    public void setInvokeCount(long invokeCount) {
        this.invokeCount = invokeCount;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public static final Parcelable.Creator<Service> CREATOR = new Creator<Service>() {

        @Override
        public Service[] newArray(int size) {
            return new Service[size];
        }

        @Override
        public Service createFromParcel(Parcel source) {
            Service s = new Service();

            s.setStatus(source.readByte());
            s.setInvokeCount(source.readLong());
            s.setId(source.readString());
            s.setName(source.readString());
            s.setDescription(source.readString());
            s.setLastInvokedTime(source.readString());
            s.setIconPath(source.readString());

            return s;
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(status);
        parcel.writeLong(invokeCount);

        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(lastInvokedTime);
        parcel.writeString(iconPath);
    }
}
