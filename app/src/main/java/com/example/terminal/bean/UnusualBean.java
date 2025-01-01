package com.example.terminal.bean;

import java.io.Serializable;
import java.util.List;

/**
 *    author : King
 *    date   : 2022/1/10
 *    desc   : 异常信息
 */
public class UnusualBean implements Serializable {

    private static final long serialVersionUID = -7060210544600464488L;

    private int count;
    private List<UnusualData> data;
    private int code;
    private String msg;
    private int total;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UnusualData> getData() {
        return data;
    }

    public void setData(List<UnusualData> data) {
        this.data = data;
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class UnusualData implements Serializable {
        private static final long serialVersionUID = -7060210544600464487L;
        private int id;
        private int businessId;
        private int sourceType;//进港入库异常:1 ；进港提货异常:2； 进港盘库异常:3；出港盘库异常:4'；进港出库:5
        private int type;
        private String typeName ;
        private String grade;
        private int number;
        private String explain;
        private String createName ;
        private List<UnusualImageBean> bizAnomalyRecordFileVOList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBusinessId() {
            return businessId;
        }

        public void setBusinessId(int businessId) {
            this.businessId = businessId;
        }

        public int getSourceType() {
            return sourceType;
        }

        public void setSourceType(int sourceType) {
            this.sourceType = sourceType;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public List<UnusualImageBean> getBizAnomalyRecordFileVOList() {
            return bizAnomalyRecordFileVOList;
        }

        public void setBizAnomalyRecordFileVOList(List<UnusualImageBean> bizAnomalyRecordFileVOList) {
            this.bizAnomalyRecordFileVOList = bizAnomalyRecordFileVOList;
        }

        public static class UnusualImageBean implements Serializable {

            private static final long serialVersionUID = -7060210544600464486L;

            private int id;
            private int anomalyRecordId;
            private int fileId;
            private String url ;
            private String fileName ;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAnomalyRecordId() {
                return anomalyRecordId;
            }

            public void setAnomalyRecordId(int anomalyRecordId) {
                this.anomalyRecordId = anomalyRecordId;
            }

            public int getFileId() {
                return fileId;
            }

            public void setFileId(int fileId) {
                this.fileId = fileId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }
        }
    }
}
