package com.example.terminal.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.terminal.R;
import com.example.terminal.global.Constant;
import com.example.terminal.listener.RecyclerView.RefreshAdapter;
import com.example.terminal.listener.RecyclerView.RefreshListener;
import com.example.terminal.util.ProgressUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzy.okgo.model.HttpParams;

import java.util.Map;


/**
 * @author : YanKing
 * @date : 2019/5/6  15:35
 * @Description : Fragment基类
 */

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected View mView;

    protected Intent mIntent;
    protected Bundle mBundle;

    protected Handler mHandler;
    protected Bundle savedInstanceState;

    protected Map<String , Object> valueMap ;
    protected HttpParams valueParams ;

    //当前页面是否显示过
    private boolean isVisibleToUser = true;

    // 当前页面
    protected int mCurrentPage ;

    // fragment创建
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        mActivity = getActivity();
        mHandler = new Handler();
    }

    // 处理fragment的布局
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null) {
            mView = View.inflate(mActivity, setContentView(), null);
            initView();
            initData();
            initConnect();
        } else {
            ViewParent parent = mView.getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                // 将childView从父view中移除
                group.removeView(mView);
            }
        }
        return mView;
    }

    /**
     * 子类必须实现初始化布局的方法
     *
     * @return 返回布局文件, 例如:R.layout.main_fragment
     */
    public abstract int setContentView();

    /**
     * 初始化布局控件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 连接数据
     */
    public void initConnect() {
    }

    public void onGlobalChange(boolean isShow){

    }

    /**
     * 扫描结果
     * @param code
     */
    public void onScanResult(String code){

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisibleToUser && isVisibleToUser) {
            this.isVisibleToUser = false;
            fragmentFirstVisible();
        }

        if (isVisibleToUser)
            fragmentVisible();
    }

    /**
     * 该页面显示出来
     */
    protected void fragmentVisible() {

    }

    /**
     * 该页面第一次显示出来
     */
    protected void fragmentFirstVisible() {

    }

    /**
     * 设置刷新
     * @param mRefreshLayout
     */
    protected void setRefreshListener(TwinklingRefreshLayout mRefreshLayout){
        mRefreshLayout.setOnRefreshListener(new RefreshAdapter(onRefreshListener));
        ProgressUtils.showProgress(mActivity);
    }

    /**
     * 刷新页面
     */
    protected RefreshListener onRefreshListener = new RefreshListener() {
        @Override
        protected void onRefresh() {
            ProgressUtils.showProgress(mActivity);
            initConnect();
        }
    };

    /**
     * act跳转
     *
     * @param it
     */
    public void turnToAct(Intent it) {
        ((BaseActivity)mActivity).turnToAct(it);

    }

    /**
     * act跳转
     *
     * @param it
     * @param flag
     */
    public void turnToAct(Intent it, boolean flag) {
        ((BaseActivity)mActivity).turnToAct(it , flag);
    }

    /**
     * act跳转
     *
     * @param target
     */
    public void turnToAct(Class<?> target) {
        ((BaseActivity)mActivity).turnToAct(target);
    }


    /**
     * act跳转
     *
     * @param target
     * @param flag
     */
    public void turnToAct(Class<?> target, boolean flag) {
        ((BaseActivity)mActivity).turnToAct(target , flag);
    }

    /**
     * act跳转
     */
    public void turnActForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
    }

    /**
     * act跳转
     */
    public void turnActForResult(Class<?> mClass, int requestCode) {
        mIntent = new Intent(mActivity, mClass);
        startActivityForResult(mIntent, requestCode);
        mActivity.overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
    }

    /**
     * act跳转
     */
    public void turnActForResult(Class<?> mClass) {
        mIntent = new Intent(mActivity, mClass);
        startActivityForResult(mIntent, Constant.REQUEST_CODE);
        mActivity.overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
    }

    /**
     * act跳转
     */
    public void turnActForResult(Intent intent) {
        startActivityForResult(intent, Constant.REQUEST_CODE);
        mActivity.overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Constant.RESULT_CODE && requestCode == Constant.REQUEST_CODE){
            initConnect();
        }
    }
}
