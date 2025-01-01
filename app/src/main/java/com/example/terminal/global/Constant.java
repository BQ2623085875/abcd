package com.example.terminal.global;

public interface Constant {

    String MODEL_NW = "NW10";
    String MODEL_M5 = "M5";

    String SP_NAME = "FlightTrans";

    String USER_NAME = "username";
    String USER_ID = "userId";
    String PASSWORD = "password";

    String Http = "http";
    String ID = "id";
    String DamageLossID = "damageLossId";
    String FlightID = "flightId";
    String Deap = "deap";
    String ShippingBillID = "shippingBillId";
    String OperationStatus = "operationStatus";
    String RETURNED_ID = "returnedId";
    String BIZ_ORDER_DETAIL_ID = "bizOrderDetailId";
    String BIZ_ORDER_ID = "bizOrderId";
    String Consignee_No = "consigneeNo";
    String Delivery_Channel = "deliveryChannel";
    String Source_Type = "sourceType";
    String Business_ID = "businessId";
    String ModuleID = "moudleId";
    String Type = "type";
    String ACT_SHAPE = "actshape";

    String Container_Code = "containerCode";
    String Container_Type = "containerType";
    String Query_Date = "queryDate";

    int Type_PRO = 2;
    int Type_DEV = 1;
    int Type_HET = 0;

    String PalletCode = "X000";

    String WasteMessageReceiver = "WasteMessage_receiver";
    String MessageReceiver = "message_receiver";
    String RefreshReceiver = "refresh_receiver";

    String Authorization = "Authorization";

    String InOutFlag = "inOutFlag";

    String File = "file";
    String Count = "count";
    String Number = "Number";
    String Weight = "weight";
    String Length = "length";
    String Width = "width";
    String Height = "height";
    String Volume = "volume";
    String VolumeResolve = "volumeResolve";
    String Position = "position";
    String State = "state";
    String WarehouseID = "warehouseId";
    String STATUS = "status";

    String Content = "content";
    String IsNumberInput = "isNumberInput";

    String AssembleDetailListVO = "assembleDetailListVO";

    String TurnType = "turnType";
    String Title = "title";

    String WASTE_INFO = "waste_info";

    String INFO = "info";
    String POSITION = "position";
    String VersionName = "versionName";
    String Value = "value";

    String IsEditor = "isEditor";
    String SelectState = "SelectState";
    String IsCA = "isCA";
    String ShippingCount = "ShippingCount";

    String Key = "key";
    String Key_Done = "done";
    String Key_Is_Done = "isDone";
    String Key_Outbound_State = "outboundState";
    String Key_State = "state";
    String Key_Create_Date = "createDate";
    String Key_Consignee_Date = "consigneeDate";
    String Key_Order_No = "orderNo";
    String Key_Check_Date = "checkDate";
    String Key_Storage_Check_ID = "storageCheckId";

    String Key_Flight_No = "flightNo";
    String Key_Flight_No2 = "flightNo2";

    String Key_Outward_Flight_No2 = "outwardFlightNo2";
    String Key_Outward_Fldt = "outwardFldt";
    String Key_Fldt = "fldt";
    String Key_Fid = "fid";
    String Key_Flight_Number = "flightNumber";
    String Key_Flight_Date = "flightDate";
    String Key_Order_Create_Date = "orderCreateDate";
    String Key_Order_Nos = "orderNos";
    String Key_Returned_Date = "returnedDate";

    String Key_Biz_Flight_VO = "bizFlightVO";

    String Key_Transport_Code = "transportCode";
    String Key_Transport_Type = "transportType";
    String Key_Transport_Weight = "transportWeight";

    String Key_SID = "sid";


    /*
     * 国际 国内 区分菜单
     * */
    //一级菜单
    String GJJGBZ = "GJJGBZ";
    //二级菜单
    String GJJGRK = "GJJGRK";
    String GJJGTH = "GJJGTH";
    String GJKJJJ = "GJKJJJ";
    String GJJGPK = "GJJGPK";
    //一级菜单
    String GJCGBZ = "GJCGBZ";
    //二级菜单
    String GJSYHC = "GJSYHC";
    String GJCGRK = "GJCGRK";
    String GJCGZB = "GJCGZB";
    String GJCGFZ = "GJCGFZ";
    String GJCGFH = "GJCGFH";
    String GJCGTY = "GJCGTY";
    String GJCGPK = "GJCGPK";
    //一级菜单
    String GJHWXX = "GJHWXX";
    //二级菜单
    String GJHWCX = "GJHWCX";//货物查询

    // 国内菜单
    String JGBZ = "JGBZ";

    String JGRK = "JGRK";
    String JGTH = "JGTH";
    String JGPK = "JGPK";
    String JGCK = "JGCK";
    String KJJJ = "KJJJ";

    String CGBZ = "CGBZ";

    String SYHC = "SYHC";
    String CGRK = "CGRK";
    String CGZB = "CGZB";
    String CGFZ = "CGFZ";
    String CGFH = "CGFH";
    String CGTY = "CGTY";
    String CGPK = "CGPK";
    String CGCZ = "CGCZ";

    String HWXX = "HWXX";

    String HWCX = "HWCX";//货物查询

    String YHGL = "YHGL";

    String XGMM = "XGMM";
    String TCDL = "TCDL";
    String BQXR = "BQXR";

    String CGRK_MODIFY = "CGRK.MODIFY";
    String CGFZ_MODIFY = "CGFZ.MODIFY";

    String ZDYTD = "ZDYTD";

    int SortNullPosition = 10001;

    int LeavePortWarehousing = 2;
    int LeavePortDuplicate = 3;
    int EnterPortTake = 4;
    int Leave_All = 5;

    int EnterPortStorage = 1;//国内进港
    int LeavePortStorage = 2;//国内出港

    // 请求码
    int REQUEST_CODE = 110;
    // 反馈码
    int RESULT_CODE = 112;
    // NFC反馈吗
    int RESULT_NFC_CODE = 113;
    // 反馈码
    int RESULT_REFRESH_CODE = 114;
    // 空板入库反馈码
    int RESULT_BLANK_REFRESH_CODE = 115;

    // 网络请求成功
    int CODE_SUCCESS = 200;
    int LOGIN_OUT = 401;
    int CODE_ERROR = 5000;


    int Be_Warehousing = 110;
    int In_Warehousing = 120;
    int Be_Picking = 122;
    int Be_Take = 130;
    int In_Take = 140;

    String RESULT = "result";

    /**
     * 开始扫描服务
     */
    String M5_Scan_Start_Service = "com.yunlian.start.scanner.service";
    String M5_Scan_Stop_Service = "com.yunlian.stop.scanner.service";

    // 开启/关闭扫描功能
    String M5_Scan_Start_Scan = "com.yunlian.scan.begin";

    // 监听扫描结果
    String M5_Scan_Result = "com.yunlian.scan.result";
    // 监听按键
    String M5_Click_Poc = "com.aoro.poc.key.down";
    String M5_Click_Ptt = "com.aoro.ptt.key.down";

    String Poc_Key = "poc_key";
    String Ptt_Key = "ptt_key";
    String Enable = "enable";

    int StatusCollectCheck = 10;
    int StatusLeaveWarehousing = 20;

    int PULL_OFF = 201;
    int Transport_Code = 1;
    int Prior_Mark = 1;

    int Leave_Port_Warehousing_Channel = 11;
    int Leave_Port_Duplicate_Channel = 12;
    int Leave_Port_Area = 13;
    int Port_Tug = 14;
    int Port_Pallet = 15;

    int Enter_Port_Take_Channel = 21;
    int Enter_Port_Area = 22;

    String Net_File_Path = "sdcard/hzx/StatusLog/";
    String Crash_File_Path = "sdcard/hzx/CrashLog/";
    int LOG_FILE_SAVE_DAYS = 3;

    //国内国际(I:国际  D：国内  M：混合)
    String International = "I";
    String Domestic = "D";
    String Mix = "M";


    String IF_GUOJI = "ifGuoJi";
}
