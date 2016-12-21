package com.lishi.baijiaxing.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.GridView;

import com.lishi.baijiaxing.activity.MainActivity;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HorizontalGridView extends GridView {
    private Context mContext;
    private int width;
    private int viewWidth;

    public HorizontalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public HorizontalGridView(Context context) {
        this(context, null);
    }

    public HorizontalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = ((MainActivity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        viewWidth = (int) (width / 3.5);
        int childCount = getChildCount();
        int childWidth = viewWidth;
        int childHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, getResources().getDisplayMetrics());
        int lastPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(expandSpec, heightMeasureSpec);

        //设置GridView的宽度
        setMeasuredDimension(childCount * childWidth + lastPadding, childHeight);
    }
}
