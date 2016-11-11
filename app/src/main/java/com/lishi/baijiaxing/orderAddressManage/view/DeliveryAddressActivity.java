package com.lishi.baijiaxing.orderAddressManage.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.adapter.DeliveryAddressAdapter;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;
import com.lishi.baijiaxing.inter.OnAddressChange;
import com.lishi.baijiaxing.orderAddressManage.presenter.AddressPresenterImpl;
import com.lishi.baijiaxing.submitOrder.view.SubmitOrderActivity;
import com.lishi.baijiaxing.view.MyListView;
import com.lishi.baijiaxing.view.TopNavigationBar;

import java.util.ArrayList;

/**
 * 收货地址管理
 */
public class DeliveryAddressActivity extends BaseActivity implements OnAddressChange, View.OnClickListener, AdapterView.OnItemClickListener, AddressView {
    private MyListView mMyListView;
    private ArrayList<DeliveryAddressBean> mDeliveryAddressBeans;
    private DeliveryAddressAdapter adapter;
    private int mPosition = 0;
    private TextView tv_deliveryaddress_add;
    private boolean isAdd = false;
    private TopNavigationBar navigation_deliveryaddress;
    private AddressPresenterImpl mAddressPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
        mAddressPresenter = new AddressPresenterImpl(this);
        initData();

        tv_deliveryaddress_add.setOnClickListener(this);

        mMyListView.setOnItemClickListener(this);
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

    private void initData() {
        mAddressPresenter.loadAddressData();
    }

    @Override
    public void onCheckChangeListener(int position, boolean checkble) {
        Log.i("onCheckChangeListener", "点击了" + position + "" + checkble);
        mAddressPresenter.setDefault(mDeliveryAddressBeans, position, checkble);
    }

    @Override
    public void onEditClickListener(int position) {
        Intent startEditAddressActivity = new Intent(DeliveryAddressActivity.this, EditAddressActivity.class);
        startEditAddressActivity.putExtra("data", mDeliveryAddressBeans.get(position));
        startActivityForResult(startEditAddressActivity, 0);
        Log.i("as", "下标是——————" + position);
        mPosition = position;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            DeliveryAddressBean deliveryAddress = data.getParcelableExtra("data");
            if (deliveryAddress != null) {//更新地址信息
                if (requestCode == 0) {//编辑地址
                    mAddressPresenter.changeAddress(mDeliveryAddressBeans, mPosition, deliveryAddress);
                } else if (requestCode == 1) {//新增地址
                    Log.i("asd", "是否为默认" + deliveryAddress.isChecked());
                    isAdd = true;
                    mAddressPresenter.addAddress(mDeliveryAddressBeans, deliveryAddress);
                }
                if (deliveryAddress.isChecked()) {
//                    SetDefault();
                    mAddressPresenter.setDefault(mDeliveryAddressBeans, mPosition, true);
                }
            } else {
                mAddressPresenter.deleteAddress(mDeliveryAddressBeans, mPosition);
                Log.i("ad", "_____________删除");
            }
//            adapter.notifyDataSetChanged();

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
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("delivery", "点击了" + position);
        DeliveryAddressBean deliveryAddressBean = mDeliveryAddressBeans.get(position);
        Intent intent = new Intent(DeliveryAddressActivity.this, SubmitOrderActivity.class);
        setResult(RESULT_OK, intent.putExtra("address", deliveryAddressBean));
        finish();
    }

    @Override
    public void loadAddressData() {

    }

    @Override
    public void onLoadAddressDataSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen) {
        if (deliveryAddressBeen != null) {
            mDeliveryAddressBeans = deliveryAddressBeen;
            adapter = new DeliveryAddressAdapter(this, mDeliveryAddressBeans);
            mMyListView.setAdapter(adapter);
            adapter.setOnAddressChange(this);
        }
    }

    @Override
    public void onLoadAddressDataFailed() {

    }

    @Override
    public void changeAddress() {

    }

    @Override
    public void deleteAddress() {

    }

    @Override
    public void addAddress() {

    }

    @Override
    public void setDefault() {

    }

    @Override
    public void onChangeAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen) {
        mDeliveryAddressBeans = deliveryAddressBeen;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onChangeAddressFailed() {

    }

    @Override
    public void onDeleteAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen) {
        mDeliveryAddressBeans = deliveryAddressBeen;
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDeleteAddressFailed() {

    }

    @Override
    public void onAddAddressSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen) {
        mDeliveryAddressBeans = deliveryAddressBeen;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAddAddressFailed() {

    }

    @Override
    public void onSetDefaultSuccess(ArrayList<DeliveryAddressBean> deliveryAddressBeen) {
        mDeliveryAddressBeans = deliveryAddressBeen;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSetDefaultFailed() {

    }
}
