package com.lishi.baijiaxing.details.view;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.customize.widget.MyNormsView;
import com.lishi.baijiaxing.details.adapter.FragmentDetailsAdapter;
import com.lishi.baijiaxing.details.model.CommodityDetailsBean;
import com.lishi.baijiaxing.details.presenter.CommodityDetailsPresenterImpl;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartActivity;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.submitOrder.view.SubmitOrderActivity;
import com.lishi.baijiaxing.utils.ShoppingBadgeUtil;
import com.lishi.baijiaxing.view.BadgeView;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

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
    private CommodityDetailsBean mCommodityDetailsBean;
    private CommodityDetailsPresenterImpl mCommodityDetailsPresenter;
    private TextView pop_num, pop_confirm, pop_inventory, pop_name, pop_price;
    private ImageView pop_photo, pop_minus, pop_plus;
    private List<MyNormsView> mNormsList;
    private FragmentDetailsAdapter adapter;
    private PopupWindow configWindow;
    private TextView addShoppingCart;
    private BadgeView mBadgeView;
    private ImageView commodity_details_shoppingcart;//加入购物车
    private TextView immediately_buy;//立即购买

    public static Fragment_Commodity_Details newInstance() {
        if (mFragment_Commodity_Details == null) {
            mFragment_Commodity_Details = new Fragment_Commodity_Details();
        }
        return mFragment_Commodity_Details;
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
        mCommodityDetailsPresenter.loadData();
    }

    private void initView() {
        findId();
        initData();

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
        for (int i = 0; i < mCommodityDetailsBean.getNormsBeens().size(); i++) {
            MyNormsView myNormsView = new MyNormsView(getActivity(), mCommodityDetailsBean.getNormsBeens().get(i));
            ll.addView(myNormsView);
            mNormsList.add(myNormsView);
        }

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
        pop_inventory.setText("库存:" + mCommodityDetailsBean.getInventory() + "件");
        pop_name.setText(mCommodityDetailsBean.getName());
        pop_price.setText("￥" + mCommodityDetailsBean.getPrice());
        pop_photo.setImageResource(R.drawable.commodity_config_norms_photo);
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


    private void initData() {

    }

    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_commodity_Details);
        addShoppingCart = (TextView) mView.findViewById(R.id.commodity_details_addCart);
        commodity_details_shoppingcart = (ImageView) mView.findViewById(R.id.commodity_details_shoppingcart);
        immediately_buy = (TextView) mView.findViewById(R.id.commodity_details_buy);

        addShoppingCart.setOnClickListener(this);
        commodity_details_shoppingcart.setOnClickListener(this);
        immediately_buy.setOnClickListener(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(CommodityDetailsBean data) {
        mCommodityDetailsBean = data;
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
                if (num2 < mCommodityDetailsBean.getInventory()) {
                    pop_num.setText(++num2 + "");
                }
                break;
            case R.id.commodity_config_submit://确定规格
                //更新规格
                String norms = "";
                for (int i = 0; i < mCommodityDetailsBean.getNormsBeens().size(); i++) {
                    norms += "  " + mCommodityDetailsBean.getNormsBeens().get(i).getClassifys().get(mCommodityDetailsBean.getNormsBeens().get(i).getCheckableIndex());
                }

                //更新数量
                norms += "  " + Integer.valueOf(pop_num.getText().toString());
                //刷新RecyclerView
                mCommodityDetailsBean.setNorms(norms);
                adapter.notifyItemChanged(2);
                adapter.notifyItemChanged(1);
                configWindow.dismiss();
                break;
            case R.id.commodity_details_addCart://加入到购物车
                int count = mBadgeView.getBadgeCount();
                mBadgeView.setBadgeCount(++count);
                mBadgeView.setVisibility(View.VISIBLE);
                //保存
                ShoppingBadgeUtil.getInstance().setBadgeCount(count);
                break;
            case R.id.commodity_details_shoppingcart://启动购物车
                Intent startShoppingCart = new Intent(getActivity(), ShoppingCartActivity.class);
                startActivity(startShoppingCart);
                break;
            case R.id.commodity_details_buy:
                Intent startSubmitOrderActivity = new Intent(getActivity(), SubmitOrderActivity.class);
                ArrayList<CommodityBean> commodityBeens = new ArrayList<>();
                CommodityBean commodityBeen = new CommodityBean(mCommodityDetailsBean.getPhotoUrl()
                        , mCommodityDetailsBean.getName(), "", mCommodityDetailsBean.getPrice(), 11200, Integer.valueOf("1"), true);
                commodityBeens.add(commodityBeen);
                startSubmitOrderActivity.putParcelableArrayListExtra("list",commodityBeens);
                startActivity(startSubmitOrderActivity);
                break;
        }
    }
}
