package com.lishi.baijiaxing.Address;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.bean.RobBean;

import java.util.List;

/**
 * 秒杀
 * Created by Administrator on 2016/7/28.
 */
public class RobAdapter extends BaseAdapter {
    private Context mContext;
    private List<RobBean> mRobBeans;
    private LayoutInflater mLayoutInflater;

    public RobAdapter(Context context, List<RobBean> robBeans) {
        this.mContext = context;
        this.mRobBeans = robBeans;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mRobBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mRobBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RobViewHolder holder = null;
        if (convertView == null) {
            holder = new RobViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.item_rob, null, false);
            holder.iv_photo = (ImageView) convertView.findViewById(R.id.iv_rob_photo);
            holder.iv_rob = (ImageView) convertView.findViewById(R.id.iv_rob_rob_state);
            holder.iv_isavailable = (ImageView) convertView.findViewById(R.id.iv_rob_item_isavailable);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_rob_item_title);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_rob_item_price);
            holder.tv_old_price = (TextView) convertView.findViewById(R.id.tv_rob_item_old_price);
            holder.tv_sold_number = (TextView) convertView.findViewById(R.id.tv_rob_item_soldnumber);
            holder.tv_isavailable_true = (TextView) convertView.findViewById(R.id.tv_rob_item_number_true);
            holder.tv_isavailable_false = (TextView) convertView.findViewById(R.id.tv_rob_item_number_false);
            holder.tv_sold_out = (TextView) convertView.findViewById(R.id.tv_rob_item_number_true2);
            holder.mProgressBar = (ProgressBar) convertView.findViewById(R.id.progressbar_rob_item_number);
            holder.iv_hot = (ImageView) convertView.findViewById(R.id.iv_rob_item_hot);

            convertView.setTag(holder);
        } else {
            holder = (RobViewHolder) convertView.getTag();
        }
        RobBean robBean = mRobBeans.get(position);

        if (robBean.isRob()) {//是否是秒杀
            holder.iv_rob.setVisibility(View.VISIBLE);
        } else {
            holder.iv_rob.setVisibility(View.INVISIBLE);
        }
        if (robBean.isTop()) {//是否是热门
            holder.iv_hot.setVisibility(View.VISIBLE);
        } else {
            holder.iv_hot.setVisibility(View.INVISIBLE);
        }
        if (robBean.isavailable()) {//是否还有货
            holder.mProgressBar.setVisibility(View.VISIBLE);
            holder.tv_sold_out.setVisibility(View.INVISIBLE);
            holder.iv_isavailable.setVisibility(View.INVISIBLE);
            holder.tv_sold_number.setVisibility(View.VISIBLE);
            holder.tv_isavailable_true.setVisibility(View.VISIBLE);
            holder.tv_isavailable_false.setVisibility(View.INVISIBLE);
        } else {
            holder.mProgressBar.setVisibility(View.INVISIBLE);
            holder.tv_sold_out.setVisibility(View.VISIBLE);
            holder.iv_isavailable.setVisibility(View.VISIBLE);
            holder.tv_sold_number.setVisibility(View.INVISIBLE);
            holder.tv_isavailable_true.setVisibility(View.INVISIBLE);
            holder.tv_isavailable_false.setVisibility(View.VISIBLE);
        }

        holder.iv_photo.setImageResource(R.drawable.rob_photo);
        holder.tv_title.setText(robBean.getCommodityBean().getCommTitle());
        holder.tv_price.setText("￥" + robBean.getPrice());
        TextPaint textPaint = holder.tv_price.getPaint();
        textPaint.setFakeBoldText(true);//字体加粗
        holder.tv_old_price.setText("￥" + robBean.getOld_price());
        holder.tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//加删除线
        holder.tv_sold_number.setText("已出售" + robBean.getSold_number() + "件");
        holder.tv_sold_out.setText(robBean.getNumber() + "件被抢光了");
        return convertView;
    }

    class RobViewHolder {
        private ImageView iv_photo, iv_rob, iv_isavailable, iv_hot;
        private TextView tv_title, tv_price, tv_old_price, tv_sold_number;
        private TextView tv_isavailable_true, tv_isavailable_false, tv_sold_out;
        private ProgressBar mProgressBar;
    }
}
