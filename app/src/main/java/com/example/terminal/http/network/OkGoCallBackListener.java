package com.example.terminal.http.network;

import android.text.TextUtils;

import com.example.terminal.activity.LoginActivity;
import com.example.terminal.bean.CommonBean;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.global.MainApplication;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.JsonUtils;
import com.example.terminal.util.ProgressUtils;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * @author : YanKing
 * @date : 2019/6/11  15:38
 * @Description : 网络回调
 */
public abstract class OkGoCallBackListener extends StringCallback {

    private OkGoBackListener listener;
    private boolean dismissSingleDialog;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public OkGoCallBackListener(OkGoBackListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSuccess(Response<String> response) {

        ProgressUtils.dismissProgress();

        if (dismissSingleDialog)
            ProgressUtils.dismissSingleProgress();

        if (response != null && !TextUtils.isEmpty(response.body())) {
            try {

                Headers headers = response.headers();
                String dateHeader = headers.get("Date");

                try {
                    SimpleDateFormat oldFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
                    oldFormat.setLenient(false);
                    Date date = oldFormat.parse(dateHeader);

                    SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String formattedDate = newFormat.format(date);
                    ConstantInfo.ServerDates = formattedDate;

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Request request = response.getRawResponse().request();
                HttpUrl httpurl = request.url();
                URL url = httpurl.url();
                String method = request.method();

                CommonUtils.printJson("TAG--", response.body(), "--->" + method + "  URL: " + url);

                CommonBean commonBean = JsonUtils.parseJsonToBean(response.body(), CommonBean.class);

                if (commonBean.getCode() == Constant.CODE_SUCCESS) {
                    // 请求成功
                    onSuccess(response.body());
                } else if (commonBean.getCode() == Constant.LOGIN_OUT) {
                    ConstantInfo.TOKEN = null;
                    listener.onError();
                    listener.getActivity().turnToAct(LoginActivity.class);
                    MainApplication.finishActivity();
                } else if (commonBean.getCode() == Constant.CODE_ERROR) {
                    // 请求失败
                    listener.onWrong(commonBean.getMsg());
                    listener.onError();
                } else {
                    // 请求失败
                    listener.onWrong(commonBean.getMsg());
                    listener.onError();
                }
            } catch (Exception e) {
                e.printStackTrace();
                listener.onChangeError(e.getMessage());
                listener.onError();
            }
        } else {
            listener.onDataEmpty();
            listener.onError();
        }
    }

    public OkGoCallBackListener(OkGoBackListener listener, boolean dismissSingleDialog) {
        this.listener = listener;
        this.dismissSingleDialog = dismissSingleDialog;
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        ProgressUtils.dismissProgress();
        ProgressUtils.dismissSingleProgress();
        listener.onError(response.message());
        listener.onError();
    }

    @Override
    public void onFinish() {
    }

    public abstract void onSuccess(String body);
}
