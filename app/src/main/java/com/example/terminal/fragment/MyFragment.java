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
import com.example.terminal.util.ToastUtils;
import com.example.terminal.view.dialog.AlertDialog;

import java.io.File;

/**
 *
 */
public class MyFragment extends BaseFragment {

    private ImageView mIv_userlogo;
    private TextView mTv_Department,//部门
            mTv_Post,//岗位
            mTv_userName;//用户名称
    private RelativeLayout mRl_UpLogin, mRl_ClearCache;

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
        mRl_ClearCache = mView.findViewById(R.id.mRl_ClearCache);
    }

    @Override
    public void initData() {
        mRl_UpLogin.setOnClickListener(this::onClickSort);
        mRl_ClearCache.setOnClickListener(this::onClickSort);
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
            case R.id.mRl_ClearCache:
                clearCache();
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

    /**
     * 清除应用缓存
     */
    private void clearCache() {
        // 获取缓存目录
        File cacheDir = getActivity().getCacheDir();

        // 如果缓存目录存在且是一个目录
        if (cacheDir != null && cacheDir.isDirectory()) {
            // 递归删除缓存目录中的所有文件和子目录
            deleteDir(cacheDir);
        }

        // 可选：清除外部缓存（如果有的话）
        File externalCacheDir = getActivity().getExternalCacheDir();
        if (externalCacheDir != null && externalCacheDir.isDirectory()) {
            deleteDir(externalCacheDir);
        }

        // 通知用户缓存已清除（例如，通过Toast）
        ToastUtils.showToast("缓存已清除");
    }

    /**
     * 递归删除目录中的所有文件和子目录
     *
     * @param dir 要删除的目录
     * @return 是否成功删除
     */
    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        }
        // 如果是文件则直接删除
        return dir != null && dir.isFile() && dir.delete();
    }
}