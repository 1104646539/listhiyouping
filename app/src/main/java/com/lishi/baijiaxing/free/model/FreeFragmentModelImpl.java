package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.free.FreeCallback;
import com.lishi.baijiaxing.free.FreeFragmentCallback;
import com.lishi.baijiaxing.free.network.FreeService;
import com.lishi.baijiaxing.free.presenter.FreeFragmentPresenterImpl;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/17.
 */
public class FreeFragmentModelImpl extends BaseModel implements FreeFragmentModel {
    private FreeService mFreeService;

    public FreeFragmentModelImpl() {
        mFreeService = (FreeService) getRetrofitManager().getHomeService(FreeService.class);
    }

    @Override
    public void loadData(final FreeFragmentCallback callback, int type) {
        final ArrayList<FreeCommodityBean> fcbs = new ArrayList<>();
        if (type == 0) {
            for (int i = 0; i < 10; i++) {
                if (i < 6) {
                    FreeCommodityBean fcb = new FreeCommodityBean(FreeCommodityBean.TYPE_BE_BEING_APPLY_NOT, "【利世优品】万仟堂陶瓷 同心杯带盖过滤办公室..万仟堂陶瓷 同心杯带盖过滤办公万仟堂陶瓷 同心杯带盖过滤办公.",
                            "http://www.bx5000.com/data/upload/shop/editor/web-101-101-3.jpg", 266, 1500, 150);
                    fcbs.add(fcb);
                } else {
                    FreeCommodityBean fcb = new FreeCommodityBean(FreeCommodityBean.TYPE_BE_BEING_APPLY_OK, "【利世优品】万仟堂陶瓷 同心杯带盖过滤办公室..万仟堂陶瓷 同心杯带盖过滤办公万仟堂陶瓷 同心杯带盖过滤办公.",
                            "http://www.bx5000.com/data/upload/shop/editor/web-101-101-3.jpg", 266, 1501, 150);
                    fcbs.add(fcb);
                }
            }
        } else if (type == 1) {
            for (int i = 0; i < 10; i++) {
                FreeCommodityBean fcb = new FreeCommodityBean(FreeCommodityBean.TYPE_START_BEFORE, "【利世优品】万仟堂陶瓷 同心杯带盖过滤办公室..万仟堂陶瓷 同心杯带盖过滤办公万仟堂陶瓷 同心杯带盖过滤办公.",
                        "http://www.bx5000.com/data/upload/shop/editor/web-101-101-3.jpg", 266, 1502, 150);
                fcbs.add(fcb);
            }
           
        } else if (type == 2) {
            for (int i = 0; i < 10; i++) {
                FreeCommodityBean fcb = new FreeCommodityBean(FreeCommodityBean.TYPE_FINISH, "【利世优品】万仟堂陶瓷 同心杯带盖过滤办公室..万仟堂陶瓷 同心杯带盖过滤办公万仟堂陶瓷 同心杯带盖过滤办公.",
                        "http://www.bx5000.com/data/upload/shop/editor/web-101-101-3.jpg", 266, 1503, 150);
                fcbs.add(fcb);
            }
        }
        callback.onLoadBefore();
        mFreeService.loadData("phone.get", "18696287331", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<FreeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onLoadFailed("FreeFragment错误" + e.toString());
                    }

                    @Override
                    public void onNext(FreeBean freeBean) {
                        callback.onLoadSuccess(fcbs);
                    }
                });

    }
}
