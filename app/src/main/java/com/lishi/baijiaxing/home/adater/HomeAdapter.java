package com.lishi.baijiaxing.home.adater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.LogPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.home.model.HomeCommodityBean;
import com.lishi.baijiaxing.home.utils.GridSpacingItemDecoration;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.lishi.baijiaxing.yiyuan.view.YiYuanActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/14.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //一元拼
    private static final int TYPE_YiYUAN_HEAD = 0X000;
    private static final int TYPE_YIYUAN = 0X001;
    //商城热卖
    private static final int TYPE_COMMODITY1_HEAD = 0X002;
    private static final int TYPE_COMMODITY1 = 0X003;
    //数码办公
    private static final int TYPE_COMMODITY2_HEAD = 0X004;
    private static final int TYPE_COMMODITY2 = 0X005;
    //定制礼品
    private static final int TYPE_COMMODITY3_HEAD = 0X006;
    private static final int TYPE_COMMODITY3 = 0X007;
    //餐饮用具
    private static final int TYPE_COMMODITY4_HEAD = 0X008;
    private static final int TYPE_COMMODITY4 = 0X009;

    private static final int TYPE_COMMODITY_TITLE = 0X010;


    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<HomeCommodityBean> mCommodity;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;


    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public HomeAdapter(Context context, ArrayList<HomeCommodityBean> commodity) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.mCommodity = commodity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_YIYUAN) {//一元拼
            return new HolderTypeYiYuan(mLayoutInflater.inflate(R.layout.item_home2, parent, false));
        } else if (viewType == TYPE_YiYUAN_HEAD) {//一元拼Head
            GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(1, 10, false);
            return new HolderTypeYiYuan_Head(mLayoutInflater.inflate(R.layout.item_home_yiyuan_head, parent, false));
        } else if (viewType == TYPE_COMMODITY_TITLE) {//Commodity Title
            return new HolderTypeCommodityTitle(mLayoutInflater.inflate(R.layout.item_home4_1, parent, false));
        } else if (viewType == TYPE_COMMODITY1) {//商城热卖
            return new HolderTypeCommodity1(mLayoutInflater.inflate(R.layout.item_home3_2, parent, false));
        } else if (viewType == TYPE_COMMODITY2 || viewType == TYPE_COMMODITY3
                || viewType == TYPE_COMMODITY4) {//数码办公||定制礼品||餐饮用具
            return new HolderTypeCommodity2(mLayoutInflater.inflate(R.layout.item_home4_2, parent, false));
        } else if (viewType == TYPE_COMMODITY1_HEAD || viewType == TYPE_COMMODITY2_HEAD
                || viewType == TYPE_COMMODITY3_HEAD || viewType == TYPE_COMMODITY4_HEAD) {//Commodity
            return new HolderTypeCommodity_Head(mLayoutInflater.inflate(R.layout.item_home_commodity_head, parent, false));
        }
        return new HolderTypeCommodity2(mLayoutInflater.inflate(R.layout.item_home4_2, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            if (holder instanceof HolderTypeYiYuan) {
                HolderTypeYiYuan mHolderTypeYiYuan = (HolderTypeYiYuan) holder;
                mHolderTypeYiYuan.mName.setText("虎符龙节文具套装");
                mHolderTypeYiYuan.mPhoto.setImageResource(R.drawable.home2_1);
                if (position == 3) {
                    mHolderTypeYiYuan.mView.setVisibility(View.GONE);
                }
            } else if (holder instanceof HolderTypeYiYuan_Head) {
                HolderTypeYiYuan_Head mHolderTypeYiYuan_Head = (HolderTypeYiYuan_Head) holder;
                mHolderTypeYiYuan_Head.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent startYiYuanActivity = new Intent(mContext, YiYuanActivity.class);
                        mContext.startActivity(startYiYuanActivity);
                    }
                });

            } else if (holder instanceof HolderTypeCommodity1) {
                HolderTypeCommodity1 mHolderTypeCommodity1 = (HolderTypeCommodity1) holder;
                mHolderTypeCommodity1.mPhoto.setImageResource(R.drawable.home3_1);
                mHolderTypeCommodity1.mName.setText("茶叶罐");
                mHolderTypeCommodity1.mInfo.setText("禅定純锡茶叶罐");
                if (position == 6 || position == 9) {
                    GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) mHolderTypeCommodity1.itemView.getLayoutParams();
                    lp.leftMargin = lp.leftMargin * 2;
                }
                if (position == 8 || position == 11) {
                    GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) mHolderTypeCommodity1.itemView.getLayoutParams();
                    lp.rightMargin = lp.rightMargin * 2;
                }

                if (position == 6 || position == 7 || position == 8) {
                    GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) mHolderTypeCommodity1.itemView.getLayoutParams();
                    lp.topMargin = lp.topMargin * 4;
                }

                if (position == 9 || position == 10 || position == 11) {
                    GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) mHolderTypeCommodity1.itemView.getLayoutParams();
                    lp.bottomMargin = lp.bottomMargin * 4;
                }

            } else if (holder instanceof HolderTypeCommodity2) {
                HolderTypeCommodity2 mHolderTypeCommodity2 = (HolderTypeCommodity2) holder;
                mHolderTypeCommodity2.mPhoto.setImageResource(R.drawable.home4_1);
                mHolderTypeCommodity2.mName.setText("万仟堂 陶瓷同心杯");
                mHolderTypeCommodity2.mPrice.setText("299元");
            } else if (holder instanceof HolderTypeCommodityTitle) {
                HolderTypeCommodityTitle mHolderTypeCommodityTitle = (HolderTypeCommodityTitle) holder;
                mHolderTypeCommodityTitle.mTitle.setImageResource(R.drawable.home_title);
                mHolderTypeCommodityTitle.mTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent startCommodityDetailsActivity = new Intent(mContext, CommodityDetailsActivity.class);
                        mContext.startActivity(startCommodityDetailsActivity);
                    }
                });
            } else if (holder instanceof HolderTypeCommodity_Head) {
                HolderTypeCommodity_Head mHolderTypeCommodity_Head = (HolderTypeCommodity_Head) holder;
                int type = getItemViewType(position);
                switch (type) {
                    case TYPE_COMMODITY1_HEAD:
                        mHolderTypeCommodity_Head.mTitle.setText("商城热卖");
                        break;
                    case TYPE_COMMODITY2_HEAD:
                        mHolderTypeCommodity_Head.mTitle.setText("数码办公");
                        break;
                    case TYPE_COMMODITY3_HEAD:
                        mHolderTypeCommodity_Head.mTitle.setText("定制礼品");
                        break;
                    case TYPE_COMMODITY4_HEAD:
                        mHolderTypeCommodity_Head.mTitle.setText("餐饮用具");
                        break;
                }
            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_YiYUAN_HEAD;
        } else if (position > 0 && position < 4) {
            return TYPE_YIYUAN;
        } else if (position == 4) {
            return TYPE_COMMODITY1_HEAD;
        } else if (position == 5 || position == 13 || position == 21
                || position == 29) {
            return TYPE_COMMODITY_TITLE;
        } else if (position == 12) {
            return TYPE_COMMODITY2_HEAD;
        } else if (position == 20) {
            return TYPE_COMMODITY3_HEAD;
        } else if (position == 28) {
            return TYPE_COMMODITY4_HEAD;
        } else if (position > 5 && position < 12) {
            return TYPE_COMMODITY1;
        } else if (position > 13 && position < 20) {
            return TYPE_COMMODITY2;
        } else if (position > 21 && position < 28) {
            return TYPE_COMMODITY3;
        } else if (position > 29 && position < 36) {
            return TYPE_COMMODITY4;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return mCommodity.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager mManger = recyclerView.getLayoutManager();
        if (mManger instanceof GridLayoutManager) {
            final GridLayoutManager gmanager = ((GridLayoutManager) mManger);
            gmanager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_YIYUAN:
                        case TYPE_COMMODITY1:
                            return 2;
                        case TYPE_COMMODITY2:
                        case TYPE_COMMODITY3:
                        case TYPE_COMMODITY4:
                            return 3;
                        case TYPE_YiYUAN_HEAD:
                        case TYPE_COMMODITY1_HEAD:
                        case TYPE_COMMODITY2_HEAD:
                        case TYPE_COMMODITY3_HEAD:
                        case TYPE_COMMODITY4_HEAD:
                        case TYPE_COMMODITY_TITLE:
                            return gmanager.getSpanCount();

                    }
                    return gmanager.getSpanCount();
                }
            });
        }
    }

    class HolderTypeYiYuan_Head extends RecyclerView.ViewHolder {
        TextView more;

        public HolderTypeYiYuan_Head(View itemView) {
            super(itemView);
            more = (TextView) itemView.findViewById(R.id.yiyuan_more);
        }
    }

    class HolderTypeCommodity_Head extends RecyclerView.ViewHolder {
        TextView mTitle;

        public HolderTypeCommodity_Head(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.item_home_commodity_title);
        }
    }

    class HolderTypeYiYuan extends RecyclerView.ViewHolder {
        ImageView mPhoto;
        TextView mName;
        View mView;

        public HolderTypeYiYuan(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.item_home2_photo);
            mName = (TextView) itemView.findViewById(R.id.item_home2_name);
            mView = itemView.findViewById(R.id.item_home2_bg);
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

    class HolderTypeCommodity1 extends RecyclerView.ViewHolder {
        TextView mName, mInfo;
        ImageView mPhoto;

        public HolderTypeCommodity1(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.item_home3_2_name);
            mInfo = (TextView) itemView.findViewById(R.id.item_home3_2_info);
            mPhoto = (ImageView) itemView.findViewById(R.id.item_home3_2_photo);
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

    class HolderTypeCommodity2 extends RecyclerView.ViewHolder {
        ImageView mPhoto;
        TextView mName, mPrice;

        public HolderTypeCommodity2(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.item_home4_2_photo);
            mName = (TextView) itemView.findViewById(R.id.item_home4_2_name);
            mPrice = (TextView) itemView.findViewById(R.id.item_home4_2_price);
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

    class HolderTypeCommodityTitle extends RecyclerView.ViewHolder {
        ImageView mTitle;

        public HolderTypeCommodityTitle(View itemView) {
            super(itemView);
            mTitle = (ImageView) itemView.findViewById(R.id.item_home4_1_title);
        }
    }
}
