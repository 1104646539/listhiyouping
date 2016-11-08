package com.lishi.baijiaxing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2016/10/25.
 */
public class HintProgressBar extends ProgressBar {
    private String str = "";
    private Paint mPaint;
    private Paint mTextPaint;
    private int textSize = 10;

    public HintProgressBar(Context context) {
        this(context, null);
        init();
    }

    public HintProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public HintProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public synchronized void setProgress(int progress) {
        super.setProgress(progress);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, getResources().getDisplayMetrics()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int progress = getProgress();
        int max = getMax();
        str = progress + "%";

        int width = getMeasuredWidth() + getPaddingLeft() + getPaddingRight();
        int height = getMeasuredHeight();
        int radius = height / 2;

        float bai = (float) progress / (float) max;
        int start = (int) (width * bai);

        int textWidth = (int) mTextPaint.measureText(str);

        if (start + radius >= width) {
            start = width - radius;
            
        } 
        //画圆
        canvas.drawCircle(start, height / 2, radius, mPaint);

        //绘制%进度
        canvas.drawText(str, 0, str.length(), start - textWidth / 2, height / 2 + textSize, mTextPaint);

    }
}
