package com.example.terminal.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.terminal.R;

public class CustomCircleProgress extends View {
    // 初始颜色画笔-灰色
    private Paint grayPaint;
    // 圆形变化画笔-粉红色
    private Paint pinkPaint;
    // 百分比文字画笔
    private Paint textPaint;
    private int strokeWidth = 10; // 圆弧边缘宽度
    private int progress = 0; // 当前进度

    public CustomCircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 初始化默认画笔-白色
        grayPaint = new Paint();
        // 设置绘制图形的样式,填充（FILL）、描边（STROKE）
        grayPaint.setStyle(Paint.Style.STROKE);
        // 设置描边的宽度
        grayPaint.setStrokeWidth(strokeWidth);
        // 初始颜色为灰色
        grayPaint.setColor(Color.GRAY);
        // 开启抗锯齿
        grayPaint.setAntiAlias(true);

        // 初始化圆形变动颜色的画笔-粉色
        pinkPaint = new Paint();
        // 设置绘制图形的样式,填充（FILL）、描边（STROKE）
        pinkPaint.setStyle(Paint.Style.STROKE);
        // 设置描边的宽度
        pinkPaint.setStrokeWidth(strokeWidth);
        // 初始颜色为粉红色
        pinkPaint.setColor(getResources().getColor(R.color.color_blue_77D1F7));
        // 开启抗锯齿
        pinkPaint.setAntiAlias(true);

        // 绘制进度条文字
        textPaint = new Paint();
        // 描边填充
        textPaint.setStyle(Paint.Style.FILL);
        // 红色
        textPaint.setColor(getResources().getColor(R.color.color_blue_77D1F7));
        // 设置文字大小
        textPaint.setTextSize(50);
        // 设置文字对齐
        textPaint.setTextAlign(Paint.Align.CENTER);
        // 开启抗锯齿
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取 View 的中心坐标。例如宽度是263，结果是 263 / 2 = 131
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        // 计算圆形的半径，取视图宽高的最小值，并考虑边缘宽度。 半径126
        int radius = Math.min(getWidth(), getHeight()) / 2 - strokeWidth / 2;

        // 根据中心坐标和半径计算圆形的外接矩形区域
        RectF rectF = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        // 画灰色的圆
        canvas.drawArc(rectF, -90, 360, false, grayPaint);

        // 画粉色的进度圆
        int sweepAngle = (int) (360 * (progress / 100f));
        canvas.drawArc(rectF, -90, sweepAngle, false, pinkPaint);

    }

    /**
     * 增加进度
     */
    public void increaseProgress(int progress) {
        if (this.progress < 100) {
            // 增加进度
            this.progress += progress;
            // 重新绘制
            invalidate();
        }
    }

}
