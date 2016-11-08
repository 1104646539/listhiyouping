package com.lishi.baijiaxing.shoppingCart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<CommodityBean> mCommodityBeen;
    private LayoutInflater mLayoutInflater;
    private OnItemClick mOnItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        mOnItemClick = onItemClick;
    }

    public ShoppingCartAdapter(Context context, ArrayList<CommodityBean> commodityBeens) {
        this.mCommodityBeen = commodityBeens;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShoppingCartViewHolder(mLayoutInflater.inflate(R.layout.item_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ShoppingCartViewHolder viewHolder = (ShoppingCartViewHolder) holder;
        viewHolder.photo.setImageResource(R.drawable.classity_item);
        viewHolder.mCheckBox.setChecked(mCommodityBeen.get(position).isChecked());
        viewHolder.price.setText(mCommodityBeen.get(position).getCommPrice() + "");
        viewHolder.num.setText(mCommodityBeen.get(position).getCommNum() + "");
        viewHolder.iv_cartlist_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick != null) {
                    mOnItemClick.onCommodityPlus(v, position);
                }
            }
        });

        viewHolder.iv_cartlist_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick != null) {
                    mOnItemClick.onCommodityMinus(v, position);
                }
            }
        });

        viewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick != null) {
                    if (viewHolder.mCheckBox.isChecked()) {
                        mOnItemClick.onCommodityCheck(true, position);
                        Log.e("onCommodityCheck", "onCommodityCheck=" + position + "false");
                    } else {
                        mOnItemClick.onCommodityCheck(false, position);
                        Log.e("onCommodityCheck", "onCommodityCheck=" + position + "true");
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCommodityBeen.size();
    }

    class ShoppingCartViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, num;
        CheckBox mCheckBox;
        ImageView photo;
        ImageButton iv_cartlist_minus, iv_cartlist_plus;


        public ShoppingCartViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.shoppingcart_commodity_name);
            price = (TextView) itemView.findViewById(R.id.shoppingcart_commodity_price);
            mCheckBox = (CheckBox) itemView.findViewById(R.id.shoppingcart_commodity_checkbox);
            photo = (ImageView) itemView.findViewById(R.id.shoppingcart_commodity_photo);
            iv_cartlist_minus = (ImageButton) itemView.findViewById(R.id.shoppingcart_commodity_minus);
            iv_cartlist_plus = (ImageButton) itemView.findViewById(R.id.shoppingcart_commodity_plus);
            num = (TextView) itemView.findViewById(R.id.shoppingcart_commodity_number);
        }
    }

    public interface OnItemClick {
        void onCommodityMinus(View v, int position);

        void onCommodityPlus(View v, int position);

        void onCommodityCheck(boolean isChecked, int position);
    }
}
