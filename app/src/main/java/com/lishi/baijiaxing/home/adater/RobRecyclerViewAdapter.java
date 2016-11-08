package com.lishi.baijiaxing.home.adater;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.MainActivity;

/**
 * 首页秒杀adapter
 * Created by Administrator on 2016/6/2.
 */
public class RobRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    int width;
    int viewWidth;

    public RobRecyclerViewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.item_home_rob, null, false);
        RobViewhodler robViewhodler = new RobViewhodler(root);

        width = ((MainActivity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        viewWidth = (int) (width / 3.5);
        Log.i("safa", "_)________________________________________屏幕宽" + width + "item宽" + viewWidth);
        return robViewhodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RobViewhodler robViewhodler = (RobViewhodler) holder;
        robViewhodler.iv_img.setImageResource(R.drawable.item_recycle);
        robViewhodler.tv_price.setText("￥" + "168");
        robViewhodler.tv_price_rob.setText("￥" + "254");
        //为textvie加删除线
        robViewhodler.tv_price_rob.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        robViewhodler.itemView.setLayoutParams(new ViewGroup.LayoutParams(viewWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RobViewhodler extends RecyclerView.ViewHolder {
        private ImageView iv_img;
        private TextView tv_price;
        private TextView tv_price_rob;


        public RobViewhodler(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_robitem_img);
            tv_price = (TextView) itemView.findViewById(R.id.tv_robitem_price);
            tv_price_rob = (TextView) itemView.findViewById(R.id.tv_robitem_price_rob);
        }
    }
}
