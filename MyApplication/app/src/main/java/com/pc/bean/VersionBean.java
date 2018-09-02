package com.pc.bean;

/**
 * Created by Administrator on 2018-03-12.
 */

public class VersionBean {


    /**
     * VersionCode : 1000
     * VersionName : 1.0.0
     * VersionUrl : http://139.196.204.207:8018/Apk/app-debug.apk
     * UpdateType : 1
     */

    private String VersionCode;
    private String VersionName;
    private String VersionUrl;
    private int UpdateType;

    public String getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(String VersionCode) {
        this.VersionCode = VersionCode;
    }

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String VersionName) {
        this.VersionName = VersionName;
    }

    public String getVersionUrl() {
        return VersionUrl;
    }

    public void setVersionUrl(String VersionUrl) {
        this.VersionUrl = VersionUrl;
    }

    public int getUpdateType() {
        return UpdateType;
    }

    public void setUpdateType(int UpdateType) {
        this.UpdateType = UpdateType;
    }
}
