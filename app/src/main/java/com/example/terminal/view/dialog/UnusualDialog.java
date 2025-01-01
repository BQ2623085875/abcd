package com.example.terminal.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminal.R;
import com.example.terminal.activity.SeePicActivity;
import com.example.terminal.adapter.DialogImageAdapter;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.bean.CommonDataBean;
import com.example.terminal.bean.FileBean;
import com.example.terminal.bean.UnusualBean.UnusualData;
import com.example.terminal.bean.UnusualBean.UnusualData.UnusualImageBean;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.Url;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.GridItemDecoration;
import com.example.terminal.listener.OnItemClickListener;
import com.example.terminal.listener.OnPopupWindowClickListener;
import com.example.terminal.listener.OnSheetItemClickListener;
import com.example.terminal.listener.ResultCallbackListener;
import com.example.terminal.listener.UnusualConfirmClickListener;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.DensityUtils;
import com.example.terminal.util.JsonUtils;
import com.example.terminal.util.ProgressUtils;
import com.example.terminal.util.ToastUtils;
import com.example.terminal.view.edit.EditorActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : YanKing
 * @date : 2019/5/19  9:53
 * @Description : 确定取消弹框
 */
public class UnusualDialog extends BaseDialog {

    private LinearLayout mLayoutBg;
    private TextView mTvUnusualType;//异常类型
    private TextView mTvUnusualCount;//异常件数
    private TextView mTvUnusualGradle;//异常等级
    private EditText mEtUnusualRemarks;//备注
    private LinearLayout mLlUnusualGradle;
    private RecyclerView mRvImageContainer;
    private Button mBtnCancel,//取消按钮
            mBtnConfirm;//确定按钮

    private boolean isCa;
    private DialogImageAdapter dialogImageAdapter;
    private List<UnusualImageBean> mUnusualImageList;
    private UnusualData mUnusualData;
    private boolean isNetwork = true;
    private int mPosition = -1;

    public static UnusualDialog getInstance(Context mContext, boolean isCa) {
        return new UnusualDialog(mContext, isCa);
    }

    private UnusualDialog(Context mContext, boolean isCa) {
        this.mContext = mContext;
        this.isCa = isCa;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private UnusualDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_unusual, null);

        // 获取自定义Dialog布局中的控件
        mLayoutBg = view.findViewById(R.id.mLayoutBg);
        mTvUnusualType = view.findViewById(R.id.mTvUnusualType);
        mTvUnusualGradle = view.findViewById(R.id.mTvUnusualGradle);
        mLlUnusualGradle = view.findViewById(R.id.mLlUnusualGradle);
        mRvImageContainer = view.findViewById(R.id.mRvImageContainer);
        mBtnCancel = view.findViewById(R.id.mBtnCancel);
        mBtnConfirm = view.findViewById(R.id.mBtnConfirm);
        mTvUnusualCount = view.findViewById(R.id.mTvUnusualCount);
        mEtUnusualRemarks = view.findViewById(R.id.mEtUnusualRemarks);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小
        mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams(4 * DensityUtils.getScreenWidth((Activity) mContext) / 5, 4 * DensityUtils.getScreenHeight((Activity) mContext) / 5));

        // 抽检件数
        mTvUnusualCount.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditorActivity.openActivity((BaseActivity) mContext, new EditorActivity.EditorCallbackListener() {
                    @Override
                    public void onSubmit(String content) {
                        mEtUnusualRemarks.requestFocus();
                        mTvUnusualCount.setText(content);
                        mUnusualData.setNumber(TextUtils.isEmpty(content) ? 0 : Integer.parseInt(content));
                    }
                }, mTvUnusualCount.getText().toString());
            }
        });

        //初始化取消按钮
        setCancelButton();
        //点击空白处可消失
        setCancelable(false);

        return this;
    }

    /**
     * @return
     */

    public UnusualDialog setUnusualData(UnusualData unusualData, int position) {
        mUnusualData = unusualData;
        mPosition = position;

        if (mUnusualData != null) {


//            mUnusualImageList = mUnusualData.getBizAnomalyRecordFileVOList();

            List<UnusualImageBean> pictures = mUnusualData.getBizAnomalyRecordFileVOList();
            if (pictures != null && pictures.size() > 0) {
                for (int i = 0; i < pictures.size(); i++) {
                    if (pictures.get(i) != null)
                        pictures.get(i).setUrl(pictures.get(i).getUrl().contains(Constant.Http) ? pictures.get(i).getUrl() : Url.DOWN_APK_URL + pictures.get(i).getUrl());
                }
            }
            mUnusualImageList = pictures;

            mTvUnusualCount.setText(String.valueOf(mUnusualData.getNumber() == 0 ? "" : mUnusualData.getNumber()));
            mTvUnusualGradle.setText(mUnusualData.getGrade());
            mEtUnusualRemarks.setText(mUnusualData.getExplain());

            setUnusualType();
        }

        initImageAdapter();

        return this;
    }

    public UnusualDialog setNetwork(boolean isNetwork) {
        this.isNetwork = isNetwork;
        return this;
    }

    /**
     * 设置内容
     *
     * @return
     */
    private UnusualDialog setUnusualType() {
        mTvUnusualType.setText(mUnusualData.getTypeName());

        if (mUnusualData.getType() == 1 && isCa) {
            mLlUnusualGradle.setVisibility(View.VISIBLE);
            mTvUnusualGradle.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    new SpinnerPopWindow(mContext, ConstantInfo.UnusualGradleList, new OnPopupWindowClickListener() {
                        @Override
                        public void onClick(int position) {
                            mTvUnusualGradle.setText(ConstantInfo.UnusualGradleList.get(position));
                            mUnusualData.setGrade(ConstantInfo.UnusualGradleList.get(position));
                        }
                    }).showAsDropDown(mTvUnusualGradle);
                }
            });
        } else {
            mLlUnusualGradle.setVisibility(View.GONE);
        }

        return this;
    }

    private UnusualDialog initImageAdapter() {
        if (mUnusualImageList == null)
            mUnusualImageList = new ArrayList<>();

        if (!mUnusualImageList.contains(null))
            mUnusualImageList.add(null);

        if (dialogImageAdapter == null) {
            dialogImageAdapter = new DialogImageAdapter(mContext, mUnusualImageList);
            mRvImageContainer.setAdapter(dialogImageAdapter);
            mRvImageContainer.setLayoutManager(new GridLayoutManager(mContext, 4));
            mRvImageContainer.addItemDecoration(new GridItemDecoration(new GridItemDecoration.Builder(mContext)));

            dialogImageAdapter.setOnItemClickListener(onClickListener);
        } else {
            dialogImageAdapter.updateAdapter(mUnusualImageList);
        }
        return this;
    }

    /**
     * 图片点击事件
     */
    private OnItemClickListener onClickListener = new OnItemClickListener() {
        @Override
        public void onClick(View view, int position) {
            if (mUnusualImageList.get(position) == null) {
                PictureSelector.create((BaseActivity) mContext)
                        .openCamera(PictureMimeType.ofImage())
                        .forResult(new ResultCallbackListener() {
                            @Override
                            public void onResult(String localPath) {

                                if (!TextUtils.isEmpty(localPath) && localPath.contains("content://media"))
                                    localPath = CommonUtils.uriToPath(localPath, mContext);

                                UnusualImageBean imageBean = new UnusualImageBean();
                                imageBean.setUrl(localPath);

                                mUnusualImageList.add(mUnusualImageList.size() - 1, imageBean);

                                initImageAdapter();

                                // 结果回调
                                ProgressUtils.showProgress(mContext);
                                NetworkUtils.uploadFile(new OkGoBackListener(mDialog.getOwnerActivity()) {
                                    @Override
                                    public void onSuccess(Object body) {
                                        FileBean fileBean = (FileBean) body;

                                        UnusualImageBean imageBean = new UnusualImageBean();
                                        imageBean.setUrl(fileBean.getUrl().contains(Constant.Http) ? fileBean.getUrl() : Url.DOWN_APK_URL + fileBean.getUrl());
                                        imageBean.setFileId(fileBean.getFileId());

                                        mUnusualImageList.set(mUnusualImageList.size() - 2, imageBean);
                                    }
                                }, new File(localPath));
                            }
                        });
            } else {
                ArrayList<String> imageList = new ArrayList<>();
                for (int i = 0; i < mUnusualImageList.size(); i++) {
                    if (mUnusualImageList.get(i) != null)
                        imageList.add(mUnusualImageList.get(i).getUrl());
                }

                Intent mIntent = new Intent(mContext, SeePicActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putStringArrayList(Constant.INFO, imageList);
                mBundle.putInt(Constant.POSITION, position);
                mIntent.putExtras(mBundle);
                ((BaseActivity) mContext).turnActForResult(mIntent);
            }
        }

        @Override
        public void onLongClick(View view, int position) {
            AlertDialog.getInstance(mContext)
                    .setContent(R.string.dialog_confirm_delete)
                    .setConfirmButton(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mUnusualImageList.remove(position);

                            initImageAdapter();
                        }
                    }).show();
        }
    };


    private void turnVideoImage() {
        BottomDialog.getInstance(mContext)
                .addSheetItem(R.string.text_photo)
                .addSheetItem(R.string.text_video)
                .setOnItemClickListener(new OnSheetItemClickListener() {
                    @Override
                    public void onClick(int which) {
                        PictureSelector.create((BaseActivity) mContext)
                                .openCamera(which == 0 ? PictureMimeType.ofImage() : PictureMimeType.ofVideo())
                                .forResult(new ResultCallbackListener() {
                                    @Override
                                    public void onResult(String localPath) {
                                        // 结果回调
                                        ProgressUtils.showProgress(mContext);
                                        NetworkUtils.uploadFile(new OkGoBackListener(mDialog.getOwnerActivity()) {
                                            @Override
                                            public void onSuccess(Object body) {
                                                FileBean fileBean = (FileBean) body;

                                                UnusualImageBean imageBean = new UnusualImageBean();
                                                imageBean.setUrl(fileBean.getUrl());
                                                imageBean.setFileId(fileBean.getFileId());

                                                mUnusualImageList.add(mUnusualImageList.size() - 1, imageBean);
                                                initImageAdapter();
                                            }
                                        }, new File(localPath));
                                    }
                                });
                    }
                }).show();
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public UnusualDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     *
     * @param listener
     * @return
     */
    public UnusualDialog setConfirmButton(final UnusualConfirmClickListener listener) {
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUnusualData.getType() == 1 && isCa && TextUtils.isEmpty(mUnusualData.getGrade())) {
                    ToastUtils.showToast(R.string.toast_unusual_grade_null);
                    return;
                }

                if (mUnusualData.getNumber() == 0) {
                    ToastUtils.showToast(R.string.toast_unusual_count_null);
                    return;
                }

                mUnusualImageList.remove(null);
                mUnusualData.setBizAnomalyRecordFileVOList(mUnusualImageList);
                mUnusualData.setExplain(mEtUnusualRemarks.getText().toString());

                if (isNetwork) {
                    NetworkUtils.saveUnusualData(new OkGoBackListener((BaseActivity) mContext) {
                        @Override
                        public void onSuccess(Object body) {
                            CommonDataBean commonDataBean = (CommonDataBean) body;
                            ConstantInfo.UnusualIDList.add(commonDataBean.getData());

                            mDialog.dismiss();
                            listener.onClick(mUnusualData, mPosition);
                        }
                    }, JsonUtils.parseBeanToJson(mUnusualData), mPosition == -1);
                } else {
                    mDialog.dismiss();
                    listener.onClick(mUnusualData, mPosition);
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
    public UnusualDialog setCancelButton() {
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
    public UnusualDialog setCancelButton(final OnClickListener listener) {
        mBtnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                listener.onClick(v);
            }
        });
        return this;
    }
}
