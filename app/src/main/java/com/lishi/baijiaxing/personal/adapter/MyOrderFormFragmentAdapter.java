package com.lishi.baijiaxing.personal.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 我的订单
 * Created by Administrator on 2016/8/3.
 */
public class MyOrderFormFragmentAdapter extends FragmentPagerAdapter {
    public String[] titles = new String[]{"待付款", "待收货", "待评论", "返修/退换"};
    private List<Fragment> mFragments;

    public MyOrderFormFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
