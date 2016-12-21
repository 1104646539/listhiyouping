package com.lishi.baijiaxing.details.view;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.customize.model.NormsBean;
import com.lishi.baijiaxing.customize.widget.MyNormsView;
import com.lishi.baijiaxing.details.adapter.FragmentDetailsAdapter;
import com.lishi.baijiaxing.details.model.CommodityDetails;
import com.lishi.baijiaxing.details.model.CommodityDetailsBean;
import com.lishi.baijiaxing.details.presenter.CommodityDetailsPresenterImpl;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartActivity;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.shoppingCart.model.SCOperation;
import com.lishi.baijiaxing.submitOrder.view.SubmitOrderActivity;
import com.lishi.baijiaxing.utils.LoginUtil;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.utils.ShoppingBadgeUtil;
import com.lishi.baijiaxing.utils.StartLoginRequest;
import com.lishi.baijiaxing.view.BadgeView;
import com.lishi.baijiaxing.wxapi.LoginActivity;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通商品详情——详情
 * Created by Administrator on 2016/10/19.
 */
public class Fragment_Commodity_Details extends BaseFragmentV4 implements CommodityDetailsView, View.OnClickListener {
    private View mView;
    private static Fragment_Commodity_Details mFragment_Commodity_Details;
    private boolean isPrepare;
    private RecyclerView mRecyclerView;
    private ArrayList<Integer> srcIds;
    private CommodityDetails.DataBean mCommodityDetailsBean;
    private CommodityDetailsPresenterImpl mCommodityDetailsPresenter;
    private TextView pop_num, pop_confirm, pop_inventory, pop_name, pop_price;
    private ImageView pop_photo, pop_minus, pop_plus;
    private List<MyNormsView> mNormsList;
    private FragmentDetailsAdapter adapter;
    private PopupWindow configWindow;
    private TextView addShoppingCart;
    private BadgeView mBadgeView;
    private ImageView commodity_details_shoppingcart;//购物车
    private TextView commodity_details_shoppingcart_tv;
    private TextView immediately_buy;//立即购买
    private String gid;//商品id
    private int mNumber = 1;
    private NormsBean mNormsBean;
    private ProgressBarUtil mProgressBarUtil;

    public static Fragment_Commodity_Details newInstance() {
//        if (mFragment_Commodity_Details == null) {
//            mFragment_Commodity_Details = new Fragment_Commodity_Details();
//        }
        return new Fragment_Commodity_Details();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_commodity_details, container, false);
        return mView;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isPrepare || !isVisible) {
            return;
        }
        if (mCommodityDetailsPresenter == null) {
            mCommodityDetailsPresenter = new CommodityDetailsPresenterImpl(this);
        }
        gid = getActivity().getIntent().getStringExtra("gid");
        Log.i("gid", "gid=" + gid);
        mCommodityDetailsPresenter.loadData(gid);
    }

    private void initView() {
        findId();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        adapter = new FragmentDetailsAdapter(getActivity(), mCommodityDetailsBean);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new YiYuanHotAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                showConfigWindow();
            }
        });

        mBadgeView = new BadgeView(getActivity());
        mBadgeView.setTargetView(commodity_details_shoppingcart);
        mBadgeView.setBadgeCount(0);
        mBadgeView.setVisibility(View.GONE);

//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                switch (newState) {
//                    case 2:
//                        Glide.with(getActivity()).pauseRequests();
//                        Log.d("AAAAAAAAAAAAAAA", "暂停加载" + newState);
//                        break;
//                    case 0:
//                        Glide.with(getActivity()).resumeRequests();
//                        Log.d("AAAAAAAAAAAAAAA", "恢复加载" + newState);
//                        break;
//                    case 1:
//                        Glide.with(getActivity()).pauseRequests();
//                        Log.d("AAAAAAAAAAAAAAA", "恢复加载" + newState);
//                        break;
//                }
//            }
//        });
    }

    /**
     * 显示选择配置window
     */
    private void showConfigWindow() {
        View configView = LayoutInflater.from(getActivity()).inflate(R.layout.commodity_details_config, null, false);
        View root = mView.findViewById(R.id.commodity_details_root);
        configWindow = new PopupWindow(configView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        configWindow.setFocusable(true);
        configWindow.setBackgroundDrawable(new BitmapDrawable());
        setBackgroundAlpha(0.4F);
        configWindow.showAtLocation(root, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        configWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0F);
            }
        });
        LinearLayout ll = (LinearLayout) configView.findViewById(R.id.commodity_config_ll);
        mNormsList = new ArrayList<>();
        mNormsBean = new NormsBean("配置", mCommodityDetailsBean.getNormsList());
        MyNormsView myNormsView = new MyNormsView(getActivity(), mNormsBean);
        ll.addView(myNormsView);
        mNormsList.add(myNormsView);

        pop_minus = (ImageView) configView.findViewById(R.id.commodity_config_minus);
        pop_plus = (ImageView) configView.findViewById(R.id.commodity_config_plus);
        pop_num = (TextView) configView.findViewById(R.id.commodity_config_num);
        pop_confirm = (TextView) configView.findViewById(R.id.commodity_config_submit);
        pop_inventory = (TextView) configView.findViewById(R.id.commodity_config_inventory);
        pop_name = (TextView) configView.findViewById(R.id.commodity_config_name);
        pop_price = (TextView) configView.findViewById(R.id.commodity_config_price);
        pop_photo = (ImageView) configView.findViewById(R.id.commodity_config_photo);
        pop_minus.setOnClickListener(this);
        pop_plus.setOnClickListener(this);
        pop_confirm.setOnClickListener(this);

        //初始化
        pop_inventory.setText("库存:" + mCommodityDetailsBean.getNormsList().get(0).getInventory() + "件");
        pop_name.setText(mCommodityDetailsBean.getName());
        pop_price.setText("￥" + mCommodityDetailsBean.getNormsList().get(0).getNowPrice());
//        pop_photo.setImageResource(R.drawable.commodity_config_norms_photo);
        Glide.with(this).load(mCommodityDetailsBean.getNormsList().get(0).getThumbnailUrl()).into(pop_photo);
    }

    /**
     * 设置页面透明度
     *
     * @param
     */
    private void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = alpha;
        getActivity().getWindow().setAttributes(lp);
    }

    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_commodity_Details);
        addShoppingCart = (TextView) mView.findViewById(R.id.commodity_details_addCart);
        commodity_details_shoppingcart = (ImageView) mView.findViewById(R.id.commodity_details_shoppingcart);
        immediately_buy = (TextView) mView.findViewById(R.id.commodity_details_buy);
        commodity_details_shoppingcart_tv = (TextView) mView.findViewById(R.id.commodity_details_shoppingcart_tv);

        commodity_details_shoppingcart_tv.setOnClickListener(this);
        addShoppingCart.setOnClickListener(this);
        commodity_details_shoppingcart.setOnClickListener(this);
        immediately_buy.setOnClickListener(this);
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
    public void loadDataSuccess(CommodityDetails.DataBean data) {
        mCommodityDetailsBean = data;
        getActivity().getIntent().putExtra("brief", mCommodityDetailsBean);
        initView();
    }

    @Override
    public void loadDataFailed(String error) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBadgeView != null) {
            mBadgeView.setBadgeCount(ShoppingBadgeUtil.getInstance().getBadgeCount());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commodity_config_minus:
                int num1 = Integer.valueOf(pop_num.getText().toString());
                if (num1 > 1) {
                    pop_num.setText(--num1 + "");
                }
                break;
            case R.id.commodity_config_plus:
                int num2 = Integer.valueOf(pop_num.getText().toString());
                if (num2 < Integer.valueOf(mNormsBean.getClassifys().get(mNormsBean.getCheckableIndex()).getInventory())) {
                    pop_num.setText(++num2 + "");
                }
                break;
            case R.id.commodity_config_submit://确定规格
                //更新规格
                String norms = "";
                for (int i = 0; i < mNormsBean.getClassifys().size(); i++) {
//                    norms += "  " + mCommodityDetailsBean.getNormsList().get(i).getClassifys().get(mCommodityDetailsBean.getNormsBeens().get(i).getCheckableIndex());
                }

                //更新数量
                norms += "  " + Integer.valueOf(pop_num.getText().toString());
                mNumber = Integer.valueOf(pop_num.getText().toString());
                //刷新RecyclerView
//                mCommodityDetailsBean.setNorms(mNumber);
                adapter.setNumber(String.valueOf(mNumber));
                adapter.notifyItemChanged(2);
                configWindow.dismiss();
                break;
            case R.id.commodity_details_addCart://加入到购物车
//                int count = mBadgeView.getBadgeCount();
//                mBadgeView.setBadgeCount(++count);
//                mBadgeView.setVisibility(View.VISIBLE);
//                //保存
//                ShoppingBadgeUtil.getInstance().setBadgeCount(count);
                if (LoginUtil.getInstance().isLogin()) {
                    Log.i("addCart", "已登录，加入购物车");
                    addShoppingCart();
                } else {
                    Log.i("addCart", "未登录，加入购物车");
                    Intent startLoginActivity = new Intent(getActivity(), LoginActivity.class);
                    startActivity(startLoginActivity);
                }
                break;
            case R.id.commodity_details_shoppingcart://启动购物车
            case R.id.commodity_details_shoppingcart_tv:
                Intent startShoppingCart = new Intent(getActivity(), ShoppingCartActivity.class);
                startActivityForResult(startShoppingCart, StartLoginRequest.COMMODITY_DETAILS);
                break;
            case R.id.commodity_details_buy:
//                Intent startSubmitOrderActivity = new Intent(getActivity(), SubmitOrderActivity.class);
//                ArrayList<CommodityBean> commodityBeens = new ArrayList<>();
//                CommodityBean commodityBeen = new CommodityBean(mCommodityDetailsBean.getPhotoUrl()
//                        , mCommodityDetailsBean.getName(), "", mCommodityDetailsBean.getPrice(), 11200, Integer.valueOf("1"), true);
//                commodityBeens.add(commodityBeen);
//                startSubmitOrderActivity.putParcelableArrayListExtra("list", commodityBeens);
//                startActivity(startSubmitOrderActivity);
                break;
        }
    }

    private void addShoppingCart() {
        if (gid.equals("")) {
            return;
        }
        mCommodityDetailsPresenter.addCart(gid, String.valueOf(mNumber > 1 ? mNumber : 1));
    }

    @Override
    public void loadSuccess(CommodityDetails.DataBean commodityDetails) {
        mCommodityDetailsBean = commodityDetails;
        getActivity().getIntent().putExtra("brief", mCommodityDetailsBean);
        initView();
    }

    @Override
    public void addCartSuccess(SCOperation scOperation) {
        int count = mBadgeView.getBadgeCount();
        mBadgeView.setBadgeCount(++count);
        mBadgeView.setVisibility(View.VISIBLE);
        //保存
        ShoppingBadgeUtil.getInstance().setBadgeCount(count);
        Log.i("addCartFailed", "添加成功:" + scOperation.getMsg());
    }

    @Override
    public void addCartFailed(String error) {
        Log.i("addCartFailed", "添加失败:" + error);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragment_Commodity_Details = null;
        mRecyclerView = null;
        mCommodityDetailsPresenter = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {

        }
    }
}
