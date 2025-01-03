package com.example.terminal.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.terminal.R;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.global.MainApplication;
import com.example.terminal.listener.RecyclerView.RefreshAdapter;
import com.example.terminal.listener.RecyclerView.RefreshListener;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.ProgressUtils;
import com.example.terminal.util.ToastUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lzy.okgo.model.HttpParams;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author : YanKing
 * @date : 2019/5/6  15:35
 * @Description : 页面基类
 */

public abstract class BaseActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener {

    protected BaseActivity mActivity;
    protected Intent mIntent;
    protected Bundle mBundle;
    protected Bundle savedInstanceState;
    private FrameLayout mFlContent;
    private IntentFilter messageIntentFilter,//消息
            wasteMessageIntentFilter,
            refreshIntentFilter;//刷新

    protected Map<String, Object> valueMap;
    protected HttpParams valueParams;
    protected boolean IsEditor;
    public boolean IsSaveIng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(setContentView());

        this.mActivity = this;

        changeSystemUi();

        mIntent = getIntent();

        if (getIntent() != null)
            mBundle = getIntent().getExtras();

        if (mBundle == null)
            mBundle = new Bundle();

        mFlContent = findViewById(android.R.id.content);
        mFlContent.getViewTreeObserver().addOnGlobalLayoutListener(this);

        CommonUtils.hideBottomUIMenu2(mActivity);

        initView();
        initData();
        initConnect();

        MainApplication.addActivity(mActivity);

        // 监听消息广播
        initRegisterBroadcast();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            // remove掉保存的Fragment
            outState.remove(FRAGMENTS_TAG);
        }
    }

    protected abstract int setContentView();

    protected abstract void initView();

    protected abstract void initData();

    protected void initConnect() {
    }

    /**
     * 设置刷新
     *
     * @param mRefreshLayout
     */
    protected void setRefreshListener(TwinklingRefreshLayout mRefreshLayout) {
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
     * @param code
     */
    public void onScanResult(String code) {

    }

    @Override
    public void onGlobalLayout() {
        onGlobalChange(mFlContent.getRootView().getWidth() - mFlContent.getWidth() != 0);
    }

    protected void onGlobalChange(boolean isShow) {

    }

    /**
     * Intent跳转
     *
     * @param intent
     */
    public void turnToAct(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
        onFinish();
    }

    /**
     * Intent跳转
     *
     * @param mClass
     */
    public void onFinishAct(Class<?> mClass) {
        mIntent = new Intent(this, mClass);
        startActivity(mIntent);
        overridePendingTransition(R.anim.no_animation, R.anim.dialog_right_in);
        onFinish();
    }

    /**
     * Intent跳转
     *
     * @param mClass
     */
    public void turnToAct(Class<?> mClass) {
        mIntent = new Intent(this, mClass);
        startActivity(mIntent);
        overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
        onFinish();
    }

    /**
     * act跳转
     */
    public void turnToAct(Intent intent, boolean flag) {
        startActivity(intent);
        overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
        if (flag)
            onFinish();
    }

    /**
     * act跳转
     */
    public void turnToAct(Class<?> mClass, boolean flag) {
        mIntent = new Intent(this, mClass);
        startActivity(mIntent);
        overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
        if (flag)
            onFinish();
    }

    /**
     * act跳转
     */
    public void turnActForResult(Class<?> mClass, int requestCode) {
        mIntent = new Intent(this, mClass);
        startActivityForResult(mIntent, requestCode);
        overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
    }

    /**
     * act跳转
     */
    public void turnActForResult(Class<?> mClass) {
        mIntent = new Intent(this, mClass);
        startActivityForResult(mIntent, Constant.REQUEST_CODE);
        overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
    }

    /**
     * act跳转
     */
    public void turnActForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
    }

    /**
     * act跳转
     */
    public void turnActForResult(Intent intent) {
        startActivityForResult(intent, Constant.REQUEST_CODE);
        overridePendingTransition(R.anim.dialog_right_in, R.anim.no_animation);
    }

    public void onFinish(Bundle bundle) {
        if (mIntent == null)
            mIntent = new Intent();
        mIntent.putExtras(bundle);
        setResult(Constant.RESULT_CODE, mIntent);
        onFinish();
    }

    public void onFinish(Bundle bundle, int resultCode) {
        if (mIntent == null)
            mIntent = new Intent();
        mIntent.putExtras(bundle);
        setResult(resultCode, mIntent);
        onFinish();
    }

    public void onFinish(int result) {
        setResult(result);
        onFinish();
    }

    public void onFinishResult() {
        setResult(Constant.RESULT_CODE);
        onFinish();
    }


    public void onFinish() {
        MainApplication.removeActivity(mActivity);
        finish();
        overridePendingTransition(R.anim.no_animation, R.anim.dialog_right_out);
    }

    /**
     * 全屏展示，沉浸式，哪个页面不需要该方法就重写该方法
     */
    protected void changeSystemUi() {
        CommonUtils.changeSystemUi(mActivity);
    }

    /**
     * 监听消息广播
     */
    private void initRegisterBroadcast() {

    }


    /**
     * 设置回调，页面刷新箭头
     */
    protected void initRefresh(int count) {
        if (count != ConstantInfo.RefreshTime)
            return;
    }

    // 震动，声音
    private SoundPool soundPool;
    private AudioManager audioManager;
    private Vibrator mVibrator;
    private long[] mPattern = {0, 500};

    private void initSoundPool() {
        soundPool = new SoundPool(10, AudioManager.STREAM_RING, 5);
        soundPool.load(this, R.raw.beep_message, 1);

        //初始化振动
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * 发出警报
     */
    private void performSound() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // 获取最大音量值
        float audioMaxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        // 不断获取当前的音量值
        float audioCurrentVolume = audioManager.getStreamVolume(AudioManager.STREAM_RING);
        //最终影响音量
        float volumeRatio = audioCurrentVolume / audioMaxVolume;

        mVibrator.vibrate(mPattern, -1);
        soundPool.play(1, volumeRatio, volumeRatio, 0, 0, 1);
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        View view = mActivity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 弹起软键盘
     *
     * @param editText
     */
    public void openKeyBoard(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getApplication().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, 0);
                editText.setSelection(editText.getText().length());
            }
        }, 200);
    }

    @Override
    public void onBackPressed() {
        onFinish();
    }

    //返回键双击事件
    private long clickBackButtonTime;

    protected void onBackDoubleClick() {
        if (System.currentTimeMillis() - clickBackButtonTime > 2000) {
            //大于2000，提示再点击一次退出
            clickBackButtonTime = System.currentTimeMillis();
            ToastUtils.showToast(R.string.double_click_exit);
        } else {
            clickBackButtonTime = 0;
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFlContent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }
}