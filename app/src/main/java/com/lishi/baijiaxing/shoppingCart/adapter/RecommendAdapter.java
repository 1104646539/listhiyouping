package com.lishi.baijiaxing.shoppingCart.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.shoppingCart.model.SCRecommendList;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

/**
 * Created by Administrator on 2016/6/7.
 */
public class RecommendAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private SCRecommendList mRecommends;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    public RecommendAdapter(Context context, SCRecommendList recommendList) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mRecommends = recommendList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecommendHolder recommendHolder = new RecommendHolder(mInflater.inflate(R.layout.home_item_recyclerview_recommend, parent, false));

        return recommendHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecommendHolder mRecommendHolder = (RecommendHolder) holder;
        SCRecommendList.DataBean recommend = mRecommends.getData().get(position);
        Glide.with(mContext).load(recommend.getPhotoUrl()).placeholder(R.drawable.home_commodity_349x256).into(mRecommendHolder.iv_img);
        mRecommendHolder.tv_brief.setText("" + recommend.getName());
        mRecommendHolder.tv_newprice.setText("￥" + recommend.getOldPrice());
        mRecommendHolder.tv_price.setText("￥" + recommend.getNowPrice());
        //为textvie加删除线
        mRecommendHolder.tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return mRecommends.getData().size();
    }

    class RecommendHolder extends RecyclerView.ViewHolder {
        private ImageView iv_img;
        private TextView tv_brief;
        private TextView tv_newprice;
        private TextView tv_price;


        public RecommendHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_home_recommend);
            tv_brief = (TextView) itemView.findViewById(R.id.tv_recommend_brief);
            tv_newprice = (TextView) itemView.findViewById(R.id.tv_recommend_newprice);
            tv_price = (TextView) itemView.findViewById(R.id.tv_recommend_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClickListener(v, getPosition());
                    }
                }
            });
        }
    }

    public YiYuanHotAdapter.OnItemClickListener getOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
