package com.lishi.baijiaxing.personal.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.BrowsingHistoryActivity;
import com.lishi.baijiaxing.adapter.OtherAdapter;
import com.lishi.baijiaxing.base.BaseFragment;
import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.myCollect.MyCollectActivity;
import com.lishi.baijiaxing.myOrders.MyOrderFormActivity;
import com.lishi.baijiaxing.myfree.MyFreeActivity;
import com.lishi.baijiaxing.myyiyuan.view.MyYiYuanActivity;
import com.lishi.baijiaxing.personal.adapter.MyOrderFormAdapter;
import com.lishi.baijiaxing.personal.presenter.PersonalPresenterImpl;
import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.wxapi.LoginActivity;
import com.lishi.baijiaxing.userManager.view.UserManagerActivity;
import com.lishi.baijiaxing.view.MyGridView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Personal extends BaseFragment implements View.OnClickListener, PersonalView {
    private MyGridView gridview_orderform, gridview_other;
    private String[] orderFromTitles = new String[]{"待付款", "待收货", "待评价", "返修/退换", "我的订单"};
    private int[] orderFromSrcs = new int[]{R.drawable.orderform1, R.drawable.orderform2, R.drawable.orderform3, R.drawable.orderform4, R.drawable.orderform5};
    private String[] otherTitles = new String[]{"我的收藏", "收藏店铺", "游览记录", "售后服务", "我的免费领", "我的一元拼"};
    private int[] otherSrcs = new int[]{R.drawable.my_other1, R.drawable.my_other2, R.drawable.my_other3, R.drawable.my_other4, R.drawable.free, R.drawable.yiyuan};
    private CircleImageView iv_user_icon;//用户头像
    private TextView tv_user_name;
    private PersonalPresenterImpl mPersonalPresenter;
    private View mView;
    /**
     * 帐号管理
     */
    private TextView personal_userManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, container, false);
        findId();
        initView();

        return mView;
    }

    private void findId() {
        tv_user_name = (TextView) mView.findViewById(R.id.tv_personal_username);
        gridview_orderform = (MyGridView) mView.findViewById(R.id.gridview_my_orderform);
        gridview_other = (MyGridView) mView.findViewById(R.id.gridview_my_other);
        iv_user_icon = (CircleImageView) mView.findViewById(R.id.iv_user_icon);
        personal_userManager = (TextView) mView.findViewById(R.id.personal_userManager);
    }

    private void initView() {
        mPersonalPresenter = new PersonalPresenterImpl(this);
        mPersonalPresenter.obtainLogin();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_icon://头像
                Intent startLoginActivity = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(startLoginActivity, 0);
                break;
            case R.id.personal_userManager://帐号管理
                Intent startUserManagerActivity = new Intent(getActivity(), UserManagerActivity.class);
                startActivity(startUserManagerActivity);
                break;
        }
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void login() {

    }

    @Override
    public void obtainLogin(boolean isLogin) {
        UserBean userBean = new UserBean("咸鱼这名也不给", "110110110", "wangluo", 0, R.drawable.tou);
        mPersonalPresenter.login(userBean);
        mPersonalPresenter.loadData();
    }

    @Override
    public void onLoginSuccess(UserBean userBean) {

        tv_user_name.setText(userBean.getUserName());
        iv_user_icon.setImageResource(userBean.getPhoto());

        MyOrderFormAdapter orderFromAdapter = new MyOrderFormAdapter(getActivity(), orderFromTitles, orderFromSrcs);
        gridview_orderform.setAdapter(orderFromAdapter);

        OtherAdapter otherAdapter = new OtherAdapter(getActivity(), otherTitles, otherSrcs);
        gridview_other.setAdapter(otherAdapter);

        iv_user_icon.setOnClickListener(this);
        tv_user_name.setOnClickListener(this);
        personal_userManager.setOnClickListener(this);
        gridview_orderform.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent startMyOrderFormActivity = new Intent(getActivity(), MyOrderFormActivity.class);
                if (position != 4) {
                    startMyOrderFormActivity.putExtra("page", position);
                    startActivity(startMyOrderFormActivity);
                }
            }
        });

        gridview_other.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent startMyCollectActivity = new Intent(getActivity(), MyCollectActivity.class);
                        startActivity(startMyCollectActivity);
                        break;
                    case 1:
                        break;
                    case 2:
                        Intent startBrowsingHistoryActivity = new Intent(getActivity(), BrowsingHistoryActivity.class);
                        startActivity(startBrowsingHistoryActivity);
                        break;
                    case 3:
                        break;
                    case 4:
                        Intent startMyFreeActivity = new Intent(getActivity(), MyFreeActivity.class);
                        startActivity(startMyFreeActivity);
                        break;
                    case 5:
                        Intent startMyYiYuanActivity = new Intent(getActivity(), MyYiYuanActivity.class);
                        startActivity(startMyYiYuanActivity);
                        break;

                }
            }
        });
    }

    @Override
    public void onLoginFailed(String error) {

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (data.getStringExtra("result").equals("wx")) {
                if (!LocalUserInfo.getInstance().isNull()) {
                    tv_user_name.setText(LocalUserInfo.getInstance().getNickName() + "");
                    Glide.with(this).load(LocalUserInfo.getInstance().getPhotoUrl()).into(iv_user_icon).onStart();
                    Log.i("onActivityResult", "wx登录成功");
                }
            } else if (data.getStringExtra("result").equals("qq")) {
                tv_user_name.setText(LocalUserInfo.getInstance().getNickName() + "");
                Glide.with(this).load(LocalUserInfo.getInstance().getPhotoUrl()).into(iv_user_icon).onStart();
                Log.i("onActivityResult", "qq登录成功");
            }

        }
    }
}
