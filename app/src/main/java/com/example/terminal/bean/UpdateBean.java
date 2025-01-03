package com.example.terminal.bean;

/**
 * author : King
 * date   : 2022/2/1115:30
 * desc   :
 */
public class UpdateBean {

    private int code;
    private String msg;
    private UpdateData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UpdateData getData() {
        return data;
    }

    public void setData(UpdateData updateData) {
        this.data = updateData;
    }

    public static class UpdateData {
        private int id;
        private String type;
        private int version;
        private String versionName;
        private String content;
        private int apkFileId;
        private String apkFileUrl;
        private String apkFileName;
        private int createBy;
        private Object createTime;
        private int updateBy;
        private Object updateTime;
        private String remark;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getApkFileId() {
            return apkFileId;
        }

        public void setApkFileId(int apkFileId) {
            this.apkFileId = apkFileId;
        }

        public String getApkFileUrl() {
            return apkFileUrl;
        }

        public void setApkFileUrl(String apkFileUrl) {
            this.apkFileUrl = apkFileUrl;
        }

        public String getApkFileName() {
            return apkFileName;
        }

        public void setApkFileName(String apkFileName) {
            this.apkFileName = apkFileName;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public int getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(int updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
