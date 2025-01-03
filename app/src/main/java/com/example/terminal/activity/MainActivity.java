package com.example.terminal.activity;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;

import com.example.terminal.fragment.HomeFragment;
import com.example.terminal.fragment.MyFragment;
import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.FragmentUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioGroup mRg_main;
    private RadioButton mRb_home_main;
    private RadioButton mRb_my_main;

    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentManager manager;
    private FrameLayout mFl_main;

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mFl_main = findViewById(R.id.mFl_main);
        mRg_main = findViewById(R.id.mRg_main);
        mRb_home_main = findViewById(R.id.mRb_Home_main);
        mRb_my_main = findViewById(R.id.mRb_My_main);

        //碎片事务管理器
        manager = getSupportFragmentManager();
        //默认碎片
        FragmentUtils.addFragment(manager, HomeFragment.class, R.id.mFl_main, null);
        //默认选中首页
        mRb_home_main.setChecked(true);
    }

    @Override
    protected void initData() {
        mRg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.mRb_Home_main:
                        FragmentUtils.addFragment(manager, HomeFragment.class, R.id.mFl_main, null);
                        break;
                    case R.id.mRb_My_main:
                        FragmentUtils.addFragment(manager, MyFragment.class, R.id.mFl_main, null);
                        break;
                }
            }
        });
    }


    @Override
    protected void initConnect() {

    }


    @Override
    protected void initRefresh(int count) {

    }

    @Override
    public void onBackPressed() {
        onBackDoubleClick();
    }

    @Override
    public void onClick(View v) {
        if (CommonUtils.isFastDoubleClick())
            return;
    }
    @Override
    protected void changeSystemUi() {
        CommonUtils.changeSystemUi(mActivity);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public abstract class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}