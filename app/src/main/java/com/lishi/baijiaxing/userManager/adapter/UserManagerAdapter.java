package com.lishi.baijiaxing.userManager.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.userManager.model.UserListBean;
import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import org.apache.commons.cli.TypeHandler;

import java.io.File;
import java.util.IllegalFormatCodePointException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 用户管理
 * Created by Administrator on 2016/11/2.
 */
public class UserManagerAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private final static int TYPE_HEADPHOTO = 0X001;
    private final static int TYPE_ITEM = 0X002;

    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    public UserManagerAdapter(Context context) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(YiYuanHotAdapter.OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADPHOTO) {
            return new UserManagerItem1ViewHolder(mLayoutInflater.inflate(R.layout.usermanager_item1, parent, false));
        }
        return new UserManagerItem2ViewHolder(mLayoutInflater.inflate(R.layout.usermanager_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserManagerItem1ViewHolder) {
            UserManagerItem1ViewHolder viewHolder = (UserManagerItem1ViewHolder) holder;
            viewHolder.tv_classify.setText("头像");
            try {
                String path = LocalUserInfo.getInstance().getPhotoUrl();
                if (path == null || path.equals("")) {
                    Glide.with(mContext).load(R.drawable.tou).into(viewHolder.headPhoto);
                } else {
                    Glide.with(mContext).load(path).into(viewHolder.headPhoto);
                }
            } catch (Exception e) {
                Toast.makeText(mContext, "找不到图片path", Toast.LENGTH_SHORT).show();
            }

        } else {
            UserManagerItem2ViewHolder viewHolder = (UserManagerItem2ViewHolder) holder;
            switch (position) {
                case 1:
                    viewHolder.tv_classify.setText("会员名");
                    viewHolder.value.setText(LocalUserInfo.getInstance().getNid());
                    break;
                case 2:
                    viewHolder.tv_classify.setText("昵称");
                    viewHolder.value.setText(LocalUserInfo.getInstance().getNickName());
                    break;
                case 3:
                    viewHolder.tv_classify.setText("性别");
                    viewHolder.value.setText(LocalUserInfo.getInstance().getSex());
                    break;
                case 4:
                    viewHolder.tv_classify.setText("收货地址");
                    viewHolder.value.setText("");
                    break;
                case 5:
                    RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams();
                    lp.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, mContext.getResources().getDisplayMetrics());
                    viewHolder.tv_classify.setText("帐号安全");
                    viewHolder.value.setText("修改密码");
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADPHOTO;
        }
        return TYPE_ITEM;
    }

    class UserManagerItem1ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_classify, value;
        CircleImageView headPhoto;

        public UserManagerItem1ViewHolder(View itemView) {
            super(itemView);
            tv_classify = (TextView) itemView.findViewById(R.id.usermanager_item1_tv);
            value = (TextView) itemView.findViewById(R.id.usermanager_item2_tv);
            headPhoto = (CircleImageView) itemView.findViewById(R.id.usermanager_item1_headPhoto);
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

    class UserManagerItem2ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_classify, value;

        public UserManagerItem2ViewHolder(View itemView) {
            super(itemView);
            tv_classify = (TextView) itemView.findViewById(R.id.usermanager_item2_tv);
            value = (TextView) itemView.findViewById(R.id.usermanager_item2_value);
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
