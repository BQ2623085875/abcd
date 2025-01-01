package com.example.terminal.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminal.R;
import com.example.terminal.adapter.StorageAreaContainerAdapter;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.bean.ContainerBean;
import com.example.terminal.bean.StorageAreaBean;
import com.example.terminal.bean.TugBean.TugData;
import com.example.terminal.bean.EnterPortStorageBean;
import com.example.terminal.bean.StorageAreaBean.StorageAreaData;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.OnItemClickListener;
import com.example.terminal.listener.OnSheetItemClickListener;
import com.example.terminal.listener.OnTextClickListener;
import com.example.terminal.util.DensityUtils;
import com.example.terminal.util.ToastUtils;
import com.example.terminal.view.edit.CustomContainerEditorActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 确定取消弹框
 */
public class StorageContainerDialog extends BaseDialog {
    private LinearLayout mLayoutBg;
    private RecyclerView mRlContainer;//内容
    private TextView mBtnCancel,//取消按钮
            mBtnConfirm;//确定按钮

    private boolean IsEditor ;
    private List<EnterPortStorageBean> contentList ;

    private StorageAreaContainerAdapter mContainerAdapter;

    private static StorageContainerDialog alertDialog ;
    private StorageAreaData mStorageArea;

    public static StorageContainerDialog getInstance(Context mContext) {
        if(alertDialog == null)
            alertDialog = new StorageContainerDialog(mContext);
        return new StorageContainerDialog(mContext);
    }

    private StorageContainerDialog(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private StorageContainerDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_storage_container, null);

        // 获取自定义Dialog布局中的控件
        mLayoutBg = view.findViewById(R.id.mLayoutBg);
        mRlContainer = view.findViewById(R.id.mRlContainer);
        mBtnCancel = view.findViewById(R.id.mBtnCancel);
        mBtnConfirm = view.findViewById(R.id.mBtnConfirm);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小
        mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams( 4 * DensityUtils.getScreenWidth((Activity) mContext) / 5, 4 * DensityUtils.getScreenHeight((Activity) mContext) / 5));


        //初始化取消按钮
        setCancelButton();
        //点击空白处可消失
        setCancelable(false);
        return this;
    }

    /**
     * 设置内容
     *
     * @param contentList
     * @return
     */
    public StorageContainerDialog setContent(List<EnterPortStorageBean> contentList , boolean IsEditor) {
        this.contentList = contentList ;
        this.IsEditor = IsEditor ;

        if(contentList == null || contentList.size() == 0)
            getStorageArea();

        initStorageAreaContainerAdapter();

        return this;
    }

    private void getStorageArea() {
        NetworkUtils.getStorageAreaList(new OkGoBackListener((Activity) mContext) {
            @Override
            public void onSuccess(Object body) {
                List<StorageAreaData> mStorageList = (List<StorageAreaData>) body;

                if (mStorageList != null && mStorageList.size() > 0) {
                    // 根据序号排序 小到大
                    Collections.sort(mStorageList, new Comparator<StorageAreaData>() {
                        @Override
                        public int compare(StorageAreaBean.StorageAreaData o1, StorageAreaBean.StorageAreaData o2) {
                            return Integer.compare(o1.getSort(), o2.getSort());
                        }
                    });
                }


                if(mStorageList != null && mStorageList.size() > 0) {
                    mStorageArea = mStorageList.get(0);

                    if(mStorageArea != null) {
                        if (contentList != null && contentList.size() != 0) {
                            contentList.get(0).setWarehouseAreaName(mStorageArea.getName());
                            contentList.get(0).setWarehouseAreaId(mStorageArea.getId());
                            initStorageAreaContainerAdapter();
                        }
                    }
                }
            }
        } , Constant.EnterPortStorage);
    }

    /**
     * 获取
     */
    private void initStorageAreaContainerAdapter(){
        if(contentList == null || contentList.size() == 0){
            contentList = new ArrayList<>();
            contentList.add(new EnterPortStorageBean(ConstantInfo.Enter_Container , 1));
            contentList.add(null);
        }

        if(!contentList.contains(null) && IsEditor)
            contentList.add(null);

        if(mContainerAdapter == null){
            mContainerAdapter = new StorageAreaContainerAdapter(mContext , contentList , IsEditor);
            mRlContainer.setAdapter(mContainerAdapter);
            mRlContainer.setLayoutManager(new LinearLayoutManager(mContext));

            mContainerAdapter.setOnTextClickListener(new ItemTextClickListener());
            mContainerAdapter.setOnItemClickListener(new ItemClickListener());
        }else{
            mContainerAdapter.updateAdapter(contentList , IsEditor);
        }
    }

    /**
     * 条目字段点击事件
     */
    private class ItemTextClickListener implements OnTextClickListener {

        @Override
        public void onClick(View view, int position) {
            switch (view.getId()){
                case R.id.mTvContainer :
                    // 拖斗号
                    BottomDialog.getInstance(mContext)
                            .addSheetItem(ConstantInfo.ContainerList)
                            .setOnItemClickListener(new OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {
                                    if(which == 0) {
                                        // 容器编号
                                        clickTug(position);
                                    }else {
                                        clickContainer(position);
                                    }
                                }
                            }).show();
                    break;
                case R.id.mTvStorageArea :
                    // 存放区域
                    StorageAreaDialog.getInstance(mContext , Constant.EnterPortStorage)
                            .setSingleSelect(true)
                            .setConfirmButton(new StorageAreaDialog.OnStorageOnClickListener() {
                                @Override
                                public void onClick(List<StorageAreaData> storageAreaList) {
                                    contentList.get(position).setWarehouseAreaId(storageAreaList.get(0).getId());
                                    contentList.get(position).setWarehouseAreaName(storageAreaList.get(0).getName());
                                    initStorageAreaContainerAdapter();
                                }
                            })
                            .show();
                    break;
            }
        }
    }



    private void clickTug(int position) {
        TugDialog.getInstance(mContext)
                .setTugType(0 , true)
                .setOnItemClickListener(new TugDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(TugData tugData) {
                        if(tugData == null) {
                            CustomContainerEditorActivity.openActivity((BaseActivity)mContext, new CustomContainerEditorActivity.EditorCallbackListener() {
                                @Override
                                public void onSubmit(Bundle bundle) {
                                    addTugData(bundle , position);
                                }
                            } , 1);
                        }else {
                            contentList.get(position).setTransportCode(tugData.getCode());
                            contentList.get(position).setTransportType(tugData.getType());
                            initStorageAreaContainerAdapter();
                        }
                    }

                    @Override
                    public void onLongClick(TugData channelData) {
                        CustomContainerEditorActivity.openActivity((BaseActivity)mContext, new CustomContainerEditorActivity.EditorCallbackListener() {
                            @Override
                            public void onSubmit(Bundle bundle) {
                                resetTugData(bundle , channelData , position);
                            }
                        } , channelData , 4);
                    }
                }).show();
    }

    /**
     * 设置拖斗数据
     * @param mBundle
     */
    private void addTugData(Bundle mBundle , int position){
        TugData mTugData = new TugData(mBundle.getString(Constant.Number));
        mTugData.setName(mBundle.getString(Constant.Number));
        mTugData.setWeight(mBundle.getDouble(Constant.Weight));
        mTugData.setType(1);
        mTugData.setStatus(1);

        NetworkUtils.addTug(new OkGoBackListener((BaseActivity)mContext) {
            @Override
            public void onSuccess(Object body) {
                contentList.get(position).setTransportCode(mTugData.getCode());
                contentList.get(position).setTransportType(mTugData.getType());
                initStorageAreaContainerAdapter();
            }
        }, mTugData);
    }


    /**
     * 设置拖斗数据
     * @param mBundle
     */
    private void resetTugData(Bundle mBundle , TugData tugData, int position){
        tugData.setCode(mBundle.getString(Constant.Number));
        tugData.setWeight(mBundle.getDouble(Constant.Weight));

        NetworkUtils.resetTug(new OkGoBackListener((BaseActivity)mContext) {
            @Override
            public void onSuccess(Object body) {
                contentList.get(position).setTransportCode(tugData.getCode());
                contentList.get(position).setTransportType(tugData.getType());
                initStorageAreaContainerAdapter();
            }
        }, tugData);
    }

    private void clickContainer(int position){
        ContainerDialog.getInstance(mContext)
                .setOnItemClickListener(new ContainerDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(ContainerBean.ContainerData containerData) {
                        if(containerData == null){
                            CustomContainerEditorActivity.openActivity((BaseActivity)mContext, new CustomContainerEditorActivity.EditorCallbackListener() {
                                @Override
                                public void onSubmit(Bundle bundle) {
                                    addContainerData(bundle , position);
                                }
                            } , 2);
                        }else {
                            contentList.get(position).setTransportCode(containerData.getCode());
                            contentList.get(position).setTransportType(containerData.getType());

                            initStorageAreaContainerAdapter();
                        }
                    }

                    @Override
                    public void onLongClick(ContainerBean.ContainerData containerData) {
                        CustomContainerEditorActivity.openActivity((BaseActivity) mContext, new CustomContainerEditorActivity.EditorCallbackListener() {
                            @Override
                            public void onSubmit(Bundle bundle) {
                                resetContainerData(bundle , containerData , position);
                            }
                        } , containerData , 5);
                    }
                }).show();
    }

    /**
     * 设置集装器数据
     * @param mBundle
     */
    private void addContainerData(Bundle mBundle , int position){
        ContainerBean.ContainerData mContainerData = new ContainerBean.ContainerData(mBundle.getString(Constant.Number));
        mContainerData.setWeight(mBundle.getDouble(Constant.Weight));

        List<ContainerBean.ContainerData> containerDataList = new ArrayList<>();
        containerDataList.add(mContainerData);

        NetworkUtils.addContainer(new OkGoBackListener((BaseActivity) mContext) {
            @Override
            public void onSuccess(Object body) {
                contentList.get(position).setTransportCode(mContainerData.getCode());
                contentList.get(position).setTransportType(mContainerData.getType());

                initStorageAreaContainerAdapter();
            }
        }, containerDataList);
    }

    /**
     * 设置集装器数据
     * @param mBundle
     */
    private void resetContainerData(Bundle mBundle , ContainerBean.ContainerData containerData , int position){
        containerData.setCode(mBundle.getString(Constant.Number));
        containerData.setWeight(mBundle.getDouble(Constant.Weight));

        NetworkUtils.resetContainer(new OkGoBackListener((Activity) mContext) {
            @Override
            public void onSuccess(Object body) {
                contentList.get(position).setTransportCode(containerData.getCode());
                contentList.get(position).setTransportType(containerData.getType());

                initStorageAreaContainerAdapter();
            }
        }, containerData);
    }

    /**
     * 条目点击事件
     */
    private class ItemClickListener extends OnItemClickListener{
        @Override
        public void onClick(View view, int position) {
            contentList.add(contentList.size() - 1 , new EnterPortStorageBean());
            initStorageAreaContainerAdapter();
        }

        @Override
        public void onLongClick(View view, int position) {
            AlertDialog.getInstance(mContext)
                    .setContent(R.string.dialog_remove_storage_info)
                    .setConfirmButton(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            contentList.remove(position);
                            initStorageAreaContainerAdapter();
                        }
                    }).show();
        }
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public StorageContainerDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public StorageContainerDialog setConfirmButton(final OnConfirmClickListener listener) {
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contentList == null)
                    return;

                for (int i = 0; i < contentList.size(); i++) {
                    if(contentList.get(i) == null)
                        continue;

                    if(TextUtils.isEmpty(contentList.get(i).getTransportCode()) && contentList.get(i).getWarehouseAreaId() == 0){
                        ToastUtils.showToast(R.string.toast_storage_many_null);
                        return;
                    }
                }

                mDialog.dismiss();
                contentList.remove(null);
                listener.onClick(contentList);
            }
        });
        return this;
    }

    /**
     * 设置取消按钮
     *
     * @return
     */
    public StorageContainerDialog setCancelButton() {
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public StorageContainerDialog setCancelButton(final OnClickListener listener) {
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                listener.onClick(v);
            }
        });
        return this;
    }

    public interface OnConfirmClickListener {
        void onClick(List<EnterPortStorageBean> contentList);
    }
}
