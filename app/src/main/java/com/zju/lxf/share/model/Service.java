package com.zju.lxf.share.model;

import com.zju.lxf.share.util.Utils;

import java.util.Date;

public class Service {
    private String id;
    private String name;
    private String description;
    private String lastInvokedTime;
    private long   invokeCount;
    private String iconPath;
    private boolean status;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
