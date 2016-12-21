package com.lishi.baijiaxing.submitOrder.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.CouponsBean;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.submitOrder.SubmitOrderCallback;
import com.lishi.baijiaxing.submitOrder.network.SubmitOrderService;
import com.lishi.baijiaxing.submitOrder.presenter.SubmitOrderPresenter;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.Status;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/8/22.
 */
public class SubmitOrderModelImpl extends BaseModel implements SubmitOrderModel {
    private SubmitOrderService mSubmitOrderService;

    public SubmitOrderModelImpl() {
        mSubmitOrderService = (SubmitOrderService) getRetrofitManager().getHomeService(SubmitOrderService.class);
    }

    @Override
    public void loadOrderData(final SubmitOrderCallback callback, String from) {
        if (!LoginUtil.getInstance().isLogin()) {
            Logger.d("未登录");
            callback.onLoadOrderDataFailed("未登录");
            return;
        }
        Logger.d(from);
        mSubmitOrderService.loadOrderData(LoginUtil.getInstance().getLogin().getData().getType(), LoginUtil.getInstance().getLogin().getData().getToken()
                , LoginUtil.getInstance().getLogin().getData().getUid(), from).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WriteOrder>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onLoadOrderDataFailed(e.toString());
                    }

                    @Override
                    public void onNext(WriteOrder writeOrder) {
                        Logger.d("writeOrder:" + writeOrder);
                        if (writeOrder.getStatus().equals(Status.STATUS_GETUSERINFO_SUCCESS)) {
                            callback.onLoadOrderDataSuccess(writeOrder);
                        } else {
                            callback.onLoadOrderDataFailed(writeOrder.getMsg());
                        }
                    }
                });
    }

    @Override
    public void submitOrderData(final SubmitOrderCallback callback, SubmitOrderBean submitOrderBean) {
        if (!LoginUtil.getInstance().isLogin()) {
            Logger.d("未登录");
            callback.onSubmitOrderFailed("未登录");
            return;
        }

        int size = submitOrderBean.getDataBeen().size();
        Gson gson = new Gson();
        String cid = "";
        if (submitOrderBean.getFrom().equals("0")) {
            List<SubmitId> cids = new ArrayList<SubmitId>();
            for (int i = 0; i < size; i++) {
                cids.add(new SubmitId(submitOrderBean.getDataBeen().get(i).getCid(), submitOrderBean.getDataBeen().get(i).getType()));
            }
            cid = gson.toJson(cids);
        } else {
            List<SubmitFreeId> freeIds = new ArrayList<>();
            freeIds.add(new SubmitFreeId(submitOrderBean.getZid(), submitOrderBean.getDataBeen().get(0).getGid(), submitOrderBean.getFrom()));
            cid = gson.toJson(freeIds);
        }
      
        Logger.d(cid);

        mSubmitOrderService.submitOrderData(LoginUtil.getInstance().getLogin().getData().getType(), LoginUtil.getInstance().getLogin().getData().getToken()
                , LoginUtil.getInstance().getLogin().getData().getUid(), submitOrderBean.getDeliveryAddressBean().getAid(), cid,
                submitOrderBean.getLeaveMsg()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<SubmitOrder>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Logger.d("writeOrder:" + e.toString());
                callback.onSubmitOrderFailed(e.toString());
            }

            @Override
            public void onNext(SubmitOrder submitOrder) {
                Logger.d("writeOrder:" + submitOrder);
                if (Status.STATUS_ORDER_SUCCESS.equals(submitOrder.getStatus())) {
                    callback.onSubmitOrderSuccess(submitOrder);
                } else {
                    callback.onSubmitOrderFailed(submitOrder.getStatus());
                }
            }
        });
    }

    static class SubmitId implements Parcelable {
        private String cid;
        private String type;

        public SubmitId(String cid, String type) {
            this.cid = cid;
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cid);
            dest.writeString(this.type);
        }

        public SubmitId() {
        }

        protected SubmitId(Parcel in) {
            this.cid = in.readString();
            this.type = in.readString();
        }

        public static final Parcelable.Creator<SubmitId> CREATOR = new Parcelable.Creator<SubmitId>() {
            @Override
            public SubmitId createFromParcel(Parcel source) {
                return new SubmitId(source);
            }

            @Override
            public SubmitId[] newArray(int size) {
                return new SubmitId[size];
            }
        };
    }

    static class SubmitFreeId implements Parcelable {
        private String zid;
        private String gid;
        private String type;

        public String getZid() {
            return zid;
        }

        public void setZid(String zid) {
            this.zid = zid;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.zid);
            dest.writeString(this.gid);
            dest.writeString(this.type);
        }

        public SubmitFreeId(String zid, String gid, String type) {
            this.zid = zid;
            this.gid = gid;
            this.type = type;
        }

        public SubmitFreeId() {
        }

        protected SubmitFreeId(Parcel in) {
            this.zid = in.readString();
            this.gid = in.readString();
            this.type = in.readString();
        }

        public static final Parcelable.Creator<SubmitFreeId> CREATOR = new Parcelable.Creator<SubmitFreeId>() {
            @Override
            public SubmitFreeId createFromParcel(Parcel source) {
                return new SubmitFreeId(source);
            }

            @Override
            public SubmitFreeId[] newArray(int size) {
                return new SubmitFreeId[size];
            }
        };
    }
}
