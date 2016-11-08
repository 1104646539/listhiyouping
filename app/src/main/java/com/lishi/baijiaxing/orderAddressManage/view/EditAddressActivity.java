package com.lishi.baijiaxing.orderAddressManage.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.Address.BaseAddressActivity;
import com.lishi.baijiaxing.Address.widget.OnWheelChangedListener;
import com.lishi.baijiaxing.Address.widget.WheelView;
import com.lishi.baijiaxing.Address.widget.adapters.ArrayWheelAdapter;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.bean.DeliveryAddressBean;

/**
 * 编辑/新增收货地址
 */
public class EditAddressActivity extends BaseAddressActivity implements OnClickListener, OnWheelChangedListener {
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private LinearLayout ll_editaddress_wheelview;
    private TextView tv_editaddress_confirm;
    private DeliveryAddressBean mAddressBean;
    private TextView tv_editaddress_save;
    private EditText edit_editaddress_name;
    private EditText edit_editaddress_number;
    private TextView edit_editaddress_address;
    private EditText edit_editaddress_particular;
    private TextView tv_editaddress_default;
    private ImageView iv_editaddress_back;
    private TextView tv_editaddress_delete;
    private AlertDialog deletaDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_address);
        setUpViews();
        setUpListener();
        setUpData();
    }

    private void setUpViews() {
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewDistrict = (WheelView) findViewById(R.id.id_district);
        ll_editaddress_wheelview = (LinearLayout) findViewById(R.id.ll_editaddress_wheelview);
        edit_editaddress_address = (TextView) findViewById(R.id.edit_editaddress_address);
        tv_editaddress_confirm = (TextView) findViewById(R.id.tv_editaddress_confirm);
        tv_editaddress_save = (TextView) findViewById(R.id.tv_editaddress_save);
        edit_editaddress_name = (EditText) findViewById(R.id.ed_editaddress_name);
        edit_editaddress_number = (EditText) findViewById(R.id.ed_editaddress_number);
        edit_editaddress_particular = (EditText) findViewById(R.id.ed_editaddress_particular);
        tv_editaddress_default = (TextView) findViewById(R.id.tv_editaddress_default);
        iv_editaddress_back = (ImageView) findViewById(R.id.iv_editaddress_back);
        tv_editaddress_delete = (TextView) findViewById(R.id.tv_editaddress_delete);

    }

    private void setUpListener() {
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);
        ll_editaddress_wheelview.setOnClickListener(this);
        edit_editaddress_address.setOnClickListener(this);
        tv_editaddress_confirm.setOnClickListener(this);
        tv_editaddress_save.setOnClickListener(this);
        tv_editaddress_default.setOnClickListener(this);
        iv_editaddress_back.setOnClickListener(this);
        tv_editaddress_delete.setOnClickListener(this);
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(EditAddressActivity.this, mProvinceDatas));
        //最多显示7个item
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);

        updateCities();
        updateAreas();
        /**
         * 接受页面传入的地址
         */
        mAddressBean = getIntent().getParcelableExtra("data");
        if (mAddressBean != null) {//点击编辑进入
            String province = mAddressBean.getProvince();
            String city = mAddressBean.getCity();
            String area = mAddressBean.getArea();

            for (int i = 0; i < mProvinceDatas.length; i++) {
                if (mProvinceDatas[i].equals(province)) {
                    mViewProvince.setCurrentItem(i);
                    for (int j = 0; j < mCitisDatasMap.get(province).length; j++) {
                        if (mCitisDatasMap.get(province)[j].equals(city)) {
                            mViewCity.setCurrentItem(j);
                            for (int k = 0; k < mDistrictDatasMap.get(city).length; k++) {
                                if (mDistrictDatasMap.get(city)[k].equals(area)) {
                                    mViewDistrict.setCurrentItem(k);
                                }
                            }
                        }
                    }
                }
            }
            edit_editaddress_number.setText(mAddressBean.getNumber());
            edit_editaddress_name.setText(mAddressBean.getName());
            edit_editaddress_address.setText(mAddressBean.getProvince() + mAddressBean.getCity() + mAddressBean.getArea());
            edit_editaddress_particular.setText(mAddressBean.getSpecific());
        } else {//点击新增进入

        }

    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub

        if (wheel == mViewProvince) {
            updateCities();

        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
            if (oldValue == newValue) {
                ll_editaddress_wheelview.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     */
    private void updateAreas() {

        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);
        mCurrentDistrictName = areas[0];
        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
    }

    /**
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_confirm:
//                showSelectedResult();
//                break;
            case R.id.edit_editaddress_address://设置省市区
                if (ll_editaddress_wheelview.getVisibility() == View.VISIBLE) {
                    ll_editaddress_wheelview.setVisibility(View.INVISIBLE);
                } else {
                    ll_editaddress_wheelview.setVisibility(View.VISIBLE);
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(edit_editaddress_address
                            .getWindowToken(), 0);
                }
                break;
            case R.id.tv_editaddress_confirm://点击确定选择省市区
                ll_editaddress_wheelview.setVisibility(View.INVISIBLE);
                edit_editaddress_address.setText(mCurrentProviceName + mCurrentCityName + mCurrentDistrictName);
                if (mAddressBean == null) {
                    mAddressBean = new DeliveryAddressBean();
                }
                mAddressBean.setProvince(mCurrentProviceName);
                mAddressBean.setCity(mCurrentCityName);
                mAddressBean.setArea(mCurrentDistrictName);


                break;
            case R.id.tv_editaddress_save://保存
                if (mAddressBean == null) {
                    mAddressBean = new DeliveryAddressBean();
                }
                if (edit_editaddress_name.getText().toString().length() >= 1
                        && edit_editaddress_number.getText().toString().length() == 11
                        && edit_editaddress_particular.getText().length() >= 1) {
                    mAddressBean.setName(edit_editaddress_name.getText().toString());
                    mAddressBean.setNumber(edit_editaddress_number.getText().toString());
                    mAddressBean.setProvince(mCurrentProviceName);
                    mAddressBean.setCity(mCurrentCityName);
                    mAddressBean.setArea(mCurrentDistrictName);
                    mAddressBean.setSpecific(edit_editaddress_particular.getText().toString());
                    if (mAddressBean != null) {
                        Intent intent = new Intent(this, DeliveryAddressActivity.class);
                        setResult(RESULT_OK, intent.putExtra("data", mAddressBean));
                        Log.i("保存", mAddressBean.isChecked() + "!!!!!!!!!!!!!!!!");
                    }
                    finish();
                } else {
                    Toast.makeText(this, "格式错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_editaddress_default://设为默认地址
                if (mAddressBean == null) {
                    mAddressBean = new DeliveryAddressBean();
                }
                mAddressBean.setChecked(true);
                Toast.makeText(this, "已设置成默认地址", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_editaddress_back://topBar返回
                finish();
                break;
            case R.id.tv_editaddress_delete://topBar删除
                showDeleteDialog();
                break;
        }
    }

    /**
     * 删除对话框
     */
    private void showDeleteDialog() {
        deletaDialog = new AlertDialog.Builder(EditAddressActivity.this).setMessage("确定删除?").setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAddressBean = null;
                Intent intent = new Intent(EditAddressActivity.this, DeliveryAddressActivity.class);
                intent.putExtra("data", mAddressBean);
                setResult(RESULT_OK, intent);
                finish();
            }
        }).show();
    }

    private void showSelectedResult() {
        Toast.makeText(EditAddressActivity.this, "��ǰѡ��:" + mCurrentProviceName + "," + mCurrentCityName + ","
                + mCurrentDistrictName + "," + mCurrentZipCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (deletaDialog != null) {
            deletaDialog.dismiss();
        }
    }
}
