package com.example.terminal.bean;

import java.util.List;

public class StocktakingListBean {

    /**
     * total : 3
     * rows : [{"createBy":1,"createName":null,"createTime":"2024-12-20 18:24:18","updateBy":143,"updateTime":"2024-12-26 16:09:30","remark":null,"id":1,"name":"测试","storeHouseId":3,"storeHouseName":"测试库房","checkUserId":"2","checkUserName":"普通用户","checkDate":"2024-12-20","status":"2","checkFlag":2,"statusName":"已提交","checkFlagName":"异常","delFlag":"0","deptId":null,"tcheckDeviceList":[{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":5,"checkId":1,"deviceId":null,"deviceNo":"4_1_3","deviceName":"定位终端","deviceType":"AXK-V6","deviceLeixing":"1","deviceLeixingName":null,"deviceCompany":"素泰","deviceNum":4,"checkNum":4,"imeiStatus":"1","deviceDetailList":[{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":1,"checkDeviceId":5,"imei":"11","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":2,"checkDeviceId":5,"imei":"12","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":3,"checkDeviceId":5,"imei":"1","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":4,"checkDeviceId":5,"imei":"6","num":1,"errorStatus":"未盘点","ackFlag":0}]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":6,"checkId":1,"deviceId":null,"deviceNo":"4_2_3","deviceName":"定位终端","deviceType":"AXK-V6","deviceLeixing":"2","deviceLeixingName":null,"deviceCompany":"素泰","deviceNum":1,"checkNum":1,"imeiStatus":"1","deviceDetailList":[{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":5,"checkDeviceId":6,"imei":"5","num":1,"errorStatus":"未盘点","ackFlag":0}]}]},{"createBy":143,"createName":null,"createTime":"2024-12-26 16:09:21","updateBy":null,"updateTime":null,"remark":null,"id":2,"name":"11","storeHouseId":3,"storeHouseName":"测试库房","checkUserId":"2","checkUserName":"普通用户","checkDate":"2024-12-26","status":"2","checkFlag":0,"statusName":"已提交","checkFlagName":"未校验","delFlag":"0","deptId":null,"tcheckDeviceList":[{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":3,"checkId":2,"deviceId":null,"deviceNo":"4_1_3","deviceName":"定位终端","deviceType":"AXK-V6","deviceLeixing":"1","deviceLeixingName":null,"deviceCompany":"素泰","deviceNum":null,"checkNum":4,"imeiStatus":"1","deviceDetailList":[]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":4,"checkId":2,"deviceId":null,"deviceNo":"4_2_3","deviceName":"定位终端","deviceType":"AXK-V6","deviceLeixing":"2","deviceLeixingName":null,"deviceCompany":"素泰","deviceNum":null,"checkNum":1,"imeiStatus":"1","deviceDetailList":[]}]},{"createBy":143,"createName":null,"createTime":"2024-12-26 16:10:11","updateBy":null,"updateTime":null,"remark":null,"id":3,"name":"测试001","storeHouseId":3,"storeHouseName":"测试库房","checkUserId":"100,2","checkUserName":"普通用户,rhj","checkDate":"2024-12-26","status":"2","checkFlag":2,"statusName":"已提交","checkFlagName":"异常","delFlag":"0","deptId":null,"tcheckDeviceList":[{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":7,"checkId":3,"deviceId":null,"deviceNo":"4_1_3","deviceName":"定位终端","deviceType":"AXK-V6","deviceLeixing":"1","deviceLeixingName":null,"deviceCompany":"素泰","deviceNum":4,"checkNum":4,"imeiStatus":"1","deviceDetailList":[{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:10:10","updateBy":null,"updateTime":null,"remark":null,"id":6,"checkDeviceId":7,"imei":"11","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:10:10","updateBy":null,"updateTime":null,"remark":null,"id":7,"checkDeviceId":7,"imei":"12","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:10:10","updateBy":null,"updateTime":null,"remark":null,"id":8,"checkDeviceId":7,"imei":"1","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:10:10","updateBy":null,"updateTime":null,"remark":null,"id":9,"checkDeviceId":7,"imei":"6","num":1,"errorStatus":"未盘点","ackFlag":0}]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":8,"checkId":3,"deviceId":null,"deviceNo":"4_2_3","deviceName":"定位终端","deviceType":"AXK-V6","deviceLeixing":"2","deviceLeixingName":null,"deviceCompany":"素泰","deviceNum":1,"checkNum":1,"imeiStatus":"1","deviceDetailList":[{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:10:10","updateBy":null,"updateTime":null,"remark":null,"id":10,"checkDeviceId":8,"imei":"5","num":1,"errorStatus":"未盘点","ackFlag":0}]}]}]
     * code : 200
     * msg : 查询成功
     */

    private int total;
    private int code;
    private String msg;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * createBy : 1
         * createName : null
         * createTime : 2024-12-20 18:24:18
         * updateBy : 143
         * updateTime : 2024-12-26 16:09:30
         * remark : null
         * id : 1
         * name : 测试
         * storeHouseId : 3
         * storeHouseName : 测试库房
         * checkUserId : 2
         * checkUserName : 普通用户
         * checkDate : 2024-12-20
         * status : 2
         * checkFlag : 2
         * statusName : 已提交
         * checkFlagName : 异常
         * delFlag : 0
         * deptId : null
         * tcheckDeviceList : [{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":5,"checkId":1,"deviceId":null,"deviceNo":"4_1_3","deviceName":"定位终端","deviceType":"AXK-V6","deviceLeixing":"1","deviceLeixingName":null,"deviceCompany":"素泰","deviceNum":4,"checkNum":4,"imeiStatus":"1","deviceDetailList":[{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":1,"checkDeviceId":5,"imei":"11","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":2,"checkDeviceId":5,"imei":"12","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":3,"checkDeviceId":5,"imei":"1","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":4,"checkDeviceId":5,"imei":"6","num":1,"errorStatus":"未盘点","ackFlag":0}]},{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":6,"checkId":1,"deviceId":null,"deviceNo":"4_2_3","deviceName":"定位终端","deviceType":"AXK-V6","deviceLeixing":"2","deviceLeixingName":null,"deviceCompany":"素泰","deviceNum":1,"checkNum":1,"imeiStatus":"1","deviceDetailList":[{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":5,"checkDeviceId":6,"imei":"5","num":1,"errorStatus":"未盘点","ackFlag":0}]}]
         */

        private int createBy;
        private Object createName;
        private String createTime;
        private int updateBy;
        private String updateTime;
        private Object remark;
        private int id;
        private String name;
        private int storeHouseId;
        private String storeHouseName;
        private String checkUserId;
        private String checkUserName;
        private String checkDate;
        private String status;
        private int checkFlag;
        private String statusName;
        private String checkFlagName;
        private String delFlag;
        private Object deptId;
        private List<TcheckDeviceListBean> tcheckDeviceList;

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public Object getCreateName() {
            return createName;
        }

        public void setCreateName(Object createName) {
            this.createName = createName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getStoreHouseId() {
            return storeHouseId;
        }

        public void setStoreHouseId(int storeHouseId) {
            this.storeHouseId = storeHouseId;
        }

        public String getStoreHouseName() {
            return storeHouseName;
        }

        public void setStoreHouseName(String storeHouseName) {
            this.storeHouseName = storeHouseName;
        }

        public String getCheckUserId() {
            return checkUserId;
        }

        public void setCheckUserId(String checkUserId) {
            this.checkUserId = checkUserId;
        }

        public String getCheckUserName() {
            return checkUserName;
        }

        public void setCheckUserName(String checkUserName) {
            this.checkUserName = checkUserName;
        }

        public String getCheckDate() {
            return checkDate;
        }

        public void setCheckDate(String checkDate) {
            this.checkDate = checkDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getCheckFlag() {
            return checkFlag;
        }

        public void setCheckFlag(int checkFlag) {
            this.checkFlag = checkFlag;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getCheckFlagName() {
            return checkFlagName;
        }

        public void setCheckFlagName(String checkFlagName) {
            this.checkFlagName = checkFlagName;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public Object getDeptId() {
            return deptId;
        }

        public void setDeptId(Object deptId) {
            this.deptId = deptId;
        }

        public List<TcheckDeviceListBean> getTcheckDeviceList() {
            return tcheckDeviceList;
        }

        public void setTcheckDeviceList(List<TcheckDeviceListBean> tcheckDeviceList) {
            this.tcheckDeviceList = tcheckDeviceList;
        }

        public static class TcheckDeviceListBean {
            /**
             * createBy : null
             * createName : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * id : 5
             * checkId : 1
             * deviceId : null
             * deviceNo : 4_1_3
             * deviceName : 定位终端
             * deviceType : AXK-V6
             * deviceLeixing : 1
             * deviceLeixingName : null
             * deviceCompany : 素泰
             * deviceNum : 4
             * checkNum : 4
             * imeiStatus : 1
             * deviceDetailList : [{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":1,"checkDeviceId":5,"imei":"11","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":2,"checkDeviceId":5,"imei":"12","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":3,"checkDeviceId":5,"imei":"1","num":1,"errorStatus":"未盘点","ackFlag":0},{"createBy":143,"createName":"ceshi001","createTime":"2024-12-26 16:09:29","updateBy":null,"updateTime":null,"remark":null,"id":4,"checkDeviceId":5,"imei":"6","num":1,"errorStatus":"未盘点","ackFlag":0}]
             */

            private Object createBy;
            private Object createName;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private int id;
            private int checkId;
            private Object deviceId;
            private String deviceNo;
            private String deviceName;
            private String deviceType;
            private String deviceLeixing;
            private Object deviceLeixingName;
            private String deviceCompany;
            private int deviceNum;
            private int checkNum;
            private String imeiStatus;
            private List<DeviceDetailListBean> deviceDetailList;

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

            public int getCheckId() {
                return checkId;
            }

            public void setCheckId(int checkId) {
                this.checkId = checkId;
            }

            public Object getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(Object deviceId) {
                this.deviceId = deviceId;
            }

            public String getDeviceNo() {
                return deviceNo;
            }

            public void setDeviceNo(String deviceNo) {
                this.deviceNo = deviceNo;
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

            public String getDeviceLeixing() {
                return deviceLeixing;
            }

            public void setDeviceLeixing(String deviceLeixing) {
                this.deviceLeixing = deviceLeixing;
            }

            public Object getDeviceLeixingName() {
                return deviceLeixingName;
            }

            public void setDeviceLeixingName(Object deviceLeixingName) {
                this.deviceLeixingName = deviceLeixingName;
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

            public int getCheckNum() {
                return checkNum;
            }

            public void setCheckNum(int checkNum) {
                this.checkNum = checkNum;
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

            public static class DeviceDetailListBean {
                /**
                 * createBy : 143
                 * createName : ceshi001
                 * createTime : 2024-12-26 16:09:29
                 * updateBy : null
                 * updateTime : null
                 * remark : null
                 * id : 1
                 * checkDeviceId : 5
                 * imei : 11
                 * num : 1
                 * errorStatus : 未盘点
                 * ackFlag : 0
                 */

                private int createBy;
                private String createName;
                private String createTime;
                private Object updateBy;
                private Object updateTime;
                private Object remark;
                private int id;
                private int checkDeviceId;
                private String imei;
                private int num;
                private String errorStatus;
                private int ackFlag;

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

                public int getCheckDeviceId() {
                    return checkDeviceId;
                }

                public void setCheckDeviceId(int checkDeviceId) {
                    this.checkDeviceId = checkDeviceId;
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

                public String getErrorStatus() {
                    return errorStatus;
                }

                public void setErrorStatus(String errorStatus) {
                    this.errorStatus = errorStatus;
                }

                public int getAckFlag() {
                    return ackFlag;
                }

                public void setAckFlag(int ackFlag) {
                    this.ackFlag = ackFlag;
                }
            }
        }
    }
}
