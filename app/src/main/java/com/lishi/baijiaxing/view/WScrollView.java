package com.lishi.baijiaxing.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/19.
 */

public class WScrollView extends ScrollView {
    public WScrollView(Context context) {
        super(context);
    }

    public WScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private List list = new ArrayList();
    private int scrollPaddingTop; // scrollview的顶部内边距
    private int scrollPaddingLeft;// scrollview的左侧内边距
    private int[] scrollLoaction = new int[2]; // scrollview在窗口中的位置
    private final static int UPGLIDE = 0;
    private final static int DOWNGLIDE = 1;
    private int glideState;

    private int downY = 0;
    private int moveY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
                //System.out.println("actiondown" + ev.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) ev.getY();
                //System.out.println("move" + moveY + "down" + downY);
                if ((moveY - downY) >= 0) {
                    //System.out.println("'''''''''DOWNGLIDE'''''''''''");
                    glideState = DOWNGLIDE;
                } else {
                    //System.out.println("'''''''''UPGLIDE'''''''''''");
                    glideState = UPGLIDE;
                }
                break;
            case MotionEvent.ACTION_UP:
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 该事件的xy是以scrollview的左上角为00点而不是以窗口为00点
        int x = (int) ev.getX() + scrollLoaction[0];
        int y = (int) ev.getY() + scrollLoaction[1];
        for (int i = 0; i < list.size(); i++) {
            ListView listView = (ListView) list.get(i);
            int[] location = new int[2];
            listView.getLocationInWindow(location);
            int width = listView.getWidth();
            int height = listView.getHeight();
            // 在listview的位置之内则可以滑动
            if (x >= location[0] + scrollPaddingLeft
                    && x <= location[0] + scrollPaddingLeft + width
                    && y >= location[1] + scrollPaddingTop
                    && y <= location[1] + scrollPaddingTop + height) {
                //System.out.println(glideState);
                if (((listView.getLastVisiblePosition() == (listView.getCount() - 1)) && (glideState == UPGLIDE))) {
                    //System.out.println("up");
                    break;
                }
                if (((listView.getFirstVisiblePosition() == 0) && (glideState == DOWNGLIDE))) {
                    //System.out.println("down");
                    break;
                }
                return false; //让子控件直接处理
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }


    private void findAllListView(View view) {
        if (view instanceof ViewGroup) {
            int count = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < count; i++) {
                if (!(view instanceof ListView)) {
                    findAllListView(((ViewGroup) view).getChildAt(i));
                }
            }
            if (view instanceof ListView) {
                list.add((ListView) view);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        scrollPaddingTop = getTop();
        scrollPaddingLeft = getLeft();
        getLocationInWindow(scrollLoaction);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.getChildCount() != 1) {
            try {
                throw new ScrollException();
            } catch (ScrollException e) {
                e.printStackTrace();
            }
        }
        list.clear();
        findAllListView(this.getChildAt(0));
    }
}
