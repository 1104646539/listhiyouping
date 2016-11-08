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
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class MyCollectStoreAdapter extends BaseAdapter {
    private Context mContext;
    private List<StoreBean> mStoreBeen;
    private LayoutInflater mLayoutInflater;
    private boolean mFlag = false;

    public MyCollectCommodityAdapter.OnCheckChangeListener getOnCheckChangeListener() {
        return mOnCheckChangeListener;
    }

    public void setOnCheckChangeListener(MyCollectCommodityAdapter.OnCheckChangeListener onCheckChangeListener) {
        mOnCheckChangeListener = onCheckChangeListener;
    }

    private MyCollectCommodityAdapter.OnCheckChangeListener mOnCheckChangeListener;

    public MyCollectStoreAdapter(Context context, List<StoreBean> storeBeens) {
        mContext = context;
        mStoreBeen = storeBeens;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mStoreBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mStoreBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyCollectStoreViewHolder holder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_mycollect_store, null, false);
            holder = new MyCollectStoreViewHolder();
            holder.iv_photo = (ImageView) convertView.findViewById(R.id.iv_mycollect_store_photo);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_mycollect_storename);
            holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.checkbox_mycollect_store_isflag);
            convertView.setTag(holder);

        } else {
            holder = (MyCollectStoreViewHolder) convertView.getTag();
        }
        
        holder.iv_photo.setImageResource(R.drawable.classity_item);
        holder.tv_title.setText(mStoreBeen.get(position).getCommStore());
        
        if (mFlag) {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        }
        if (mStoreBeen.get(position).isChecked()) {
            holder.mCheckBox.setChecked(true);
        } else {
            holder.mCheckBox.setChecked(false);
        }
        

        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    mStoreBeen.get(position).setChecked(true);
                    if (mOnCheckChangeListener != null) {
                        mOnCheckChangeListener.onCheckChangeListener(position, true);
                        Log.i("asf", "第" + position + "个选择了true");
                    }
                } else {
                    mStoreBeen.get(position).setChecked(false);
                    if (mOnCheckChangeListener != null) {
                        mOnCheckChangeListener.onCheckChangeListener(position, false);
                        Log.i("asf", "第" + position + "个选择了false");
                    }
                }
            }
        });


        return convertView;
    }

    class MyCollectStoreViewHolder {
        ImageView iv_photo;
        TextView tv_title;
        CheckBox mCheckBox;
    }
    public void ShowCheck(boolean flag) {
        mFlag = flag;
    }

}
