package com.fwk.lkxj3.android.bean;

public class AutoUpdaterDTO {

    private String AllowMinVersion;// 最低版本号
    private String CurrentVersion;// 最新版本号
    private String CurrentVersionFileUrl;// apk下载地址
    private String CurrentVersionFileName;// apk文件名称

    public String getCurrentVersionFileUrl() {
        return CurrentVersionFileUrl;
    }

    public void setCurrentVersionFileUrl(String currentVersionFileUrl) {
        CurrentVersionFileUrl = currentVersionFileUrl;
    }

    public String getCurrentVersionFileName() {
        return CurrentVersionFileName;
    }

    public void setCurrentVersionFileName(String currentVersionFileName) {
        CurrentVersionFileName = currentVersionFileName;
    }

    public String getAllowMinVersion() {
        return AllowMinVersion;
    }

    public void setAllowMinVersion(String allowMinVersion) {
        AllowMinVersion = allowMinVersion;
    }

    public String getCurrentVersion() {
        return CurrentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        CurrentVersion = currentVersion;
    }

}
