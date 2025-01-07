package com.example.terminal.bean;

import java.io.Serializable;
import java.util.List;

public class OutboudListDetailsBean  implements Serializable {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:48:37","updateBy":"1","updateTime":"2023-03-12 12:49:19","remark":null,"id":6,"flowDataId":24,"deptId":null,"deviceBorrowId":null,"type":"3","typeName":"借用出库","orderName":null,"storeHouseName":"测试库房","applyDate":"2023-03-12","applyInfo":"1","individuals":null,"status":"5","storeHouseId":3,"inStoreHouseId":null,"inStoreHouseName":null,"orderId":null,"operUserId":1,"operUserName":"","delayDate":"2023-03-13","courierNumber":null,"outFile":"","arrs":["完成"],"delFlag":"0","rejectUser":null,"rejectUserName":null,"rejectTime":null,"rejectRemark":"","startTime":null,"endTime":null,"filtration":null,"tstoregeOutDeviceList":[{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":10,"storegeOutId":6,"outNum":null,"deviceId":4,"deviceName":"定位终端","deviceType":"AXK-V6","deviceLeixin":"1","deviceCompany":"素泰","deviceNum":3,"devicePrice":0,"imeiStatus":"1","tstoregeOutDeviceDetailList":[{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:49:14","updateBy":null,"updateTime":"2023-03-12 12:51:41","remark":"1","id":7,"deviceId":4,"storegeOutDeviceId":10,"imei":"5","status":"1","imeiOld":null,"borrow":"1","num":1},{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:49:14","updateBy":null,"updateTime":"2023-03-12 12:51:41","remark":"2","id":8,"deviceId":4,"storegeOutDeviceId":10,"imei":"10","status":"3","imeiOld":"6","borrow":"1","num":1},{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:49:14","updateBy":null,"updateTime":"2023-03-12 12:51:41","remark":null,"id":9,"deviceId":4,"storegeOutDeviceId":10,"imei":"1","status":"0","imeiOld":null,"borrow":"1","num":1}]}]}
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

    public static class DataBean  implements Serializable {
        /**
         * createBy : 1
         * createName : 系统超级管理员
         * createTime : 2023-03-12 12:48:37
         * updateBy : 1
         * updateTime : 2023-03-12 12:49:19
         * remark : null
         * id : 6
         * flowDataId : 24
         * deptId : null
         * deviceBorrowId : null
         * type : 3
         * typeName : 借用出库
         * orderName : null
         * storeHouseName : 测试库房
         * applyDate : 2023-03-12
         * applyInfo : 1
         * individuals : null
         * status : 5
         * storeHouseId : 3
         * inStoreHouseId : null
         * inStoreHouseName : null
         * orderId : null
         * operUserId : 1
         * operUserName :
         * delayDate : 2023-03-13
         * courierNumber : null
         * outFile :
         * arrs : ["完成"]
         * delFlag : 0
         * rejectUser : null
         * rejectUserName : null
         * rejectTime : null
         * rejectRemark :
         * startTime : null
         * endTime : null
         * filtration : null
         * tstoregeOutDeviceList : [{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"id":10,"storegeOutId":6,"outNum":null,"deviceId":4,"deviceName":"定位终端","deviceType":"AXK-V6","deviceLeixin":"1","deviceCompany":"素泰","deviceNum":3,"devicePrice":0,"imeiStatus":"1","tstoregeOutDeviceDetailList":[{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:49:14","updateBy":null,"updateTime":"2023-03-12 12:51:41","remark":"1","id":7,"deviceId":4,"storegeOutDeviceId":10,"imei":"5","status":"1","imeiOld":null,"borrow":"1","num":1},{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:49:14","updateBy":null,"updateTime":"2023-03-12 12:51:41","remark":"2","id":8,"deviceId":4,"storegeOutDeviceId":10,"imei":"10","status":"3","imeiOld":"6","borrow":"1","num":1},{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:49:14","updateBy":null,"updateTime":"2023-03-12 12:51:41","remark":null,"id":9,"deviceId":4,"storegeOutDeviceId":10,"imei":"1","status":"0","imeiOld":null,"borrow":"1","num":1}]}]
         */

        private String createBy;
        private String createName;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Object remark;
        private int id;
        private int flowDataId;
        private Object deptId;
        private Object deviceBorrowId;
        private String type;
        private String typeName;
        private Object orderName;
        private String storeHouseName;
        private String applyDate;
        private String applyInfo;
        private Object individuals;
        private String status;
        private int storeHouseId;
        private Object inStoreHouseId;
        private Object inStoreHouseName;
        private Object orderId;
        private int operUserId;
        private String operUserName;
        private String delayDate;
        private Object courierNumber;
        private String outFile;
        private String delFlag;
        private Object rejectUser;
        private Object rejectUserName;
        private Object rejectTime;
        private String rejectRemark;
        private Object startTime;
        private Object endTime;
        private Object filtration;
        private List<String> arrs;
        private List<DataBean.TstoregeOutDeviceListBean> tstoregeOutDeviceList;

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

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
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

        public int getFlowDataId() {
            return flowDataId;
        }

        public void setFlowDataId(int flowDataId) {
            this.flowDataId = flowDataId;
        }

        public Object getDeptId() {
            return deptId;
        }

        public void setDeptId(Object deptId) {
            this.deptId = deptId;
        }

        public Object getDeviceBorrowId() {
            return deviceBorrowId;
        }

        public void setDeviceBorrowId(Object deviceBorrowId) {
            this.deviceBorrowId = deviceBorrowId;
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

        public Object getIndividuals() {
            return individuals;
        }

        public void setIndividuals(Object individuals) {
            this.individuals = individuals;
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

        public Object getInStoreHouseId() {
            return inStoreHouseId;
        }

        public void setInStoreHouseId(Object inStoreHouseId) {
            this.inStoreHouseId = inStoreHouseId;
        }

        public Object getInStoreHouseName() {
            return inStoreHouseName;
        }

        public void setInStoreHouseName(Object inStoreHouseName) {
            this.inStoreHouseName = inStoreHouseName;
        }

        public Object getOrderId() {
            return orderId;
        }

        public void setOrderId(Object orderId) {
            this.orderId = orderId;
        }

        public int getOperUserId() {
            return operUserId;
        }

        public void setOperUserId(int operUserId) {
            this.operUserId = operUserId;
        }

        public String getOperUserName() {
            return operUserName;
        }

        public void setOperUserName(String operUserName) {
            this.operUserName = operUserName;
        }

        public String getDelayDate() {
            return delayDate;
        }

        public void setDelayDate(String delayDate) {
            this.delayDate = delayDate;
        }

        public Object getCourierNumber() {
            return courierNumber;
        }

        public void setCourierNumber(Object courierNumber) {
            this.courierNumber = courierNumber;
        }

        public String getOutFile() {
            return outFile;
        }

        public void setOutFile(String outFile) {
            this.outFile = outFile;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
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

        public String getRejectRemark() {
            return rejectRemark;
        }

        public void setRejectRemark(String rejectRemark) {
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

        public Object getFiltration() {
            return filtration;
        }

        public void setFiltration(Object filtration) {
            this.filtration = filtration;
        }

        public List<String> getArrs() {
            return arrs;
        }

        public void setArrs(List<String> arrs) {
            this.arrs = arrs;
        }

        public List<DataBean.TstoregeOutDeviceListBean> getTstoregeOutDeviceList() {
            return tstoregeOutDeviceList;
        }

        public void setTstoregeOutDeviceList(List<DataBean.TstoregeOutDeviceListBean> tstoregeOutDeviceList) {
            this.tstoregeOutDeviceList = tstoregeOutDeviceList;
        }

        public static class TstoregeOutDeviceListBean  implements Serializable {
            /**
             * createBy : null
             * createName : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * id : 10
             * storegeOutId : 6
             * outNum : null
             * deviceId : 4
             * deviceName : 定位终端
             * deviceType : AXK-V6
             * deviceLeixin : 1
             * deviceCompany : 素泰
             * deviceNum : 3
             * devicePrice : 0.0
             * imeiStatus : 1
             * tstoregeOutDeviceDetailList : [{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:49:14","updateBy":null,"updateTime":"2023-03-12 12:51:41","remark":"1","id":7,"deviceId":4,"storegeOutDeviceId":10,"imei":"5","status":"1","imeiOld":null,"borrow":"1","num":1},{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:49:14","updateBy":null,"updateTime":"2023-03-12 12:51:41","remark":"2","id":8,"deviceId":4,"storegeOutDeviceId":10,"imei":"10","status":"3","imeiOld":"6","borrow":"1","num":1},{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:49:14","updateBy":null,"updateTime":"2023-03-12 12:51:41","remark":null,"id":9,"deviceId":4,"storegeOutDeviceId":10,"imei":"1","status":"0","imeiOld":null,"borrow":"1","num":1}]
             */

            private Object createBy;
            private Object createName;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private int id;
            private int storegeOutId;
            private Object outNum;
            private int deviceId;
            private String deviceName;
            private String deviceType;
            private String deviceLeixin;
            private String deviceCompany;
            private int deviceNum;
            private double devicePrice;
            private String imeiStatus;
            private List<DataBean.TstoregeOutDeviceListBean.TstoregeOutDeviceDetailListBean> tstoregeOutDeviceDetailList;

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

            public int getStoregeOutId() {
                return storegeOutId;
            }

            public void setStoregeOutId(int storegeOutId) {
                this.storegeOutId = storegeOutId;
            }

            public Object getOutNum() {
                return outNum;
            }

            public void setOutNum(Object outNum) {
                this.outNum = outNum;
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

            public List<DataBean.TstoregeOutDeviceListBean.TstoregeOutDeviceDetailListBean> getTstoregeOutDeviceDetailList() {
                return tstoregeOutDeviceDetailList;
            }

            public void setTstoregeOutDeviceDetailList(List<DataBean.TstoregeOutDeviceListBean.TstoregeOutDeviceDetailListBean> tstoregeOutDeviceDetailList) {
                this.tstoregeOutDeviceDetailList = tstoregeOutDeviceDetailList;
            }

            public static class TstoregeOutDeviceDetailListBean  implements Serializable {
                /**
                 * createBy : 1
                 * createName : 系统超级管理员
                 * createTime : 2023-03-12 12:49:14
                 * updateBy : null
                 * updateTime : 2023-03-12 12:51:41
                 * remark : 1
                 * id : 7
                 * deviceId : 4
                 * storegeOutDeviceId : 10
                 * imei : 5
                 * status : 1
                 * imeiOld : null
                 * borrow : 1
                 * num : 1
                 */

                private String createBy;
                private String createName;
                private String createTime;
                private Object updateBy;
                private String updateTime;
                private String remark;
                private int id;
                private int deviceId;
                private int storegeOutDeviceId;
                private String imei;
                private String status;
                private Object imeiOld;
                private String borrow;
                private int num;

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

                public int getStoregeOutDeviceId() {
                    return storegeOutDeviceId;
                }

                public void setStoregeOutDeviceId(int storegeOutDeviceId) {
                    this.storegeOutDeviceId = storegeOutDeviceId;
                }

                public String getImei() {
                    return imei;
                }

                public void setImei(String imei) {
                    this.imei = imei;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public Object getImeiOld() {
                    return imeiOld;
                }

                public void setImeiOld(Object imeiOld) {
                    this.imeiOld = imeiOld;
                }

                public String getBorrow() {
                    return borrow;
                }

                public void setBorrow(String borrow) {
                    this.borrow = borrow;
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
