package com.example.terminal.http;

import com.example.terminal.BuildConfig;

public interface Url {

    String BASE_URL = "http://" + BuildConfig.BASE_URL + ":" + BuildConfig.Port;

    String DOWN_APK_URL = "http://" + BuildConfig.BASE_URL + ":9000/";//"http://8.140.98.110:9000/"

    String MessageSocketUrl = "ws://" + BuildConfig.WEB_SOCKET_URL + ":9229/webSocket/";

    /**
     * 基础模块
     */
    String login = BASE_URL + "/login";

    String logOut = BASE_URL + "/auth/logout";

    /**
     * 个人信息
     */
    String UserProfile = BASE_URL + "/system/user/profile";

    /**
     * 入库管理列表
     */
    String getStoreList = BASE_URL + "/terminal/terminalIn/list";

    /**
     * 入库管理列表详情
     */
    String getStoreDetails = BASE_URL + "/terminal/terminalIn/";

}
