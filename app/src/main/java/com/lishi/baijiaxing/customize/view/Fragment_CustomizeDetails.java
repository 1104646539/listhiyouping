package com.lishi.baijiaxing.customize.view;

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
import com.lishi.baijiaxing.base.BaseView;
import com.lishi.baijiaxing.customize.adapter.CustomizeDetailsAdapter;
import com.lishi.baijiaxing.customize.model.CustomizeCommodityBean;
import com.lishi.baijiaxing.customize.model.NormsBean;
import com.lishi.baijiaxing.customize.presenter.CustomizeDetailsPresenterImpl;
import com.lishi.baijiaxing.customize.widget.MyNormsView;
import com.lishi.baijiaxing.shoppingCart.ShoppingCartActivity;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.submitOrder.view.SubmitOrderActivity;
import com.lishi.baijiaxing.utils.DividerItemDecoration;
import com.lishi.baijiaxing.utils.ShoppingBadgeUtil;
import com.lishi.baijiaxing.view.BadgeView;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.lishi.baijiaxing.yiyuan.presenter.YiYuanHotDetailsPresenterImpl;
import com.lishi.baijiaxing.yiyuan.presenter.YiYuanNewestDetailsPresenterImpl;
import com.lishi.baijiaxing.yiyuan.view.YiYuanActivity;

import java.util.ArrayList;

/**
 * 个性定制详情
 * Created by Administrator on 2016/10/28.
 */
public class Fragment_CustomizeDetails extends BaseFragmentV4 implements CustomizeDetailsView, YiYuanHotAdapter.OnItemClickListener, View.OnClickListener {
    private static Fragment_CustomizeDetails mFragment_customizeDetails;
    private boolean isPrepare;
    private View mView;
    private RecyclerView mRecyclerView;
    private CustomizeCommodityBean mCustomizeCommodityBean;
    private CustomizeDetailsAdapter adapter;
    private PopupWindow mPopupWindow;
    private ImageView minus;
    private ImageView plus;
    private TextView tv_num;
    private TextView tv_submit;
    private TextView config_norms;
    private TextView uploadLogo;
    private CustomizeDetailsPresenterImpl mCustomizeDetailsPresenterImpl;
    private ImageView shoppingCart_iv;
    private TextView shoppingCart_tv;
    private TextView buy, addShoppingCart;
    private BadgeView mBadgeView;

    public static Fragment_CustomizeDetails newInstance() {
        if (mFragment_customizeDetails == null) {
            mFragment_customizeDetails = new Fragment_CustomizeDetails();
        }
        return mFragment_customizeDetails;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isPrepare || !isVisible) {
            return;
        }
        if (mCustomizeDetailsPresenterImpl == null) {
            mCustomizeDetailsPresenterImpl = new CustomizeDetailsPresenterImpl(this);
        }
        mCustomizeDetailsPresenterImpl.loadData();
    }

    private void init() {
        findId();
        initData();

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        adapter = new CustomizeDetailsAdapter(getActivity(), mCustomizeCommodityBean);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        mBadgeView = new BadgeView(getActivity());
        mBadgeView.setTargetView(shoppingCart_iv);
        if (ShoppingBadgeUtil.getInstance().getBadgeCount() == 0) {
            mBadgeView.setVisibility(View.GONE);
        } else {
            mBadgeView.setVisibility(View.VISIBLE);
            mBadgeView.setBadgeCount(ShoppingBadgeUtil.getInstance().getBadgeCount());
        }
    }

    private void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBadgeView != null) {
            mBadgeView.setBadgeCount(ShoppingBadgeUtil.getInstance().getBadgeCount());
        }
    }

    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_customizeDetails);
        addShoppingCart = (TextView) mView.findViewById(R.id.customize_details_bottom_add);
        buy = (TextView) mView.findViewById(R.id.customize_details_bottom_buy);
        shoppingCart_iv = (ImageView) mView.findViewById(R.id.customize_shoppingCart_iv);
        shoppingCart_tv = (TextView) mView.findViewById(R.id.customize_shoppingCart_tv);

        addShoppingCart.setOnClickListener(this);
        buy.setOnClickListener(this);
        shoppingCart_iv.setOnClickListener(this);
        shoppingCart_tv.setOnClickListener(this);
    }

    @Override
    public void onClickListener(View view, int position) {
        switch (position) {
            case 0://定制
                showNormsWindow();
                break;
            case 4://配置
                showConfigWindow();
                break;
        }
    }

    /**
     * 显示选择产品规格窗口
     *
     * @param
     */
    private void showConfigWindow() {
        View pop = LayoutInflater.from(getActivity()).inflate(R.layout.customize_config, null, false);
        View root = mView.findViewById(R.id.customize_details_root);

        final PopupWindow pw = new PopupWindow(pop, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        pw.setFocusable(true);
        pw.setBackgroundDrawable(new BitmapDrawable());
        setBackgroundAlpha(0.4F);
        pw.showAtLocation(root, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0F);
            }
        });

        /**
         * 添加规格选择
         */
        for (int i = 0; i < mCustomizeCommodityBean.getNormsBeens().size(); i++) {
            addNormsLayout(mCustomizeCommodityBean.getNormsBeens().get(i), pop);
        }
        tv_submit = (TextView) pop.findViewById(R.id.customize_config_submit);
        minus = (ImageView) pop.findViewById(R.id.customize_config_minus);
        tv_num = (TextView) pop.findViewById(R.id.customize_config_num);
        plus = (ImageView) pop.findViewById(R.id.customize_config_plus);
        config_norms = (TextView) pop.findViewById(R.id.customize_config_norms);

        config_norms.setText(mCustomizeCommodityBean.getNorms());
        tv_num.setText(mCustomizeCommodityBean.getNum() + "");

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.valueOf(tv_num.getText().toString());
                if (num > 1) {
                    tv_num.setText(--num + "");
                    mCustomizeCommodityBean.setConfig_num(num);
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.valueOf(tv_num.getText().toString());
                tv_num.setText(++num + "");
                mCustomizeCommodityBean.setConfig_num(num);
            }
        });

        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer num = Integer.valueOf(tv_num.getText().toString());
                mCustomizeCommodityBean.setNum(num);
                String norms = "";
                for (int i = 0; i < mCustomizeCommodityBean.getNormsBeens().size(); i++) {
                    norms += mCustomizeCommodityBean.getNormsBeens().get(i).getClassifys().get(mCustomizeCommodityBean.getNormsBeens().get(i).getCheckableIndex());
                }
                mCustomizeCommodityBean.setNorms(norms);
                adapter.notifyItemChanged(3);
                pw.dismiss();
            }
        });
    }

    private void addNormsLayout(NormsBean normsBean, View pop) {
        MyNormsView normsView = new MyNormsView(getActivity(), normsBean);
        LinearLayout ll = (LinearLayout) pop.findViewById(R.id.customize_config_ll);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(normsView, lp);
    }

    /**
     * 显示定制窗口
     */
    private void showNormsWindow() {
        View pop = LayoutInflater.from(getActivity()).inflate(R.layout.customize_norms, null);
        View root = mView.findViewById(R.id.customize_details_root);
        mPopupWindow = new PopupWindow(pop, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        setBackgroundAlpha(0.4F);
        mPopupWindow.showAtLocation(root, Gravity.BOTTOM | Gravity.LEFT, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0F);
            }
        });

        uploadLogo = (TextView) pop.findViewById(R.id.customize_norms_uploadLogo);
        //上传logo
        uploadLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setBackgroundAlpha(float background) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = background;
        getActivity().getWindow().setAttributes(lp);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_customize_details, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }


    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(CustomizeCommodityBean data) {
        this.mCustomizeCommodityBean = data;
        init();
    }

    @Override
    public void loadDataFailed(String error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customize_details_bottom_add:
                int count = mBadgeView.getBadgeCount();
                mBadgeView.setBadgeCount(++count);
                mBadgeView.setVisibility(View.VISIBLE);
                //保存
                ShoppingBadgeUtil.getInstance().setBadgeCount(count);
                break;
            case R.id.customize_details_bottom_buy:
                Intent startSubmitOrderActivity = new Intent(getActivity(), SubmitOrderActivity.class);
                ArrayList<CommodityBean> commodityBeens = new ArrayList<>();
//                CommodityBean commodityBeen = new CommodityBean(mCustomizeCommodityBean.getPhotoUrl()
//                        , mCustomizeCommodityBean.getCommodityBrief(), "", Integer.valueOf(mCustomizeCommodityBean.getPrice())
//                        , 11200, Integer.valueOf("1"), true);
//                commodityBeens.add(commodityBeen);
                startSubmitOrderActivity.putParcelableArrayListExtra("list", commodityBeens);
                startActivity(startSubmitOrderActivity);
                break;
            case R.id.customize_shoppingCart_iv:
            case R.id.customize_shoppingCart_tv:
                Intent startShoppingCartActivity = new Intent(getActivity(), ShoppingCartActivity.class);
                startActivity(startShoppingCartActivity);
                break;
        }
    }
}
