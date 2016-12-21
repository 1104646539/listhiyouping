package com.lishi.baijiaxing.personal.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.stetho.common.LogUtil;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.BrowsingHistoryActivity;
import com.lishi.baijiaxing.adapter.OtherAdapter;
import com.lishi.baijiaxing.base.BaseFragment;
import com.lishi.baijiaxing.bean.UserBean;
import com.lishi.baijiaxing.home.view.Fragment_Home;
import com.lishi.baijiaxing.myCollect.MyCollectActivity;
import com.lishi.baijiaxing.myOrders.MyOrderFormActivity;
import com.lishi.baijiaxing.myOrders.view.AllOrderActivity;
import com.lishi.baijiaxing.myfree.MyFreeActivity;
import com.lishi.baijiaxing.myyiyuan.view.MyYiYuanActivity;
import com.lishi.baijiaxing.personal.adapter.MyOrderFormAdapter;
import com.lishi.baijiaxing.personal.model.LocalUserInfo;
import com.lishi.baijiaxing.personal.presenter.PersonalPresenterImpl;
import com.lishi.baijiaxing.userManager.view.UserManagerActivity;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.StartLoginRequest;
import com.lishi.baijiaxing.utils.UserUtil;
import com.lishi.baijiaxing.view.MyGridView;
import com.lishi.baijiaxing.wxapi.LoginActivity;
import com.lishi.baijiaxing.wxapi.model.Login;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@SuppressLint("ValidFragment")
public class Fragment_Personal extends BaseFragment implements View.OnClickListener, PersonalView {
    private MyGridView gridview_orderform, gridview_other;
    private String[] orderFromTitles = new String[]{"待付款", "待收货", "待评价", "返修/退换", "我的订单"};
    private int[] orderFromSrcs = new int[]{R.drawable.orderform1, R.drawable.orderform2, R.drawable.orderform3, R.drawable.orderform4, R.drawable.orderform5};
    private String[] otherTitles = new String[]{"我的收藏", "游览记录", "我的免费领", "我的一元拼"};
    private int[] otherSrcs = new int[]{R.drawable.my_other1, R.drawable.my_other3, R.drawable.free, R.drawable.yiyuan};
    private CircleImageView iv_user_icon;//用户头像
    private TextView tv_user_name;
    private PersonalPresenterImpl mPersonalPresenter;
    private View mView;
    /**
     * 帐号管理
     */
    private TextView personal_userManager;
    private TextView tv_userlevel;

    private static Fragment_Personal mFragment_personal;

    private boolean oldLogin = false;
    private boolean isLogin = false;


    public Fragment_Personal() {

    }

    public static Fragment_Personal newInstance() {
        if (mFragment_personal == null) {
            mFragment_personal = new Fragment_Personal();
        }
        return new Fragment_Personal();
    }

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
        tv_userlevel = (TextView) mView.findViewById(R.id.tv_userlevel);
    }

    private void initView() {
        if (mPersonalPresenter == null) {
            mPersonalPresenter = new PersonalPresenterImpl(this);
        }
        mPersonalPresenter.obtainLogin();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user_icon://头像
                Intent startLoginActivity = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(startLoginActivity, StartLoginRequest.PERSONAL);
                break;
            case R.id.personal_userManager://帐号管理
                if (UserUtil.getInstance().isLogin()) {
                    Intent startUserManagerActivity = new Intent(getActivity(), UserManagerActivity.class);
                    startActivity(startUserManagerActivity);
                } else {
                    Intent startLoginActivity2 = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(startLoginActivity2, StartLoginRequest.PERSONAL);
                }
                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (LoginUtil.getInstance().isLogin()) {
            isLogin = true;
        } else {
            isLogin = false;
        }
        oldLogin = isLogin;

        if (!hidden) {
            if (isLogin == oldLogin) {
                initView();
            }
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
    public void obtainLogin(boolean login) {
        /**
         *已登录就直接获取用户信息
         */
        if (LoginUtil.getInstance().isLogin()) {
            initGridView();
            mPersonalPresenter.getUserInfo(LoginUtil.getInstance().getLogin());
        } else {
            initGridView();
        }
    }

    private void initGridView() {
        iv_user_icon.setImageResource(R.drawable.tou);

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
                if (!UserUtil.getInstance().isLogin()) {
                    Intent startLoginActivity2 = new Intent(getActivity(), LoginActivity.class);
                    startActivityForResult(startLoginActivity2, StartLoginRequest.PERSONAL);
                } else {
                    if (position != 4) {
                        Intent startMyOrderFormActivity = new Intent(getActivity(), MyOrderFormActivity.class);
                        startMyOrderFormActivity.putExtra("page", position);
                        startActivity(startMyOrderFormActivity);
                    } else if (position == 4) {
                        Intent startAllOrderActivity = new Intent(getActivity(), AllOrderActivity.class);
                        startActivity(startAllOrderActivity);
                    }
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
                        Intent startBrowsingHistoryActivity = new Intent(getActivity(), BrowsingHistoryActivity.class);
                        startActivity(startBrowsingHistoryActivity);
                        break;
                    case 2:
                        Intent startMyFreeActivity = new Intent(getActivity(), MyFreeActivity.class);
                        startActivity(startMyFreeActivity);
                        break;
                    case 3:
                        Intent startMyYiYuanActivity = new Intent(getActivity(), MyYiYuanActivity.class);
                        startActivity(startMyYiYuanActivity);
                        break;
                }
            }
        });
    }

    @Override
    public void onLoginSuccess(UserBean userBean) {

    }

    @Override
    public void onLoginFailed(String error) {

    }

    @Override
    public void getUserInfoSuccess(com.lishi.baijiaxing.personal.model.LocalUserInfo localUserInfo) {
        Log.i("成功", "getUserInfoSuccess" + localUserInfo.getMsg());
        saveUserInfoAndSetting(localUserInfo);
    }

    /**
     * 保存用户信息
     *
     * @param localUserInfo
     */
    private void saveUserInfoAndSetting(LocalUserInfo localUserInfo) {
        UserUtil.getInstance().setLogin(localUserInfo);

        try {
            if (UserUtil.getInstance().getLogin().getData().getHeadimg() == null || UserUtil.getInstance().getLogin().getData().getHeadimg().equals("")) {
                Glide.with(this).load(R.drawable.tou).into(iv_user_icon);
            } else {
                Glide.with(this).load(UserUtil.getInstance().getLogin().getData().getHeadimg()).into(iv_user_icon);
            }
            if (!localUserInfo.getData().getNickname().equals("")) {
                tv_user_name.setText(localUserInfo.getData().getNickname());
                tv_userlevel.setText(localUserInfo.getData().getViplevel());
            }

        } catch (Exception e) {
            Glide.with(this).load(R.drawable.tou).into(iv_user_icon);
        }
    }

    @Override
    public void getUserInfoFailed(String error) {
        Log.i("失败", "getUserInfoFailed" + error);
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
    public void onResume() {
        super.onResume();
//        tv_user_name.setText(UserUtil.getInstance().getLogin().getData().getNickname());
//        try {
//            if (UserUtil.getInstance().getLogin().getData().getHeadimg() == null || UserUtil.getInstance().getLogin().getData().getHeadimg().equals("")) {
//                Glide.with(this).load(R.drawable.tou).into(iv_user_icon);
//            } else {
//                Glide.with(this).load(UserUtil.getInstance().getLogin().getData().getHeadimg()).into(iv_user_icon);
//            }
//        } catch (Exception e) {
//            Glide.with(this).load(R.drawable.tou).into(iv_user_icon);
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            final Login login = data.getParcelableExtra("login");
            if (login == null) {
                return;
            }
            Log.i("activity返回的数据", "login:" + login.getData().toString());
            mPersonalPresenter.getUserInfo(login);
//            test(login);
        }
    }


    /**
     * 测试用的
     *
     * @param login
     */
    private void test(final Login login) {
        OkHttpClient okHttpClient = new OkHttpClient();
//        FormBody fb = new FormBody.Builder().add("a","showInfo").add("uid", login.getData().getUid())
//                .add("token", login.getData().getToken()).add("type", login.getData().getType()).build();
//        Request request = new Request.Builder()
//                .url("http://192.168.17.106:8484/index_api.php?m=user")
//                .post(fb)
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//             //请求加入调度
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i("", "" + e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.i("", "" + response.body().string());
//            }
//        });

        RequestBody requestBodyPost = new FormBody.Builder()
                .add("a", "showInfo")
                .add("uid", login.getData().getUid())
                .add("token", login.getData().getToken())
                .add("type", login.getData().getType())
                .build();
        Request requestPost = new Request.Builder()
                .url("http://192.168.17.106:8484/index_api.php?m=user")
                .post(requestBodyPost)
                .build();
        okHttpClient.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("获取用户信息失败", "onFailure:" + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("获取用户信息后返回的", "onResponse:" + string);
            }
        });
    }
}
