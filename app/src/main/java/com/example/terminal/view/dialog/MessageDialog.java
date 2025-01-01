package com.example.terminal.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terminal.R;
import com.example.terminal.adapter.MessageAdapter;
import com.example.terminal.base.BaseDialog;
import com.example.terminal.bean.MessageBean.MessageData;
import com.example.terminal.http.NetworkUtils;
import com.example.terminal.http.network.OkGoBackListener;
import com.example.terminal.listener.OnItemClickListener;
import com.example.terminal.util.DensityUtils;

import java.util.List;


/**
 * @author : YanKing
 * @Description : 确定取消弹框
 */
public class MessageDialog extends BaseDialog {

    private LinearLayout mLayoutBg;
    private RadioGroup mRGMessage ;
    private RecyclerView mRvContainer ;
    private MessageAdapter messageAdapter ;

    private TextView mTvMessageTitle,//标题
            mTvMessageType ,//消息类型
            mTvMessageSender ,//消息发送人
            mTvMessageSendTime ,//发送时间
            mTvMessageInfo ,//消息信息
            mTvOperationInfo ;//操作信息
    private Button mBtnFetch ;//读取
    private ImageButton mIbClose ;//关闭
    private LinearLayout mLlMessageInfo ,//消息信息
            mLlOperationInfo ;//操作信息

    private int currentIndex = 3;
    private static MessageDialog alertDialog ;

    private List<MessageData> mMessageList ;

    public static MessageDialog getInstance(Context mContext) {
        if(alertDialog == null)
            alertDialog = new MessageDialog(mContext);
        return new MessageDialog(mContext);
    }

    private MessageDialog(Context mContext) {
        this.mContext = mContext;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplay = windowManager.getDefaultDisplay();
        builder();
    }

    private MessageDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_message, null);

        // 获取自定义Dialog布局中的控件
        mLayoutBg = view.findViewById(R.id.mLayoutBg);
        mRGMessage = view.findViewById(R.id.mRGMessage);
        mRvContainer = view.findViewById(R.id.mRvContainer);

        mTvMessageTitle = view.findViewById(R.id.mTvMessageTitle);
        mTvMessageType = view.findViewById(R.id.mTvMessageType);
        mTvMessageSender = view.findViewById(R.id.mTvMessageSender);
        mTvMessageSendTime = view.findViewById(R.id.mTvMessageSendTime);
        mTvMessageInfo = view.findViewById(R.id.mTvMessageInfo);
        mTvOperationInfo = view.findViewById(R.id.mTvOperationInfo);

        mIbClose = view.findViewById(R.id.mIbClose);
        mBtnFetch = view.findViewById(R.id.mBtnFetch);

        mLlOperationInfo = view.findViewById(R.id.mLlOperationInfo);
        mLlMessageInfo = view.findViewById(R.id.mLlMessageInfo);

        // 定义Dialog布局和参数
        mDialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);

        // 调整dialog背景大小
        mLayoutBg.setLayoutParams(new FrameLayout.LayoutParams( 3 * DensityUtils.getScreenWidth((Activity) mContext) / 4, 3 * DensityUtils.getScreenHeight((Activity) mContext) / 4));
        Window dialogWindow = mDialog.getWindow();
        dialogWindow.setGravity(Gravity.RIGHT | Gravity.BOTTOM);

        mIbClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });

        // 初始化集合
        initMessageList();
        initRadioGroup();
        initAdapter();

        //点击空白处可消失
        setCancelable(false);
        return this;
    }

    /**
     * 初始化消息集合
     */
    private void initMessageList(){
        NetworkUtils.personalMessage(new OkGoBackListener((Activity) mContext){
            @Override
            public void onSuccess(Object body) {
                mMessageList = (List<MessageData>) body;
                initAdapter();
            }
        } , currentIndex);
    }

    /**
     * 设置是否点击空白处消失,false为不消失
     *
     * @param cancel
     * @return
     */
    public MessageDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    /**
     * 监听变化
     * @return
     */
    public MessageDialog initRadioGroup(){
        mRGMessage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.mRbRead:
                        // 已读
                        changeRadioButton(0);
                        break;
                    case R.id.mRbUnread:
                        // 未读
                        changeRadioButton(3);
                        break;
                }
            }
        });
        return this;
    }

    /**
     * 切换选择
     * @param buttonType
     */
    private void changeRadioButton(int buttonType){
        currentIndex = buttonType ;
        sendMessageInfo(getSelectedItem(-1));

        initMessageList();
    }

    /**
     * 初始化
     */
    private void initAdapter(){
        if(messageAdapter == null) {
            messageAdapter = new MessageAdapter(mContext, mMessageList);
            mRvContainer.setAdapter(messageAdapter);
            mRvContainer.setLayoutManager(new LinearLayoutManager(mContext));

            messageAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    sendMessageInfo(getSelectedItem(position));
                    initAdapter();
                }
            });
        }else {
            messageAdapter.updateAdapter(mMessageList);
        }
    }

    /**
     * 初始化信息
     * @param messageData
     */
    private void sendMessageInfo(MessageData messageData) {
        if(messageData != null){
            mLlMessageInfo.setVisibility(View.VISIBLE);
            mTvMessageTitle.setVisibility(View.VISIBLE);
            mTvMessageTitle.setText(messageData.getTitle());
            mTvMessageSender.setText(messageData.getSender());
            mTvMessageSendTime.setText(messageData.getTime());
            mTvMessageInfo.setText(messageData.getContent());

            mLlOperationInfo.setVisibility(currentIndex == 3 ? View.GONE : View.VISIBLE);
            mBtnFetch.setVisibility(currentIndex == 3 ? View.VISIBLE : View.GONE);

            if(currentIndex != 3)
                NetworkUtils.MessageDetailsReply(new OkGoBackListener((Activity) mContext) {
                    @Override
                    public void onSuccess(Object body) {
                        mTvOperationInfo.setText(body.toString());
                    }
                }, messageData.getId());

            mBtnFetch.setOnClickListener(new FetchMessageOnClickListener(messageData));
        }else {
            mLlMessageInfo.setVisibility(View.INVISIBLE);
            mTvMessageTitle.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 读取点击事件
     */
    private class FetchMessageOnClickListener implements View.OnClickListener{

        private MessageData messageData ;

        public FetchMessageOnClickListener(MessageData messageBean){
            this.messageData = messageBean ;
        }

        @Override
        public void onClick(View v) {
            NetworkUtils.messageReceive(new OkGoBackListener((Activity) mContext) {
                @Override
                public void onSuccess(Object body) {
                    changeRadioButton(currentIndex);
                }
            }, messageData.getId() , messageData.getType());
        }
    }

    /**
     * 初始化选中
     * @param position
     */
    private MessageData getSelectedItem(int position){

        for (int i = 0; i < mMessageList.size(); i++) {
            mMessageList.get(i).setSelected(false);
        }

        if(position == -1)
            return null ;

        mMessageList.get(position).setSelected(!mMessageList.get(position).isSelected());

        return mMessageList.get(position).isSelected() ? mMessageList.get(position) : null;
    }
}
