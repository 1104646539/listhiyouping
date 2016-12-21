package com.lishi.baijiaxing.classify.Widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.classify.model.ClassOne;
import com.lishi.baijiaxing.classify.model.ThreeClassify;
import com.lishi.baijiaxing.classify.model.TwoClassify;

import java.util.List;

/**
 * 三级分类下的自定义gridview
 * Created by Administrator on 2016/6/12.
 */
public class ThreeClassifyGridView extends LinearLayout {
    //    private TwoClassify mData;
    private LayoutInflater mInflater;
    private TextView tv_title;
    private GridView mGridView;
    private ClassOne.DataBean mDatas;
    private Context mContext;

    public GridView getGridView() {
        return mGridView;
    }

    public ThreeClassifyGridView(Context context, ClassOne.DataBean data) {
        super(context);
        mContext = context;
        mDatas = data;
        mInflater = LayoutInflater.from(context);
//        datas = mData.getClassDatas();
        initView();
    }


    public void initView() {
        View rootView = mInflater.inflate(R.layout.classity_twoitem, null, false);

        tv_title = (TextView) rootView.findViewById(R.id.tv_classitythree_title);
        mGridView = (GridView) rootView.findViewById(R.id.gridview_classity);

        tv_title.setText(mDatas.getCategoryName());

        mGridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                if (mDatas == null || mDatas.getCategoryGoodsList() == null || mDatas.getCategoryGoodsList().size() == 0) {
                    return 0;
                }
                return mDatas.getCategoryGoodsList().size();
            }

            @Override
            public Object getItem(int position) {
                return mDatas.getCategoryGoodsList().get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                GridViewHolder gridViewHolder = null;
                if (convertView == null) {
                    gridViewHolder = new GridViewHolder();
                    convertView = mInflater.inflate(R.layout.classity_gridview_item, null, false);
                    gridViewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_classity_three);
                    gridViewHolder.mImageView = (ImageView) convertView.findViewById(R.id.iv_classity_three);
                    convertView.setTag(gridViewHolder);
                } else {
                    gridViewHolder = (GridViewHolder) convertView.getTag();
                }

                gridViewHolder.mTextView.setText(mDatas.getCategoryGoodsList().get(position).getName());
//                gridViewHolder.mImageView.setImageResource(R.drawable.classity_item);
                Glide.with(mContext).load(mDatas.getCategoryGoodsList().get(position).getPhotoUrl()).asBitmap()
                        .placeholder(R.drawable.classify_photo_140x160).dontAnimate().into(gridViewHolder.mImageView);

                return convertView;
            }

            class GridViewHolder {
                TextView mTextView;
                ImageView mImageView;
            }
        });

        addView(rootView, new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }


}
