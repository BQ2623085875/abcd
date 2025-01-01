package com.example.terminal.http;

import com.example.terminal.BuildConfig;

public interface Url {

    String BASE_URL = "http://" + BuildConfig.BASE_URL + ":" + BuildConfig.Port;

    String DOWN_APK_URL = "http://" + BuildConfig.BASE_URL + ":9000/";//"http://8.140.98.110:9000/"

    String MessageSocketUrl = "ws://" + BuildConfig.WEB_SOCKET_URL + ":9229/webSocket/";

    /**
     * 基础模块
     */
    String check_update = BASE_URL + "/config/config/apkVersion/getCurrentVersion";

    String login = BASE_URL + "/auth/login";

    String logOut = BASE_URL + "/auth/logout";

    /**
     * 修改密码
     */
    String resetPwd = BASE_URL + "/system/user/resetPwd";

    /**
     * 消息确定参数
     */
    String MessageReceive = BASE_URL + "/message/api/message/receiveEvent";

    /**
     * 消息忽略参数
     */
    String MessageNeglect = BASE_URL + "/message/api/message/neglectToast";

    /**
     * 消息列表
     */
    String PersonalMessage = BASE_URL + "/message/api/message/personalMessage";

    /**
     * 消息详情，操作
     */
    String MessageDetailsReply = BASE_URL + "/message/api/message/messageDetailsReply";

    /**
     * 获取菜单
     */
    String getRouters = BASE_URL + "/system/menu/getRouters";

    /**
     * 获取用户信息
     */
    String getInfo = BASE_URL + "/system/user/getInfo";

    /**
     * 航班列表
     */
    String getFlightList = BASE_URL + "/flight/biz/flight/listInBus";

    /**
     * 获取安检通道
     */
    String getCheckChannel = BASE_URL + "/config/config/channel/list";

    /**
     * 获取区域列表
     */
    String getStorageAreaList = BASE_URL + "/config/config/warehouseArea/list";

    /**
     * 获取拖斗信息列表
     */
    String getBroadTugList = BASE_URL + "/config/config/transportBoxboard/list";

    /**
     * 获取托盘列表
     */
    String getBroadPalletList = BASE_URL + "/config/config/transportContainer/list";

    /**
     * 进港集装器列表
     */
    String getEnterPortContainerList = BASE_URL + "/anomaly/biz/containerUnit/inwardList";

    /**
     * 出港集装器列表
     */
    String getLeavePortContainerList = BASE_URL + "/anomaly/biz/containerUnit/outwardSonList";

    /**
     * 获取未出库集装器列表
     */
    String getEmptyContainerList = BASE_URL + "/anomaly/biz/containerUnit/emptyList";

    /**
     * 获取未出库集装器列表
     */
    String setContainerFlightEmpty = BASE_URL + "/anomaly/biz/containerUnit/empty";

    /**
     * 获取未出库集装器列表
     */
    String getAllContainerList = BASE_URL + "/anomaly/biz/containerUnit/listBizContainerUnit";

    /**
     * 新增集装器
     */
    String addTug = BASE_URL + "/config/config/transportBoxboard";

    /**
     * 编辑集装器
     */
    String resetTug = BASE_URL + "/config/config/transportBoxboard";

    /**
     * 新增托盘
     */
    String addPallet = BASE_URL + "/config/config/transportContainer";

    /**
     * 编辑托盘
     */
    String resetPallet = BASE_URL + "/config/config/transportContainer";

    /**
     * 新增集装器
     */
    String addContainer = BASE_URL + "/anomaly/biz/containerUnit/addList";

    /**
     * 编辑集装器
     */
    String resetContainer = BASE_URL + "/anomaly/biz/containerUnit";

    /**
     * 更新出港航班号
     */
    String renewContainer = BASE_URL + "/anomaly/biz/containerUnit/renew";

    /**
     * 获取集装器信息列表
     */
    String getBroadContainerList = BASE_URL + "/anomaly/biz/containerUnit/list1";

    /**
     * 获取集装器信息列表
     */
    String getInwardContainerList = BASE_URL + "/anomaly/biz/containerUnit/list";

    /**
     * 获取拖斗+集装器+托盘
     */
    String getContainerAllList = BASE_URL + "/config/config/transportBoxboardType/containerAllList";

    /**
     * 字段表
     */
    String getDictList = BASE_URL + "/system/dict/data/type/";

    /**
     * 上传文件
     */
    String uploadFile = BASE_URL + "/file/file/upload";

    /**
     * 获取异常信息列表
     */
    String getUnusualDataList = BASE_URL + "/anomaly/anomalyRecord/listPage";

    /**
     * 获取异常信息列表
     */
    String deleteUnusual = BASE_URL + "/anomaly/anomalyRecord/";

    /**
     * 获取异常信息列表
     */
    String saveUnusualData = BASE_URL + "/anomaly/anomalyRecord";

    /**
     * 锁住页面条目
     */
    String checkPageLock = BASE_URL + "/auth/getPageLock";

    /**
     * 解锁
     */
    String securePageLock = BASE_URL + "/outwardEnsure/outward/storage/rmRedis";

    /**
     * 根据地磅获取重量
     */
    String getBridgeWeigh = BASE_URL + "/outwardEnsure/weighbridge/";

    /**
     * 货物查询列表
     */
    String getFreightInquiryollectCheckList = BASE_URL + "/cargo/biz/cargonPosition/getPositionList";
    String getFreightInquiryollectCheckDetails = BASE_URL + "/record/biz/customerServiceLog/list";
    String getFreightInquiryollectDetails = BASE_URL + "/outwardEnsure/outward/storage/pc/list";

    /**
     * 收运核查列表
     */
    String getLeavePortCollectCheckList = BASE_URL + "/outwardEnsure/outward/storage/checkBeforeInstorage/list";

    /**
     * 收运核查详情
     */
    String getLeavePortCollectCheckDetails = BASE_URL + "/outwardEnsure/outward/storage/checkBeforeInstorage/detail";

    /**
     * 保存核查详情
     */
    String saveLeavePortCollectCheck = BASE_URL + "/outwardEnsure/outward/storage/checkBeforeInstorage/save";

    /**
     * 出港入库列表
     */
    String getLeavePortWarehousingList = BASE_URL + "/outwardEnsure/outward/storage/webList";

    /**
     * 出港入库详情
     */
    String getLeavePortWarehousingDetails = BASE_URL + "/outwardEnsure/outward/storage/info/";

    /**
     * 出港入库信息保存
     */
    String saveLeavePortWarehousingDetails = BASE_URL + "/outwardEnsure/outward/storage/saveStorage";

    /**
     * 组板列表查询
     */
    String getLeavePortGroupBoardList = BASE_URL + "/outwardEnsure/outward/storage/get/assembleList";

    /**
     * 组板详情查询
     */
    String getLeavePortGroupBoardDetails = BASE_URL + "/outwardEnsure/outward/storage/get/assembleDetail";

    /**
     * 保存组板详情
     */
    String saveLeavePortGroupBoardDetails = BASE_URL + "/outwardEnsure/outward/storage/save/assembleDetail";

    /**
     * 多舱位组板完成
     */
    String saveLeavePortGroupBoardShippingDetails = BASE_URL + "/outwardEnsure/outward/storage/update/shippingBillOutboundState/";

    /**
     * 复重列表查询
     */
    String getLeavePortDuplicateList = BASE_URL + "/outwardEnsure/outward/outbound/list";

    /**
     * 复重
     */
    String getLeavePortDuplicateDetails = BASE_URL + "/outwardEnsure/outward/outbound/detailList";
    ;

    /**
     * 复核查询
     */
    String getLeavePortCheckDetails = BASE_URL + "/outwardEnsure/outboundReview/info";

    /**
     * 复核保存
     */
    String saveLeavePortCheckDetails = BASE_URL + "/outwardEnsure/outboundReview/save";

    /**
     * 获取斗重
     */
    String getTransportWeight = BASE_URL + "/outwardEnsure/outward/outbound/updateOstWeight";

    /**
     * 保存复重详情
     */
    String saveLeavePortDuplicateDetails = BASE_URL + "/outwardEnsure/outward/outbound/saveOutbound";


    /**
     * 出港入库司机交接列表查询
     */
    String getLeavePortHandoverList = BASE_URL + "/outwardEnsure/outward/storage/getHandover";

    /**
     * 出港入库司机交接保存
     */
    String saveLeavePortHandoverData = BASE_URL + "/outwardEnsure/outward/storage/saveHandover";

    /**
     * 退运列表查询
     */
    String getLeavePortReturnList = BASE_URL + "/outwardEnsure/outward/storage/get/returnedList";

    /**
     * 确认退运
     */
    String saveLeavePortReturn = BASE_URL + "/outwardEnsure/outward/storage/confirmReturn";

    /**
     * 出港称重列表
     */
    String getLeavePortWeighting = BASE_URL + "/outwardEnsure/outward/weighing/outboundWeighingList";

    /**
     * 出港称重详情
     */
    String getLeavePortWeightingDetails = BASE_URL + "/outwardEnsure/outward/weighing/outboundWeighingDatail";

    /**
     * 出港称重保存
     */
    String saveLeavePortWeightingDetails = BASE_URL + "/outwardEnsure/outward/weighing/save";

    /**
     * 出港打印装机单
     */
    String printInstallationSign = BASE_URL + "/outwardEnsure/outward/tagout/printPad";

    /**
     * 进港入库列表
     */
    String getEnterPortWarehousingList = BASE_URL + "/inwardEnsure/inward/storage/instorageList";

    /**
     * 进港入库详情
     */
    String getEnterPortWarehousingDetails = BASE_URL + "/inwardEnsure/inward/storage/instorageDetail";

    /**
     * 进港运单详情
     */
    String getEnterPortOrderDetails = BASE_URL + "/inward/inward/order/";

    /**
     * 进港运单事故签证
     */
    String getDamageLoss = BASE_URL + "/anomaly/damage/loss/";

    /**
     * 进港入库详情保存
     */
    String saveEnterPortWarehousingDetails = BASE_URL + "/inwardEnsure/inward/storage/saveDetail";

    /**
     * 进港入库司机交接列表查询
     */
    String getEnterPortHandoverList = BASE_URL + "/inwardEnsure/inward/storage/getHandover";

    /**
     * 进港入库司机交接保存
     */
    String saveEnterPortHandoverData = BASE_URL + "/inwardEnsure/inward/storage/saveHandover";

    /**
     * 进港出库列表
     */
    String getEnterPortPickingList = BASE_URL + "/inwardEnsure/inward/storageOut/getAwaitList";

    /**
     * 进港出库详情
     */
    String getEnterPortPickingDetails = BASE_URL + "/inwardEnsure/inward/storageOut/getAwaitDetail";

    /**
     * 进港出库详情保存
     */
    String saveEnterPortPickingDetails = BASE_URL + "/inwardEnsure/inward/storageOut/saveAwaitInwardOut";

    /**
     * 进港提货列表
     */
    String getEnterPortTakeList = BASE_URL + "/inwardEnsure/inward/storageOut/getList";

    /**
     * 进港提货详情
     */
    String getEnterPortTakeDetails = BASE_URL + "/inwardEnsure/inward/storageOut/getDetail";

    /**
     * 进港分配提货通道保存
     */
    String saveEnterPortChannel = BASE_URL + "/inwardEnsure/inward/storageOut/saveDeliveryChannel";

    /**
     * 进港提货详情保存
     */
    String saveEnterPortTakeDetails = BASE_URL + "/inwardEnsure/inward/storageOut/saveInwardOut";

    /**
     * 进港盘库列表
     */
    String getEnterStoreroomInventoryList = BASE_URL + "/inwardEnsure/inward/storageCheck/getCheckList";

    /**
     * 新建进港盘库
     */
    String getEnterStoreroomInventoryNew = BASE_URL + "/inwardEnsure/inward/storageCheck/new";

    /**
     * 进港盘库详情
     */
    String getEnterStoreroomInventoryDetail = BASE_URL + "/inwardEnsure/inward/storageCheckDetail/detailList";

    /**
     * 保存进港盘库信息
     */
    String saveEnterStoreroomInventoryData = BASE_URL + "/inwardEnsure/inward/storageCheckDetail/save";


    /**
     * 出港盘库列表
     */
    String getLeaveStoreroomInventoryList = BASE_URL + "/outwardEnsure/outensure/outward/storageCheck/getCheckList";

    /**
     * 新建出港盘库
     */
    String getLeaveStoreroomInventoryNew = BASE_URL + "/outwardEnsure/outensure/outward/storageCheck/new";

    /**
     * 出港盘库详情
     */
    String getLeaveStoreroomInventoryDetail = BASE_URL + "/outwardEnsure/outensure/outward/storageCheckDetail/detailList";

    /**
     * 保存出港盘库信息
     */
    String saveLeaveStoreroomInventoryData = BASE_URL + "/outwardEnsure/outensure/outward/storageCheckDetail/save";

    /**
     * 获取复重区间集合
     */
    String getWeighSectionList = BASE_URL + "/config/config/weightrange/list";

    /**
     * 进港出库
     */
    String inWardUpdateOrderToOut = BASE_URL + "/inward/inward/order/updateOrderToOut/";

    /**
     * 进港出库
     */
    String outWardUpdateOrderToOut = BASE_URL + "/outward/outward/order/outWardEnsureOut/";

    /**
     * 进港快件交接列表
     */
    String getEnterExpressDeliveryList = BASE_URL + "/inwardEnsure/inward/expressDelivery/list";

    /**
     * 进港快件交接保存
     */
    String saveEnterExpressDeliverySave = BASE_URL + "/inwardEnsure/inward/expressDelivery/saveVisa";
}
