package com.lishi.baijiaxing.home.adater;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.bean.HomeRecommendBean;

import java.util.List;

/**为你推荐adapter
 * Created by Administrator on 2016/6/7.
 */
public class GridRecommendAdapter extends BaseAdapter {
    private Context mContext;
    private List<HomeRecommendBean> mHomeRecommends;
    private LayoutInflater mInflater;

    public GridRecommendAdapter(Context context, List<HomeRecommendBean> datas) {
        mContext = context;
        mHomeRecommends = datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mHomeRecommends.size();
    }

    @Override
    public Object getItem(int position) {
        return mHomeRecommends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecommendViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_item_recyclerview_recommend, null);
            holder = new RecommendViewHolder();
            holder.iv_img = (ImageView) convertView.findViewById(R.id.iv_home_recommend);
            holder.tv_brief = (TextView) convertView.findViewById(R.id.tv_recommend_brief);
            holder.tv_newprice = (TextView) convertView.findViewById(R.id.tv_recommend_newprice);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_recommend_price);
            convertView.setTag(holder);
        } else {
            holder = (RecommendViewHolder) convertView.getTag();
        }

        HomeRecommendBean recommend = mHomeRecommends.get(position);
        holder.iv_img.setImageResource(recommend.getImgurl());
        holder.tv_brief.setText("" + recommend.getBrief());
        holder.tv_newprice.setText("￥" + recommend.getNewprice());
        holder.tv_price.setText("￥" + recommend.getPrice());
        //为textvie加删除线
        holder.tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);


        return convertView;
    }

    class RecommendViewHolder {
        private ImageView iv_img;
        private TextView tv_brief;
        private TextView tv_newprice;
        private TextView tv_price;

        public RecommendViewHolder() {

        }
    }
}
