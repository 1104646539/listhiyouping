package com.lishi.baijiaxing.home.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.search.SearchActivity;
import com.lishi.baijiaxing.base.BaseFragment;
import com.lishi.baijiaxing.bean.GridNavigationBean;
import com.lishi.baijiaxing.bean.HomeRecommendBean;
import com.lishi.baijiaxing.customize.view.CustomizeActivity;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;
import com.lishi.baijiaxing.free.model.FreeCommodityBean;
import com.lishi.baijiaxing.free.view.FreeActivity;
import com.lishi.baijiaxing.free.view.FreeDetailsActivity;
import com.lishi.baijiaxing.freeDesign.FreeDesignActivity;
import com.lishi.baijiaxing.home.adater.HomeAdapter;
import com.lishi.baijiaxing.home.model.HomeBean;
import com.lishi.baijiaxing.home.model.HomeCommodityBean;
import com.lishi.baijiaxing.home.presenter.HomePresenterImpl;
import com.lishi.baijiaxing.home.widget.Advertisements;
import com.lishi.baijiaxing.home.widget.MyGridLayoutManager;
import com.lishi.baijiaxing.integral.IntegralActivity;
import com.lishi.baijiaxing.latest.LatestActivity;
import com.lishi.baijiaxing.view.MyGridView;
import com.lishi.baijiaxing.view.MyScrollView;
import com.lishi.baijiaxing.weeklyChoiceness.WeeklyChoicenessActivity;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.lishi.baijiaxing.yiyuan.view.YiYuanActivity;
import com.lishi.baijiaxing.yiyuan.view.YiYuanDetailsActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Home extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, HomeView, YiYuanHotAdapter.OnItemClickListener {
    private static final String TAG = "Fragment_Home";
    private LinearLayout mLinearLayout;
    private LayoutInflater mInflater;
    private int[] mGridViewIds = new int[]{R.drawable.icon_home1, R.drawable.icon_home2, R.drawable.icon_home3, R.drawable.icon_home4, R.drawable.icon_home5, R.drawable.icon_home6, R.drawable.icon_home7, R.drawable.icon_home8};
    private String[] texts = new String[]{"免费领", "一元拼", "积分兑换", "免费设计", "天天微课", "每周精选", "个性定制", "最新活动"};
    private List<GridNavigationBean> mGridNavigations = new ArrayList<GridNavigationBean>();
    private Context mContext;
    private TextView tv_msg;
    private TextView scanner;
    private List<HomeCommodityBean> classifyDatas = new ArrayList<>();
    private MyScrollView mScroll;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<HomeRecommendBean> recommendDatas = new ArrayList<>();
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
    /**
     * 免费领更多
     */
    private TextView free_more;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                mSwipeRefreshLayout.setRefreshing(false);
            } else if (msg.what == 2) {
                if (recommendDatas != null) {
                    Log.i(TAG, recommendDatas.size() + "========原来的大小============");
                    mHomePresenterImpl.recommendPullLoadData();
                }
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        mInflater = LayoutInflater.from(getActivity());
        findId(mView);
        initView(mView);
        return mView;
    }

    private void findId(View view) {
        tv_msg = (TextView) view.findViewById(R.id.tv_top_navigation_msg);
        scanner = (TextView) view.findViewById(R.id.tv_topnavigation_scanner);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshLayout_home);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.llAdvertiseBoard);
        gridView_navigation = (MyGridView) view.findViewById(R.id.home_gridview_navigation);
//        tv_load = (TextView) view.findViewById(R.id.tv_home_gridview_load);
        ll = (LinearLayout) view.findViewById(R.id.ll_homeroot);
//        footer = (LinearLayout) view.findViewById(R.id.footer);
        mScroll = (MyScrollView) view.findViewById(R.id.scroll_home);
        free_more = (TextView) view.findViewById(R.id.free_more);
        top_search = (ImageView) view.findViewById(R.id.top_search);

        free_photo1 = (ImageView) view.findViewById(R.id.free_photo1);
        free_photo2 = (ImageView) view.findViewById(R.id.free_photo2);
        free_photo3 = (ImageView) view.findViewById(R.id.free_photo3);
        free_photo1.setOnClickListener(this);
        free_photo2.setOnClickListener(this);
        free_photo3.setOnClickListener(this);
        top_search.setOnClickListener(this);
        free_more.setOnClickListener(this);
        
    }

    private void initView(View view) {
        mHomePresenterImpl = new HomePresenterImpl(this);
        mHomePresenterImpl.loadData();
    }

    /**
     * 初始化，下拉刷新控件
     *
     * @param
     */
    private void initSwpieRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    /**
     * 初始化TopNavigation
     * 顶部导航栏
     *
     * @param
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void initTopNavigation() {
        final View d = mView.findViewById(R.id.ll_top_navigation);
        d.getBackground().mutate().setAlpha(00);


        //处理imageView和textView点击事件冲突,将事件消费，返回true即可
        tv_msg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(mContext, "点击了登录", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        scanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });


        mScroll.setScrollViewChangeListener(new MyScrollView.onScrollViewListener() {
            @Override
            public void onScrollViewChange(MyScrollView view, int x, int y, int oldx, int oldy) {
                if (y > 0) {
                    if (y <= 255) {
                        if (y >= 100) {
                            tv_msg.setTextColor(Color.BLACK);
                            scanner.setTextColor(Color.BLACK);
                            Drawable drawable1 = getResources().getDrawable(R.drawable.scanner_black);
                            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
                            scanner.setCompoundDrawables(null, drawable1, null, null);

                            Drawable drawable2 = getResources().getDrawable(R.drawable.message_black);
                            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                            tv_msg.setCompoundDrawables(null, drawable2, null, null);

                        } else {
                            tv_msg.setTextColor(Color.WHITE);
                            scanner.setTextColor(Color.WHITE);
                            Drawable drawable3 = getResources().getDrawable(R.drawable.scanner_white);
                            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
                            scanner.setCompoundDrawables(null, drawable3, null, null);

                            Drawable drawable4 = getResources().getDrawable(R.drawable.message_white);
                            drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
                            tv_msg.setCompoundDrawables(null, drawable4, null, null);
                        }
                        d.getBackground().mutate().setAlpha(y);
                    } else {
                        d.getBackground().mutate().setAlpha(255);
                    }
                } else {
                    d.getBackground().mutate().setAlpha(00);
                    scanner.setTextColor(Color.WHITE);
                    tv_msg.setTextColor(Color.WHITE);
                    Drawable drawable2 = getResources().getDrawable(R.drawable.scanner_white);
                    drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                    scanner.setCompoundDrawables(null, drawable2, null, null);

                    Drawable drawable4 = getResources().getDrawable(R.drawable.message_white);
                    drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
                    tv_msg.setCompoundDrawables(null, drawable4, null, null);
                }
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

        BaseAdapter mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 8;
            }

            @Override
            public Object getItem(int position) {
                return mGridViewIds[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.home_gridview_item, parent, false);
                ImageView iv = (ImageView) view.findViewById(R.id.iv_home_gridview);
                GridNavigationBean gridNavigation1 = mGridNavigations.get(position);
                iv.setImageResource(gridNavigation1.getSrcId());
                TextView tv = (TextView) view.findViewById(R.id.tv_home_gridview);
                tv.setText(gridNavigation1.getText());
                return view;
            }
        };
        gridView_navigation.setAdapter(mAdapter);

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
            case 4://天天微课
                break;
            case 5://每周精选
                Intent startWeeklyActivity = new Intent(getActivity(), WeeklyChoicenessActivity.class);
                startActivity(startWeeklyActivity);
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
    private void initViewPager(JSONArray jsonArray) {
        mLinearLayout.setEnabled(true);
        mLinearLayout.requestLayout();
        // 添加图片的Url地址
        mLinearLayout.addView(new Advertisements(getActivity(), true, mInflater, 2000).initView(jsonArray));
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mSwipeRefreshLayout.setRefreshing(true);
                    Thread.sleep(2000);
                    mHandler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
                Intent startFreeDetailsActivity2 = new Intent(getActivity(), FreeDetailsActivity.class);
                startFreeDetailsActivity2.putExtra("type", FreeCommodityBean.TYPE_START_BEFORE);
                startActivity(startFreeDetailsActivity2);
                break;
            case R.id.free_photo3:
                Intent startFreeDetailsActivity3 = new Intent(getActivity(), FreeDetailsActivity.class);
                startFreeDetailsActivity3.putExtra("type", FreeCommodityBean.TYPE_FINISH);
                startActivity(startFreeDetailsActivity3);
                break;
            case R.id.free_more:
                Intent startFreeActivity = new Intent(getActivity(), FreeActivity.class);
                startActivity(startFreeActivity);
                break;
            case R.id.top_search:
                Intent startSearchActivity = new Intent(getActivity(), SearchActivity.class);
                startActivity(startSearchActivity);
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
    public void onRecommendonPullSuccess(ArrayList<HomeRecommendBean> datas) {
        recommendDatas.addAll(datas);
        tv_load.setText("上拉加载更多");
        Log.i(TAG, recommendDatas.size() + "" + datas.size() + tv_load.getText().toString() + "==============接受的大小===============================");
        isLoading = false;

    }


    @Override
    public void onPullloadFailed() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {
        Log.e("closeDialog", "加载完成");
    }

    @Override
    public void loadDataSuccess(HomeBean data) {
        initViewPager(data.getAdvertiseArray());

        initTopNavigation();
        initSwpieRefreshLayout();
        initGridView();
        //新版
        initRecyclerView(data.getClassifyDatas());
    }

    private void initRecyclerView(ArrayList<HomeCommodityBean> comm) {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_home);
        MyGridLayoutManager mManager = new MyGridLayoutManager(getActivity(), 6, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);

        HomeAdapter mAdapter = new HomeAdapter(getActivity(), comm);
        mRecyclerView.setAdapter(mAdapter);
        Log.i("initRecyclerView", "initRecyclerView" + comm.size());

        mAdapter.setOnItemClickListener(this);
    }


    @Override
    public void loadDataFailed(String error) {

    }

    @Override
    public void onClickListener(View view, int position) {
        if (position > 0 && position <= 3) {
            Intent startYiYuanDetails = new Intent(getActivity(), YiYuanDetailsActivity.class);
            startYiYuanDetails.putExtra("type",YiYuanActivity.TYPE_HOT);
            startActivity(startYiYuanDetails);
        }else {
            Intent startCommodityDetails = new Intent(getActivity(), CommodityDetailsActivity.class);
            startActivity(startCommodityDetails);
        }
    }
}
