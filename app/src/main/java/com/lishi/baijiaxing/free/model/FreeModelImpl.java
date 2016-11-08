package com.lishi.baijiaxing.free.model;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.free.FreeCallback;
import com.lishi.baijiaxing.free.network.FreeService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/17.
 */
public class FreeModelImpl extends BaseModel implements FreeModel {
    private FreeService mFreeService;

    public FreeModelImpl() {
        mFreeService = (FreeService) getRetrofitManager().getHomeService(FreeService.class);
    }

    @Override
    public void loadData(final FreeCallback callback, int type) {
        final FreeBean mFreeBean = new FreeBean();
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

        mFreeBean.setJsonArray(advertiseArray);

//        ArrayList<FreeCommodityBean> fcbs = new ArrayList<FreeCommodityBean>();
//        for (int i = 0; i < 10; i++) {
//            if (i < 6) {
//                FreeCommodityBean fcb = new FreeCommodityBean(FreeCommodityBean.TYPE_BE_BEING_APPLY_NOT, "【利世优品】万仟堂陶瓷 同心杯带盖过滤办公室..万仟堂陶瓷 同心杯带盖过滤办公万仟堂陶瓷 同心杯带盖过滤办公.",
//                        "http://www.bx5000.com/data/upload/shop/editor/web-101-101-3.jpg", 266, 1500, 150);
//                fcbs.add(fcb);
//            } else {
//                FreeCommodityBean fcb = new FreeCommodityBean(FreeCommodityBean.TYPE_BE_BEING_APPLY_OK, "【利世优品】万仟堂陶瓷 同心杯带盖过滤办公室..万仟堂陶瓷 同心杯带盖过滤办公万仟堂陶瓷 同心杯带盖过滤办公.",
//                        "http://www.bx5000.com/data/upload/shop/editor/web-101-101-3.jpg", 266, 1500, 150);
//                fcbs.add(fcb);
//            }
//        }
//        mFreeBean.setFreeCommodityBeen(fcbs);
        callback.onLoadBefore();
        mFreeService.loadData("phone.get", "18696287334", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json").observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<FreeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onLoadFailed("Free错误" + e.toString());
                    }

                    @Override
                    public void onNext(FreeBean freeBean) {
                        callback.onLoadSuccess(mFreeBean);
                    }
                });
    }

}
