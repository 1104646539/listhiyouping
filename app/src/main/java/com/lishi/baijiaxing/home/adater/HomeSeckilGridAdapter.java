package com.lishi.baijiaxing.home.adater;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.MainActivity;
import com.lishi.baijiaxing.home.model.Seckill;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomeSeckilGridAdapter extends BaseAdapter {
    private final int width;
    private final int viewWidth;
    private Context mContext;
    private Seckill.DataBean mSeckilBeen;
    private int margin;

    public HomeSeckilGridAdapter(Context context, Seckill.DataBean seckilBeen, int margin) {
        this.mContext = context;
        this.mSeckilBeen = seckilBeen;
        this.margin = margin;
        width = ((MainActivity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        viewWidth = (int) (width / 3.5);
    }

    @Override
    public int getCount() {
        if (mSeckilBeen == null || mSeckilBeen.getSeckillList() == null) {
            return 0;
        }
        return mSeckilBeen.getSeckillList().size();
    }

    @Override
    public Object getItem(int position) {
        return mSeckilBeen.getSeckillList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HRobViewhodler robViewhodler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_rob, parent, false);
            robViewhodler = new HRobViewhodler(convertView);
            convertView.setTag(robViewhodler);
        } else {
            robViewhodler = (HRobViewhodler) convertView.getTag();
        }
        Seckill.DataBean.SeckillListBean seckil = mSeckilBeen.getSeckillList().get(position);
        Glide.with(mContext).load(seckil.getPhotoUrl()).error(R.drawable.item_recycle).placeholder(R.drawable.home_198x155).into(robViewhodler.iv_img);
        robViewhodler.tv_price.setText("￥" + seckil.getNowPrice());
        robViewhodler.tv_price_rob.setText("￥" + seckil.getOldPrice());
        //为textvie加删除线
        robViewhodler.tv_price_rob.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        LinearLayout.LayoutParams vlp = new LinearLayout.LayoutParams(viewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
//        vlp.setMargins(margin, 0, 0, 0);
//        robViewhodler.itemView.setLayoutParams(vlp);
        return convertView;
    }

    public class HRobViewhodler extends RecyclerView.ViewHolder {
        private ImageView iv_img;
        private TextView tv_price;
        private TextView tv_price_rob;

        public HRobViewhodler(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_robitem_img);
            tv_price = (TextView) itemView.findViewById(R.id.tv_robitem_price);
            tv_price_rob = (TextView) itemView.findViewById(R.id.tv_robitem_price_rob);
        }
    }
}
