package com.lishi.baijiaxing.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/12/19.
 */

public class WListView extends ListView {

    private float downY;
    private float y;
    private float mTouchSlop;

    public WListView(Context context) {
        super(context);
    }

    public WListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e(TAG, TAG + "dispatchTouchEvent");

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = ev.getRawY();
                y = downY;
                getParent().requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_MOVE:
                y = ev.getRawY();
                if (scrollToTop()) {
                    if (y - downY > mTouchSlop) {
                        /**
                         * Point 1 : 如果滑动到顶部，并且手指还想向下滑动，则事件交还给父控件，要求父控件可以拦截事件
                         */
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    } else if (y - downY < -mTouchSlop) {
                        /**
                         * Point 2 : 如果滑动到顶部，并且手指正常向上滑动，则事件由自己处理，要求父控件不许拦截事件
                         */
                        getParent().requestDisallowInterceptTouchEvent(true);

                    }

                }

                if (scrollToBottom()) {
                    if (y - downY < -mTouchSlop) {
                        /**
                         * Point 3 : 如果滑动到底部，并且手指还想向上滑动，则事件交还给父控件，要求父控件可以拦截事件
                         */
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return false;
                    } else if (y - downY > mTouchSlop) {
                        /**
                         * Point 4 : 如果滑动到底部，并且手指正常向下滑动，则事件由自己处理，要求父控件不许拦截事件
                         */
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }

                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
    
    public boolean scrollToBottom() {
        int first = getFirstVisiblePosition();
        int last = getLastVisiblePosition();
        int visibleCoutn = getChildCount();
        int count = getCount();
        if ((first + visibleCoutn) == count) {
            return true;
        }
        return false;
    }

    public boolean scrollToTop() {
        int first = getFirstVisiblePosition();
        int last = getLastVisiblePosition();
        int visibleCoutn = getChildCount();
        int count = getCount();

        if (first == 0) {
            return true;
        }
        return false;
    }
    
}
