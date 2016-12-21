package com.lishi.baijiaxing.orderAddressManage.network;

import com.lishi.baijiaxing.orderAddressManage.model.UpAddress;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/2.
 */

public interface EditAddressService {
    @FormUrlEncoded
    @POST("/index_api.php?m=add")
    Observable<UpAddress> changeAddress(@Field("a") String upAdd, @Field("type") String type, @Field("uid") String uid, @Field("token") String token,
                                        @Field("aid") String aid, @Field("consigneeName") String consigneeName, @Field("province") String province, @Field("city") String city,
                                        @Field("district") String district, @Field("details") String details, @Field("consigneeNumber") String consigneeNumber,
                                        @Field("isDefalut") String isDefalut);

    @FormUrlEncoded
    @POST("/index_api.php?m=add")
    Observable<UpAddress> deleteAddress(@Field("a") String delAdd, @Field("type") String type, @Field("uid") String uid, @Field("aid") String add,
                                        @Field("token") String token);
}
