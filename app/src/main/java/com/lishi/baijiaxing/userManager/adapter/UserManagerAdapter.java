package com.lishi.baijiaxing.userManager.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.userManager.model.UserListBean;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import org.apache.commons.cli.TypeHandler;

import java.util.IllegalFormatCodePointException;
import java.util.List;

/**
 * 用户管理
 * Created by Administrator on 2016/11/2.
 */
public class UserManagerAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private UserListBean mUserListBeen;
    private LayoutInflater mLayoutInflater;

    private final static int TYPE_HEADPHOTO = 0X001;
    private final static int TYPE_ITEM = 0X002;
    
    private YiYuanHotAdapter.OnItemClickListener mOnItemClickListener;

    public UserManagerAdapter(Context context, UserListBean userListBeen) {
        this.mContext = context;
        this.mUserListBeen = userListBeen;
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
            viewHolder.headPhoto.setImageResource(R.drawable.usermanager_headphoto);
        } else {
            UserManagerItem2ViewHolder viewHolder = (UserManagerItem2ViewHolder) holder;
            switch (position) {
                case 1:
                    viewHolder.tv_classify.setText("会员名");
                    viewHolder.value.setText(mUserListBeen.getName());
                    break;
                case 2:
                    viewHolder.tv_classify.setText("昵称");
                    viewHolder.value.setText(mUserListBeen.getNickname());
                    break;
                case 3:
                    viewHolder.tv_classify.setText("性别");
                    viewHolder.value.setText(mUserListBeen.getSex());
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
        ImageView headPhoto;

        public UserManagerItem1ViewHolder(View itemView) {
            super(itemView);
            tv_classify = (TextView) itemView.findViewById(R.id.usermanager_item1_tv);
            value = (TextView) itemView.findViewById(R.id.usermanager_item2_tv);
            headPhoto = (ImageView) itemView.findViewById(R.id.usermanager_item1_headPhoto);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onClickListener(v,getPosition());
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
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onClickListener(v,getPosition());
                    }
                }
            });
        }
    }
}
