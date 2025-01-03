package com.example.terminal.bean;

import java.util.List;

public class UserProfileBean {

    /**
     * msg : 操作成功
     * postGroup : 董事长
     * code : 200
     * data : {"createBy":"admin","createName":null,"createTime":"2022-12-08 09:33:26","updateBy":null,"updateTime":null,"remark":"管理员","userId":1,"deptId":103,"userName":"admin","nickName":"系统超级管理员","email":"admin@tms.com","phonenumber":"15888888888","sex":"0","avatar":"/profile/avatar/2022/12/22/blob_20221222145904A001.png","password":"$2a$10$r1w/X/fSXbEpoC0D3.luQ.PF1kzjkF87gvaPFzb9Fr/JXwI71ACCe","status":"0","delFlag":"0","loginIp":"222.129.137.64","loginDate":"2025-01-02T10:02:43.000+08:00","dept":{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"deptId":103,"parentId":101,"ancestors":"0,100,101","deptName":"项目管理部","orderNum":1,"leader":"系统管理员","phone":null,"email":null,"status":"0","delFlag":null,"parentName":null,"children":[]},"roles":[{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"roleId":1,"roleName":"超级管理员","roleKey":"admin","roleSort":1,"dataScope":"1","menuCheckStrictly":false,"deptCheckStrictly":false,"status":"0","delFlag":null,"flag":false,"menuIds":null,"deptIds":null,"permissions":null,"admin":true}],"roleIds":null,"postIds":null,"roleId":null,"admin":true}
     * roleGroup : 超级管理员
     */

    private String msg;
    private String postGroup;
    private int code;
    private DataBean data;
    private String roleGroup;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPostGroup() {
        return postGroup;
    }

    public void setPostGroup(String postGroup) {
        this.postGroup = postGroup;
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

    public String getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(String roleGroup) {
        this.roleGroup = roleGroup;
    }

    public static class DataBean {
        /**
         * createBy : admin
         * createName : null
         * createTime : 2022-12-08 09:33:26
         * updateBy : null
         * updateTime : null
         * remark : 管理员
         * userId : 1
         * deptId : 103
         * userName : admin
         * nickName : 系统超级管理员
         * email : admin@tms.com
         * phonenumber : 15888888888
         * sex : 0
         * avatar : /profile/avatar/2022/12/22/blob_20221222145904A001.png
         * password : $2a$10$r1w/X/fSXbEpoC0D3.luQ.PF1kzjkF87gvaPFzb9Fr/JXwI71ACCe
         * status : 0
         * delFlag : 0
         * loginIp : 222.129.137.64
         * loginDate : 2025-01-02T10:02:43.000+08:00
         * dept : {"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"deptId":103,"parentId":101,"ancestors":"0,100,101","deptName":"项目管理部","orderNum":1,"leader":"系统管理员","phone":null,"email":null,"status":"0","delFlag":null,"parentName":null,"children":[]}
         * roles : [{"createBy":null,"createName":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"roleId":1,"roleName":"超级管理员","roleKey":"admin","roleSort":1,"dataScope":"1","menuCheckStrictly":false,"deptCheckStrictly":false,"status":"0","delFlag":null,"flag":false,"menuIds":null,"deptIds":null,"permissions":null,"admin":true}]
         * roleIds : null
         * postIds : null
         * roleId : null
         * admin : true
         */

        private String createBy;
        private Object createName;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private String remark;
        private int userId;
        private int deptId;
        private String userName;
        private String nickName;
        private String email;
        private String phonenumber;
        private String sex;
        private String avatar;
        private String password;
        private String status;
        private String delFlag;
        private String loginIp;
        private String loginDate;
        private DeptBean dept;
        private Object roleIds;
        private Object postIds;
        private Object roleId;
        private boolean admin;
        private List<RolesBean> roles;

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(String loginDate) {
            this.loginDate = loginDate;
        }

        public DeptBean getDept() {
            return dept;
        }

        public void setDept(DeptBean dept) {
            this.dept = dept;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds = roleIds;
        }

        public Object getPostIds() {
            return postIds;
        }

        public void setPostIds(Object postIds) {
            this.postIds = postIds;
        }

        public Object getRoleId() {
            return roleId;
        }

        public void setRoleId(Object roleId) {
            this.roleId = roleId;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public List<RolesBean> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesBean> roles) {
            this.roles = roles;
        }

        public static class DeptBean {
            /**
             * createBy : null
             * createName : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * deptId : 103
             * parentId : 101
             * ancestors : 0,100,101
             * deptName : 项目管理部
             * orderNum : 1
             * leader : 系统管理员
             * phone : null
             * email : null
             * status : 0
             * delFlag : null
             * parentName : null
             * children : []
             */

            private Object createBy;
            private Object createName;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private int deptId;
            private int parentId;
            private String ancestors;
            private String deptName;
            private int orderNum;
            private String leader;
            private Object phone;
            private Object email;
            private String status;
            private Object delFlag;
            private Object parentName;
            private List<?> children;

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

            public int getDeptId() {
                return deptId;
            }

            public void setDeptId(int deptId) {
                this.deptId = deptId;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getAncestors() {
                return ancestors;
            }

            public void setAncestors(String ancestors) {
                this.ancestors = ancestors;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }

            public String getLeader() {
                return leader;
            }

            public void setLeader(String leader) {
                this.leader = leader;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(Object delFlag) {
                this.delFlag = delFlag;
            }

            public Object getParentName() {
                return parentName;
            }

            public void setParentName(Object parentName) {
                this.parentName = parentName;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }

        public static class RolesBean {
            /**
             * createBy : null
             * createName : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * roleId : 1
             * roleName : 超级管理员
             * roleKey : admin
             * roleSort : 1
             * dataScope : 1
             * menuCheckStrictly : false
             * deptCheckStrictly : false
             * status : 0
             * delFlag : null
             * flag : false
             * menuIds : null
             * deptIds : null
             * permissions : null
             * admin : true
             */

            private Object createBy;
            private Object createName;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private int roleId;
            private String roleName;
            private String roleKey;
            private int roleSort;
            private String dataScope;
            private boolean menuCheckStrictly;
            private boolean deptCheckStrictly;
            private String status;
            private Object delFlag;
            private boolean flag;
            private Object menuIds;
            private Object deptIds;
            private Object permissions;
            private boolean admin;

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

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getRoleKey() {
                return roleKey;
            }

            public void setRoleKey(String roleKey) {
                this.roleKey = roleKey;
            }

            public int getRoleSort() {
                return roleSort;
            }

            public void setRoleSort(int roleSort) {
                this.roleSort = roleSort;
            }

            public String getDataScope() {
                return dataScope;
            }

            public void setDataScope(String dataScope) {
                this.dataScope = dataScope;
            }

            public boolean isMenuCheckStrictly() {
                return menuCheckStrictly;
            }

            public void setMenuCheckStrictly(boolean menuCheckStrictly) {
                this.menuCheckStrictly = menuCheckStrictly;
            }

            public boolean isDeptCheckStrictly() {
                return deptCheckStrictly;
            }

            public void setDeptCheckStrictly(boolean deptCheckStrictly) {
                this.deptCheckStrictly = deptCheckStrictly;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(Object delFlag) {
                this.delFlag = delFlag;
            }

            public boolean isFlag() {
                return flag;
            }

            public void setFlag(boolean flag) {
                this.flag = flag;
            }

            public Object getMenuIds() {
                return menuIds;
            }

            public void setMenuIds(Object menuIds) {
                this.menuIds = menuIds;
            }

            public Object getDeptIds() {
                return deptIds;
            }

            public void setDeptIds(Object deptIds) {
                this.deptIds = deptIds;
            }

            public Object getPermissions() {
                return permissions;
            }

            public void setPermissions(Object permissions) {
                this.permissions = permissions;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }
        }
    }
}
