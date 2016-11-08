package com.lishi.baijiaxing.details.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bean.CommentBean;
import com.lishi.baijiaxing.details.CommodityBriefCallback;
import com.lishi.baijiaxing.details.CommodityCommentCallback;
import com.lishi.baijiaxing.details.model.CommodityBriefModelImpl;
import com.lishi.baijiaxing.details.model.CommodityCommentModel;
import com.lishi.baijiaxing.details.model.CommodityCommentModelImpl;
import com.lishi.baijiaxing.details.view.CommodityBriefView;
import com.lishi.baijiaxing.details.view.CommodityCommentView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CommodityCommentPresenterImpl<T, V extends BaseView> extends BasePresenter implements CommodityCommentCallback, CommodityCommentPresenter {
    private CommodityCommentModelImpl mCommodityCommentModel;
    private CommodityCommentView mCommodityCommentView;

    public CommodityCommentPresenterImpl(BaseView baseView) {
        super(baseView);
        mCommodityCommentModel = new CommodityCommentModelImpl();
        mCommodityCommentView = (CommodityCommentView) baseView;
    }

    @Override
    public void loadData() {
        mCommodityCommentModel.loadData(this);
    }

    @Override
    public void loadDataSuccess(ArrayList<CommentBean> commentBeen) {
        mCommodityCommentView.loadDataSuccess(commentBeen);
    }


    @Override
    public void loadDataFailed(String error) {
        mCommodityCommentView.loadDataFailed(error);
    }
}
