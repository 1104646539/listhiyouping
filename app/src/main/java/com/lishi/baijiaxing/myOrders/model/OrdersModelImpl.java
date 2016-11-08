package com.lishi.baijiaxing.myOrders.model;

import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.myOrders.presenter.OrdersPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
public class OrdersModelImpl implements OrdersModel {

    @Override
    public void loadData(OrdersPresenter presenter, String url) {
        ArrayList<MyOrderFormBean> mMyOrderFormBeen = new ArrayList<>();
        if (url.equals("Fragment_StayPayment")) {//待付款
            for (int j = 0; j < 2; j++) {
                List<CommodityBean> commodityBeans = new ArrayList<CommodityBean>();
                for (int i = 0; i < 2; i++) {
                    CommodityBean cbean = new CommodityBean("", "百姓图腾《百姓珍邮》本商品只接受在线个性化预订，详情敬请拨   打400 859 000", "颜色：6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸", 5200, 1000, 2, false);
                    commodityBeans.add(cbean);
                }
                StoreBean storeBean = new StoreBean(false, 2000, "百姓图腾商城旗舰店", "", commodityBeans);
                MyOrderFormBean myOrderFormBean = new MyOrderFormBean(MyOrderFormBean.STAYPAYMENT, storeBean, 5, 6666);
                mMyOrderFormBeen.add(myOrderFormBean);
            }
        } else if (url.equals("Fragment_StayTakeGoods")) {//待收货
            for (int j = 0; j < 2; j++) {
                List<CommodityBean> commodityBeans = new ArrayList<CommodityBean>();
                for (int i = 0; i < 2; i++) {
                    CommodityBean cbean = new CommodityBean("", "百姓图腾《百姓珍邮》本商品只接受在线个性化预订，详情敬请拨   打400 859 000", "颜色：6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸", 5200, 1000, 2, false);
                    commodityBeans.add(cbean);
                }
                StoreBean storeBean = new StoreBean(false, 2000, "百姓图腾商城旗舰店", "", commodityBeans);
                MyOrderFormBean myOrderFormBean = new MyOrderFormBean(MyOrderFormBean.STAYPAYMENT, storeBean, 5, 6666);
                if (j == 0) {
                    myOrderFormBean.setState(MyOrderFormBean.STAYSHIPMENTS);
                } else {
                    myOrderFormBean.setState(MyOrderFormBean.YETSHIPMENTS);
                }
                mMyOrderFormBeen.add(myOrderFormBean);
            }
        } else if (url.equals("Fragment_StayEvaluate")) {//评价

            for (int j = 0; j < 2; j++) {
                List<CommodityBean> commodityBeans = new ArrayList<CommodityBean>();
                for (int i = 0; i < 2; i++) {
                    CommodityBean cbean = new CommodityBean("", "百姓图腾《百姓珍邮》本商品只接受在线个性化预订，详情敬请拨   打400 859 000", "颜色：6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸", 5200, 1000, 2, false);
                    commodityBeans.add(cbean);
                }
                StoreBean storeBean = new StoreBean(false, 2000, "百姓图腾商城旗舰店", "", commodityBeans);
                MyOrderFormBean myOrderFormBean = new MyOrderFormBean(MyOrderFormBean.STAYEVALUATE, storeBean, 5, 6666);
                mMyOrderFormBeen.add(myOrderFormBean);
            }

        } else if (url.equals("Fragment_ReturnedGoods")) {//待返修/退货

            for (int j = 0; j < 3; j++) {
                List<CommodityBean> commodityBeans = new ArrayList<CommodityBean>();
                for (int i = 0; i < 2; i++) {
                    CommodityBean cbean = new CommodityBean("", "百姓图腾《百姓珍邮》本商品只接受在线个性化预订，详情敬请拨   打400 859 000", "颜色：6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸", 5200, 1000, 2, false);
                    commodityBeans.add(cbean);
                }
                StoreBean storeBean = new StoreBean(false, 2000, "百姓图腾商城旗舰店", "", commodityBeans);
                MyOrderFormBean myOrderFormBean = new MyOrderFormBean(MyOrderFormBean.STAYPAYMENT, storeBean, 5, 6666);
                if (j == 0) {
                    myOrderFormBean.setState(MyOrderFormBean.DEALFINISH);
                } else if (j == 1) {
                    myOrderFormBean.setState(MyOrderFormBean.REFUNDIN);
                } else {
                    myOrderFormBean.setState(MyOrderFormBean.REFUNDFINISH);
                }
                mMyOrderFormBeen.add(myOrderFormBean);
            }
        }
        presenter.onSuccess(mMyOrderFormBeen);
    }
}
