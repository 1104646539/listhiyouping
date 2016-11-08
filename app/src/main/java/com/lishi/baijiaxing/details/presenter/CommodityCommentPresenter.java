package com.lishi.baijiaxing.details.presenter;

import com.lishi.baijiaxing.bean.CommentBean;
import com.lishi.baijiaxing.details.view.CommodityBriefView;
import com.lishi.baijiaxing.details.view.CommodityCommentView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public interface CommodityCommentPresenter {
    void loadData();

    void loadDataSuccess(ArrayList<CommentBean> commentBeen);

    void loadDataFailed(String error);
}
