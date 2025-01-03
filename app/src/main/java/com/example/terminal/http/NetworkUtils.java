package com.example.terminal.http;

import com.example.terminal.BuildConfig;
import com.example.terminal.bean.LoginBean;
import com.example.terminal.bean.StoreListBean;
import com.example.terminal.bean.StoreListDetailsBean;
import com.example.terminal.bean.UserProfileBean;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.http.network.OkGoCallBackListener;
import com.example.terminal.util.JsonUtils;
import com.example.terminal.util.ProgressUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author :
 * @date :
 * @Description : 网络连接
 */
public class NetworkUtils {

    /**
     * 登录接口
     */
    public static void login(final OkGoBackListener listener, Map<String, Object> valueMap) {

        ProgressUtils.showProgress(listener.getActivity());

        OkGo.<String>post(Url.login)
                .tag(listener.getActivity())
                .upJson(new JSONObject(valueMap))
                .execute(new OkGoCallBackListener(listener) {
                    @Override
                    public void onSuccess(String body) {
                        listener.onSuccess(JsonUtils.parseJsonToBean(body, LoginBean.class));
                    }
                });
    }

    /**
     * 退出登录接口
     */
    public static void logOut(final OkGoBackListener listener, Map<String, Object> valueMap) {

        ProgressUtils.showProgress(listener.getActivity());

        OkGo.<String>post(Url.login)
                .tag(listener.getActivity())
                .upJson(new JSONObject(valueMap))
                .execute(new OkGoCallBackListener(listener) {
                    @Override
                    public void onSuccess(String body) {
                        listener.onSuccess(JsonUtils.parseJsonToBean(body, LoginBean.class));
                    }
                });
    }

    /**
     * 个人信息
     */
    public static void getUserProfile(final OkGoBackListener listener) {

        ProgressUtils.showProgress(listener.getActivity());

        OkGo.<String>get(Url.UserProfile)
                .headers(Constant.Authorization, ConstantInfo.TOKEN)
                .tag(listener.getActivity())
                .execute(new OkGoCallBackListener(listener) {
                    @Override
                    public void onSuccess(String body) {
                        listener.onSuccess(JsonUtils.parseJsonToBean(body, UserProfileBean.class));
                    }
                });
    }

    /**
     * 入库办理列表
     */
    public static void getStoreList(final OkGoBackListener listener, HttpParams valueParams) {

        ProgressUtils.showProgress(listener.getActivity());

        OkGo.<String>get(Url.getStoreList)
                .headers(Constant.Authorization, ConstantInfo.TOKEN)
                .params(valueParams)
                .tag(listener.getActivity())
                .execute(new OkGoCallBackListener(listener) {
                    @Override
                    public void onSuccess(String body) {
                        listener.onSuccess(JsonUtils.parseJsonToBean(body, StoreListBean.class));
                    }
                });
    }

    public static void getStoreDetails(final OkGoBackListener listener, int id) {
        ProgressUtils.showProgress(listener.getActivity());

        OkGo.<String>get(Url.getStoreDetails + id)
                .headers(Constant.Authorization, ConstantInfo.TOKEN)
                .tag(listener.getActivity())
                .execute(new OkGoCallBackListener(listener) {
                    @Override
                    public void onSuccess(String body) {
                        listener.onSuccess(JsonUtils.parseJsonToBean(body, StoreListDetailsBean.class));
                    }
                });
    }
}