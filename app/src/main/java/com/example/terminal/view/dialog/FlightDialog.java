package com.example.terminal.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminal.R;
import com.example.terminal.adapter.FlightAdapter;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.bean.FlightBean.FlightData;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.EditTextWatcher;
import com.example.terminal.listener.GridItemDecoration;
import com.example.terminal.listener.OnItemClickListener;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.DensityUtils;
import com.example.terminal.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 航班弹框
 */
public class FlightDialog extends BaseDialog {

    private LinearLayout mLayoutBg;

    private EditText mEtSearch ;
    private ImageButton mIbClearSearch ;

    private RecyclerView mRlStoreContainer;//区域
    private Button mBtnCancel,//取消按钮
            mBtnConfirm;//确定按钮

    private List<FlightData> mNetFlightList , mFlightList;
    private FlightAdapter mFlightAdapter;

    private FlightData selectedFlight ;
    private List<FlightData> selectedFlightList;

    public static FlightDialog getInstance(Context mContext) {
        return new FlightDialog(mContext);
    }

    private FlightDialog(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private FlightDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_flight, null);

        // 获取自定义Dialog布局中的控件
        mLayoutBg = view.findViewById(R.id.mLayoutBg);

        mEtSearch = view.findViewById(R.id.mEtSearch);
        mIbClearSearch = view.findViewById(R.id.mIbClearSearch);

        mRlStoreContainer = view.findViewById(R.id.mRlStoreContainer);
        mBtnCancel = view.findViewById(R.id.mBtnCancel);
        mBtnConfirm = view.findViewById(R.id.mBtnConfirm);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小
        mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams( 4 * DensityUtils.getScreenWidth((Activity) mContext) / 5, 4 * DensityUtils.getScreenHeight((Activity) mContext) / 5));

        // 获取数据
        getFlightData();

        // 初始化搜索
        initSearch();

        //初始化取消按钮
        setButtonClickListener();
        //点击空白处可消失
        setCancelable(false);
        return this;
    }

    private FlightDialog getFlightData(){
        NetworkUtils.getFlightList(new OkGoBackListener((BaseActivity) mContext){
            @Override
            public void onSuccess(Object body) {
                mNetFlightList = (List<FlightData>) body;

                mFlightList = mNetFlightList ;

                initSelected();
                initAdapter();
            }
        });
        return this ;
    }

    public FlightDialog setSelectedFlight(FlightData flightData){
        this.selectedFlight = flightData ;
        return this;
    }

    public FlightDialog setSelectedFlight(List<FlightData> selectedFlightList){
        this.selectedFlightList = selectedFlightList ;
        return this;
    }

    private void initSearch(){
        mEtSearch.addTextChangedListener(new EditTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mIbClearSearch.setVisibility(TextUtils.isEmpty(s.toString()) ? View.GONE : View.VISIBLE);
            }
        });

        mIbClearSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CommonUtils.isFastDoubleClick())
                    return;

                mEtSearch.setText("");
                clickSearch("");
            }
        });

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    clickSearch(v.getText().toString());
                }
                return false;
            }
        });
    }

    private void clickSearch(String content){
        if(mNetFlightList == null || mNetFlightList.size() == 0)
            return;

        if(!TextUtils.isEmpty(content)){
            mFlightList = new ArrayList<>();

            for(FlightData flightData : mNetFlightList){
                if((!TextUtils.isEmpty(flightData.getFlightNo2()) && flightData.getFlightNo2().contains(CommonUtils.toLowerCase(content)))
                        || (!TextUtils.isEmpty(flightData.getOriginStation()) && flightData.getOriginStation().contains(CommonUtils.toLowerCase(content))))
                    mFlightList.add(flightData);
            }
        }else {
            mFlightList = mNetFlightList ;
        }

        initAdapter();
    }

    /**
     * 初始化选择
     */
    private void initSelected(){
        if(mFlightList == null)
            return;

        if(selectedFlight != null)
            for (int i = 0; i < mFlightList.size(); i++) {
                if(mFlightList.get(i).getId() == selectedFlight.getId()){
                    mFlightList.get(i).setSelected(true);
                    continue;
                }
            }

        if(selectedFlightList != null && selectedFlightList.size() != 0)
            for (int i = 0; i < mFlightList.size(); i++) {
                for (int j = 0; j < selectedFlightList.size(); j++) {
                    if(mFlightList.get(i).getId() == selectedFlightList.get(j).getId()){
                        mFlightList.get(i).setSelected(true);
                        continue;
                    }
                }
            }
    }

    private void initAdapter(){
        if(mFlightAdapter == null) {
            mFlightAdapter = new FlightAdapter(mContext, mFlightList);
            mRlStoreContainer.setAdapter(mFlightAdapter);
            mRlStoreContainer.setLayoutManager(new GridLayoutManager(mContext, 4));
            mRlStoreContainer.addItemDecoration(new GridItemDecoration(new GridItemDecoration.Builder(mContext)));

            mFlightAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    clearSelect();
                    mFlightList.get(position).setSelected(!mFlightList.get(position).isSelected());

                    initAdapter();
                }
            });
        }else {
            mFlightAdapter.updateAdapter(mFlightList);
        }
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public FlightDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public FlightDialog setConfirmButton(final OnFlightOnClickListener listener) {
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FlightData> flightBeans = new ArrayList<>();
                for (int i = 0; i < mFlightList.size(); i++) {
                    if(mFlightList.get(i).isSelected())
                        flightBeans.add(mFlightList.get(i));
                }

                if(flightBeans.size() == 0){
                    ToastUtils.showToast(R.string.toast_please_select_area);
                }else {
                    listener.onClick(flightBeans);
                    mDialog.dismiss();
                }

            }
        });
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @return
     */
    public FlightDialog setButtonClickListener() {
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        return this;
    }

    private void clearSelect(){
        for (int i = 0; i < mFlightList.size(); i++) {
            mFlightList.get(i).setSelected(false);
        }

        for (int i = 0; i < mNetFlightList.size(); i++) {
            mNetFlightList.get(i).setSelected(false);
        }
    }

    public interface OnFlightOnClickListener {
        void onClick(List<FlightData> flightList);
    }
}
