package com.lishi.baijiaxing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lishi.baijiaxing.R;

/**详情——商品
 * Created by Administrator on 2016/7/12.
 */
public class DetailsCommodityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_commodity, null, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        
        
    }

}