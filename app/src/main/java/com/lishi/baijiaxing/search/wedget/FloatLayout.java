package com.lishi.baijiaxing.search.wedget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/15.
 */

public class FloatLayout extends ViewGroup {
    private Context mContext;

    public FloatLayout(Context context) {
        this(context, null);
    }

    public FloatLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attr) {
        return new MarginLayoutParams(mContext, attr);
    }

    /**
     * 记录全部View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<>();
    /**
     * 记录每一行的行高
     */
    private List<Integer> mLineHeight = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();

        int width = getWidth();

        int lineWidht = 0;
        int lineHeight = 0;

        /**
         * 存储每一行的View
         */
        List<View> mLineViews = new ArrayList<>();

        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {

            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight();
            int childWidth = child.getMeasuredWidth();
            /**
             * 如果已经需要换行
             */
            if (childWidth + lp.leftMargin + lp.rightMargin + lineWidht > width) {
                /**
                 *记录这一行的View和行高,并开启新的list记录下一行的View 
                 */
                mAllViews.add(mLineViews);
                mLineHeight.add(lineHeight);
                lineWidht = 0;//重置行宽
                mLineViews = new ArrayList<>();
            }
            /**
             * 如果不需要换行,则累加
             */
            lineWidht += childWidth + lp.rightMargin + lp.leftMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin + lp.bottomMargin);
            mLineViews.add(child);
        }

        /**
         *记录最后一行
         */
        mLineHeight.add(lineHeight);
        mAllViews.add(mLineViews);


        int left = 0;
        int top = 0;

        for (int i = 0; i < mAllViews.size(); i++) {
            /**
             * 获取每一行的View和行高
             */
            mLineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);
            /**
             * 遍历当前行的所有View
             */
            for (int j = 0; j < mLineViews.size(); j++) {
                View child = mLineViews.get(j);
                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                /**
                 * 计算child的left,top,right,bottom
                 */
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                child.layout(lc, tc, rc, bc);
                /**
                 * 更新下一个View的left坐标，累加width
                 */
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            /**
             * 换行时将left清零，top则加上当前行高
             */
            left = 0;
            top += lineHeight;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 获取父容器给的大小和测量模式
         */
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        /**
         * 容器的宽高
         */
        int width = 0;
        int height = 0;

        /**
         * 行宽,不断取最大宽度
         */
        int lineWidth = 0;
        /**
         * 行高，不断累加 
         */
        int lineHeight = 0;

        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            /**
             * 测量每个子view的大小
             */
            View child = getChildAt(i);
            final int finalI = i;
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClickListener(v, finalI);
                    }
                }
            });
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            /**
             * 获取LayoutParams
             */
            MarginLayoutParams mp = (MarginLayoutParams) child.getLayoutParams();

            /**
             * 获取子View的实际宽高
             */
            int childWidth = child.getMeasuredWidth() + mp.leftMargin + mp.rightMargin;
            int childHeight = child.getMeasuredHeight() + mp.topMargin + mp.bottomMargin;
            /**
             * 如果加入子View后的宽度大于ViewGroup的宽度，则累加高度，开启下一行（当前行的宽度装不下子View了）
             * ViewGroup的宽度取最大宽度
             */
            if (lineWidth + childWidth > wSize) {
                width = Math.max(lineWidth, childWidth);//取最大值
                lineWidth = childWidth;//开启新行
                height += lineHeight;//累加ViewGroup高度
                lineHeight = childHeight;//记录下一行的高度
                /**
                 * 如果不超过，就将View放入当前行中
                 */
            } else {
                lineWidth += childWidth;//累加ViewGroup宽度
                lineHeight = Math.max(childHeight, lineHeight);//行高取当前行高和加入的View的高度的最大值
            }
            /**
             * 如果是最后一个,将当前行的宽度和ViewGroup的宽度取最大
             */
            if (i == cCount - 1) {
                width = Math.max(lineWidth, width);//取最大值
                height += lineHeight;
            }

        }
        /**
         * 如果父容器已经设置了具体值，就用具体值，否则就用自己测量的值
         */
        int nWidth = wMode == MeasureSpec.EXACTLY ? wSize : width;
        int nHeight = hMode == MeasureSpec.EXACTLY ? hSize : height;
        setMeasuredDimension(nWidth, nHeight);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClickListener(View v, int position);
    }
}
