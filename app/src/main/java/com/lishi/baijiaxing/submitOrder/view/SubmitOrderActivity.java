package com.lishi.baijiaxing.submitOrder.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.CouponsActivity;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.bean.CouponsBean;
import com.lishi.baijiaxing.orderAddressManage.model.AddressList;
import com.lishi.baijiaxing.orderAddressManage.view.DeliveryAddressActivity;
import com.lishi.baijiaxing.orderAddressManage.view.EditAddressActivity;
import com.lishi.baijiaxing.pay.PayActivity;
import com.lishi.baijiaxing.shoppingCart.model.SCCommodityList;
import com.lishi.baijiaxing.submitOrder.adapter.SubmitOrderAdapter;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrder;
import com.lishi.baijiaxing.submitOrder.model.SubmitOrderBean;
import com.lishi.baijiaxing.submitOrder.model.WriteOrder;
import com.lishi.baijiaxing.submitOrder.presenter.SubmitOrderPresenterImpl;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * 提交订单
 */
public class SubmitOrderActivity extends BaseActivity implements View.OnClickListener, SubmitOrderView {
    private MyListView mMyListView;
    //    private SubmitOrderBean mSubmitOrderBean;
    private ImageView iv_left;
    private ImageView iv_right;
    private LinearLayout mLinearLayout_address;
    private RelativeLayout ll_submit_coupon;
    private AddressList.DataBean mDeliveryAddressBean;//收货地址
    private CouponsBean mCouponsBean;
    private TextView tv_deliveryaddress_name;
    private TextView tv_deliveryaddress_number;
    private TextView tv_deliveryaddress_address;
    private TopNavigationBar navigation_submit;
    private List<SCCommodityList.DataBean> mCommodityBeen;
    private SubmitOrderPresenterImpl mSubmitOrderPresenter;
    private WriteOrder mWriteOrder;
    private LinearLayout submit_order_addAddress;
    private TextView submit_order_addAddress_null;//添加收货地址
    private TextView submit_order_submit;//提交订单
    private String mForm = "";
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
    private String mZid ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        submit_order_addAddress_null = (TextView) findViewById(R.id.submit_order_addAddress_null);
        submit_order_addAddress = (LinearLayout) findViewById(R.id.submit_order_addAddress);


        tv_counttotalprice = (TextView) findViewById(R.id.tv_submitorder_totalprice);
        deliveryMethod_ll = (LinearLayout) findViewById(R.id.submit_order_deliveryMethod_ll);
        deliveryMethod_tv = (TextView) findViewById(R.id.submit_order_deliveryMethod);
        bill = (TextView) findViewById(R.id.submit_order_bill);
        coupons = (TextView) findViewById(R.id.submit_order_coupons);
        integral = (TextView) findViewById(R.id.submit_order_integral);
        ed_message = (EditText) findViewById(R.id.submit_order_leaveMessage);
        commodityPrice = (TextView) findViewById(R.id.submit_order_commodityPrice);
        freight = (TextView) findViewById(R.id.submit_order_freight);
        submit_order_submit = (TextView) findViewById(R.id.submit_order_submit);

        tv_counttotalprice.setOnClickListener(this);
        deliveryMethod_ll.setOnClickListener(this);
        bill.setOnClickListener(this);
        coupons.setOnClickListener(this);
        integral.setOnClickListener(this);
        ed_message.setOnClickListener(this);
        commodityPrice.setOnClickListener(this);
        freight.setOnClickListener(this);
        submit_order_addAddress_null.setOnClickListener(this);
        submit_order_submit.setOnClickListener(this);
        ed_message.clearFocus();

    }

    private void initView() {
        initData();
        if (mSubmitOrderPresenter == null) {
            mSubmitOrderPresenter = new SubmitOrderPresenterImpl(this);
        }
        mSubmitOrderPresenter.loadOrderData(mForm);
    }

    /**
     * 计算商品价格（未计算邮费等）
     *
     * @return
     */
    private int countCommodityPrice() {
        int commodityPrice = 0;
        for (int i = 0; i < mCommodityBeen.size(); i++) {
            commodityPrice += Double.valueOf(mCommodityBeen.get(i).getPrice()) * Integer.valueOf(mCommodityBeen.get(i).getBuyNum());
        }
        return commodityPrice;
    }

    /**
     * 计算总价
     *
     * @param
     */
    private void CountTotalPrice() {
        double totalprice = 0;
        for (int i = 0; i < mCommodityBeen.size(); i++) {
            totalprice += Integer.valueOf(mCommodityBeen.get(i).getBuyNum()) * Double.valueOf(mCommodityBeen.get(i).getPrice());
        }
        if (mCouponsBean != null) {
            if (totalprice > mCouponsBean.getAstrict_money()) {
                totalprice -= mCouponsBean.getMoney();
            } else {
                totalprice = 0;
            }
        }
        totalprice += Double.valueOf(mWriteOrder.getData().getFreight());
        tv_counttotalprice.setText("￥" + totalprice);
    }

    public void setDeliveryAddressBean(AddressList.DataBean deliveryAddressBean) {
        mDeliveryAddressBean = deliveryAddressBean;
        if (mDeliveryAddressBean == null || mDeliveryAddressBean.equals("")) {
            submit_order_addAddress.setVisibility(View.GONE);
            submit_order_addAddress_null.setVisibility(View.VISIBLE);
        } else {
            tv_deliveryaddress_name.setText(mDeliveryAddressBean.getConsigneeName());
            tv_deliveryaddress_number.setText(mDeliveryAddressBean.getConsigneeNumber());
            tv_deliveryaddress_address.setText(mDeliveryAddressBean.toString());
            submit_order_addAddress.setVisibility(View.VISIBLE);
            submit_order_addAddress_null.setVisibility(View.GONE);
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
        mCommodityBeen = getIntent().getParcelableArrayListExtra("list");
        mForm = getIntent().getStringExtra("type");
        if (mForm.equals("3")) {
            mZid = getIntent().getStringExtra("zid");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_address_root://收货地址
            case R.id.submit_order_addAddress_null:
                Intent startDeliveryAddressActivity1 = new Intent(SubmitOrderActivity.this, DeliveryAddressActivity.class);
                startActivityForResult(startDeliveryAddressActivity1, 1);
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
            case R.id.submit_order_submit://提交订单
                submitOrder();
                break;
        }
    }

    /**
     * 提交订单
     */
    private void submitOrder() {
        SubmitOrderBean sob = new SubmitOrderBean();
        sob.setDeliveryAddressBean(mDeliveryAddressBean);
        sob.setDataBeen(mCommodityBeen);
        if (mWriteOrder.getData().getInvoice() == null || mWriteOrder.getData().getInvoice().equals("")) {
            sob.setBill("不开发票");
        } else {
            sob.setBill(mWriteOrder.getData().getInvoice());
        }
        if (mWriteOrder.getData().getCoupon() == null || mWriteOrder.getData().getCoupon().equals("")) {
            sob.setCoupons(mWriteOrder.getData().getCoupon());
        } else {
            sob.setCoupons("无优惠券");
        }
        if (mWriteOrder.getData().getIntegral() == null || mWriteOrder.getData().getIntegral().equals("")) {
            sob.setIntegral(mWriteOrder.getData().getIntegral());
        } else {
            sob.setIntegral("无积分");
        }
        sob.setLeaveMsg(ed_message.getText().toString().trim());
        sob.setFrom(mForm);
        sob.setZid(mZid);
        if (mSubmitOrderPresenter == null) {
            mSubmitOrderPresenter = new SubmitOrderPresenterImpl(this);
        }
        mSubmitOrderPresenter.submitOrderData(sob);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {//优惠券
                CouponsBean couponsBean = data.getParcelableExtra("coupons");
                setCouponsBean(couponsBean);
                CountTotalPrice();
            } else if (requestCode == 1) {//收货地址
                AddressList.DataBean deliveryAddressBean = data.getParcelableExtra("address");
                setDeliveryAddressBean(deliveryAddressBean);
            }
        }
    }


    @Override
    public void onLoadOrderDataSuccess(WriteOrder writeOrder) {
        this.mWriteOrder = writeOrder;
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

        SubmitOrderAdapter adapter = new SubmitOrderAdapter(this, mCommodityBeen);
        mMyListView.setAdapter(adapter);

        setOrderInfo();
        Logger.d(writeOrder);

        if (writeOrder.getData().getAddr() == null || writeOrder.getData().getAddr().equals("")) {

        } else {
            setDeliveryAddressBean(writeOrder.getData().getAddr());
        }
        CountTotalPrice();
    }

    @Override
    public void onLoadOrderDataFailed(String error) {
        Logger.d(error);
    }

    @Override
    public void onSubmitOrderSuccess(SubmitOrder submitOrder) {
        Logger.d("sn" + submitOrder.getData().getSn() + "price" + submitOrder.getData().getPrice());
        Intent startPayActivity = new Intent(this, PayActivity.class);
        startPayActivity.putExtra("order", submitOrder.getData());
        startActivity(startPayActivity);
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onSubmitOrderFailed(String error) {
        Logger.d(error);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(Object data) {

    }

    @Override
    public void loadDataFailed(String error) {

    }

    public void setOrderInfo() {
        if (mWriteOrder.getData().getInvoice() == null || mWriteOrder.getData().getInvoice().equals("")) {
            bill.setText("不开发票");//发票
        } else {
            bill.setText(mWriteOrder.getData().getInvoice());
        }

        if (mWriteOrder.getData().getCoupon() == null || mWriteOrder.getData().getCoupon().equals("")) {
            coupons.setText("无优惠券");//优惠券
        } else {
            coupons.setText(mWriteOrder.getData().getInvoice());
        }

//        coupons.setText("请选择");//优惠券
        if (mWriteOrder.getData().getIntegral() == null || mWriteOrder.getData().getIntegral().equals("")) {
            integral.setText("无可用");//积分
        } else {
            integral.setText(mWriteOrder.getData().getIntegral());
        }
        if (mWriteOrder.getData().getFreight() == null || mWriteOrder.getData().getFreight().equals("")) {
            freight.setText("+￥0");//邮费
        } else {
            freight.setText("+￥" + mWriteOrder.getData().getFreight());
        }
        commodityPrice.setText("￥" + countCommodityPrice());//总价
        
        Logger.d(freight);
    }


//    @Override
//    public void onLoadOrderDataSuccess(SubmitOrderBean submitOrderBean) {
//        mSubmitOrderBean = submitOrderBean;
//        SubmitOrderAdapter adapter = new SubmitOrderAdapter(this, submitOrderBean.getSubmitOrderCommodityBeen());
//        mMyListView.setAdapter(adapter);
//        CountTotalPrice();
//        setDeliveryAddressBean(submitOrderBean.getDeliveryAddressBean());
//    }

}
