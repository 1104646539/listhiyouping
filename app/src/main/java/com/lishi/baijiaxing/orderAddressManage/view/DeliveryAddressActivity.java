package com.lishi.baijiaxing.orderAddressManage.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.adapter.DeliveryAddressAdapter;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.inter.OnAddressChange;
import com.lishi.baijiaxing.orderAddressManage.model.AddressList;
import com.lishi.baijiaxing.orderAddressManage.model.UpAddress;
import com.lishi.baijiaxing.orderAddressManage.presenter.AddressPresenterImpl;
import com.lishi.baijiaxing.submitOrder.view.SubmitOrderActivity;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 收货地址管理
 */
public class DeliveryAddressActivity extends BaseActivity implements OnAddressChange, View.OnClickListener, AddressView, AdapterView.OnItemClickListener {
    private MyListView mMyListView;
    //    private ArrayList<DeliveryAddressBean> mDeliveryAddressBeans;
    private DeliveryAddressAdapter adapter;
    private AddressList mAddressList;
    private int mPosition = 0;
    private TextView tv_deliveryaddress_add;
    private boolean isAdd = false;
    private TopNavigationBar navigation_deliveryaddress;
    private AddressPresenterImpl mAddressPresenter;
    private ProgressBarUtil mProgressBarUtil;
    private boolean isChangeDeflute = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);
        findId();
        initView();
    }

    private void findId() {
        mMyListView = (MyListView) findViewById(R.id.listview_deliveryaddress);
        tv_deliveryaddress_add = (TextView) findViewById(R.id.tv_deliveryaddress_add);
        navigation_deliveryaddress = (TopNavigationBar) findViewById(R.id.navigation_deliveryaddress);
    }

    private void initView() {
        if (mAddressPresenter == null) {
            mAddressPresenter = new AddressPresenterImpl(this);
        }
        mAddressPresenter.loadAddressData();

        tv_deliveryaddress_add.setOnClickListener(this);

        navigation_deliveryaddress.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
    }


    @Override
    public void onCheckChangeListener(int position, boolean checkble) {
        Log.i("onCheckChangeListener", "点击了" + position + "" + checkble);
        if (mAddressList.getData().get(position).getIsDefalut().equals("0")) {
            mAddressList.getData().get(position).setIsDefalut("1");
        } else {
            mAddressList.getData().get(position).setIsDefalut("0");
        }
        mAddressPresenter.changeAddress(mAddressList.getData().get(position));
    }

    @Override
    public void onEditClickListener(int position) {
        Intent startEditAddressActivity = new Intent(DeliveryAddressActivity.this, EditAddressActivity.class);
        startEditAddressActivity.putExtra("data", mAddressList.getData().get(position));
        startActivityForResult(startEditAddressActivity, 0);
        Log.i("as", "下标是——————" + position);
        mPosition = position;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            AddressList.DataBean dataBean = data.getParcelableExtra("result");
            if (dataBean != null) {//更新地址信息
                if (requestCode == 0) {//编辑地址
                    if (mAddressPresenter == null) {
                        mAddressPresenter = new AddressPresenterImpl(this);
                    }
                    mAddressPresenter.changeAddress(dataBean);
                } else if (requestCode == 1) {//新增地址
                    Log.i("asd", "是否为默认" + dataBean.getIsDefalut());
                    isAdd = true;
                    Log.i("onActivityResult", "接受到新增地址");
                    if (mAddressPresenter == null) {
                        mAddressPresenter = new AddressPresenterImpl(this);
                    }
                    mAddressPresenter.addAddress(dataBean);
                }
            } else {
                Log.i("ad", "_____________删除");
            }
        } else {
            if (mAddressPresenter == null) {
                mAddressPresenter = new AddressPresenterImpl(this);
            }
            mAddressPresenter.loadAddressData();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_deliveryaddress_add://新增收货地址
                Intent startEditAddressActivity = new Intent(DeliveryAddressActivity.this, EditAddressActivity.class);
                startActivityForResult(startEditAddressActivity, 1);
                break;
        }

    }

    /**
     * 选择收货地址
     *
     * @param 
     * @param 
     * @param 
     * @param 
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("delivery", "点击了" + position);
        AddressList.DataBean deliveryAddressBean = mAddressList.getData().get(position);
        Intent intent = new Intent(DeliveryAddressActivity.this, SubmitOrderActivity.class);
        setResult(RESULT_OK, intent.putExtra("address", deliveryAddressBean));
        finish();
    }

    @Override
    public void onLoadAddressDataSuccess(AddressList addressList) {
        Log.i("DeliveryAddressActivity", "onLoadAddressDataSuccess：" + addressList.getData().toString());
        mAddressList = addressList;
        adapter = new DeliveryAddressAdapter(this, mAddressList.getData());
        mMyListView.setAdapter(adapter);
        mMyListView.setOnItemClickListener(this);
        adapter.setOnAddressChange(this);
    }

    @Override
    public void onLoadAddressDataFailed(String error) {
        Log.i("onAddAddressSuccess", "加载地址失败");
    }

    @Override
    public void onChangeAddressSuccess(UpAddress upAddress) {
        Log.i("onAddAddressSuccess", "修改地址成功");
        if (mAddressPresenter == null) {
            mAddressPresenter = new AddressPresenterImpl(this);
        }

        mAddressPresenter.loadAddressData();
    }

    @Override
    public void onChangeAddressFailed(String error) {
        Log.i("onAddAddressSuccess", "修改地址失败");
    }

    @Override
    public void onDeleteAddressSuccess(UpAddress upAddress) {
        Log.i("onAddAddressSuccess", "删除地址成功");
        if (mAddressPresenter == null) {
            mAddressPresenter = new AddressPresenterImpl(this);
        }
        mAddressPresenter.loadAddressData();
    }

    @Override
    public void onDeleteAddressFailed(String error) {
        Log.i("onAddAddressSuccess", "删除地址失败");
    }

    @Override
    public void onAddAddressSuccess(UpAddress address) {
        Log.i("onAddAddressSuccess", "添加地址成功");
        if (mAddressPresenter == null) {
            mAddressPresenter = new AddressPresenterImpl(this);
        }
        mAddressPresenter.loadAddressData();
    }

    @Override
    public void onAddAddressFailed(String error) {
        Log.i("onAddAddressSuccess", "添加地址成功");

    }

    @Override
    public void showDialog() {
        if (mProgressBarUtil == null) {
            mProgressBarUtil = new ProgressBarUtil(this);
        }
        mProgressBarUtil.show();
    }

    @Override
    public void closeDialog() {
        if (mProgressBarUtil != null) {
            mProgressBarUtil.dismiss();
        }
    }

    @Override
    public void loadDataSuccess(Object data) {

    }

    @Override
    public void loadDataFailed(String error) {

    }
}
