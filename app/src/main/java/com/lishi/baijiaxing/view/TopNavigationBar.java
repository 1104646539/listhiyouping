package com.lishi.baijiaxing.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;

/**
 * 顶部导航栏
 * Created by Administrator on 2016/7/4.
 */
public class TopNavigationBar extends RelativeLayout {
    private TextView tv_title;
    private ImageView iv_left, iv_right;
    private String text_title;
    private Drawable left_iv, right_iv;
    private float title_size;
    private int title_color;
    private OnTopClick onTopClick;
    private LayoutParams left_params, right_params, title_params;

    public String getText_title() {
        return text_title;
    }

    public void setText_title(String text_title) {
        this.text_title = text_title;
        tv_title.setText(text_title);
    }

    public void setOnTopClick(OnTopClick onTopClick) {
        this.onTopClick = onTopClick;
    }

    public TopNavigationBar(Context context) {
        super(context, null);
    }


    public TopNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        //获取Xml属性
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.TopNavigationBar);

        text_title = typeArray.getString(R.styleable.TopNavigationBar_text_title);
        title_size = typeArray.getDimension(R.styleable.TopNavigationBar_title_size, 0);
        title_color = typeArray.getColor(R.styleable.TopNavigationBar_title_color, Color.BLACK);

        left_iv = typeArray.getDrawable(R.styleable.TopNavigationBar_left_src);

        right_iv = typeArray.getDrawable(R.styleable.TopNavigationBar_right_src);

        typeArray.recycle();

//        View root = LayoutInflater.from(context).inflate(R.layout.topnavigationbar, null, false);
//
//        iv_left = (ImageView) root.findViewById(R.id.iv_topnavigation_left);
//        iv_right = (ImageView) root.findViewById(R.id.iv_topnavigation_right);
//        tv_title = (TextView) root.findViewById(R.id.tv_topnavigation_title);
        iv_left = new ImageView(context);
        iv_right = new ImageView(context);
        tv_title = new TextView(context);
        tv_title.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});

        //设置到控件上
        tv_title.setText(text_title + "");
        tv_title.setTextColor(title_color);
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        Log.i("size", "size___________________+++++++++++++++++++++++++++++++++" + title_size);
        if (left_iv == null) {
            iv_left.setVisibility(View.GONE);
        } else {
            iv_left.setVisibility(View.VISIBLE);
            iv_left.setImageDrawable(left_iv);
            iv_left.setClickable(true);
            iv_left.setPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()), 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()), 0);
            iv_left.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTopClick != null) {
                        onTopClick.onTopLeftClick(v);
                    }
                }
            });
        }
        if (right_iv == null) {
            iv_right.setVisibility(View.GONE);
        } else {
            iv_left.setVisibility(View.VISIBLE);
            iv_right.setClickable(true);
            iv_right.setImageDrawable(right_iv);
            iv_right.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onTopClick != null) {
                        onTopClick.onTopRightClick(v);
                    }
                }
            });

        }
        //设置控件的位置
        left_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        left_params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        left_params.addRule(RelativeLayout.CENTER_VERTICAL);
//        getResources().getDimensionPixelSize(R.dimen.topnatigationbar_left)
        left_params.setMargins((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, getResources().getDimensionPixelSize(R.dimen.topnatigationbar_left), getResources().getDisplayMetrics()), 0, 0, 0);
        addView(iv_left, left_params);

        title_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        title_params.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE);
        title_params.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(tv_title, title_params);

        right_params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        right_params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        right_params.addRule(RelativeLayout.CENTER_VERTICAL);
        right_params.setMargins(0, 0, 40, 0);
        addView(iv_right, right_params);
    }


    public interface OnTopClick {
        void onTopLeftClick(View view);

        void onTopRightClick(View view);
    }

}
