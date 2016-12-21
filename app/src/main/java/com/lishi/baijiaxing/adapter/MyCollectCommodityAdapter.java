package com.lishi.baijiaxing.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class MyCollectCommodityAdapter extends BaseAdapter {
    private Context mContext;
    private List<CommodityBean> mCommodityBeen;
    private LayoutInflater mLayoutInflater;
    private boolean mFlag = false;

    public OnCheckChangeListener getOnCheckChangeListener() {
        return mOnCheckChangeListener;
    }

    public void setOnCheckChangeListener(OnCheckChangeListener onCheckChangeListener) {
        mOnCheckChangeListener = onCheckChangeListener;
    }

    private OnCheckChangeListener mOnCheckChangeListener;


    public MyCollectCommodityAdapter(Context context, List<CommodityBean> commodityBeen) {
        mCommodityBeen = commodityBeen;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCommodityBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mCommodityBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyCollectCommodityViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_mycollect_commodity, null, false);
            viewHolder = new MyCollectCommodityViewHolder();
            viewHolder.iv_brief = (ImageView) convertView.findViewById(R.id.iv_mycollect_photo);
            viewHolder.tv_info = (TextView) convertView.findViewById(R.id.tv_mycollect_title);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_mycollect_price);
            viewHolder.tv_addcrat = (TextView) convertView.findViewById(R.id.tv_mycollect_addcart);
            viewHolder.mCheckBox = (CheckBox) convertView.findViewById(R.id.checkbox_mycollect_commodity_isflag);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyCollectCommodityViewHolder) convertView.getTag();
        }
        CommodityBean commodityBean = mCommodityBeen.get(position);
        viewHolder.iv_brief.setImageResource(R.drawable.classity_item);
//        viewHolder.tv_info.setText(commodityBean.getCommTitle());
//        viewHolder.tv_price.setText(commodityBean.getCommPrice() + "");

        if (mFlag) {
            viewHolder.mCheckBox.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mCheckBox.setVisibility(View.INVISIBLE);
        }
        if (commodityBean.isChecked()){
            viewHolder.mCheckBox.setChecked(true);
        }else{
            viewHolder.mCheckBox.setChecked(false);
        }

        viewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    mCommodityBeen.get(position).setChecked(true);
                    if (mOnCheckChangeListener != null) {
                        mOnCheckChangeListener.onCheckChangeListener(position, true);
                        Log.i("asf","第"+position+"个选择了true");
                    }
                } else {
                    mCommodityBeen.get(position).setChecked(false);
                    if (mOnCheckChangeListener != null) {
                        mOnCheckChangeListener.onCheckChangeListener(position, false);
                        Log.i("asf","第"+position+"个选择了false");
                    }
                }
            }
        });
        return convertView;
    }

    class MyCollectCommodityViewHolder {
        ImageView iv_brief;
        TextView tv_info;
        TextView tv_price;
        TextView tv_addcrat;
        CheckBox mCheckBox;
    }

    public void ShowCheck(boolean flag) {
        mFlag = flag;
    }

    public interface OnCheckChangeListener {
        void onCheckChangeListener(int position, boolean checked);
    }
}
