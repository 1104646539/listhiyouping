package com.lishi.baijiaxing.classify.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.classify.adapter.OneClassifyAdapter;
import com.lishi.baijiaxing.base.BaseFragment;
import com.lishi.baijiaxing.classify.model.ClassAd;
import com.lishi.baijiaxing.classify.model.ClassList;
import com.lishi.baijiaxing.classify.model.ClassOne;
import com.lishi.baijiaxing.classify.model.OneClassify;
import com.lishi.baijiaxing.classify.Widget.Fragment_ClassifyThree;
import com.lishi.baijiaxing.classify.presenter.ClassifyPresenterImpl;
import com.lishi.baijiaxing.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Fragment_Classify extends BaseFragment implements ClassifyView, View.OnClickListener {
    private static Fragment_Classify mFragment_Classify;
    private ListView mListView_calssity1;//一级分类
    private String[] oneclassity = new String[]{"推荐分类", "礼品", "数码", "电脑办公", "餐饮具", "饰品", "化妆品", "茶酒", "零食", "粮油副食", "图书", "生鲜", "运动户外", "家具", "奢品", "钟表珠宝", "生活旅行", "宠物", "汽车"};
    private List<OneClassify> oneDatas = new ArrayList<>();
    private View mView;
    public static int mPosition = 0;
    private ImageView tv_topnavigation_icon;
    private TextView tv_message;
    private ClassifyPresenterImpl mClassifyPresenterImpl;
    private ImageView search;

    private ClassList mClassList;
    private ClassOne mClassOne;
    private ClassAd mClassAd;
    private LinearLayout search_ll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_classify, container, false);
        findId();
        initView();

        Log.e("onCreateView", "onCreateFragment_Classify");
        return mView;
    }

    public Fragment_Classify() {

    }

    public static Fragment_Classify newInstance() {
        if (mFragment_Classify == null) {
            mFragment_Classify = new Fragment_Classify();
        }
        return new Fragment_Classify();
    }

    private void findId() {
        tv_topnavigation_icon = (ImageView) mView.findViewById(R.id.tv_topnavigation_icon);
        tv_message = (TextView) mView.findViewById(R.id.tv_top_navigation_msg);
        mListView_calssity1 = (ListView) mView.findViewById(R.id.list_classify1);
        search_ll = (LinearLayout) mView.findViewById(R.id.home_search_ll);
        search = (ImageView) mView.findViewById(R.id.top_search);
        search.setOnClickListener(this);
        search_ll.getBackground().mutate().setAlpha(150);
    }

    private void initView() {
        initTopNavigation();
        mClassifyPresenterImpl = new ClassifyPresenterImpl(this);
        mClassifyPresenterImpl.loadClassList();
    }

    /**
     * 三级分类fragment
     *
     * @param oneClassity
     */
    private void initClassifyThree(List<ClassOne.DataBean> oneClassity) {
        Fragment_ClassifyThree mFragment_classitythree = new Fragment_ClassifyThree(oneClassity);
        getActivity().getFragmentManager().beginTransaction().replace(R.id.ll_classity_root, mFragment_classitythree).commit();
    }

    /**
     * 初始化顶部导航栏
     */
    private void initTopNavigation() {
        Drawable drawable1 = getResources().getDrawable(R.drawable.scanner_black);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());

        tv_message.setTextColor(Color.BLACK);
        Drawable drawable2 = getResources().getDrawable(R.drawable.message_black);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        tv_message.setCompoundDrawables(null, drawable2, null, null);

    }

    /**
     * 初始化一级分类
     */
    private void initClassify1() {
        final OneClassifyAdapter oneClassifyAdapter = new OneClassifyAdapter(getActivity(), mClassList.getData());
        mListView_calssity1.setAdapter(oneClassifyAdapter);
        mListView_calssity1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
                mPosition = position;
                mClassifyPresenterImpl.loadClassOne(mClassList.getData().get(position).getClassifyid());
                mListView_calssity1.post(new Runnable() {
                    @Override
                    public void run() {
                        //滑动到指定的item到顶部，可以设置偏移量。设置偏移量为屏幕能显示的item数量的一半*每个item的height即可将item滑动到中间
                        mListView_calssity1.smoothScrollToPositionFromTop(position, 5 * mListView_calssity1.getChildAt(0).getHeight(), 300);
                    }
                });
                oneClassifyAdapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {

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

//    public void loadDataSuccess(ArrayList<OneClassify> data) {
//        oneDatas = data;
//        initClassifyThree(oneDatas.get(0));
//        Log.e("loadDataSuccess", "加载成功——————+——+——+——+");
//    }

    @Override
    public void loadDataFailed(String error) {
        Log.e("loadDataFailed", "loadDataFailed+  " + error);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_search:
                Intent startSearchActivity = new Intent(getActivity(), SearchActivity.class);
                startActivity(startSearchActivity);
                break;
        }
    }

    @Override
    public void loadClassListSuccess(ClassList classList) {
        mClassList = classList;
        initClassify1();
        //第一次默认加载推荐分类(第一个分类信息)
        mClassifyPresenterImpl.loadClassOne(mClassList.getData().get(0).getClassifyid());
    }

    @Override
    public void loadClassListFailed(String error) {

    }

    @Override
    public void loadAdSuccess(ClassAd classAd) {

    }

    @Override
    public void loadAdFailed(String error) {

    }

    @Override
    public void loadClassSuccess(ClassOne classOne) {
        initClassifyThree(classOne.getData());
    }

    @Override
    public void loadClassFailed(String error) {

    }
}
