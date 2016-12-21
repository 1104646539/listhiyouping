package com.lishi.baijiaxing.home.adater;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.MainActivity;
import com.lishi.baijiaxing.home.model.HomeSeckilBean;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.List;

/**
 * 首页秒杀adapter
 * Created by Administrator on 2016/6/2.
 */
public class HomeSeckilAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<HomeSeckilBean> mSeckilBeans;
    int width;
    int viewWidth;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    public HomeSeckilAdapter(Context context, List<HomeSeckilBean> seckilBeans) {
        this.mContext = context;
        this.mSeckilBeans = seckilBeans;
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.item_home_rob, parent, false);
        RobViewhodler robViewhodler = new RobViewhodler(root);

        width = ((MainActivity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        viewWidth = (int) (width / 3.5);
        Log.i("safa", "_)________________________________________屏幕宽" + width + "item宽" + viewWidth);
        return robViewhodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RobViewhodler robViewhodler = (RobViewhodler) holder;
        HomeSeckilBean seckilBean = mSeckilBeans.get(position);
        robViewhodler.iv_img.setImageResource(R.drawable.item_recycle);
        robViewhodler.tv_price.setText("￥" + seckilBean.getNowPrice());
        robViewhodler.tv_price_rob.setText("￥" + seckilBean.getOldPrice());
        //为textvie加删除线
        robViewhodler.tv_price_rob.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        robViewhodler.itemView.setLayoutParams(new ViewGroup.LayoutParams(viewWidth, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    public int getItemCount() {
        return mSeckilBeans.size();
    }

    public class RobViewhodler extends RecyclerView.ViewHolder {
        public ImageView iv_img;
        public TextView tv_price;
        public TextView tv_price_rob;

        public RobViewhodler(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_robitem_img);
            tv_price = (TextView) itemView.findViewById(R.id.tv_robitem_price);
            tv_price_rob = (TextView) itemView.findViewById(R.id.tv_robitem_price_rob);
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
}
