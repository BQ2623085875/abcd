package com.example.terminal.test;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;


import java.text.DecimalFormat;

/**
 * author : King
 * date   : 2023/11/1019:28
 * desc   : 定位点数据
 */
public class Locations implements Parcelable {

    //id
    private long id;
    //通讯方式类别  0:未知，1：北斗，2:4G
    private int FT;
    //终端当前版本号终端当前版本号）
    private String appVersions;
    //终端地址码（手机号后六位）
    private String HX;
    //经度
    private double LO;
    //纬度
    private double LA;
    //速度
    private float SP;
    //高度
    private double HE;
    //方向
    private float CO;
    //定位时间
    private String TE;
    //终端注册号
    private String RE;
    //时间戳
    private Long TS;
    //是不是离线缓存数据 0: 在线，1 : 离线
    private String isCache;
    // 定位信息
    private String rkInfo;

    private String sendTime;

    /**
     * 车辆信息
     */
    // 车辆id
    private long vehicleId;
    // 车牌号
    private String FN;
    // 车辆类型
    private long type;
    // 车辆类型名称
    private String vehicleTypeName;
    // 部门名称
    private String deptName;
    // 部门id
    private long deptId;
    // 车辆高度
    private double height;

    public Locations(String rkInfo) {
        setRkInfo(rkInfo);
    }


    public void setRkInfo(String rkInfo) {
        this.rkInfo = rkInfo;
        String[] location = rkInfo.split(",");


        //经度
        LO = TextUtils.isEmpty(location[5]) ? 0 : Double.parseDouble(location[5].substring(0, 3)) + Double.parseDouble(location[5].substring(3)) / 60;
        //纬度
        LA = TextUtils.isEmpty(location[3]) ? 0 : Double.parseDouble(location[3].substring(0, 2)) + Double.parseDouble(location[3].substring(2)) / 60;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getFT() {
        return FT;
    }

    public void setFT(int FT) {
        this.FT = FT;
    }

    public String getAppVersions() {
        return appVersions;
    }

    public void setAppVersions(String appVersions) {
        this.appVersions = appVersions;
    }

    public String getHX() {
        return HX;
    }

    public void setHX(String HX) {
        this.HX = HX;
    }

    public double getLO() {
        return LO;
    }

    public void setLO(double LO) {
        this.LO = LO;
    }

    public double getLA() {
        return LA;
    }

    public void setLA(double LA) {
        this.LA = LA;
    }

    public float getSP() {
        return SP;
    }

    public void setSP(float SP) {
        this.SP = SP;
    }

    public double getHE() {
        return HE;
    }

    public void setHE(double HE) {
        this.HE = HE;
    }

    public float getCO() {
        return CO;
    }

    public void setCO(float CO) {
        this.CO = CO;
    }

    public String getTE() {
        return TE;
    }

    public void setTE(String TE) {
        this.TE = TE;
    }

    public String getRE() {
        return RE;
    }

    public void setRE(String RE) {
        this.RE = RE;
    }

    public Long getTS() {
        return TS;
    }

    public void setTS(Long TS) {
        this.TS = TS;
    }

    public String getIsCache() {
        return isCache;
    }

    public void setIsCache(int isCache) {
//        if(isCache == 1)
//            this.id = 0 ;
        this.isCache = String.valueOf(isCache);
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getRkInfo() {
        return rkInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getFN() {
        return FN;
    }

    public void setFN(String FN) {
        this.FN = FN;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public long getDeptId() {
        return deptId;
    }

    public void setDeptId(long deptId) {
        this.deptId = deptId;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeInt(this.FT);
        dest.writeString(this.appVersions);
        dest.writeString(this.HX);
        dest.writeDouble(this.LO);
        dest.writeDouble(this.LA);
        dest.writeFloat(this.SP);
        dest.writeDouble(this.HE);
        dest.writeFloat(this.CO);
        dest.writeString(this.TE);
        dest.writeString(this.RE);
        dest.writeValue(this.TS);
        dest.writeString(this.isCache);

        dest.writeLong(this.vehicleId);
        dest.writeString(this.FN);
        dest.writeLong(this.type);
        dest.writeString(this.vehicleTypeName);
        dest.writeString(this.deptName);
        dest.writeLong(this.deptId);
        dest.writeDouble(this.height);
    }

    protected Locations(Parcel in) {
        this.id = in.readLong();
        this.FT = in.readInt();
        this.appVersions = in.readString();
        this.HX = in.readString();
        this.LO = in.readDouble();
        this.LA = in.readDouble();
        this.SP = in.readFloat();
        this.HE = in.readDouble();
        this.CO = in.readFloat();
        this.TE = in.readString();
        this.RE = in.readString();
        this.TS = (Long) in.readValue(Long.class.getClassLoader());
        this.isCache = in.readString();

        this.vehicleId = in.readLong();
        this.FN = in.readString();
        this.type = in.readLong();
        this.vehicleTypeName = in.readString();
        this.deptName = in.readString();
        this.deptId = in.readLong();
        this.height = in.readDouble();
    }

    public static final Creator<Locations> CREATOR = new Creator<Locations>() {
        @Override
        public Locations createFromParcel(Parcel source) {
            return new Locations(source);
        }

        @Override
        public Locations[] newArray(int size) {
            return new Locations[size];
        }
    };
}
