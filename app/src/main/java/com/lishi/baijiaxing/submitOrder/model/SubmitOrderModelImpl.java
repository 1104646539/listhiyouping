package com.lishi.baijiaxing.submitOrder.model;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.CouponsBean;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.submitOrder.presenter.SubmitOrderPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22.
 */
public class SubmitOrderModelImpl implements SubmitOrderModel {
    @Override
    public void loadOrderData(SubmitOrderPresenter presenter, ArrayList<CommodityBean> commodity) {
        DeliveryAddressBean mDeliveryAddressBean;
        CouponsBean mCouponsBean;

        mDeliveryAddressBean = new DeliveryAddressBean("MrWang", "18888886666", "广东省", "深圳市", "宝安区",
                "宝安中心啊宝安中心啊宝安中心啊宝安中心啊宝安中心啊", true);
        mCouponsBean = new CouponsBean("2016-9-9", 100, "全平台可用", 88, true);

        List<SubmitOrderCommodityBean> socbs = new ArrayList<>();
        for (int i = 0; i < commodity.size(); i++) {
            SubmitOrderCommodityBean soc = new SubmitOrderCommodityBean(commodity.get(i).getCommImgUrl(), commodity.get(i).getCommTitle()
                    , commodity.get(i).getCommPrice(), commodity.get(i).getCommId(), commodity.get(i).getCommNum());
            socbs.add(soc);
        }

        SubmitOrderBean sobs = new SubmitOrderBean(mDeliveryAddressBean, socbs, "平台配送", "无发票", mCouponsBean, 20, 20);

        presenter.onLoadOrderDataSuccess(sobs);
    }
}
