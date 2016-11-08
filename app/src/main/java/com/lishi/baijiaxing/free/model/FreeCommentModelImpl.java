package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.free.FreeCommentCallback;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/21.
 */
public class FreeCommentModelImpl extends BaseModel implements FreeCommentModel {

    private ArrayList<FreeCommentBean> fcbs;

    @Override
    public void loadData(FreeCommentCallback callback) {
        fcbs = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            ArrayList<ReplyBean> mReplyBean = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                ReplyBean rb = new ReplyBean("199****2222", "1111", "评论的真好");
                mReplyBean.add(rb);
            }
            FreeCommentBean fcb = new FreeCommentBean("186****1121", "", "2016-10-08 17:55:55", "这个真的好", mReplyBean, 2);
            fcbs.add(fcb);
        }

        callback.onLoadSuccess(fcbs);
    }
}
