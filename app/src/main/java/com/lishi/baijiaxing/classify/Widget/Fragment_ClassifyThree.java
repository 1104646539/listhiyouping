package com.lishi.baijiaxing.classify.Widget;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragment;
import com.lishi.baijiaxing.classify.model.ClassOne;
import com.lishi.baijiaxing.classify.model.OneClassify;
import com.lishi.baijiaxing.classify.model.ThreeClassify;
import com.lishi.baijiaxing.classify.model.TwoClassify;
import com.lishi.baijiaxing.details.CommodityDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
@SuppressLint("ValidFragment")
public class Fragment_ClassifyThree extends BaseFragment {
    private List<ClassOne.DataBean> tClassitys;

    public Fragment_ClassifyThree() {

    }

    public Fragment_ClassifyThree(List<ClassOne.DataBean> oneClassify) {
        tClassitys = oneClassify;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classitythree, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        //加载广告图 未完成
        
        
        for (int i = 0; i < tClassitys.size(); i++) {
            LinearLayout root = (LinearLayout) view.findViewById(R.id.ll_classitythree_root);
            final ThreeClassifyGridView threeClassityGridView = new ThreeClassifyGridView(getActivity(), tClassitys.get(i));
            root.addView(threeClassityGridView);
            final int finalI = i;
            threeClassityGridView.getGridView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent startCommodityDetails = new Intent(getActivity(), CommodityDetailsActivity.class);
                    startCommodityDetails.putExtra("gid", tClassitys.get(finalI).getCategoryGoodsList().get(position).getCid());
                    startActivity(startCommodityDetails);
                }
            });
        }
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {

    }
}
