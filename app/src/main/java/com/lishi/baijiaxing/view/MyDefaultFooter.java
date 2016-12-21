package com.lishi.baijiaxing.view;

/**
 * Created by Administrator on 2016/12/19.
 */

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liaoinstan.springview.container.BaseFooter;

/**
 * Created by Administrator on 2016/3/21.
 */
public class MyDefaultFooter extends BaseFooter {
    private Context context;
    private int rotationSrc;
    private TextView footerTitle;
    private ProgressBar footerProgressbar;
    private boolean isMoreLoad = true;//是否还可以加载

    public MyDefaultFooter(Context context) {
        this(context, com.liaoinstan.springview.R.drawable.progress_small);
    }

    public MyDefaultFooter(Context context, int rotationSrc) {
        this.context = context;
        this.rotationSrc = rotationSrc;
    }

    @Override
    public View getView(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(com.liaoinstan.springview.R.layout.default_footer, viewGroup, true);
        footerTitle = (TextView) view.findViewById(com.liaoinstan.springview.R.id.default_footer_title);
        footerProgressbar = (ProgressBar) view.findViewById(com.liaoinstan.springview.R.id.default_footer_progressbar);
        footerProgressbar.setIndeterminateDrawable(ContextCompat.getDrawable(context, rotationSrc));
        return view;
    }

    @Override
    public void onPreDrag(View rootView) {
    }

    @Override
    public void onDropAnim(View rootView, int dy) {
    }

    @Override
    public void onLimitDes(View rootView, boolean upORdown) {
        if (isMoreLoad) {
            if (upORdown) {
                footerTitle.setText("松开载入更多");
            } else {
                footerTitle.setText("查看更多");
            }
        }else{
            footerTitle.setText("已经是最后一页了");
        }
    }

    @Override
    public void onStartAnim() {
        footerTitle.setVisibility(View.INVISIBLE);
        footerProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFinishAnim() {
        footerTitle.setText("查看更多");
        footerTitle.setVisibility(View.VISIBLE);
        footerProgressbar.setVisibility(View.INVISIBLE);
    }

    public TextView getFooterTitle() {
        return footerTitle;
    }

    public void setMoreLoad(boolean moreLoad) {
        isMoreLoad = moreLoad;
    }
}