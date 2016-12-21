package com.lishi.baijiaxing.details.model;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.bean.CommentBean;
import com.lishi.baijiaxing.details.CommodityBriefCallback;
import com.lishi.baijiaxing.details.CommodityCommentCallback;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CommodityCommentModelImpl extends BaseModel implements CommodityCommentModel {
    @Override
    public void loadData(CommodityCommentCallback callback) {
        ArrayList<CommentBean> mCommentBeen = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (i < 3) {
                CommentBean c = new CommentBean(null, R.drawable.user_photo, "咸鱼这名也不给起" + i, R.drawable.user_leve, "2016-7-9", R.drawable.star, "东西不错哦。下次再来买", "2016-7-1");
                mCommentBeen.add(c);
            } else {
                CommentBean c = new CommentBean(null, R.drawable.user_photo, "咸鱼这名也不给起" + i, R.drawable.user_leve, "2016-7-9", R.drawable.star, "很一般", "2016-7-1");
                mCommentBeen.add(c);
            }
        }
        callback.onLoadSuccess(mCommentBeen);
    }

}
