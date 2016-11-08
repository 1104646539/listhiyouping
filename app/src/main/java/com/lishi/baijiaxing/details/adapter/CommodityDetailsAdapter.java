package com.lishi.baijiaxing.details.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 商品详情
 * Created by Administrator on 2016/7/5.
 */
public class CommodityDetailsAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    public CommodityDetailsAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
