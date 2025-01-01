package com.example.terminal.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.terminal.R;
import com.example.terminal.global.ConstantInfo;

import java.util.Calendar;

public class TimerPickerUtils {

    private static TimePickerBuilder timePickerBuilder ;
    private static TimePickerView timePickerView ;
    private static Calendar startDate ,endDate ;
    private static String[] startDates = ConstantInfo.History_Start_Date.split("-");
    private static String[] endDates = ConstantInfo.History_End_Date.split("-");

    public static TimePickerView getDatePickerView(Context mContext , String selectDate , OnTimeSelectListener listener){
        startDate = Calendar.getInstance();
        startDate.set(Integer.parseInt(startDates[0]), Integer.parseInt(startDates[1]) - 1, Integer.parseInt(startDates[2]));

        endDate = Calendar.getInstance();
        endDate.set(Integer.parseInt(endDates[0]), Integer.parseInt(endDates[1]) - 1, Integer.parseInt(endDates[2]));
//        endDate.add(Calendar.DATE , 7);

        timePickerBuilder = new TimePickerBuilder(mContext, listener)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setOutSideCancelable(false)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView mTvCancel = v.findViewById(R.id.mTvCancel);
                        TextView mTvConfirm = v.findViewById(R.id.mTvConfirm);

                        mTvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(timePickerView != null)
                                    timePickerView.dismiss();
                            }
                        });

                        mTvConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(timePickerView != null) {
                                    timePickerView.returnData();
                                    timePickerView.dismiss();
                                }
                            }
                        });
                    }
                });

        timePickerView = timePickerBuilder
                .setDate(CommonUtils.dateToCalender(selectDate))
                .build();

        return timePickerView;
    }


    public static TimePickerView getTimePickerView(Context mContext , String selectDate , OnTimeSelectListener listener){
        timePickerBuilder = new TimePickerBuilder(mContext, listener)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setOutSideCancelable(false)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView mTvCancel = v.findViewById(R.id.mTvCancel);
                        TextView mTvConfirm = v.findViewById(R.id.mTvConfirm);

                        mTvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(timePickerView != null)
                                    timePickerView.dismiss();
                            }
                        });

                        mTvConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(timePickerView != null) {
                                    timePickerView.returnData();
                                    timePickerView.dismiss();
                                }
                            }
                        });
                    }
                });

        timePickerView = timePickerBuilder
                .setDate(CommonUtils.dateToCalenderTime(selectDate))
                .build();

        return timePickerView;
    }

    public static TimePickerView showTimePickerView(Context mContext , String selectDate , OnTimeSelectListener listener){
        timePickerBuilder = new TimePickerBuilder(mContext, listener)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setOutSideCancelable(false)
                .setGravity(Gravity.BOTTOM)
                .isDialog(true)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView mTvCancel = v.findViewById(R.id.mTvCancel);
                        TextView mTvConfirm = v.findViewById(R.id.mTvConfirm);

                        mTvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(timePickerView != null)
                                    timePickerView.dismiss();
                            }
                        });

                        mTvConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(timePickerView != null) {
                                    timePickerView.returnData();
                                    timePickerView.dismiss();
                                }
                            }
                        });
                    }
                });

        timePickerView = timePickerBuilder
                .setDate(CommonUtils.dateToCalenderTime(selectDate))
                .build();

        return timePickerView;
    }

    public static TimePickerView showTimePickerViewSecond(Context mContext , String selectDate , OnTimeSelectListener listener){
        timePickerBuilder = new TimePickerBuilder(mContext, listener)
                .setType(new boolean[]{true, true, true, true, true, true})
                .setOutSideCancelable(false)
                .setGravity(Gravity.BOTTOM)
                .isDialog(true)
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        TextView mTvCancel = v.findViewById(R.id.mTvCancel);
                        TextView mTvConfirm = v.findViewById(R.id.mTvConfirm);

                        mTvCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(timePickerView != null)
                                    timePickerView.dismiss();
                            }
                        });

                        mTvConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(timePickerView != null) {
                                    timePickerView.returnData();
                                    timePickerView.dismiss();
                                }
                            }
                        });
                    }
                });

        timePickerView = timePickerBuilder
                .setDate(CommonUtils.dateToCalenderTime(selectDate))
                .build();

        return timePickerView;
    }
}
