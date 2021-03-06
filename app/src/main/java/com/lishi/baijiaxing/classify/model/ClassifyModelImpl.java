package com.lishi.baijiaxing.classify.model;

import android.util.Log;

import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.classify.ClassifyCallback;
import com.lishi.baijiaxing.classify.network.ClassifySerVice;
import com.lishi.baijiaxing.home.model.HomeBean;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/17.
 */
public class ClassifyModelImpl extends BaseModel implements ClassifyModel {
    private String[] oneclassify = new String[]{"推荐分类", "礼品", "数码", "电脑办公", "餐饮具", "饰品", "化妆品", "茶酒", "零食", "粮油副食", "图书", "生鲜", "运动户外", "家具", "奢品", "钟表珠宝", "生活旅行", "宠物", "汽车"};
    private ClassifySerVice mClassifySerVice;

    public ClassifyModelImpl() {
        mClassifySerVice = (ClassifySerVice) getRetrofitManager().getHomeService(ClassifySerVice.class);
    }
//
//    @Override
//    public void loadData(final ClassifyCallback callback) {
//        final ArrayList<OneClassify> oneDatas = new ArrayList<>();
//
//        for (int j = 0; j < oneclassify.length; j++) {
//            List<TwoClassify> ts = new ArrayList<>();
//
//            List<ThreeClassify> tts = new ArrayList<>();
//            for (int i = 0; i < 6; i++) {
//                ThreeClassify tt1 = new ThreeClassify("苹果U盘" + i, "http://www.bx5000.com/data/upload/shop/editor/web-101-102-1-3.jpg");
//                tts.add(tt1);
//            }
//            TwoClassify t1 = new TwoClassify("收藏品" + j, tts);
//            ts.add(t1);
//
//            List<ThreeClassify> tts1 = new ArrayList<>();
//            for (int i = 0; i < 6; i++) {
//                ThreeClassify tt1 = new ThreeClassify("苹果U盘" + i, "http://www.bx5000.com/data/upload/shop/editor/web-101-102-1-3.jpg");
//                tts1.add(tt1);
//            }
//            TwoClassify t2 = new TwoClassify("收藏品" + j, tts);
//            ts.add(t2);
//
//            OneClassify oneClassity = new OneClassify("http://www.bx5000.com/data/upload/shop/editor/web-101-102-1-3.jpg", ts);
//            oneDatas.add(oneClassity);
//            callback.onLoadSuccess(oneDatas);
//        }
//    }

    @Override
    public void loadClassList(final ClassifyCallback callback) {
        mClassifySerVice.loadClassList("categoryNameList").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ClassList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.loadClassListFailed(e.toString());
                    }

                    @Override
                    public void onNext(ClassList classList) {
                        callback.loadClassListSuccess(classList);
                    }
                });
    }

    @Override
    public void loadClassOne(final ClassifyCallback callback, String classId) {
        mClassifySerVice.loadClassOne("categoryGoodsList", classId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ClassOne>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.loadClassFailed(e.toString());
                    }

                    @Override
                    public void onNext(ClassOne classOne) {
                        callback.loadClassSuccess(classOne);
                    }
                });
    }

    @Override
    public void loadAd(final ClassifyCallback callback) {
        mClassifySerVice.loadAd("adUrl").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ClassAd>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.loadAdFailed(e.toString());
                    }

                    @Override
                    public void onNext(ClassAd classAd) {
                        callback.loadAdSuccess(classAd);
                    }
                });
    }
}
