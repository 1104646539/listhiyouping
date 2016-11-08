package com.lishi.baijiaxing.shoppingCart.model;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartCallback;
import com.lishi.baijiaxing.shoppingCart.network.ShoppingCartService;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/18.
 */
public class ShoppingCartModelImpl extends BaseModel implements ShoppingCartModel {
    private static final String TAG = "ShoppingCartModelImpl";
    private final ShoppingCartService mShoppingCartService;

    public ShoppingCartModelImpl() {
        mShoppingCartService = (ShoppingCartService) getRetrofitManager().getHomeService(ShoppingCartService.class);
    }


    @Override
    public void loadData(final ShoppingCartCallback cartCallback) {

        ArrayList<CommodityBean> commodityBeans = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CommodityBean cbean = new CommodityBean("", "联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救",
                    "颜色：6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸", 5200, 1000, 2, false);
            commodityBeans.add(cbean);
        }
        ArrayList<HomeRecommendBean> homeRecommendBeens = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            HomeRecommendBean hrb = new HomeRecommendBean(200, 320, R.drawable.src7, "尽享丝滑尽享丝滑尽享丝滑尽享丝滑尽享丝滑尽享丝滑尽享丝滑");
            homeRecommendBeens.add(hrb);
        }
        final ShoppingBean shoppingBean = new ShoppingBean(commodityBeans, homeRecommendBeens);

        cartCallback.onLoadBefore();
        mShoppingCartService.loadData("phone.get", "18696287339", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommodityBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CommodityBean commodityBean) {
                        cartCallback.onLoadSuccess(shoppingBean);
                    }
                });
    }

    @Override
    public void addStore(final ShoppingCartCallback cartCallback, final ArrayList<CommodityBean> commodityBeens) {
        mShoppingCartService.addStore("phone.get", "18696287331", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommodityBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CommodityBean commodityBean) {
                        cartCallback.addStoreSuccess(commodityBeens);
                    }
                });
    }

    @Override
    public void changeStore(final ShoppingCartCallback cartCallback, final ArrayList<CommodityBean> commodityBeens) {
        mShoppingCartService.changeStore("phone.get", "18696287330", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommodityBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CommodityBean commodityBean) {
                        cartCallback.changeStoreSuccess(commodityBeens);
                    }
                });
    }


    @Override
    public void pullLoad(final ShoppingCartCallback cartCallback, final ArrayList<HomeRecommendBean> homeRecommend) {
        HomeRecommendBean recommend5 = new HomeRecommendBean(555, 666, R.drawable.src7, "三味书屋-手帕+香水30*30（订购从速，赶紧的。啦啦啦啦啦啦啦啦）");
        HomeRecommendBean recommend6 = new HomeRecommendBean(528, 666, R.drawable.src7, "三味书屋-手帕+香水30*30（订购从速，赶紧的。啦啦啦啦啦啦啦啦）");
        homeRecommend.add(recommend5);
        homeRecommend.add(recommend6);

        mShoppingCartService.pullLoad("phone.get", "18696287339", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeRecommendBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeRecommendBean homeRecommendBean) {
                        cartCallback.onLoadSuccess(homeRecommend);
                    }
                });
    }

    @Override
    public void deleteStore(final ShoppingCartCallback cartCallback, final ArrayList<CommodityBean> commodityBeens) {

        mShoppingCartService.removeStore("phone.get", "18696287339", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CommodityBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        cartCallback.removeStoreFailed(e.toString());
                    }

                    @Override
                    public void onNext(CommodityBean commodityBean) {
                        cartCallback.removeStoreSuccess(commodityBeens);
                    }
                });
    }

}
