package com.lishi.baijiaxing.classify.Widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragment;
import com.lishi.baijiaxing.classify.model.OneClassify;
import com.lishi.baijiaxing.classify.model.TwoClassify;

import java.util.List;

/**
 * Created by Administrator on 2016/6/12.
 */
@SuppressLint("ValidFragment")
public class Fragment_ClassifyThree extends BaseFragment {
    private TwoClassify twoClassity;
    private String advertisementurl;
    private List<TwoClassify> tClassitys;

    public Fragment_ClassifyThree() {

    }

    public Fragment_ClassifyThree(OneClassify oneClassify) {
        advertisementurl = oneClassify.getUrl();
        tClassitys = oneClassify.getTwodatas();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_classitythree, null, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        for (int i = 0; i < tClassitys.size(); i++) {
            LinearLayout root = (LinearLayout) view.findViewById(R.id.ll_classitythree_root);
            ThreeClassifyGridView threeClassityGridView = new ThreeClassifyGridView(getActivity(), tClassitys.get(i));
            root.addView(threeClassityGridView);
        }
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {

    }
}
