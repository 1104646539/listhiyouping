package com.lishi.baijiaxing.classify.view;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.classify.adapter.OneClassifyAdapter;
import com.lishi.baijiaxing.base.BaseFragment;
import com.lishi.baijiaxing.classify.model.OneClassify;
import com.lishi.baijiaxing.classify.Widget.Fragment_ClassifyThree;
import com.lishi.baijiaxing.classify.presenter.ClassifyPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Classify extends BaseFragment implements ClassifyView {
    private ListView mListView_calssity1;//一级分类
    private String[] oneclassity = new String[]{"推荐分类", "礼品", "数码", "电脑办公", "餐饮具", "饰品", "化妆品", "茶酒", "零食", "粮油副食", "图书", "生鲜", "运动户外", "家具", "奢品", "钟表珠宝", "生活旅行", "宠物", "汽车"};
    private List<OneClassify> oneDatas = new ArrayList<>();
    private View mView;
    public static int mPosition = 0;
    private TextView tv_scanner;
    private TextView tv_message;
    private ClassifyPresenterImpl mClassifyPresenterImpl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_classify, container, false);
        findId();
        initView();

        Log.e("onCreateView", "onCreateFragment_Classify");
        return mView;
    }

    private void findId() {
        tv_scanner = (TextView) mView.findViewById(R.id.tv_topnavigation_scanner);
        tv_message = (TextView) mView.findViewById(R.id.tv_top_navigation_msg);
        mListView_calssity1 = (ListView) mView.findViewById(R.id.list_classify1);
    }

    private void initView() {
        initTopNavigation();
        mClassifyPresenterImpl = new ClassifyPresenterImpl(this);
        mClassifyPresenterImpl.loadData();
    }

    /**
     * 三级分类fragment
     */
    private void initClassifyThree(OneClassify oneClassity) {
        Fragment_ClassifyThree mFragment_classitythree = new Fragment_ClassifyThree(oneClassity);
        getActivity().getFragmentManager().beginTransaction().replace(R.id.ll_classity_root, mFragment_classitythree).commit();
    }

    /**
     * 初始化顶部导航栏
     */
    private void initTopNavigation() {
        tv_scanner.setTextColor(Color.BLACK);
        Drawable drawable1 = getResources().getDrawable(R.drawable.scanner_black);
        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
        tv_scanner.setCompoundDrawables(null, drawable1, null, null);

        tv_message.setTextColor(Color.BLACK);
        Drawable drawable2 = getResources().getDrawable(R.drawable.message_black);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        tv_message.setCompoundDrawables(null, drawable2, null, null);

    }

    /**
     * 初始化一级分类
     */
    private void initClassify1() {
        final OneClassifyAdapter oneClassifyAdapter = new OneClassifyAdapter(getActivity(), oneclassity);
        mListView_calssity1.setAdapter(oneClassifyAdapter);
        mListView_calssity1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
                initClassifyThree(oneDatas.get(position));
                mPosition = position;
                oneClassifyAdapter.notifyDataSetChanged();
                mListView_calssity1.post(new Runnable() {
                    @Override
                    public void run() {
                        //滑动到指定的item到顶部，可以设置偏移量。设置偏移量为屏幕能显示的item数量的一半*每个item的height即可将item滑动到中间
                        mListView_calssity1.smoothScrollToPositionFromTop(position, 5 * mListView_calssity1.getChildAt(0).getHeight(), 300);
                    }
                });
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
    public void loadDataSuccess(ArrayList<OneClassify> data) {
        oneDatas = data;
        initClassify1();
        initClassifyThree(oneDatas.get(0));
        Log.e("loadDataSuccess", "加载成功——————+——+——+——+");
    }

    @Override
    public void loadDataFailed(String error) {
        Log.e("loadDataFailed", "loadDataFailed+  " + error);
    }
}
