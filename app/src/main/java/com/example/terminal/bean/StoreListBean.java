package com.example.terminal.bean;

import java.util.List;

public class StoreListBean {

    /**
     * total : 11
     * rows : [{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-10 17:33:24","updateBy":"1","updateTime":"2023-03-10 17:34:28","remark":null,"id":1,"flowDataId":24,"type":"1","typeName":"新品新件入库","orderName":null,"storeHouseName":"测试库房","storeHouseId":3,"orderId":null,"applyDate":"2023-03-10","applyInfo":"1","status":"4","operUserName":null,"operUserId":1,"delFlag":"0","fileUrl":"","arrs":["完成"],"deviceBorrowId":null,"deptId":103,"rejectUser":null,"rejectUserName":null,"rejectTime":null,"rejectRemark":"","startTime":null,"endTime":null,"storegeOutId":null,"tstoregeInDevice":null}]
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
         * createName : 系统超级管理员
         * createTime : 2023-03-10 17:33:24
         * updateBy : 1
         * updateTime : 2023-03-10 17:34:28
         * remark : null
         * id : 1
         * flowDataId : 24
         * type : 1
         * typeName : 新品新件入库
         * orderName : null
         * storeHouseName : 测试库房
         * storeHouseId : 3
         * orderId : null
         * applyDate : 2023-03-10
         * applyInfo : 1
         * status : 4
         * operUserName : null
         * operUserId : 1
         * delFlag : 0
         * fileUrl :
         * arrs : ["完成"]
         * deviceBorrowId : null
         * deptId : 103
         * rejectUser : null
         * rejectUserName : null
         * rejectTime : null
         * rejectRemark :
         * startTime : null
         * endTime : null
         * storegeOutId : null
         * tstoregeInDevice : null
         */

        private String createBy;
        private String createName;
        private String createTime;
        private String updateBy;
        private String updateTime;
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
        private Object operUserName;
        private int operUserId;
        private String delFlag;
        private String fileUrl;
        private Object deviceBorrowId;
        private int deptId;
        private Object rejectUser;
        private Object rejectUserName;
        private Object rejectTime;
        private String rejectRemark;
        private Object startTime;
        private Object endTime;
        private Object storegeOutId;
        private Object tstoregeInDevice;
        private List<String> arrs;

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

        public Object getOperUserName() {
            return operUserName;
        }

        public void setOperUserName(Object operUserName) {
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

        public Object getStoregeOutId() {
            return storegeOutId;
        }

        public void setStoregeOutId(Object storegeOutId) {
            this.storegeOutId = storegeOutId;
        }

        public Object getTstoregeInDevice() {
            return tstoregeInDevice;
        }

        public void setTstoregeInDevice(Object tstoregeInDevice) {
            this.tstoregeInDevice = tstoregeInDevice;
        }

        public List<String> getArrs() {
            return arrs;
        }

        public void setArrs(List<String> arrs) {
            this.arrs = arrs;
        }
    }
}
