package com.lishi.baijiaxing.bookmagazine.presenter;

import com.lishi.baijiaxing.base.BasePresenter;
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.bookmagazine.BookMagazineCallback;
import com.lishi.baijiaxing.bookmagazine.model.BookMagazineModelImpl;
import com.lishi.baijiaxing.bookmagazine.view.BookMagazineView;
import com.lishi.baijiaxing.hot.model.HotCommodity;

/**
 * Created by Administrator on 2016/12/8.
 */

public class BookMagazinePresenterImpl extends BasePresenter implements BookMagazinePresenter, BookMagazineCallback {
    private BookMagazineModelImpl mBookMagazineModel;
    private BookMagazineView mBookMagazineView;

    public BookMagazinePresenterImpl(BaseView baseView) {
        super(baseView);
        this.mBookMagazineModel = new BookMagazineModelImpl();
        this.mBookMagazineView = (BookMagazineView) baseView;
    }

    @Override
    public void loadData() {
        mBookMagazineView.showDialog();
        mBookMagazineModel.loadData(this);
    }

    @Override
    public void loadDataSuccess(HotCommodity commodity) {
        mBookMagazineView.closeDialog();
        mBookMagazineView.loadDataSuccess(commodity);
    }

    @Override
    public void loadDataFailed(String error) {
        mBookMagazineView.closeDialog();
        mBookMagazineView.loadDataFailed(error);
    }
}
