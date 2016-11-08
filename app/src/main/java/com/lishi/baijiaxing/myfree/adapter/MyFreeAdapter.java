package com.lishi.baijiaxing.myfree.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.myfree.model.MyFreeBean;
import com.lishi.baijiaxing.free.model.FreeCommodityBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/28.
 */
public class MyFreeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<MyFreeBean> mMyFreeBeens;
    private LayoutInflater mLayoutInflater;

    public MyFreeAdapter(Context context, List<MyFreeBean> myFreeBeen) {
        this.mContext = context;
        this.mMyFreeBeens = myFreeBeen;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyFreeViewHolder(mLayoutInflater.inflate(R.layout.myfree_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyFreeViewHolder) {
            MyFreeViewHolder viewHolder = (MyFreeViewHolder) holder;
            MyFreeBean myFreeBean = mMyFreeBeens.get(position);
            viewHolder.iv_photo.setImageResource(R.drawable.myfree_photo);
            viewHolder.tv_participationNum.setText(myFreeBean.getParticipationNum() + "");
            viewHolder.tv_name.setText(myFreeBean.getName());

            switch (position) {
                case FreeCommodityBean.TYPE_BE_BEING_APPLY_NOT:
                    break;
                case FreeCommodityBean.TYPE_BE_BEING_APPLY_OK:
                    viewHolder.tv_type.setText("已申请");
                    break;
                case FreeCommodityBean.TYPE_FINISH:
                    viewHolder.tv_type.setText("已结束");
                    break;
            }

        }

    }

    @Override
    public int getItemCount() {
        return mMyFreeBeens.size();
    }

    class MyFreeViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_participationNum;
        TextView tv_type;
        ImageView iv_photo;


        public MyFreeViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.myfree_name);
            tv_participationNum = (TextView) itemView.findViewById(R.id.myfree_participationNum);
            tv_type = (TextView) itemView.findViewById(R.id.myfree_type);
            iv_photo = (ImageView) itemView.findViewById(R.id.myfree_photo);
        }
    }
}
