package com.lishi.baijiaxing.customize.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.customize.model.CustomizeBean;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

/**
 * Created by Administrator on 2016/10/25.
 */
public class CustomizeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private CustomizeBean mCustomizeBean;
    private LayoutInflater mLayoutInflater;

    private final static int TYPE_ADVERTISEMENT1 = 0x012;
    private final static int TYPE_ADVERTISEMENT_XUZHI = 0x013;
    private final static int TYPE_CLASSIFY = 0x014;
    private final static int TYPE_ADVERTISEMENT2 = 0x015;
    private final static int TYPE_ADVERTISEMENTS = 0x016;
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    private String[] classifyTitles = new String[]{"杂志定制", "相册定制", "礼品定制", "出游定制"};
    private String[] classifyBrief = new String[]{"制作个性杂志", "留下最美好的回忆", "独一无二只属于你", "你的旅行由你做主"};
    private int[] tvColors = new int[]{Color.parseColor("#fc7713"), Color.parseColor("#fc7713"), Color.parseColor("#ae74f3"), Color.parseColor("#ae74f3"),};
    private int[] classifyPhoto = new int[]{R.drawable.customize_book, R.drawable.customize_photo, R.drawable.customize_gift, R.drawable.customize_tour};

    public CustomizeAdapter(Context context, CustomizeBean customizeBean) {
        this.mContext = context;
        this.mCustomizeBean = customizeBean;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ADVERTISEMENT1:
                return new TypeIv1ViewHolder(mLayoutInflater.inflate(R.layout.customize_item1, parent, false));
            case TYPE_ADVERTISEMENT_XUZHI:
                return new TypeXuzhiViewHolder(mLayoutInflater.inflate(R.layout.customize_item2, parent, false));
            case TYPE_CLASSIFY:
                return new TypeClassifyViewHolder(mLayoutInflater.inflate(R.layout.customize_item3, parent, false));
            case TYPE_ADVERTISEMENT2:
                return new TypeIv2ViewHolder(mLayoutInflater.inflate(R.layout.customize_item4, parent, false));
            case TYPE_ADVERTISEMENTS:
                return new TypeIvsViewHolder(mLayoutInflater.inflate(R.layout.customize_item5, parent, false));
        }
        return new TypeIvsViewHolder(mLayoutInflater.inflate(R.layout.customize_item5, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TypeIv1ViewHolder) {
            TypeIv1ViewHolder viewHolder = (TypeIv1ViewHolder) holder;
            viewHolder.iv.setImageResource(R.drawable.customize_iv1);
        } else if (holder instanceof TypeXuzhiViewHolder) {
            TypeXuzhiViewHolder viewHolder = (TypeXuzhiViewHolder) holder;

        } else if (holder instanceof TypeClassifyViewHolder) {
            TypeClassifyViewHolder viewHolder = (TypeClassifyViewHolder) holder;

            changeClassify(viewHolder, position);
        } else if (holder instanceof TypeIv2ViewHolder) {
            TypeIv2ViewHolder viewHolder = (TypeIv2ViewHolder) holder;

            viewHolder.iv.setImageResource(R.drawable.customize_iv2);
        } else if (holder instanceof TypeIvsViewHolder) {
            TypeIvsViewHolder viewHolder = (TypeIvsViewHolder) holder;

            viewHolder.iv.setImageResource(mCustomizeBean.getAdvertisementUrls().get(position - 7));
        }
    }

    private void changeClassify(TypeClassifyViewHolder viewHolder, int position) {
        if (position >= 2 && position <= 5) {
            viewHolder.photo.setImageResource(classifyPhoto[position - 2]);
            viewHolder.title.setText(classifyTitles[position - 2]);
            viewHolder.brief.setText(classifyBrief[position - 2]);
            viewHolder.title.setTextColor(tvColors[position - 2]);
            if (position == 2 || position == 4) {
                GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) viewHolder.itemView.getLayoutParams();
                lp.rightMargin = 0;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mCustomizeBean.getAdvertisementUrls().size() + 7;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_ADVERTISEMENT1:
                        case TYPE_ADVERTISEMENT2:
                        case TYPE_ADVERTISEMENTS:
                        case TYPE_ADVERTISEMENT_XUZHI:
                            return 2;
                        case TYPE_CLASSIFY:
                            return 1;
                    }
                    return 2;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_ADVERTISEMENT1;
        } else if (position == 1) {
            return TYPE_ADVERTISEMENT_XUZHI;
        } else if (position >= 2 && position <= 5) {
            return TYPE_CLASSIFY;
        } else if (position == 6) {
            return TYPE_ADVERTISEMENT2;
        } else if (position > 6) {
            return TYPE_ADVERTISEMENTS;
        }
        return super.getItemViewType(position);
    }

    class TypeIv1ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public TypeIv1ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.customize_iv1);
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

    class TypeXuzhiViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public TypeXuzhiViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.customize_xuzhi);
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

    class TypeClassifyViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView title;
        TextView brief;

        public TypeClassifyViewHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(R.id.customize_classify_iv);
            title = (TextView) itemView.findViewById(R.id.customize_classify_tv1);
            brief = (TextView) itemView.findViewById(R.id.customize_classify_tv2);
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

    class TypeIv2ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public TypeIv2ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.customize_iv2);
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

    class TypeIvsViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;

        public TypeIvsViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.customize_ivs);
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
