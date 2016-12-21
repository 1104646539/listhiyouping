package com.lishi.baijiaxing.submitOrder.network;

import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;
import com.lishi.baijiaxing.submitOrder.model.WriteOrder;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/7.
 */

public interface SubmitOrderService {
    @FormUrlEncoded
    @POST("/index_api.php?m=order&a=writeOrder")
    Observable<WriteOrder> loadOrderData(@Field("type") String type, @Field("token") String token, @Field("uid") String uid, @Field("form") String form);

    @FormUrlEncoded
    @POST("/index_api.php?m=order&a=inOrder")
    Observable<SubmitOrder> submitOrderData(@Field("type") String type, @Field("token") String token, @Field("uid") String uid
            , @Field("aid") String aid, @Field("data") String commodityList, @Field("remark") String remark);
}
