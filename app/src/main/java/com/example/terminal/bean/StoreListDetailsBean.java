package com.example.terminal.bean;

import java.io.Serializable;
import java.util.List;

public class StoreListDetailsBean implements Serializable {

    /**
     * msg : 操作成功
     * code : 200
     * data : {"createBy":"1","createName":"系统超级管理员","createTime":"2025-01-02 16:43:06","updateBy":null,"updateTime":null,"remark":null,"id":12,"flowDataId":2,"type":"1","typeName":"新品新件入库","orderName":null,"storeHouseName":"北京库房","storeHouseId":1,"orderId":null,"applyDate":"2025-01-02","applyInfo":"测试","status":"1","operUserName":"刘琦","operUserId":1,"delFlag":"0","fileUrl":"","arrs":["撤回"],"deviceBorrowId":null,"deptId":103,"rejectUser":null,"rejectUserName":null,"rejectTime":null,"rejectRemark":null,"startTime":null,"endTime":null,"storegeOutId":null,"tstoregeInDevice":[{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":24,"storegeInId":12,"inNum":null,"deviceId":1,"deviceName":"高精度定位天线","deviceType":"RTK","deviceLeixin":"3","deviceCompany":"嘉兴佳丽","deviceNum":2,"devicePrice":0,"imeiStatus":"0","tstoregeInDeviceDetailList":[]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":25,"storegeInId":12,"inNum":null,"deviceId":2,"deviceName":"RTD天线","deviceType":"RTD","deviceLeixin":"3","deviceCompany":"嘉兴佳丽","deviceNum":2,"devicePrice":0,"imeiStatus":"0","tstoregeInDeviceDetailList":[]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":26,"storegeInId":12,"inNum":null,"deviceId":5,"deviceName":"高精度天线底座","deviceType":"RTKDZ","deviceLeixin":"3","deviceCompany":"嘉兴佳丽","deviceNum":2,"devicePrice":0,"imeiStatus":"0","tstoregeInDeviceDetailList":[]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":27,"storegeInId":12,"inNum":null,"deviceId":8,"deviceName":"电池","deviceType":"Abile919DCH","deviceLeixin":"3","deviceCompany":"素泰","deviceNum":2,"devicePrice":0,"imeiStatus":"0","tstoregeInDeviceDetailList":[]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":28,"storegeInId":12,"inNum":null,"deviceId":7,"deviceName":"5M射频线","deviceType":"RTKSPX","deviceLeixin":"3","deviceCompany":"嘉兴佳丽","deviceNum":2,"devicePrice":0,"imeiStatus":"0","tstoregeInDeviceDetailList":[]}]}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * createBy : 1
         * createName : 系统超级管理员
         * createTime : 2025-01-02 16:43:06
         * updateBy : null
         * updateTime : null
         * remark : null
         * id : 12
         * flowDataId : 2
         * type : 1
         * typeName : 新品新件入库
         * orderName : null
         * storeHouseName : 北京库房
         * storeHouseId : 1
         * orderId : null
         * applyDate : 2025-01-02
         * applyInfo : 测试
         * status : 1
         * operUserName : 刘琦
         * operUserId : 1
         * delFlag : 0
         * fileUrl :
         * arrs : ["撤回"]
         * deviceBorrowId : null
         * deptId : 103
         * rejectUser : null
         * rejectUserName : null
         * rejectTime : null
         * rejectRemark : null
         * startTime : null
         * endTime : null
         * storegeOutId : null
         * tstoregeInDevice : [{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":24,"storegeInId":12,"inNum":null,"deviceId":1,"deviceName":"高精度定位天线","deviceType":"RTK","deviceLeixin":"3","deviceCompany":"嘉兴佳丽","deviceNum":2,"devicePrice":0,"imeiStatus":"0","tstoregeInDeviceDetailList":[]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":25,"storegeInId":12,"inNum":null,"deviceId":2,"deviceName":"RTD天线","deviceType":"RTD","deviceLeixin":"3","deviceCompany":"嘉兴佳丽","deviceNum":2,"devicePrice":0,"imeiStatus":"0","tstoregeInDeviceDetailList":[]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":26,"storegeInId":12,"inNum":null,"deviceId":5,"deviceName":"高精度天线底座","deviceType":"RTKDZ","deviceLeixin":"3","deviceCompany":"嘉兴佳丽","deviceNum":2,"devicePrice":0,"imeiStatus":"0","tstoregeInDeviceDetailList":[]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":27,"storegeInId":12,"inNum":null,"deviceId":8,"deviceName":"电池","deviceType":"Abile919DCH","deviceLeixin":"3","deviceCompany":"素泰","deviceNum":2,"devicePrice":0,"imeiStatus":"0","tstoregeInDeviceDetailList":[]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":28,"storegeInId":12,"inNum":null,"deviceId":7,"deviceName":"5M射频线","deviceType":"RTKSPX","deviceLeixin":"3","deviceCompany":"嘉兴佳丽","deviceNum":2,"devicePrice":0,"imeiStatus":"0","tstoregeInDeviceDetailList":[]}]
         */

        private String createBy;
        private String createName;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private int id;
        private int flowDataId;
        private String type;
        private String typeName;
        private Object orderName;
        private String storeHouseName;
        private int storeHouseId;
        private Object orderId;
        private String applyDate;
        private String applyInfo;
        private String status;
        private String operUserName;
        private int operUserId;
        private String delFlag;
        private String fileUrl;
        private Object deviceBorrowId;
        private int deptId;
        private Object rejectUser;
        private Object rejectUserName;
        private Object rejectTime;
        private Object rejectRemark;
        private Object startTime;
        private Object endTime;
        private Object storegeOutId;
        private List<String> arrs;
        private List<TstoregeInDeviceBean> tstoregeInDevice;

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateName() {
            return createName;
        }

        public void setCreateName(String createName) {
            this.createName = createName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFlowDataId() {
            return flowDataId;
        }

        public void setFlowDataId(int flowDataId) {
            this.flowDataId = flowDataId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public Object getOrderName() {
            return orderName;
        }

        public void setOrderName(Object orderName) {
            this.orderName = orderName;
        }

        public String getStoreHouseName() {
            return storeHouseName;
        }

        public void setStoreHouseName(String storeHouseName) {
            this.storeHouseName = storeHouseName;
        }

        public int getStoreHouseId() {
            return storeHouseId;
        }

        public void setStoreHouseId(int storeHouseId) {
            this.storeHouseId = storeHouseId;
        }

        public Object getOrderId() {
            return orderId;
        }

        public void setOrderId(Object orderId) {
            this.orderId = orderId;
        }

        public String getApplyDate() {
            return applyDate;
        }

        public void setApplyDate(String applyDate) {
            this.applyDate = applyDate;
        }

        public String getApplyInfo() {
            return applyInfo;
        }

        public void setApplyInfo(String applyInfo) {
            this.applyInfo = applyInfo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOperUserName() {
            return operUserName;
        }

        public void setOperUserName(String operUserName) {
            this.operUserName = operUserName;
        }

        public int getOperUserId() {
            return operUserId;
        }

        public void setOperUserId(int operUserId) {
            this.operUserId = operUserId;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public Object getDeviceBorrowId() {
            return deviceBorrowId;
        }

        public void setDeviceBorrowId(Object deviceBorrowId) {
            this.deviceBorrowId = deviceBorrowId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public Object getRejectUser() {
            return rejectUser;
        }

        public void setRejectUser(Object rejectUser) {
            this.rejectUser = rejectUser;
        }

        public Object getRejectUserName() {
            return rejectUserName;
        }

        public void setRejectUserName(Object rejectUserName) {
            this.rejectUserName = rejectUserName;
        }

        public Object getRejectTime() {
            return rejectTime;
        }

        public void setRejectTime(Object rejectTime) {
            this.rejectTime = rejectTime;
        }

        public Object getRejectRemark() {
            return rejectRemark;
        }

        public void setRejectRemark(Object rejectRemark) {
            this.rejectRemark = rejectRemark;
        }

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
            this.endTime = endTime;
        }

        public Object getStoregeOutId() {
            return storegeOutId;
        }

        public void setStoregeOutId(Object storegeOutId) {
            this.storegeOutId = storegeOutId;
        }

        public List<String> getArrs() {
            return arrs;
        }

        public void setArrs(List<String> arrs) {
            this.arrs = arrs;
        }

        public List<TstoregeInDeviceBean> getTstoregeInDevice() {
            return tstoregeInDevice;
        }

        public void setTstoregeInDevice(List<TstoregeInDeviceBean> tstoregeInDevice) {
            this.tstoregeInDevice = tstoregeInDevice;
        }

        public static class TstoregeInDeviceBean implements Serializable {
            /**
             * createBy : null
             * createName : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * id : 24
             * storegeInId : 12
             * inNum : null
             * deviceId : 1
             * deviceName : 高精度定位天线
             * deviceType : RTK
             * deviceLeixin : 3
             * deviceCompany : 嘉兴佳丽
             * deviceNum : 2
             * devicePrice : 0.0
             * imeiStatus : 0
             * tstoregeInDeviceDetailList : []
             */

            private Object createBy;
            private Object createName;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private int id;
            private int storegeInId;
            private Object inNum;
            private int deviceId;
            private String deviceName;
            private String deviceType;
            private String deviceLeixin;
            private String deviceCompany;
            private int deviceNum;
            private double devicePrice;
            private String imeiStatus;
            private List<tstoregeInDeviceDetailListBean> tstoregeInDeviceDetailList;

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public Object getCreateName() {
                return createName;
            }

            public void setCreateName(Object createName) {
                this.createName = createName;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStoregeInId() {
                return storegeInId;
            }

            public void setStoregeInId(int storegeInId) {
                this.storegeInId = storegeInId;
            }

            public Object getInNum() {
                return inNum;
            }

            public void setInNum(Object inNum) {
                this.inNum = inNum;
            }

            public int getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(int deviceId) {
                this.deviceId = deviceId;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public String getDeviceLeixin() {
                return deviceLeixin;
            }

            public void setDeviceLeixin(String deviceLeixin) {
                this.deviceLeixin = deviceLeixin;
            }

            public String getDeviceCompany() {
                return deviceCompany;
            }

            public void setDeviceCompany(String deviceCompany) {
                this.deviceCompany = deviceCompany;
            }

            public int getDeviceNum() {
                return deviceNum;
            }

            public void setDeviceNum(int deviceNum) {
                this.deviceNum = deviceNum;
            }

            public double getDevicePrice() {
                return devicePrice;
            }

            public void setDevicePrice(double devicePrice) {
                this.devicePrice = devicePrice;
            }

            public String getImeiStatus() {
                return imeiStatus;
            }

            public void setImeiStatus(String imeiStatus) {
                this.imeiStatus = imeiStatus;
            }

            public List<tstoregeInDeviceDetailListBean> getTstoregeInDeviceDetailList() {
                return tstoregeInDeviceDetailList;
            }

            public void setTstoregeInDeviceDetailList(List<tstoregeInDeviceDetailListBean> tstoregeInDeviceDetailList) {
                this.tstoregeInDeviceDetailList = tstoregeInDeviceDetailList;
            }

            public static class tstoregeInDeviceDetailListBean implements Serializable {
                public String getCreateBy() {
                    return createBy;
                }

                public void setCreateBy(String createBy) {
                    this.createBy = createBy;
                }

                public String getCreateName() {
                    return createName;
                }

                public void setCreateName(String createName) {
                    this.createName = createName;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public Object getUpdateBy() {
                    return updateBy;
                }

                public void setUpdateBy(Object updateBy) {
                    this.updateBy = updateBy;
                }

                public String getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                    this.updateTime = updateTime;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getDeviceId() {
                    return deviceId;
                }

                public void setDeviceId(int deviceId) {
                    this.deviceId = deviceId;
                }

                public int getStoregeInDeviceId() {
                    return storegeInDeviceId;
                }

                public void setStoregeInDeviceId(int storegeInDeviceId) {
                    this.storegeInDeviceId = storegeInDeviceId;
                }

                public String getImei() {
                    return imei;
                }

                public void setImei(String imei) {
                    this.imei = imei;
                }

                public int getNum() {
                    return num;
                }

                public void setNum(int num) {
                    this.num = num;
                }

                private String createBy;
                private String createName;
                private String createTime;
                private Object updateBy;
                private String updateTime;
                private String remark;
                private int id;
                private int deviceId;
                private int storegeInDeviceId;
                private String imei;
                private int num;
            }

        }
    }
}
