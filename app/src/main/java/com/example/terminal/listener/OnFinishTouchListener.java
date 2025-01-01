package com.example.terminal.listener;

import android.view.MotionEvent;
import android.view.View;

import com.example.terminal.base.BaseActivity;

/**
 * author : King
 * date   : 2022/5/3114:54
 * desc   :
 */
public class OnFinishTouchListener implements View.OnTouchListener {

    private float mPosStart ;
    private BaseActivity mActivity ;

    public OnFinishTouchListener(BaseActivity mActivity){
        this.mActivity = mActivity ;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mPosStart = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if(event.getX() - mPosStart > 25)
                    mActivity.onBackPressed();
                break;
        }
        return true;
    }
}
