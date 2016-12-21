package com.lishi.baijiaxing.home.adater;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.home.model.Commodity;
import com.lishi.baijiaxing.home.utils.GridSpacingItemDecoration;
import com.lishi.baijiaxing.utils.MyBitmapImageViewTarget;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.lishi.baijiaxing.yiyuan.view.YiYuanActivity;

import java.util.List;

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
    // 定制礼品
    private static final int TYPE_COMMODITY2_HEAD = 0X004;
    private static final int TYPE_COMMODITY2 = 0X005;
    // 数码用品
    private static final int TYPE_COMMODITY3_HEAD = 0X006;
    private static final int TYPE_COMMODITY3 = 0X007;
    // 餐饮用品
    private static final int TYPE_COMMODITY4_HEAD = 0X008;
    private static final int TYPE_COMMODITY4 = 0X009;
    // 为你推荐
    private static final int TYPE_COMMODITY5_HEAD = 0X010;
    private static final int TYPE_COMMODITY5 = 0X011;

    private static final int TYPE_COMMODITY_TITLE = 0X111;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Commodity.DataBean> mCommodity;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;
    private String headUrl = "http://risevip.oss-cn-shenzhen.aliyuncs.com";

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public HomeAdapter(Context context, List<Commodity.DataBean> commodity) {
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
                || viewType == TYPE_COMMODITY4 || viewType == TYPE_COMMODITY5) {//定制礼品||数码用品||餐饮用品||为你推荐
            return new HolderTypeCommodity2(mLayoutInflater.inflate(R.layout.item_home4_2, parent, false));
        } else if (viewType == TYPE_COMMODITY1_HEAD || viewType == TYPE_COMMODITY2_HEAD
                || viewType == TYPE_COMMODITY3_HEAD || viewType == TYPE_COMMODITY4_HEAD || viewType == TYPE_COMMODITY5_HEAD) {//Commodity
            return new HolderTypeCommodity_Head(mLayoutInflater.inflate(R.layout.item_home_commodity_head, parent, false));
        }
        return new HolderTypeCommodity2(mLayoutInflater.inflate(R.layout.item_home4_2, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder != null) {
            if (holder instanceof HolderTypeYiYuan) {
                HolderTypeYiYuan mHolderTypeYiYuan = (HolderTypeYiYuan) holder;
                int pos = getYiYuanPosition(position);
                Commodity.DataBean commodity = mCommodity.get(pos);
                mHolderTypeYiYuan.mName.setText(commodity.getName().length() > 7 ? commodity.getName().length() > 7 ? commodity.getName().substring(0, 7) : commodity.getName() : commodity.getName());
                String path = commodity.getPhotoUrl();
                if (!path.contains("http")) {
                    path = path.substring(1, path.length());
                    path = headUrl + path;
                    Log.i("修改后", "path=" + path);
                }
                Log.i("HolderTypeYiYuan", "path=" + path);
//                Glide.with(mContext).load(path).placeholder(R.drawable.item_recycle).dontAnimate().into(mHolderTypeYiYuan.mPhoto);
                Glide.with(mContext).load(path).asBitmap().placeholder(R.drawable.home_yiyuan_239x239)
                        .into(new MyBitmapImageViewTarget(mHolderTypeYiYuan.mPhoto));
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
                final int pos = getHolderTypeCommodity(position);
                Commodity.DataBean commodity = mCommodity.get(pos);
                String path = commodity.getPhotoUrl();
                if (!path.contains("http")) {
                    path = path.substring(1, path.length());
                    path = headUrl + path;
                    Log.i("修改后", "path=" + path);
                }
                Glide.with(mContext).load(path).asBitmap().placeholder(R.drawable.home_hot_220x153)
                        .into(new MyBitmapImageViewTarget(mHolderTypeCommodity1.mPhoto));
                mHolderTypeCommodity1.mName.setText(commodity.getName().length() > 7 ? commodity.getName().length() > 7 ? commodity.getName().substring(0, 7) : commodity.getName() : commodity.getName());
                mHolderTypeCommodity1.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String gid = mCommodity.get(pos).getCid();
                        if (gid != null && !gid.equals("0")) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onClickListener(v, pos);
                            }
                        }
                    }
                });
            } else if (holder instanceof HolderTypeCommodity2) {
                HolderTypeCommodity2 mHolderTypeCommodity2 = (HolderTypeCommodity2) holder;
                final int pos = getHolderTypeCommodity(position);
                Commodity.DataBean commodity = mCommodity.get(pos);
                String path = commodity.getPhotoUrl();
                if (!path.contains("http")) {
                    path = path.substring(1, path.length());
                    path = headUrl + path;
                    Log.i("修改后", "path=" + path);
                }
                Glide.with(mContext).load(path).asBitmap().placeholder(R.drawable.home_commodity_349x256)
                        .into(new MyBitmapImageViewTarget(mHolderTypeCommodity2.mPhoto));

                mHolderTypeCommodity2.mName.setText(commodity.getName().length() > 7 ? commodity.getName().length() > 7 ? commodity.getName().length() > 7 ? commodity.getName().substring(0, 7) : commodity.getName() : commodity.getName() : commodity.getName());
                mHolderTypeCommodity2.mPrice.setText(commodity.getNowPrice());
                mHolderTypeCommodity2.mOldPrice.setText(commodity.getOldPrice());
                mHolderTypeCommodity2.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String gid = mCommodity.get(pos).getCid();
                        if (gid != null && !gid.equals("0")) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onClickListener(v, pos);
                            }
                        }
                    }
                });
            } else if (holder instanceof HolderTypeCommodityTitle) {
                HolderTypeCommodityTitle mHolderTypeCommodityTitle = (HolderTypeCommodityTitle) holder;
                final int pos = getHolderTypeTitle(position);
                Commodity.DataBean commodity = mCommodity.get(pos);
                String path = commodity.getPhotoUrl();
                if (!path.contains("http")) {
                    path = path.substring(1, path.length());
                    path = headUrl + path;
                    Log.i("修改后", "path=" + path);
                }
                Glide.with(mContext).load(path).asBitmap().placeholder(R.drawable.home_title_720x197).into(new MyBitmapImageViewTarget(mHolderTypeCommodityTitle.mTitle));
                mHolderTypeCommodityTitle.mTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent startCommodityDetailsActivity = new Intent(mContext, CommodityDetailsActivity.class);
                        String gid = mCommodity.get(pos).getCid();
                        startCommodityDetailsActivity.putExtra("gid",gid);
                        if (gid != null && !gid.equals("0")) {
                            mContext.startActivity(startCommodityDetailsActivity);
                        }
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
                        mHolderTypeCommodity_Head.mTitle.setText("定制礼品");
                        break;
                    case TYPE_COMMODITY3_HEAD:
                        mHolderTypeCommodity_Head.mTitle.setText("数码用品");
                        break;
                    case TYPE_COMMODITY4_HEAD:
                        mHolderTypeCommodity_Head.mTitle.setText("餐饮用品");
                        break;
                    case TYPE_COMMODITY5_HEAD:
                        mHolderTypeCommodity_Head.mTitle.setText("为你推荐");
                        break;
                }
            }
        }
    }

    private int getHolderTypeTitle(int position) {
        if (position == 5) {
            return position - 2;
        } else if (position == 13) {
            return position - 3;
        } else if (position == 19) {
            return position - 4;
        } else if (position == 25) {
            return position - 5;
        } else if (position == 33) {
            return position - 6;
        }
        return position - 6;
    }

    private int getHolderTypeCommodity(int position) {
        if (position > 5 && position < 12) {
            return position - 2;
        } else if (position > 13 && position < 18) {
            return position - 3;
        } else if (position > 19 && position < 24) {
            return position - 4;
        } else if (position > 25 && position < 32) {
            return position - 5;
        } else if (position > 33) {
            return position - 6;
        }
        return position - 6;
    }

    private int getYiYuanPosition(int position) {
        return position - 1 > 0 ? position - 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_YiYUAN_HEAD;
        } else if (position > 0 && position < 4) {
            return TYPE_YIYUAN;
        } else if (position == 4) {
            return TYPE_COMMODITY1_HEAD;
        } else if (position == 5 || position == 13 || position == 19
                || position == 25 || position == 33) {
            return TYPE_COMMODITY_TITLE;
        } else if (position == 12) {
            return TYPE_COMMODITY2_HEAD;
        } else if (position == 18) {
            return TYPE_COMMODITY3_HEAD;
        } else if (position == 24) {
            return TYPE_COMMODITY4_HEAD;
        } else if (position == 32) {
            return TYPE_COMMODITY5_HEAD;
        } else if (position > 5 && position < 12) {
            return TYPE_COMMODITY1;
        } else if (position > 13 && position < 18) {
            return TYPE_COMMODITY2;
        } else if (position > 19 && position < 24) {
            return TYPE_COMMODITY3;
        } else if (position > 25 && position < 32) {
            return TYPE_COMMODITY4;
        } else if (position > 33) {
            return TYPE_COMMODITY5;
        }
        return TYPE_COMMODITY5;
    }

    @Override
    public int getItemCount() {
        return mCommodity.size() + 6;
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
                        case TYPE_COMMODITY5:
                            return 3;
                        case TYPE_YiYUAN_HEAD:
                        case TYPE_COMMODITY1_HEAD:
                        case TYPE_COMMODITY2_HEAD:
                        case TYPE_COMMODITY3_HEAD:
                        case TYPE_COMMODITY4_HEAD:
                        case TYPE_COMMODITY5_HEAD:
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
        }
    }

    class HolderTypeCommodity2 extends RecyclerView.ViewHolder {
        ImageView mPhoto;
        TextView mName, mPrice, mOldPrice;

        public HolderTypeCommodity2(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.item_home4_2_photo);
            mName = (TextView) itemView.findViewById(R.id.item_home4_2_name);
            mPrice = (TextView) itemView.findViewById(R.id.item_home4_2_price);
            mOldPrice = (TextView) itemView.findViewById(R.id.item_home4_2_old_price);
            TextPaint textPaint = mOldPrice.getPaint();
            textPaint.setFlags(TextPaint.STRIKE_THRU_TEXT_FLAG);
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
