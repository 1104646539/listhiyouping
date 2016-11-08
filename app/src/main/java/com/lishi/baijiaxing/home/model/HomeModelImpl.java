package com.lishi.baijiaxing.home.model;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.home.HomeCallBack;
import com.lishi.baijiaxing.home.network.HomeService;
import com.lishi.baijiaxing.bean.HomeRecommendBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/17.
 */
public class HomeModelImpl extends BaseModel implements HomeModel {
    private HomeService mHomeService;

    public HomeModelImpl() {
        mHomeService = (HomeService) getRetrofitManager().getHomeService(HomeService.class);
    }

    @Override
    public void loadData(final HomeCallBack callBack) {

        HomeRecommendBean recommend1 = new HomeRecommendBean(551, 666, R.drawable.src7, "三味书屋-手帕+香水30*30（订购从速，赶紧的。啦啦啦啦啦啦啦啦）");
        HomeRecommendBean recommend2 = new HomeRecommendBean(522, 666, R.drawable.src7, "三味书屋-手帕+香水30*30（订购从速，赶紧的。啦啦啦啦啦啦啦啦）");
        HomeRecommendBean recommend3 = new HomeRecommendBean(553, 666, R.drawable.src7, "三味书屋-手帕+香水30*30（订购从速，赶紧的。啦啦啦啦啦啦啦啦）");
        HomeRecommendBean recommend4 = new HomeRecommendBean(524, 666, R.drawable.src7, "三味书屋-手帕+香水30*30（订购从速，赶紧的。啦啦啦啦啦啦啦啦）");
        ArrayList<HomeRecommendBean> recommendDatas = new ArrayList<HomeRecommendBean>();
        recommendDatas.add(recommend1);
        recommendDatas.add(recommend2);
        recommendDatas.add(recommend3);
        recommendDatas.add(recommend4);

        ArrayList<HomeCommodityBean> classifyDatas = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            CommodityBean c = new CommodityBean();
            HomeCommodityBean homeCommodity1 = new HomeCommodityBean(c);
            classifyDatas.add(homeCommodity1);
        }
        JSONArray advertiseArray = new JSONArray();
        try {
            JSONObject head_img0 = new JSONObject();
            head_img0.put("head_img", "http://www.bx5000.com/data/upload/shop/editor/web-101-102-1-3.jpg");
            JSONObject head_img1 = new JSONObject();
            head_img1.put("head_img", "http://www.bx5000.com/data/upload/shop/editor/web-101-101-2.jpg");
            JSONObject head_img2 = new JSONObject();
            head_img2.put("head_img", "http://www.bx5000.com/data/upload/shop/editor/web-101-101-3.jpg");
            advertiseArray.put(head_img0);
            advertiseArray.put(head_img1);
            advertiseArray.put(head_img2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final HomeBean mHomeBean = new HomeBean(advertiseArray, classifyDatas, recommendDatas);


        callBack.onLoadBefore();
        mHomeService.loadData("phone.get", "18696287339", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeBean>() {
                    @Override
                    public void onCompleted() {
                        callBack.onLoadComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onLoadFailed(e.toString());
                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        callBack.onLoadSuccess(mHomeBean);
                    }
                });

//        callBack.onLoadSuccess(mHomeBean);
    }

    @Override
    public void pullRecommendData(final HomeCallBack callBack) {
        final ArrayList<HomeRecommendBean> mHomeRecommends = new ArrayList<>();
        HomeRecommendBean recommend5 = new HomeRecommendBean(555, 666, R.drawable.src7, "三味书屋-手帕+香水30*30（订购从速，赶紧的。啦啦啦啦啦啦啦啦）");
        HomeRecommendBean recommend6 = new HomeRecommendBean(528, 666, R.drawable.src7, "三味书屋-手帕+香水30*30（订购从速，赶紧的。啦啦啦啦啦啦啦啦）");
        mHomeRecommends.add(recommend5);
        mHomeRecommends.add(recommend6);

        mHomeService.pullToRefresh("phone.get", "18696287339", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.pullToRefreshFailed(e.toString());
                    }

                    @Override
                    public void onNext(HomeBean homeRecommends) {
                        callBack.pullToRefreshSuccess(mHomeRecommends);
                    }
                });
    }
}
