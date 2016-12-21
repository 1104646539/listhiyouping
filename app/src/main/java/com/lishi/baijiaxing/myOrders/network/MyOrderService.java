package com.lishi.baijiaxing.myOrders.network;

import com.lishi.baijiaxing.myOrders.model.MyOrderList;
import com.lishi.baijiaxing.myOrders.model.OrderDetails;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/12.
 */

public interface MyOrderService {
    /**获取订单列表
     * @param uid
     * @param token
     * @param type
     * @param form   普通商品1
     * @param page   分页
     * @param status 0:未付款、1:已付款（未发货）、4:退换货、5:已发货(待收货)6:已完成（待评价，已收货）、7:取消订单    不输入为全部订单
     * @return
     */
    @FormUrlEncoded
    @POST("/index_api.php?m=order&a=orderList")
    Observable<MyOrderList> getMyOrderList(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("form") String form, @Field("page") String page, @Field("status") String status);

    /**
     * 获取全部订单列表
     * @param uid
     * @param token
     * @param type
     * @param form
     * @param page
     * @return
     */
    @FormUrlEncoded
    @POST("/index_api.php?m=order&a=orderList")
    Observable<MyOrderList> getAllOrderList(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("form") String form, @Field("page") String page);

    /**
     * 获取订单详情
     * @param uid
     * @param token
     * @param type
     * @param oid
     * @return
     */
    @FormUrlEncoded
    @POST("/index_api.php?m=order&a=orderDetail")
    Observable<OrderDetails> getOrderDetails(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("oid") String oid);

    /**
     * 删除订单
     * @param uid
     * @param token
     * @param type
     * @param oid
     * @return
     */
    @FormUrlEncoded
    @POST("/index_api.php?m=order&a=quitOrder")
    Observable<SCOperation> deleteOrder(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("oid") String oid);

    /**
     * 订单状态(0:未付款、1:已付款、4:退换货、5:已发货、6:已完成
     *
     * @param uid
     * @param token
     * @param type
     * @param status
     * @return
     */
    @FormUrlEncoded
    @POST("/index_api.php?m=order&a=statusUpdata")
    Observable<SCOperation> changeOrderStatus(@Field("uid") String uid, @Field("token") String token, @Field("type") String type
            , @Field("oid") String orderNumber, @Field("status") String status);
}
