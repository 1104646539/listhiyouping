package com.lishi.baijiaxing.adapter;

import android.content.Context;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.bean.CouponsBean;

import java.util.List;

/**
 * 优惠券适配器
 * Created by Administrator on 2016/7/21.
 */
public class CouponsAdapter extends BaseAdapter {
    private Context mContext;
    private List<CouponsBean> mCouponsBeen;
    private LayoutInflater mLayoutInflater;

    public CouponsAdapter(Context context, List<CouponsBean> couponsBeen) {
        this.mContext = context;
        this.mCouponsBeen = couponsBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCouponsBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mCouponsBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CouponsViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_coupons, null, false);
            viewHolder = new CouponsViewHolder();
            viewHolder.tv_astrict_local = (TextView) convertView.findViewById(R.id.tv_item_coupons_astrict_local);
            viewHolder.tv_astrict_price = (TextView) convertView.findViewById(R.id.tv_item_coupons_astrict_money);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_item_coupons_date);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_item_coupons_money);
            viewHolder.tv_isAvailable = (TextView) convertView.findViewById(R.id.tv_item_coupons_isAvailable);
            viewHolder.mRelativeLayout = (RelativeLayout) convertView.findViewById(R.id.relative_coupons);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CouponsViewHolder) convertView.getTag();
        }
        CouponsBean couponsBean = mCouponsBeen.get(position);
        viewHolder.tv_price.setText("" + couponsBean.getMoney());
        viewHolder.tv_astrict_price.setText("满" + couponsBean.getAstrict_money() + "可用");
        TextPaint p = viewHolder.tv_price.getPaint();
        p.setFakeBoldText(true);
        viewHolder.tv_astrict_local.setText(couponsBean.getAstrict_local());
        if (couponsBean.isAvailable()) {//已过期
            viewHolder.tv_isAvailable.setVisibility(View.INVISIBLE);
            viewHolder.mRelativeLayout.setBackgroundResource(R.drawable.coupon);
        } else {//可用
            viewHolder.tv_isAvailable.setVisibility(View.VISIBLE);
            viewHolder.mRelativeLayout.setBackgroundResource(R.drawable.coupon_past);
        }

        return convertView;
    }

    class CouponsViewHolder {
        TextView tv_price;//优惠多少
        TextView tv_date;//优惠有效期
        TextView tv_astrict_local;//优惠的地方
        TextView tv_astrict_price;//多少钱以上才可用
        TextView tv_isAvailable;//是否可用（在有效期内）
        RelativeLayout mRelativeLayout;

    }
}
