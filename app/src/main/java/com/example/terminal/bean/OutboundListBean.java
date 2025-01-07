package com.example.terminal.bean;

import java.util.List;

public class OutboundListBean {

    /**
     * total : 5
     * rows : [{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:48:37","updateBy":"1","updateTime":"2023-03-12 12:49:19","remark":null,"id":6,"flowDataId":24,"deptId":null,"deviceBorrowId":null,"type":"3","typeName":"借用出库","orderName":null,"storeHouseName":"测试库房","applyDate":"2023-03-12","applyInfo":"1","individuals":null,"status":"5","storeHouseId":3,"inStoreHouseId":null,"inStoreHouseName":null,"orderId":null,"operUserId":1,"operUserName":"","delayDate":"2023-03-13","courierNumber":null,"outFile":"","arrs":["完成"],"delFlag":"0","rejectUser":null,"rejectUserName":null,"rejectTime":null,"rejectRemark":"","startTime":null,"endTime":null,"filtration":null,"tstoregeOutDeviceList":null},{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-12 12:47:15","updateBy":"1","updateTime":"2023-03-12 12:47:55","remark":null,"id":5,"flowDataId":24,"deptId":null,"deviceBorrowId":null,"type":"1","typeName":"新品新件出库","orderName":null,"storeHouseName":"测试库房","applyDate":"2023-03-12","applyInfo":"1","individuals":null,"status":"5","storeHouseId":3,"inStoreHouseId":null,"inStoreHouseName":null,"orderId":null,"operUserId":1,"operUserName":"","delayDate":null,"courierNumber":null,"outFile":"","arrs":["完成"],"delFlag":"0","rejectUser":null,"rejectUserName":null,"rejectTime":null,"rejectRemark":"","startTime":null,"endTime":null,"filtration":null,"tstoregeOutDeviceList":null},{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-10 17:51:27","updateBy":"1","updateTime":"2023-03-10 17:51:43","remark":null,"id":4,"flowDataId":24,"deptId":null,"deviceBorrowId":null,"type":"3","typeName":"借用出库","orderName":null,"storeHouseName":"测试库房","applyDate":"2023-03-10","applyInfo":"1","individuals":null,"status":"5","storeHouseId":3,"inStoreHouseId":null,"inStoreHouseName":null,"orderId":null,"operUserId":1,"operUserName":"","delayDate":"2023-03-10","courierNumber":null,"outFile":"","arrs":["完成"],"delFlag":"0","rejectUser":null,"rejectUserName":null,"rejectTime":null,"rejectRemark":"","startTime":null,"endTime":null,"filtration":null,"tstoregeOutDeviceList":null},{"createBy":"1","createName":"系统超级管理员","createTime":"2023-03-10 17:46:20","updateBy":"1","updateTime":"2023-03-10 17:46:52","remark":null,"id":3,"flowDataId":24,"deptId":null,"deviceBorrowId":null,"type":"3","typeName":"借用出库","orderName":null,"storeHouseName":"测试库房","applyDate":"2023-03-10","applyInfo":"1","individuals":null,"status":"5","storeHouseId":3,"inStoreHouseId":null,"inStoreHouseName":null,"orderId":null,"operUserId":1,"operUserName":"","delayDate":"2023-03-10","courierNumber":null,"outFile":"","arrs":["完成"],"delFlag":"0","rejectUser":null,"rejectUserName":null,"rejectTime":null,"rejectRemark":"","startTime":null,"endTime":null,"filtration":null,"tstoregeOutDeviceList":null},{"createBy":"133","createName":"孙昭华","createTime":"2023-03-10 17:36:10","updateBy":"133","updateTime":"2023-03-10 17:37:49","remark":null,"id":1,"flowDataId":24,"deptId":null,"deviceBorrowId":null,"type":"3","typeName":"借用出库","orderName":null,"storeHouseName":"测试库房","applyDate":"2023-03-10","applyInfo":"1","individuals":null,"status":"5","storeHouseId":3,"inStoreHouseId":null,"inStoreHouseName":null,"orderId":null,"operUserId":133,"operUserName":"","delayDate":"2023-03-10","courierNumber":null,"outFile":"","arrs":["完成"],"delFlag":"0","rejectUser":null,"rejectUserName":null,"rejectTime":null,"rejectRemark":"","startTime":null,"endTime":null,"filtration":null,"tstoregeOutDeviceList":null}]
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
         * tstoregeOutDeviceList : null
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
        private Object tstoregeOutDeviceList;
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

        public Object getTstoregeOutDeviceList() {
            return tstoregeOutDeviceList;
        }

        public void setTstoregeOutDeviceList(Object tstoregeOutDeviceList) {
            this.tstoregeOutDeviceList = tstoregeOutDeviceList;
        }

        public List<String> getArrs() {
            return arrs;
        }

        public void setArrs(List<String> arrs) {
            this.arrs = arrs;
        }
    }
}
