package com.lishi.baijiaxing.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.bean.HomeRecommendBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7.
 */
public class RecommendAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<HomeRecommendBean> mRecommends;


    public RecommendAdapter(Context context, List<HomeRecommendBean> recommends) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mRecommends = recommends;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecommendHolder recommendHolder = new RecommendHolder(mInflater.inflate(R.layout.home_item_recyclerview_recommend, null));

        return recommendHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecommendHolder mRecommendHolder = (RecommendHolder) holder;
        HomeRecommendBean recommend = mRecommends.get(position);
        mRecommendHolder.iv_img.setImageResource(recommend.getImgurl());
        mRecommendHolder.tv_brief.setText(""+recommend.getBrief());
        mRecommendHolder.tv_newprice.setText("￥"+recommend.getNewprice());
        mRecommendHolder.tv_price.setText("￥"+recommend.getPrice());
        //为textvie加删除线
        mRecommendHolder.tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        
    }

    @Override
    public int getItemCount() {
        return mRecommends.size();
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
                    
        }
    }
}
