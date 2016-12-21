package com.lishi.baijiaxing.home.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

import com.lishi.baijiaxing.view.MyScrollView;

/**
 * Created by Administrator on 2016/11/29.
 */

public class HomeScrollView extends ScrollView {
    private HomeScrollView.onScrollViewListener mOnScrollViewListener = null;

    public HomeScrollView(Context context) {
        super(context);
    }

    public HomeScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setScrollViewChangeListener(HomeScrollView.onScrollViewListener onScrollViewListener) {
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
        void onScrollViewChange(HomeScrollView view, int x, int y, int oldx, int oldy);
    }
}
