package com.lishi.baijiaxing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.inter.OnAddressChange;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class DeliveryAddressAdapter extends BaseAdapter {
    private Context mContext;
    private List<DeliveryAddressBean> mDeliveryAddressBeen;
    private LayoutInflater mLayoutInflater;

    public DeliveryAddressAdapter(Context context, List<DeliveryAddressBean> deliveryAddressBeen) {
        this.mContext = context;
        this.mDeliveryAddressBeen = deliveryAddressBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mDeliveryAddressBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return mDeliveryAddressBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public OnAddressChange getOnAddressChange() {
        return mOnAddressChange;
    }

    public void setOnAddressChange(OnAddressChange onAddressChange) {
        mOnAddressChange = onAddressChange;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DeliveryAddressViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_deliveryaddress, null, false);
            viewHolder = new DeliveryAddressViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_deliveryaddress_name);
            viewHolder.tv_number = (TextView) convertView.findViewById(R.id.tv_deliveryaddress_number);
            viewHolder.tv_address = (TextView) convertView.findViewById(R.id.tv_deliveryaddress_address);
            viewHolder.tv_default = (TextView) convertView.findViewById(R.id.tv_deliveryaddress_default);
            viewHolder.mCheckBox = (CheckBox) convertView.findViewById(R.id.checkbox_deliveryaddress);
            viewHolder.left = (RelativeLayout) convertView.findViewById(R.id.left_address_check);
            viewHolder.right = (TextView) convertView.findViewById(R.id.right_address_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (DeliveryAddressViewHolder) convertView.getTag();
        }
        viewHolder.left.setVisibility(View.VISIBLE);
        viewHolder.right.setVisibility(View.VISIBLE);
        viewHolder.right.setClickable(true);
        final DeliveryAddressBean deliveryAddressBean = mDeliveryAddressBeen.get(position);
        if (deliveryAddressBean.isChecked()) {
            viewHolder.tv_default.setVisibility(View.VISIBLE);
        } else {
            viewHolder.tv_default.setVisibility(View.INVISIBLE);
        }
        viewHolder.tv_number.setText(deliveryAddressBean.getNumber() + "");
        viewHolder.tv_name.setText("收货人：" + deliveryAddressBean.getName());
        viewHolder.tv_address.setText("收货地址:" + deliveryAddressBean.toString());

        if (deliveryAddressBean.isChecked()) {
            viewHolder.mCheckBox.setChecked(true);
        } else {
            viewHolder.mCheckBox.setChecked(false);
        } 
        //选中设为默认地址
        viewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deliveryAddressBean.isChecked()) {
                    if (mOnAddressChange != null) {
                        mOnAddressChange.onCheckChangeListener(position, false);
                    }
                } else {
                    if (mOnAddressChange != null) {
                        mOnAddressChange.onCheckChangeListener(position, true);
                    }
                }
            }
        });
        //编辑
        viewHolder.right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnAddressChange != null) {
                    mOnAddressChange.onEditClickListener(position);
            }
        }
    }

    );

    return convertView;
}

class DeliveryAddressViewHolder {
    TextView tv_default;
    TextView tv_name;
    TextView tv_number;
    TextView tv_address;
    CheckBox mCheckBox;
    RelativeLayout left;
    TextView right;
}
    private OnAddressChange mOnAddressChange;

}
