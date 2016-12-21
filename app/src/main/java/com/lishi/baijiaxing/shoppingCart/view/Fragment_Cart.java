package com.lishi.baijiaxing.shoppingCart.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.activity.MainActivity;
import com.lishi.baijiaxing.base.BaseFragment;
import com.lishi.baijiaxing.bean.CommentBean;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.personal.view.Fragment_Personal;
import com.lishi.baijiaxing.shoppingCart.adapter.RecommendAdapter;
import com.lishi.baijiaxing.shoppingCart.adapter.ShoppingCartAdapter;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.shoppingCart.model.SCCommodityList;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.shoppingCart.model.SCRecommendList;
import com.lishi.baijiaxing.shoppingCart.model.ShoppingBean;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.shoppingCart.presenter.ShoppingCartPresenterImpl;
import com.lishi.baijiaxing.submitOrder.view.SubmitOrderActivity;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.NetUtils;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.utils.ShoppingBadgeUtil;
import com.lishi.baijiaxing.utils.StartLoginRequest;
import com.lishi.baijiaxing.utils.Status;
import com.lishi.baijiaxing.view.MyScrollView;
import com.lishi.baijiaxing.wxapi.LoginActivity;
import com.lishi.baijiaxing.wxapi.model.Login;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.security.auth.login.LoginException;

/**
 * 购物车
 */
@SuppressLint("ValidFragment")
public class Fragment_Cart extends BaseFragment implements View.OnClickListener, ShoppingCartView, ShoppingCartAdapter.OnItemClick, SpringView.OnFreshListener, YiYuanHotAdapter.OnItemClickListener {

    private static Fragment_Cart mFragment_Cart;
    private TextView tv_cart_totalprice;//总价
    private CheckBox check_cartall, check_cartall_edit;//全选按钮
    private TextView tv_edit;//编辑
    private boolean isEdit = false;//是否在编辑状态
    private LinearLayout ll_count, ll_edit;
    private View rootView;
    private SCRecommendList mHomeRecommends;
    private SCCommodityList mSCCommodityList;
    private MyScrollView mScroll;
    private TextView tv_load;
    private LinearLayout ll_root;
    private Button btn_delete;
    private TextView tv_count;
    private ShoppingCartPresenterImpl mShoppingCartPresenterImpl;
    private boolean isPrepare;
    private LinearLayout shoppingcart_null;
    private RecyclerView mRecyclerView;
    private RecyclerView recyclerView_recommend;
    private List<CommodityBean> transitionBean;//转换后的
    private ShoppingCartAdapter adapter;
    private RecommendAdapter mRecommendAdapter;
    private SpringView mSpringView;//上拉加载，下拉刷新
    private LinearLayout shoppingcart_login;//未登录
    private TextView shoppingcart_login_tv;
    private boolean loading = false;
    private int pmPostion;
    private ProgressBarUtil mProgressBarUtil;
    private LinearLayout ll_car_bottom;

    private boolean oldLogin = false;
    private boolean isLogin = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        findId();
        return rootView;
    }


    public Fragment_Cart() {

    }

    public static Fragment_Cart newInstance() {
        if (mFragment_Cart == null) {
            mFragment_Cart = new Fragment_Cart();
        }
        return new Fragment_Cart();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }

    private void findId() {
        tv_count = (TextView) rootView.findViewById(R.id.tv_cart_count);
        tv_cart_totalprice = (TextView) rootView.findViewById(R.id.tv_cartbom_num);
        check_cartall = (CheckBox) rootView.findViewById(R.id.checkbox_cart_bottom_all);
        tv_edit = (TextView) rootView.findViewById(R.id.tv_cart_topnavigation_edit);
        ll_count = (LinearLayout) rootView.findViewById(R.id.ll_cart_count);
        ll_edit = (LinearLayout) rootView.findViewById(R.id.ll_cart_edit);
        check_cartall_edit = (CheckBox) rootView.findViewById(R.id.checkbox_cart_all_edit);
        mScroll = (MyScrollView) rootView.findViewById(R.id.scroll_cart);
        ll_root = (LinearLayout) rootView.findViewById(R.id.ll_cart_gridview_root);
        mSpringView = (SpringView) rootView.findViewById(R.id.cart_springView);
        btn_delete = (Button) rootView.findViewById(R.id.btn_cart_delete);
        shoppingcart_null = (LinearLayout) rootView.findViewById(R.id.shoppingcart_null);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_shoppingcart);
        recyclerView_recommend = (RecyclerView) rootView.findViewById(R.id.recyclerView_cart_recommend);
        shoppingcart_login = (LinearLayout) rootView.findViewById(R.id.shoppingcart_login);
        shoppingcart_login_tv = (TextView) rootView.findViewById(R.id.shoppingcart_login_tv);
        ll_car_bottom = (LinearLayout) rootView.findViewById(R.id.ll_cart_bom);

        check_cartall.setOnClickListener(this);
        tv_edit.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        tv_count.setOnClickListener(this);
        shoppingcart_login_tv.setOnClickListener(this);

        mSpringView.setHeader(new DefaultHeader(getActivity()));
        mSpringView.setFooter(new DefaultFooter(getActivity()));
        mSpringView.setListener(this);
        mSpringView.setType(SpringView.Type.FOLLOW);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        check_cartall.setChecked(false);
        check_cartall_edit.setChecked(false);
        if (LoginUtil.getInstance().isLogin()) {
            shoppingcart_null.setVisibility(View.GONE);
            shoppingcart_login.setVisibility(View.GONE);
        } else {
            shoppingcart_login.setVisibility(View.VISIBLE);
        }
        allChecked(false);
        if (mSCCommodityList != null && mSCCommodityList.getData() != null && mSCCommodityList.getData().size() != 0) {
            if (ShoppingBadgeUtil.getInstance().getBadgeCount() != 0) {
                MainActivity.mBadgeView.setBadgeCount(ShoppingBadgeUtil.getInstance().getBadgeCount());
            }
        }
    }

    private void initView() {
        if (mShoppingCartPresenterImpl == null) {
            mShoppingCartPresenterImpl = new ShoppingCartPresenterImpl(this);
        }
        //开始加载数据
        mShoppingCartPresenterImpl.loadRecommend();
        mShoppingCartPresenterImpl.loadCommodity();

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
            if (LoginUtil.getInstance().isLogin()) {
                shoppingcart_login.setVisibility(View.GONE);
//                if (isLogin == oldLogin) {
                initView();
//                }
            }
        }
    }

    @Override
    public void onRefresh() {
        mShoppingCartPresenterImpl.loadCommodity();
    }

    @Override
    public void onLoadmore() {

    }

    /**
     * 计算总价
     */
    private void countTotalPrice() {
        float totalPrice = 0;
        int size = transitionBean.size();
        for (int i = 0; i < size; i++) {
            if (transitionBean.get(i).isChecked()) {
                totalPrice += Double.valueOf(transitionBean.get(i).getDataBean().getBuyNum()) * Double.valueOf(transitionBean.get(i).getDataBean().getPrice());
            }
        }
        tv_cart_totalprice.setText(totalPrice + "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cart_topnavigation_edit://编辑
                if (!isEdit) {
                    tv_edit.setText("完成");
                    isEdit = true;
                    ll_edit.setVisibility(View.VISIBLE);
                    ll_count.setVisibility(View.INVISIBLE);
                    check_cartall.setChecked(false);
                    tv_cart_totalprice.setText("0.0");
                    ll_root.setVisibility(View.GONE);
                } else {
                    tv_edit.setText("编辑");
                    isEdit = false;
                    ll_edit.setVisibility(View.INVISIBLE);
                    ll_count.setVisibility(View.VISIBLE);
                    check_cartall_edit.setChecked(false);
                    ll_root.setVisibility(View.VISIBLE);
                    tv_cart_totalprice.setText("0.0");
                }
                allChecked(false);
                break;
            case R.id.btn_cart_delete://删除
                delete();
                if (mSCCommodityList != null && mSCCommodityList.getData() != null && mSCCommodityList.getData().size() != 0) {
                    ShoppingBadgeUtil.getInstance().setBadgeCount(mSCCommodityList.getData().size());
                    if (ShoppingBadgeUtil.getInstance().getBadgeCount() != 0) {
                        MainActivity.mBadgeView.setBadgeCount(ShoppingBadgeUtil.getInstance().getBadgeCount());
                    }
                }
                break;
            case R.id.tv_cart_count://去结算
                startSubmitOrderActivity();
                break;
            case R.id.checkbox_cart_bottom_all://全选、取消全选
                if (transitionBean == null || transitionBean.size() == 0) {
                    return;
                }
                if (check_cartall.isChecked()) {
                    allChecked(true);
                } else {
                    allChecked(false);
                }
                break;
            case R.id.shoppingcart_login_tv://登录
                Intent startLoginActivity = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(startLoginActivity, StartLoginRequest.SHOPPINGCART);
                break;
        }
    }


    /**
     * 启动确认订单页面
     *
     * @param
     * @param
     */
    private void startSubmitOrderActivity() {
        if (transitionBean == null || transitionBean.size() == 0) {
            return;
        }

        ArrayList<SCCommodityList.DataBean> submitOrderData = new ArrayList<>();
        ListIterator<CommodityBean> commentBeanListIterator = transitionBean.listIterator();
        while (commentBeanListIterator.hasNext()) {
            CommodityBean cb = commentBeanListIterator.next();
            if (cb.isChecked()) {
                submitOrderData.add(cb.getDataBean());
            }
        }

        if (submitOrderData.size() != 0) {
            Intent startSubmitOrderActivity = new Intent(getActivity(), SubmitOrderActivity.class);
            startSubmitOrderActivity.putExtra("list", submitOrderData);
            startSubmitOrderActivity.putExtra("type", "0");
            startActivityForResult(startSubmitOrderActivity, 1);
        }
    }

    /**
     * 全选/取消全选
     */
    private void allChecked(boolean isChecked) {
        if (transitionBean == null || transitionBean.size() == 0) {
            return;
        }

        for (int i = 0; i < transitionBean.size(); i++) {
            transitionBean.get(i).setChecked(isChecked);
        }
        adapter.notifyDataSetChanged();
        countTotalPrice();
        Log.e("allChecked", "allChecked=" + isChecked);
    }

    /**
     * 删除
     */
    private void delete() {
        Iterator<CommodityBean> cit = transitionBean.listIterator();
        List<String> deleteIds = new ArrayList<>();
        while (cit.hasNext()) {
            CommodityBean c = cit.next();
            if (c.isChecked()) {
                deleteIds.add(c.getDataBean().getCid());
//                cit.remove();
            }
        }
        sendDeleteMsg(deleteIds);

//        adapter.notifyDataSetChanged();
//        if (transitionBean.size() == 0) {
//            shoppingcart_null.setVisibility(View.VISIBLE);
//        }
    }

    private void sendDeleteMsg(List<String> deleteIds) {
        if (mShoppingCartPresenterImpl == null) {
            mShoppingCartPresenterImpl = new ShoppingCartPresenterImpl(this);
        }
        mShoppingCartPresenterImpl.removeCommodity(deleteIds);
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isPrepare || isVisible) {
            return;
        }

        initView();
    }


    @Override
    public void showDialog() {
        if (mProgressBarUtil == null) {
            mProgressBarUtil = new ProgressBarUtil(getActivity());
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

    @Override
    public void onCommodityMinus(View v, int position) {
        int num = Integer.valueOf(transitionBean.get(position).getDataBean().getBuyNum());
        if (num > 1) {
//            transitionBean.get(position).getDataBean().setBuyNum(--num + "");
            pmPostion = position;
            mShoppingCartPresenterImpl.changeCommodity(transitionBean.get(position).getDataBean(), String.valueOf(num - 1));
        }
    }

    @Override
    public void onCommodityPlus(View v, int position) {
        int num = Integer.valueOf(transitionBean.get(position).getDataBean().getBuyNum());
//        transitionBean.get(position).getDataBean().setBuyNum(++num + "");
        pmPostion = position;
        mShoppingCartPresenterImpl.changeCommodity(transitionBean.get(position).getDataBean(), String.valueOf(num + 1));
    }

    @Override
    public void onCommodityCheck(boolean isChecked, int position) {
        transitionBean.get(position).setChecked(isChecked);
        Log.e("onCommodityCheck", "isChecked=" + isChecked + position);
        countTotalPrice();
        countIsAllChecked(isChecked);
    }

    /**
     * 计算是否触发全选
     *
     * @param isChecked
     */
    private void countIsAllChecked(boolean isChecked) {
        int max = 0;
        for (int i = 0; i < transitionBean.size(); i++) {
            if (transitionBean.get(i).isChecked() == isChecked) {
                max++;
            }
            Log.e("transitionBean", "transitionBean=" + i + transitionBean.get(i).isChecked() + isChecked);
        }
        if (max == transitionBean.size()) {
            allChecked(isChecked);
            if (!isEdit) {
                check_cartall.setChecked(isChecked);
            } else {
                check_cartall_edit.setChecked(isChecked);
            }
        }
        if (max < transitionBean.size()) {
            if (!isEdit) {
                check_cartall.setChecked(false);
            } else {
                check_cartall_edit.setChecked(false);
            }
        }
    }

    @Override
    public void onPullLoadDataSuccess(SCRecommendList recommendList) {
        mSpringView.onFinishFreshAndLoad();
    }

    @Override
    public void onPullLoadDataFailed(String error) {
        mSpringView.onFinishFreshAndLoad();
    }

    @Override
    public void onRemoveDataSuccess(SCOperation operation) {
        if (operation.getStatus().equals(Status.STATUS_OPERATION_SUCCESS)) {
            if (mShoppingCartPresenterImpl == null) {
                mShoppingCartPresenterImpl = new ShoppingCartPresenterImpl(this);
            }
            mShoppingCartPresenterImpl.loadCommodity();
        }
    }

    @Override
    public void onRemoveDataFailed(String error) {
        Log.i("onRemoveDataFailed", "error:" + error);
    }

    @Override
    public void onLoadCommoditySuccess(SCCommodityList scCommodityList) {
        this.mSCCommodityList = scCommodityList;
//        if (!LoginUtil.getInstance().isLogin()) {
//            shoppingcart_login.setVisibility(View.VISIBLE);
//        }
        ll_car_bottom.setVisibility(View.VISIBLE);
        Log.i("onLoadCommoditySuccess", "scCommodityList:" + scCommodityList.getData().size());
        //没有加入购物车的商品
        if (mSCCommodityList == null || mSCCommodityList.getData().size() == 0) {
            mRecyclerView.setVisibility(View.GONE);
            shoppingcart_null.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            shoppingcart_null.setVisibility(View.GONE);
        }
        transitionBean();
        Log.i("onLoadCommoditySuccess", "transitionBean:" + transitionBean.size());
//        if (adapter == null) {
        adapter = new ShoppingCartAdapter(getActivity(), transitionBean);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(this);
//        } else {
//            adapter.notifyDataSetChanged();
//        }
//        countTotalPrice();
        mSpringView.onFinishFreshAndLoad();
        //记录红点数量
        if (mSCCommodityList != null && mSCCommodityList.getData() != null && mSCCommodityList.getData().size() != 0) {
            ShoppingBadgeUtil.getInstance().setBadgeCount(mSCCommodityList.getData().size());
            if (ShoppingBadgeUtil.getInstance().getBadgeCount() != 0) {
                MainActivity.mBadgeView.setBadgeCount(ShoppingBadgeUtil.getInstance().getBadgeCount());
            }
        }
        check_cartall.setChecked(false);
        check_cartall_edit.setChecked(false);
        allChecked(false);
        countTotalPrice();
    }

    @Override
    public void onLoadRecommendSuccess(SCRecommendList scRecommendList) {
        this.mHomeRecommends = scRecommendList;

        Log.i("onLoadRecommendSuccess", "scRecommendList:" + scRecommendList.getData().size());
        GridLayoutManager recommendManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView_recommend.setLayoutManager(recommendManager);

        mRecommendAdapter = new RecommendAdapter(getActivity(), mHomeRecommends);
        recyclerView_recommend.setAdapter(mRecommendAdapter);

        mRecommendAdapter.setOnItemClickListener(this);

        mSpringView.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadCommodityFailed(String error) {
        Log.i("onLoadCommodityFailed", "error:" + error);

        if (error.equals("未登录")) {
            shoppingcart_login.setVisibility(View.VISIBLE);
            ll_car_bottom.setVisibility(View.GONE);
        }
        mSpringView.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadRecommendFailed(String error) {
        Log.i("onLoadRecommendFailed", "error:" + error);
        mSpringView.onFinishFreshAndLoad();
    }

    @Override
    public void onChangeCommoditySuccess(SCOperation scOperation) {
        mShoppingCartPresenterImpl.upCommodityInfo(transitionBean.get(pmPostion).getDataBean());
    }

    @Override
    public void onChangeCommodityFailed(String error) {
        Log.i("onChangeCommodityFailed", "error:" + error);

    }

    @Override
    public void upCommodityInfoSuccess(SCCommodityList scCommodityList) {
        Log.i("upCommodityInfoFailed", "scCommodityList:" + scCommodityList.getData().get(0));
//        List<SCCommodityList.DataBean> datas = mSCCommodityList.getData();
//        datas.set(0, scCommodityList.getData().get(0));
//        mSCCommodityList.setData(datas);
        transitionBean.get(pmPostion).getDataBean().setBuyNum(scCommodityList.getData().get(0).getBuyNum());

//        transitionBean();
        adapter.notifyItemChanged(pmPostion);
        countTotalPrice();
    }

    @Override
    public void upCommodityInfoFailed(String error) {
        Log.i("upCommodityInfoFailed", "error:" + error);
    }

    public void transitionBean() {
        if (mSCCommodityList.getData().size() == 0) {
            transitionBean = null;
            return;
        }
        transitionBean = new ArrayList<>();
        CommodityBean cb;

        List<SCCommodityList.DataBean> dataBeanList = mSCCommodityList.getData();
        Iterator<SCCommodityList.DataBean> dataBeanIterator = dataBeanList.listIterator();
        while (dataBeanIterator.hasNext()) {
            SCCommodityList.DataBean dataBean = dataBeanIterator.next();
            cb = new CommodityBean(dataBean);
            transitionBean.add(cb);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == 1) {//如果提交订单成功
                initView();
            } else if (requestCode == StartLoginRequest.SHOPPINGCART) {
                final Login login = data.getParcelableExtra("login");
                if (login == null) {
                    return;
                }
                shoppingcart_login.setVisibility(View.GONE);
                Log.i("activity返回的数据", "login:" + login.getData().toString());
                saveToken(login);
            }
        }
        //重新加载数据
        if (mShoppingCartPresenterImpl == null) {
            mShoppingCartPresenterImpl = new ShoppingCartPresenterImpl(this);
        }
        mShoppingCartPresenterImpl.loadCommodity();
    }

    /**
     * 保存token
     *
     * @param login
     */
    private void saveToken(Login login) {
        LoginUtil.getInstance().setLogin(login);
        if (mShoppingCartPresenterImpl == null) {
            mShoppingCartPresenterImpl = new ShoppingCartPresenterImpl(this);
        }
        mShoppingCartPresenterImpl.loadCommodity();
    }

    @Override
    public void onClickListener(View view, int position) {
        Intent startCommodityDetails = new Intent(getActivity(), CommodityDetailsActivity.class);
        if (mHomeRecommends.getData().get(position) != null && !mHomeRecommends.getData().get(position).getGid().equals("0")) {
            if (mHomeRecommends.getData().get(position) != null && !mHomeRecommends.getData().get(position).equals("0") && NetUtils.isConnected(getActivity())) {
                startCommodityDetails.putExtra("gid", mHomeRecommends.getData().get(position).getGid());
                startActivity(startCommodityDetails);
            }
        }
    }
}
