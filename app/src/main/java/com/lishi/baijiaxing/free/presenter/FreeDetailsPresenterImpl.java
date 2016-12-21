package com.lishi.baijiaxing.free.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.free.FreeDetailsCallback;
import com.lishi.baijiaxing.free.model.FreeDetails;
import com.lishi.baijiaxing.free.model.FreeDetailsModelImpl;
import com.lishi.baijiaxing.free.view.FreeDetailsView;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;

/**
 * Created by Administrator on 2016/10/21.
 */
public class FreeDetailsPresenterImpl<T, V extends BaseView> extends BasePresenter implements FreeDetailsPresenter, FreeDetailsCallback {
    private FreeDetailsModelImpl mFreeDetailsModel;
    private FreeDetailsView mFreeDetailsView;

    public FreeDetailsPresenterImpl(BaseView baseView) {
        super(baseView);
        mFreeDetailsModel = new FreeDetailsModelImpl();
        mFreeDetailsView = (FreeDetailsView) baseView;
    }


    @Override
    public void loadDetailsSuccess(FreeDetails freeDetails) {
        mFreeDetailsView.loadDetailsSuccess(freeDetails);
    }

    @Override
    public void loadDetailsFailed(String error) {
        mFreeDetailsView.loadDetailsFailed(error);
    }

    @Override
    public void submitApplySuccess(SubmitOrder submitOrder) {
        mFreeDetailsView.submitApplySuccess(submitOrder);
    }

    @Override
    public void submitApplyFailed(String error) {
        mFreeDetailsView.submitApplyFailed(error);
    }

    @Override
    public void loadData(String type, String gid, String zid) {
        mFreeDetailsModel.loadData(this, type, gid, zid);
    }

    @Override
    public void submitApply(FreeDetails freeDetails,String aid,String type) {
        mFreeDetailsModel.submitApply(this, freeDetails,aid, type);
    }
}
