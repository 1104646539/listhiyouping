package com.lishi.baijiaxing.home.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragment;
import com.lishi.baijiaxing.bean.GridNavigationBean;
import com.lishi.baijiaxing.bookmagazine.view.BookMagazineActivity;
import com.lishi.baijiaxing.customize.view.CustomizeActivity;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.free.model.FreeCommodityBean;
import com.lishi.baijiaxing.free.view.FreeActivity;
import com.lishi.baijiaxing.free.view.FreeDetailsActivity;
import com.lishi.baijiaxing.freeDesign.FreeDesignActivity;
import com.lishi.baijiaxing.home.adater.HomeAdapter;
import com.lishi.baijiaxing.home.adater.HomeNavigationAdapter;
import com.lishi.baijiaxing.home.adater.HomeSeckilGridAdapter;
import com.lishi.baijiaxing.home.model.AdList;
import com.lishi.baijiaxing.home.model.Commodity;
import com.lishi.baijiaxing.home.model.Festival;
import com.lishi.baijiaxing.home.model.HomeBean;
import com.lishi.baijiaxing.home.model.Seckill;
import com.lishi.baijiaxing.home.presenter.HomePresenterImpl;
import com.lishi.baijiaxing.home.widget.Advertisements;
import com.lishi.baijiaxing.home.widget.HomeScrollView;
import com.lishi.baijiaxing.home.widget.MyGridLayoutManager;
import com.lishi.baijiaxing.hot.view.HotCommodityActivity;
import com.lishi.baijiaxing.integral.IntegralActivity;
import com.lishi.baijiaxing.latest.LatestActivity;
import com.lishi.baijiaxing.search.SearchActivity;
import com.lishi.baijiaxing.seckill.SeckillActivity;
import com.lishi.baijiaxing.utils.NetUtils;
import com.lishi.baijiaxing.utils.ProgressBarUtil;
import com.lishi.baijiaxing.utils.TimeUtils;
import com.lishi.baijiaxing.view.MyDefaultFooter;
import com.lishi.baijiaxing.view.MyGridView;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.lishi.baijiaxing.yiyuan.view.YiYuanActivity;
import com.lishi.baijiaxing.yiyuan.view.YiYuanDetailsActivity;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.iwgang.countdownview.CountdownView;

@SuppressLint("ValidFragment")
public class Fragment_Home extends BaseFragment implements View.OnClickListener, HomeView, YiYuanHotAdapter.OnItemClickListener, AdapterView.OnItemClickListener, HomeScrollView.onScrollViewListener, SpringView.OnFreshListener {
    private static final String TAG = "Fragment_Home";
    private LinearLayout mLinearLayout;
    private LayoutInflater mInflater;
    private int[] mGridViewIds = new int[]{R.drawable.icon_home1, R.drawable.icon_home2, R.drawable.icon_home3, R.drawable.icon_home4,
            R.drawable.icon_home5, R.drawable.icon_home6, R.drawable.icon_home7, R.drawable.icon_home8};
    private String[] texts = new String[]{"免费领", "一元拼", "积分兑换", "免费设计", "书籍杂志", "爆品排行", "个性定制", "最新活动"};
    private List<GridNavigationBean> mGridNavigations = new ArrayList<GridNavigationBean>();
    private Context mContext;
    private TextView tv_msg;
    private ImageView tv_topnavigation_icon;
    private boolean isLoading = false;//是否正在加载中
    private TextView tv_load;
    private LinearLayout ll, footer;
    private MyGridView gridView_navigation;//8大导航
    private View mView;
    private HomePresenterImpl mHomePresenterImpl;
    private RecyclerView mRecyclerView;
    private ImageView free_photo1;
    private ImageView free_photo2;
    private ImageView free_photo3;
    private ImageView top_search;
    private HomeAdapter mAdapter;
    private TextView home_festivalName;
    private ImageView toTop;
    private GridView seckilRecyclerView;
    private CountdownView seckilCountDown, festivalCountDown;
    private View navigation;
    private LinearLayout search_ll;
    private TextView home_seckil_more;
    private HomeScrollView mPullToRefreshScrollView;
    private ProgressBar load_progress;
    private boolean isShowTop = false;
    //获取的
    private List<AdList.DataBean> mAdLists;//轮播图
    private Seckill.DataBean mSeckill;//秒杀
    private Festival.DataBean mFestival;//节日
    private List<Commodity.DataBean.CommodityListBean> mCommodities = new ArrayList<>();//商品
    private List<Commodity.DataBean.CommodityListBean> freeCommodity = new ArrayList<>();
    //转换后
    private JSONArray advertiseArray;
    private HomeNavigationAdapter mHomeNavigationAdapter;
    private boolean isPrepare = true;
    private SpringView springView_home;
    private TextView home_footer_text;
    private ProgressBarUtil mProgressBarUtil;
    private LinearLayout home_not_network, home_content;
    private RelativeLayout home_footer, home_header;
    private static Fragment_Home mFragment_home;
    private MyDefaultFooter mMyDefaultFooter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        mInflater = LayoutInflater.from(getActivity());
        findId();
        if (!NetUtils.isConnected(getActivity())) {
            home_not_network.setVisibility(View.VISIBLE);
            Log.i("Fragment_Home", "无网络连接");
        } else {
            home_not_network.setVisibility(View.GONE);
            Log.i("Fragment_Home", "有网络连接");
            initView();
        }
        return mView;
    }


    public Fragment_Home() {

    }

    public static Fragment_Home newInstance() {
        if (mFragment_home == null) {
            mFragment_home = new Fragment_Home();
        }
        return new Fragment_Home();
    }

    private void findId() {
        tv_msg = (TextView) mView.findViewById(R.id.tv_top_navigation_msg);
        tv_topnavigation_icon = (ImageView) mView.findViewById(R.id.tv_topnavigation_icon);
        mLinearLayout = (LinearLayout) mView.findViewById(R.id.llAdvertiseBoard);
        gridView_navigation = (MyGridView) mView.findViewById(R.id.home_gridview_navigation);
        tv_load = (TextView) mView.findViewById(R.id.tv_home_recyclerView_load);
        load_progress = (ProgressBar) mView.findViewById(R.id.tv_home_recyclerView_progress);
        ll = (LinearLayout) mView.findViewById(R.id.ll_homeroot);
        top_search = (ImageView) mView.findViewById(R.id.top_search);
        toTop = (ImageView) mView.findViewById(R.id.home_toTop);
        seckilRecyclerView = (GridView) mView.findViewById(R.id.recyclerView_homeSeckil);
        seckilCountDown = (CountdownView) mView.findViewById(R.id.home_seckilCountDown);
        festivalCountDown = (CountdownView) mView.findViewById(R.id.home_festivalCountDown);
        home_seckil_more = (TextView) mView.findViewById(R.id.home_seckil_more);
        home_festivalName = (TextView) mView.findViewById(R.id.home_festivalName);
        free_photo1 = (ImageView) mView.findViewById(R.id.free_photo1);
        free_photo2 = (ImageView) mView.findViewById(R.id.free_photo2);
        free_photo3 = (ImageView) mView.findViewById(R.id.free_photo3);
        mPullToRefreshScrollView = (HomeScrollView) mView.findViewById(R.id.swiperefreshLayout_home);
        springView_home = (SpringView) mView.findViewById(R.id.springView_home);
        search_ll = (LinearLayout) mView.findViewById(R.id.home_search_ll);
        home_not_network = (LinearLayout) mView.findViewById(R.id.home_not_network);
        home_content = (LinearLayout) mView.findViewById(R.id.home_root);
        home_footer = (RelativeLayout) mView.findViewById(R.id.home_footer);
        home_header = (RelativeLayout) mView.findViewById(R.id.home_header);


        tv_topnavigation_icon.setVisibility(View.VISIBLE);
        navigation = mView.findViewById(R.id.ll_top_navigation);
        home_seckil_more.setOnClickListener(this);
        free_photo1.setOnClickListener(this);
        free_photo2.setOnClickListener(this);
        free_photo3.setOnClickListener(this);
        top_search.setOnClickListener(this);
        toTop.setOnClickListener(this);
        home_not_network.setOnClickListener(this);
        springView_home.setHeader(new DefaultHeader(getActivity()));
        mMyDefaultFooter = new MyDefaultFooter(getActivity());
        springView_home.setFooter(mMyDefaultFooter);
        springView_home.setListener(this);
        springView_home.setType(SpringView.Type.FOLLOW);

        search_ll.getBackground().mutate().setAlpha(150);
    }

    private void initView() {
        mHomePresenterImpl = new HomePresenterImpl(this);
        mHomePresenterImpl.loadData();
    }

    /**
     * 初始化，下拉刷新控件
     *
     * @param
     */
    private void initSwpieRefreshLayout() {
    }

    /**
     * 初始化TopNavigation
     * 顶部导航栏
     *
     * @param
     */
    private void initTopNavigation() {
        navigation.getBackground().mutate().setAlpha(00);

        //处理imageView和textView点击事件冲突,将事件消费，返回true即可
        tv_msg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(mContext, "点击了", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        tv_topnavigation_icon.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    /**
     * 初始化GridView导航入口
     *
     * @param
     */
    private void initGridView() {
        GridNavigationBean gridNavigation = null;
        for (int i = 0; i < 8; i++) {
            gridNavigation = new GridNavigationBean(mGridViewIds[i], texts[i]);
            mGridNavigations.add(gridNavigation);
        }
        gridView_navigation.setAdapter(new HomeNavigationAdapter(getActivity(), mGridNavigations));
        gridView_navigation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gridSwitch(position);
            }
        });
    }

    //导航入口被点击
    private void gridSwitch(int position) {
        switch (position) {
            case 0://免费领
                Intent startFreeActivity = new Intent(getActivity(), FreeActivity.class);
                startActivity(startFreeActivity);
                break;
            case 1://一元拼
                Intent startYiYuanActivity = new Intent(getActivity(), YiYuanActivity.class);
                startActivity(startYiYuanActivity);
                break;
            case 2://积分兑换
                Intent startIntegralActivity = new Intent(getActivity(), IntegralActivity.class);
                startActivity(startIntegralActivity);
                break;
            case 3://免费设计
                Intent startFreeDesignActivity = new Intent(getActivity(), FreeDesignActivity.class);
                startActivity(startFreeDesignActivity);
                break;
            case 4://书籍杂志
                Intent startBookMagazineActivity = new Intent(getActivity(), BookMagazineActivity.class);
                startActivity(startBookMagazineActivity);
                break;
            case 5://爆品排行
//                Intent startWeeklyActivity = new Intent(getActivity(), WeeklyChoicenessActivity.class);
//                startActivity(startWeeklyActivity);
                Intent startHotCommodityActivity = new Intent(getActivity(), HotCommodityActivity.class);
                startActivity(startHotCommodityActivity);
                break;
            case 6://个性定制
                Intent startCustomizeActivity = new Intent(getActivity(), CustomizeActivity.class);
                startActivity(startCustomizeActivity);
                break;
            case 7://最新活动
                Intent startLatestActivity = new Intent(getActivity(), LatestActivity.class);
                startActivity(startLatestActivity);
                break;
        }

    }

    /**
     * 初始化轮播图
     *
     * @param
     */
    private void initViewPager() {
        mLinearLayout.setEnabled(true);
        mLinearLayout.requestLayout();
        // 添加图片的Url地址
        mLinearLayout.addView(new Advertisements(getActivity(), true, mInflater, 2000).initView(advertiseArray));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.free_photo1:
                Intent startFreeDetailsActivity1 = new Intent(getActivity(), FreeDetailsActivity.class);
                startFreeDetailsActivity1.putExtra("type", FreeCommodityBean.TYPE_BE_BEING_APPLY_OK);
                startActivity(startFreeDetailsActivity1);
                break;
            case R.id.free_photo2:
                Intent startLatest1Activity = new Intent(getActivity(), LatestActivity.class);
                startActivity(startLatest1Activity);
                break;
            case R.id.free_photo3:
                Intent startCommodityDetails = new Intent(getActivity(), CommodityDetailsActivity.class);
                Commodity.DataBean.CommodityListBean fcd = freeCommodity.get(2);
                if (fcd != null && fcd.getGid() != null) {
                    startCommodityDetails.putExtra("gid", fcd.getGid());
                    startActivity(startCommodityDetails);
                }
                break;
            case R.id.top_search:
                Intent startSearchActivity = new Intent(getActivity(), SearchActivity.class);
                startActivity(startSearchActivity);
                break;
            case R.id.home_toTop:
                mPullToRefreshScrollView.smoothScrollTo(0, 0);
                break;
            case R.id.home_seckil_more:
                Intent startSeckilActivity = new Intent(mContext, SeckillActivity.class);
                startActivity(startSeckilActivity);
                break;
            case R.id.home_not_network:
                initView();
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
    public void onPullSuccess(List<Commodity.DataBean.CommodityListBean> datas) {
        mCommodities.addAll(datas);
        mAdapter.notifyDataSetChanged();
//        tv_load.setText("上拉加载更多");
        Log.i(TAG, "原来的数量" + mCommodities.size() + "接受的数量" + datas.size());
        isLoading = false;
        springView_home.onFinishFreshAndLoad();
    }


    @Override
    public void onPullloadFailed(String error) {
        Log.i("加载失败", "error=" + error);
        isLoading = false;
        springView_home.onFinishFreshAndLoad();
    }

    @Override
    public void getAdListSuccess(List<AdList.DataBean> adLists) {
        mAdLists = adLists;
        advertiseArray = new JSONArray();
        JSONObject head_img;
        int size = mAdLists.size();
        for (int i = 0; i < size; i++) {
            try {
                head_img = new JSONObject();
                head_img.put("head_img", mAdLists.get(i).getPhotoUrl());
                advertiseArray.put(head_img);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        initViewPager();
        initGridView();
        initTopNavigation();
        initSwpieRefreshLayout();
    }

    @Override
    public void getFestivalSuccess(Festival.DataBean festivals) {
        mFestival = festivals;
        initFestival();
    }

    private void initFestival() {
        String festivalName = mFestival.getFestivalName();
        String festivalTime = mFestival.getFestibalTime();
        String nowTime = TimeUtils.timeStamp();

        home_festivalName.setText("距离" + festivalName + "还有");
        festivalCountDown.start(TimeUtils.countDownTime(festivalTime, nowTime));
    }

    @Override
    public void getSeckillSuccess(Seckill.DataBean seckill) {
        mSeckill = seckill;
        String seckillTime = mSeckill.getSeckillTime();

        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        seckilRecyclerView.setAdapter(new HomeSeckilGridAdapter(getActivity(), mSeckill, margin));
        seckilCountDown.start(Integer.valueOf(seckillTime));
        setGridWidthAndChildWidth(mSeckill.getSeckillList(), margin);
        seckilRecyclerView.setOnItemClickListener(this);

        Log.i("getSeckillSuccess", "url=" + mSeckill.getSeckillList().get(0).getPhotoUrl());
    }

    private void setGridWidthAndChildWidth(List<Seckill.DataBean.SeckillListBean> seckils, int margin) {
        int screenWidth = getActivity().getResources().getDisplayMetrics().widthPixels;
        int itemWidth = (int) (screenWidth / 3.5);
        int gridviewWidth = itemWidth * seckils.size();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                gridviewWidth, RecyclerView.LayoutParams.MATCH_PARENT);
        seckilRecyclerView.setLayoutParams(params); //重点
        seckilRecyclerView.setColumnWidth(itemWidth); //重点
        seckilRecyclerView.setNumColumns(seckils.size()); //重点
        seckilRecyclerView.setHorizontalSpacing(margin);

        Log.i("gridviewWidth", "gridviewWidth=======================" + gridviewWidth);
    }

    @Override
    public void getCommodityListSuccess(List<Commodity.DataBean.CommodityListBean> commodities) {
        Log.i("getCommodityListSuccess", "commodities:" + commodities.size());
        home_not_network.setVisibility(View.GONE);
        home_content.setVisibility(View.VISIBLE);
//        home_footer.setVisibility(View.VISIBLE);
//        home_header.setVisibility(View.VISIBLE);
        if (!isPrepare) {
            mCommodities.clear();
            freeCommodity.clear();
            for (int i = 0; i < commodities.size(); i++) {
                if (i < 3) {
                    freeCommodity.add(commodities.get(i));
                } else {
                    mCommodities.add(commodities.get(i));
                }
            }
            initFree(freeCommodity);
            mAdapter.notifyDataSetChanged();
        } else {
            for (int i = 0; i < commodities.size(); i++) {
                if (i < 3) {
                    freeCommodity.add(commodities.get(i));
                } else {
                    mCommodities.add(commodities.get(i));
                }
            }
            initFree(freeCommodity);
            initRecyclerView();
            isPrepare = false;
        }
        springView_home.onFinishFreshAndLoad();
    }

    private void initFree(List<Commodity.DataBean.CommodityListBean> freeCommodity) {
        Log.i("initFree", "initFree 0:" + freeCommodity.get(0).getPhotoUrl());
        Log.i("initFree", "initFree 1:" + freeCommodity.get(1).getPhotoUrl());
        Log.i("initFree", "initFree 2:" + freeCommodity.get(2).getPhotoUrl());
        Glide.with(this).load(freeCommodity.get(0).getPhotoUrl()).placeholder(R.drawable.home_free_322x357).into(free_photo1);
        Glide.with(this).load(freeCommodity.get(1).getPhotoUrl()).placeholder(R.drawable.home_free2_393x176).into(free_photo2);
        Glide.with(this).load(freeCommodity.get(2).getPhotoUrl()).placeholder(R.drawable.home_free2_393x176).into(free_photo3);
    }

    @Override
    public void getAdListFailed(String error) {

    }

    @Override
    public void getFestivalFailed(String error) {

    }

    @Override
    public void getSeckillFailed(String error) {
    }

    @Override
    public void getCommodityListFailed(String error) {
        home_not_network.setVisibility(View.GONE);
        if (!NetUtils.isConnected(getActivity())) {
            home_not_network.setVisibility(View.VISIBLE);
        }
        springView_home.onFinishFreshAndLoad();
    }

    @Override
    public void onLastPage(String status) {
        springView_home.onFinishFreshAndLoad();
        mMyDefaultFooter.setMoreLoad(false);
        Logger.d("home:"+"到底了");
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
        Log.e("closeDialog", "加载完成");
    }

    @Override
    public void loadDataSuccess(HomeBean data) {

    }

    /**
     * 五大模块
     */
    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_home);
        MyGridLayoutManager mManager = new MyGridLayoutManager(getActivity(), 6, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);

        mAdapter = new HomeAdapter(getActivity(), mCommodities);
        mRecyclerView.setAdapter(mAdapter);
        Log.i("initRecyclerView", "initRecyclerView" + mCommodities.size());

        mAdapter.setOnItemClickListener(this);

        //判断是否显示置顶图标
        mPullToRefreshScrollView.setScrollViewChangeListener(this);

        springView_home.setListener(this);
    }

    @Override
    public void loadDataFailed(String error) {

    }

    /**
     * 一元拼和四大模块
     *
     * @param view
     * @param position
     */
    @Override
    public void onClickListener(View view, int position) {
        if (position > 0 && position <= 3) {
            Intent startYiYuanDetails = new Intent(getActivity(), YiYuanDetailsActivity.class);
            startYiYuanDetails.putExtra("type", YiYuanActivity.TYPE_HOT);
            startActivity(startYiYuanDetails);
        } else {
            Intent startCommodityDetails = new Intent(getActivity(), CommodityDetailsActivity.class);
            if (mCommodities.get(position) != null && !mCommodities.get(position).getGid().equals("0")) {
                if (mCommodities.get(position) != null && !mCommodities.get(position).equals("0") && NetUtils.isConnected(getActivity())) {
                    startCommodityDetails.putExtra("gid", mCommodities.get(position).getGid());
                    startActivity(startCommodityDetails);
                }
            }
        }
    }

    /**
     * 秒杀
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent startSeckilActivity = new Intent(mContext, CommodityDetailsActivity.class);
        String gid = mSeckill.getSeckillList().get(position).getCid();
        if (gid != null && !gid.equals("")) {
            startSeckilActivity.putExtra("gid", gid);
            startActivity(startSeckilActivity);
        }
    }

    /**
     * 显示，隐藏置顶图标和topNavigation
     *
     * @param
     * @param
     * @param
     * @param
     * @param
     */
    @Override
    public void onScrollViewChange(HomeScrollView view, int x, int y, int oldx, int oldy) {
        if (y > 500) {
            if (!isShowTop) {
                isShowTop = true;
                toTop.setVisibility(View.VISIBLE);
            }
        } else {
            if (isShowTop) {
                isShowTop = false;
                toTop.setVisibility(View.GONE);
            }
        }
        if (y > 0) {
            if (y >= 100) {
                int alpha = (int) (40 * 1.0F + y * 1.0F * 0.5);
                if (alpha > 240) {
                    navigation.getBackground().mutate().setAlpha(240);
                } else {
                    navigation.getBackground().mutate().setAlpha(alpha);
                }
                search_ll.setBackgroundColor(Color.parseColor("#efefef"));
            } else {
                navigation.getBackground().mutate().setAlpha(0);
                search_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
                search_ll.getBackground().mutate().setAlpha(150);
            }
        } else {
            navigation.getBackground().mutate().setAlpha(0);
            search_ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
            search_ll.getBackground().mutate().setAlpha(150);
        }

    }

    @Override
    public void onRefresh() {
        Log.i("springView_home", "下拉刷新");
        if (mHomePresenterImpl == null) {
            mHomePresenterImpl = new HomePresenterImpl(this);
        }
        mHomePresenterImpl.loadData();
    }

    @Override
    public void onLoadmore() {
        Log.i("springView_home", "onLoadmore:上拉加载");
        if (mHomePresenterImpl == null) {
            mHomePresenterImpl = new HomePresenterImpl(this);
        }
        mHomePresenterImpl.PullDownLoadData();
    }
}
