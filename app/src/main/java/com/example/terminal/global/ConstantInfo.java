package com.example.terminal.global;

import android.os.Build;

import com.hjq.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * 保存信息
 */
public class ConstantInfo {

    // 登录Token
    public static String TOKEN;

    public static String USER_NAME = "username";

    public static int USER_ID = 0;

    public static int RefreshTime = 3;

    public static int StoreroomInventoryTime = 4;

    // 是否为大屏幕
    public static boolean notNW = !Constant.MODEL_NW.equals(Build.MODEL);

    // expires_in
    public static int EXPIRES_IN;

    // 获取权限
    public static String[] PermissionList = new String[]{Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_PHONE_STATE, Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE};

    public static List<String> UnusualGradleList = new ArrayList<String>() {
    };

    public static List<String> ContainerList = new ArrayList<String>() {
    };

    public static String SOCKET_KEY = "";

    public static boolean IS_ADMIN = false;

    public static List<String> MessageSocketList = new ArrayList<>();
    public static String MessageSocketSTR = "";

    public static String TEMPLE_START = "";

    /**
     * 异常类型
     */
    public static String UnusualType = "anomaly_type";

    // 进港入库
    public static String IN_STORAGE_IN = "inward_storage";
    // 进港提货
    public static String IN_STORAGE_OUT = "inward_storage_out";
    // 进港出库
    public static String IN_PICKING = "inward_await_storage_out";
    // 进港盘库
    public static String IN_STORAGE_CHECK = "inward_storage_check";

    // 出港收运核查
    public static String OUT_STORAGE_PRE_CHECK = "outward_pre_check";
    // 出港入库
    public static String OUT_STORAGE_IN = "outward_storage";
    // 出港复核
    public static String OUT_CHECK = "outward_check";
    // 出港组板
    public static String OUT_STORAGE_ASSEMBLE = "outward_assemble";
    // 出港复重·
    public static String OUT_STORAGE_OUT = "outward_storage_out";
    // 出港盘库
    public static String OUT_STORAGE_CHECK = "outward_storage_check";

    public static String CA_ORDER_NO_START = "999";

    public static String Enter_Container = "牛板";

    public static String WIDE_BODY = "W";

    public static List<String> mPermissionsList = new ArrayList<>();

    public static List<Integer> UnusualIDList = new ArrayList<>();

    public static String History_Start_Date = "2022-07-01";

    public static String History_End_Date = "2099-01-01";

    public static List<Integer> AdapterHeightList = new ArrayList<>();

    public static boolean isReturn = false;
    public static boolean IDS = false;
    public static String ModuleID = "";
    public static String StringID = "";
    public static int ID = 0;

    public static int InOutFlagType = 1;

    public static boolean isOneto = true;

    public static String Dates = "";
    public static String ServerDates = "";

    public static int IFGUOJI_TYPE = 0;

}
