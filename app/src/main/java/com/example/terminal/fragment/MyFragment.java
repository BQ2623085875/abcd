package com.example.terminal.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.terminal.R;
import com.example.terminal.base.BaseFragment;
import com.example.terminal.bean.UserProfileBean;
import com.example.terminal.bean.UserProfileBean;
import com.example.terminal.http.GlideLoader;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.Url;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.view.dialog.AlertDialog;

/**
 *
 */
public class MyFragment extends BaseFragment {

    private ImageView mIv_userlogo;
    private TextView mTv_Department,//部门
            mTv_Post,//岗位
            mTv_userName;//用户名称
    private RelativeLayout mRl_UpLogin;

    private UserProfileBean userProfileBean;

    @Override
    public int setContentView() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        mIv_userlogo = mView.findViewById(R.id.mIv_userlogo);
        mTv_Department = mView.findViewById(R.id.mTv_Department);
        mTv_Post = mView.findViewById(R.id.mTv_Post);
        mTv_userName = mView.findViewById(R.id.mTv_userName);
        mRl_UpLogin = mView.findViewById(R.id.mRl_UpLogin);
    }

    @Override
    public void initData() {
        mRl_UpLogin.setOnClickListener(this::onClickSort);
    }

    @Override
    public void initConnect() {
        NetworkUtils.getUserProfile(new OkGoBackListener(mActivity) {
            @Override
            public void onSuccess(Object body) {
                userProfileBean = (UserProfileBean) body;

                initViewData();
            }
        });
    }

    private void initViewData() {
        if (userProfileBean == null)
            return;

        GlideLoader.loader(Url.BASE_URL + userProfileBean.getData().getAvatar(), mIv_userlogo);

        mTv_userName.setText(userProfileBean.getData().getUserName());
        mTv_Department.setText(userProfileBean.getData().getDept().getDeptName());
        mTv_Post.setText(userProfileBean.getPostGroup());

    }

    public void onClickSort(View view) {
        switch (view.getId()) {
            case R.id.mRl_UpLogin:
                outLogin();
                break;

        }
    }

    private void outLogin() {
//        AlertDialog.getInstance(mActivity)
//                .setContentResource(R.string.dialog_user_login_out)
//                .setConfirmButton(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        NetworkUtils.logOut(new OkGoBackListener(mActivity) {
//                            @Override
//                            public void onSuccess(Object body) {
//                                ConstantInfo.TOKEN = null;
//                                onFinishAct(LoginActivity.class);
//                            }
//                        });
//                    }
//                }).setCancelButton(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                currentType = "";
//            }
//        }).show();
    }
}