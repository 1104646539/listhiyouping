package com.lishi.baijiaxing.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * 可以让
 * Created by Administrator on 2016/6/6.
 */
public class MyScrollView extends ScrollView {
    private onScrollViewListener mOnScrollViewListener = null;

    private int downX;
    private int downY;
    private int mTouchSlop;


    public MyScrollView(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }


    public void setScrollViewChangeListener(onScrollViewListener onScrollViewListener) {
        mOnScrollViewListener = onScrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollViewListener != null) {
            mOnScrollViewListener.onScrollViewChange(this, l, t, oldl, oldt);
        }
    }

    public interface onScrollViewListener {
        void onScrollViewChange(MyScrollView view, int x, int y, int oldx, int oldy);
    }

//    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent e) {
//        int action = e.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                downX = (int) e.getRawX();
//                downY = (int) e.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int moveY = (int) e.getRawY();
//                if (Math.abs(moveY - downY) > mTouchSlop) {
//                    return true;
//                }
//        }
//        return super.onInterceptTouchEvent(e);
//    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.i("overScrollBy", "deltaX=" + deltaX + "deltaY=" + deltaY + "scrollX=" + scrollX
                + "scrollY=" + scrollY + "scrollRangeX=" + scrollRangeX + "scrollRangeY=" + scrollRangeY
                + "maxOverScrollX=" + maxOverScrollX + "maxOverScrollY=" + maxOverScrollY + "isTouchEvent=" + isTouchEvent);
        if (deltaY > 0) {
            if (mOnLoadMore != null && scrollRangeY - 20 >= scrollY) {
                mOnLoadMore.onLoadMore();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    private OnLoadMore mOnLoadMore;

    public void setOnLoadMore(OnLoadMore onLoadMore) {
        mOnLoadMore = onLoadMore;
    }

    public interface OnLoadMore {
        void onLoadMore();
    }
}
