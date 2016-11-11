package com.lishi.baijiaxing.submitOrder.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.CouponsActivity;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.bean.CouponsBean;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.orderAddressManage.view.DeliveryAddressActivity;
import com.lishi.baijiaxing.submitOrder.adapter.SubmitOrderAdapter;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderBean;
import com.lishi.baijiaxing.submitOrder.presenter.SubmitOrderPresenterImpl;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;

/**
 * 提交订单
 */
public class SubmitOrderActivity extends BaseActivity implements View.OnClickListener, SubmitOrderView {
    private MyListView mMyListView;
    private SubmitOrderBean mSubmitOrderBean;
    private ImageView iv_left;
    private ImageView iv_right;
    private LinearLayout mLinearLayout_address;
    private RelativeLayout ll_submit_coupon;
    private DeliveryAddressBean mDeliveryAddressBean;//收货地址
    private CouponsBean mCouponsBean;
    private TextView tv_deliveryaddress_name;
    private TextView tv_deliveryaddress_number;
    private TextView tv_deliveryaddress_address;
    private TopNavigationBar navigation_submit;
    private ArrayList<CommodityBean> mCommodityBeen;
    private SubmitOrderPresenterImpl mSubmitOrderPresenter;
    /**
     * 支付方式LinearLayout
     */
    private LinearLayout deliveryMethod_ll;
    private TextView deliveryMethod_tv;
    /**
     * 发票
     */
    private TextView bill;
    /**
     * 优惠券
     */
    private TextView coupons;
    /**
     * 积分
     */
    private TextView integral;

    /**
     * 给商家留言
     */
    private EditText ed_message;
    /**
     * 商品金额
     */
    private TextView commodityPrice;
    /**
     * 运费
     */
    private TextView freight;
    /**
     * 总价
     */
    private TextView tv_counttotalprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_submit_order);
        findId();
        initView();
    }

    private void findId() {
        mMyListView = (MyListView) findViewById(R.id.listview_submit_order);
        iv_left = (ImageView) findViewById(R.id.left_address_iv);
        iv_right = (ImageView) findViewById(R.id.right_address_iv);
        ll_submit_coupon = (RelativeLayout) findViewById(R.id.ll_submit_coupon);
        mLinearLayout_address = (LinearLayout) findViewById(R.id.ll_address_root);
        tv_deliveryaddress_name = (TextView) findViewById(R.id.tv_deliveryaddress_name);
        tv_deliveryaddress_number = (TextView) findViewById(R.id.tv_deliveryaddress_number);
        tv_deliveryaddress_address = (TextView) findViewById(R.id.tv_deliveryaddress_address);
        navigation_submit = (TopNavigationBar) findViewById(R.id.navigation_submit);


        tv_counttotalprice = (TextView) findViewById(R.id.tv_submitorder_totalprice);
        deliveryMethod_ll = (LinearLayout) findViewById(R.id.submit_order_deliveryMethod_ll);
        deliveryMethod_tv = (TextView) findViewById(R.id.submit_order_deliveryMethod);
        bill = (TextView) findViewById(R.id.submit_order_bill);
        coupons = (TextView) findViewById(R.id.submit_order_coupons);
        integral = (TextView) findViewById(R.id.submit_order_integral);
        ed_message = (EditText) findViewById(R.id.submit_order_leaveMessage);
        commodityPrice = (TextView) findViewById(R.id.submit_order_commodityPrice);
        freight = (TextView) findViewById(R.id.submit_order_freight);

        tv_counttotalprice.setOnClickListener(this);
        deliveryMethod_ll.setOnClickListener(this);
        bill.setOnClickListener(this);
        coupons.setOnClickListener(this);
        integral.setOnClickListener(this);
        ed_message.setOnClickListener(this);
        commodityPrice.setOnClickListener(this);
        freight.setOnClickListener(this);
    }

    private void initView() {
        mSubmitOrderPresenter = new SubmitOrderPresenterImpl(this);
        initData();
        iv_left.setVisibility(View.VISIBLE);
        iv_right.setVisibility(View.VISIBLE);

        mLinearLayout_address.setOnClickListener(this);
        ll_submit_coupon.setOnClickListener(this);
        navigation_submit.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {
            }
        });

        bill.setText(mSubmitOrderBean.getBill());
        coupons.setText("请选择");
        integral.setText("无可用");
        commodityPrice.setText("￥" + countCommodityPrice());
        freight.setText("+￥" + mSubmitOrderBean.getFreight());
    }

    /**
     * 计算商品价格（未计算邮费等）
     *
     * @return
     */
    private int countCommodityPrice() {
        int commodityPrice = 0;
        for (int i = 0; i < mSubmitOrderBean.getSubmitOrderCommodityBeen().size(); i++) {
            commodityPrice += mSubmitOrderBean.getSubmitOrderCommodityBeen().get(i).getCommPrice() * mSubmitOrderBean.getSubmitOrderCommodityBeen().get(i).getBuyNum();
        }
        return commodityPrice;
    }

    /**
     * 计算总价
     */
    private void CountTotalPrice() {
        double totalprice = 0;
        for (int i = 0; i < mCommodityBeen.size(); i++) {
            totalprice += mCommodityBeen.get(i).getCommNum() * mCommodityBeen.get(i).getCommPrice();
        }
        if (mCouponsBean != null) {
            if (totalprice > mCouponsBean.getAstrict_money()) {
                totalprice -= mCouponsBean.getMoney();
            } else {
                totalprice = 0;
            }
        }
        totalprice += mSubmitOrderBean.getFreight();
        tv_counttotalprice.setText("￥" + totalprice);
    }

    public void setDeliveryAddressBean(DeliveryAddressBean deliveryAddressBean) {
        mDeliveryAddressBean = deliveryAddressBean;
        if (mDeliveryAddressBean != null) {
            tv_deliveryaddress_name.setText(mDeliveryAddressBean.getName());
            tv_deliveryaddress_number.setText(mDeliveryAddressBean.getNumber());
            tv_deliveryaddress_address.setText(mDeliveryAddressBean.toString());
        }
    }

    /**
     * 设置优惠券
     *
     * @param couponsBean
     */
    public void setCouponsBean(CouponsBean couponsBean) {
        mCouponsBean = couponsBean;
        if (mCouponsBean != null) {
            coupons.setText("减" + mCouponsBean.getMoney());
        } else {
            coupons.setText("无可用");
        }
    }

    private void initData() {
//        mCommodityBeen = new ArrayList<>();
//        for (int i = 0; i < 1; i++) {
//            CommodityBean c = new CommodityBean("", "韩国进口九日儿童海苔2gX40包饭紫菜...韩国进口九日儿童海苔2gX40包饭紫菜...", "", 200, 20210, 2, true);
//            mCommodityBeen.add(c);
//        }
        mCommodityBeen = getIntent().getParcelableArrayListExtra("list");
        
        mSubmitOrderPresenter.loadOrderData(mCommodityBeen);

        Log.e("mCommodityBeen", "mCommodityBeen=" + mCommodityBeen.size()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_address_root://收货地址
                Intent startDeliveryAddressActivity = new Intent(SubmitOrderActivity.this, DeliveryAddressActivity.class);
                startActivityForResult(startDeliveryAddressActivity, 1);
                break;
            case R.id.submit_order_deliveryMethod_ll://配送方式
                break;
            case R.id.submit_order_bill://发票
                break;
            case R.id.submit_order_coupons://优惠券
                Intent startCouponsActivity = new Intent(SubmitOrderActivity.this, CouponsActivity.class);
                startActivityForResult(startCouponsActivity, 0);
                break;
            case R.id.submit_order_integral://积分
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {//优惠券
                CouponsBean couponsBean = data.getParcelableExtra("coupons");
                setCouponsBean(couponsBean);
                CountTotalPrice();
            } else if (requestCode == 1) {//收货地址
                DeliveryAddressBean deliveryAddressBean = data.getParcelableExtra("address");
                setDeliveryAddressBean(deliveryAddressBean);
            }
        }
    }

    @Override
    public void loadOrderData() {

    }

    @Override
    public void onLoadOrderDataSuccess(SubmitOrderBean submitOrderBean) {
        mSubmitOrderBean = submitOrderBean;
        SubmitOrderAdapter adapter = new SubmitOrderAdapter(this, submitOrderBean.getSubmitOrderCommodityBeen());
        mMyListView.setAdapter(adapter);
        CountTotalPrice();
        setDeliveryAddressBean(submitOrderBean.getDeliveryAddressBean());
    }

    @Override
    public void onLoadOrderDataFailed() {

    }
}
