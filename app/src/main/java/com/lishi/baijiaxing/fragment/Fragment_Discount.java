package com.lishi.baijiaxing.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragment;

/**
 * 量贩
 * Created by Administrator on 2016/7/29.
 */
public class Fragment_Discount extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discount, null, false);
        findId(view);
        initView(view);
        return view;
    }

    private void initView(View view) {
    }

    private void findId(View view) {
    }

    @Override
    public void onInvisible() {
        
    }

    @Override
    public void lazyLoad() {
        
    }
}
