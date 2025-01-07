package com.example.terminal.bean;

import java.io.Serializable;
import java.util.List;

public class StocktakingDetailsBean implements Serializable {

    /**
     * code : 200
     * data : {"checkDate":"2024-12-20","checkFlag":2,"checkUserId":"2","createBy":1,"createTime":"2024-12-20 18:24:18","delFlag":"0","id":1,"name":"测试","status":"2","storeHouseId":3,"tcheckDeviceList":[{"checkId":1,"checkNum":4,"deviceCompany":"素泰","deviceDetailList":[{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":1,"imei":"11","num":1},{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":2,"imei":"12","num":1},{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":3,"imei":"1","num":1},{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":4,"imei":"6","num":1}],"deviceLeixing":"1","deviceName":"定位终端","deviceNo":"4_1_3","deviceNum":4,"deviceType":"AXK-V6","id":5,"imeiStatus":"1"},{"checkId":1,"checkNum":1,"deviceCompany":"素泰","deviceDetailList":[{"ackFlag":0,"checkDeviceId":6,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":5,"imei":"5","num":1}],"deviceLeixing":"2","deviceName":"定位终端","deviceNo":"4_2_3","deviceNum":1,"deviceType":"AXK-V6","id":6,"imeiStatus":"1"}],"updateBy":143,"updateTime":"2024-12-26 16:09:30"}
     * msg : 操作成功
     */

    private int code;
    private DataBean data;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean implements Serializable {
        /**
         * checkDate : 2024-12-20
         * checkFlag : 2
         * checkUserId : 2
         * createBy : 1
         * createTime : 2024-12-20 18:24:18
         * delFlag : 0
         * id : 1
         * name : 测试
         * status : 2
         * storeHouseId : 3
         * tcheckDeviceList : [{"checkId":1,"checkNum":4,"deviceCompany":"素泰","deviceDetailList":[{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":1,"imei":"11","num":1},{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":2,"imei":"12","num":1},{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":3,"imei":"1","num":1},{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":4,"imei":"6","num":1}],"deviceLeixing":"1","deviceName":"定位终端","deviceNo":"4_1_3","deviceNum":4,"deviceType":"AXK-V6","id":5,"imeiStatus":"1"},{"checkId":1,"checkNum":1,"deviceCompany":"素泰","deviceDetailList":[{"ackFlag":0,"checkDeviceId":6,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":5,"imei":"5","num":1}],"deviceLeixing":"2","deviceName":"定位终端","deviceNo":"4_2_3","deviceNum":1,"deviceType":"AXK-V6","id":6,"imeiStatus":"1"}]
         * updateBy : 143
         * updateTime : 2024-12-26 16:09:30
         */

        private String checkDate;
        private int checkFlag;
        private String checkUserId;
        private int createBy;
        private String createTime;
        private String delFlag;
        private int id;
        private String name;
        private String status;
        private int storeHouseId;
        private int updateBy;
        private String updateTime;
        private List<TcheckDeviceListBean> tcheckDeviceList;

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public int getCheckFlag() {
            return checkFlag;
        }

        public void setCheckFlag(int checkFlag) {
            this.checkFlag = checkFlag;
        }

        public String getCheckUserId() {
            return checkUserId;
        }

        public void setCheckUserId(String checkUserId) {
            this.checkUserId = checkUserId;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStoreHouseId() {
            return storeHouseId;
        }

        public void setStoreHouseId(int storeHouseId) {
            this.storeHouseId = storeHouseId;
        }

        public int getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(int updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public List<TcheckDeviceListBean> getTcheckDeviceList() {
            return tcheckDeviceList;
        }

        public void setTcheckDeviceList(List<TcheckDeviceListBean> tcheckDeviceList) {
            this.tcheckDeviceList = tcheckDeviceList;
        }

        public static class TcheckDeviceListBean implements Serializable {
            /**
             * checkId : 1
             * checkNum : 4
             * deviceCompany : 素泰
             * deviceDetailList : [{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":1,"imei":"11","num":1},{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":2,"imei":"12","num":1},{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":3,"imei":"1","num":1},{"ackFlag":0,"checkDeviceId":5,"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","errorStatus":"未盘点","id":4,"imei":"6","num":1}]
             * deviceLeixing : 1
             * deviceName : 定位终端
             * deviceNo : 4_1_3
             * deviceNum : 4
             * deviceType : AXK-V6
             * id : 5
             * imeiStatus : 1
             */

            private int checkId;
            private int checkNum;
            private String deviceCompany;
            private String deviceLeixing;
            private String deviceName;
            private String deviceNo;
            private int deviceNum;
            private String deviceType;
            private int id;
            private String imeiStatus;
            private List<DeviceDetailListBean> deviceDetailList;

            public int getCheckId() {
                return checkId;
            }

            public void setCheckId(int checkId) {
                this.checkId = checkId;
            }

            public int getCheckNum() {
                return checkNum;
            }

            public void setCheckNum(int checkNum) {
                this.checkNum = checkNum;
            }

            public String getDeviceCompany() {
                return deviceCompany;
            }

            public void setDeviceCompany(String deviceCompany) {
                this.deviceCompany = deviceCompany;
            }

            public String getDeviceLeixing() {
                return deviceLeixing;
            }

            public void setDeviceLeixing(String deviceLeixing) {
                this.deviceLeixing = deviceLeixing;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public String getDeviceNo() {
                return deviceNo;
            }

            public void setDeviceNo(String deviceNo) {
                this.deviceNo = deviceNo;
            }

            public int getDeviceNum() {
                return deviceNum;
            }

            public void setDeviceNum(int deviceNum) {
                this.deviceNum = deviceNum;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImeiStatus() {
                return imeiStatus;
            }

            public void setImeiStatus(String imeiStatus) {
                this.imeiStatus = imeiStatus;
            }

            public List<DeviceDetailListBean> getDeviceDetailList() {
                return deviceDetailList;
            }

            public void setDeviceDetailList(List<DeviceDetailListBean> deviceDetailList) {
                this.deviceDetailList = deviceDetailList;
            }

            public static class DeviceDetailListBean implements Serializable {
                /**
                 * ackFlag : 0
                 * checkDeviceId : 5
                 * createBy : 143
                 * createName : ceshi001
                 * createTime : 2024-12-26 16:09:29
                 * errorStatus : 未盘点
                 * id : 1
                 * imei : 11
                 * num : 1
                 */

                private int ackFlag;
                private int checkDeviceId;
                private int createBy;
                private String createName;
                private String createTime;
                private String errorStatus;
                private int id;
                private String imei;
                private int num;
                private String remark;

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public int getAckFlag() {
                    return ackFlag;
                }

                public void setAckFlag(int ackFlag) {
                    this.ackFlag = ackFlag;
                }

                public int getCheckDeviceId() {
                    return checkDeviceId;
                }

                public void setCheckDeviceId(int checkDeviceId) {
                    this.checkDeviceId = checkDeviceId;
                }

                public int getCreateBy() {
                    return createBy;
                }

                public void setCreateBy(int createBy) {
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

                public String getErrorStatus() {
                    return errorStatus;
                }

                public void setErrorStatus(String errorStatus) {
                    this.errorStatus = errorStatus;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
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
            }
        }
    }
}
