package com.lishi.baijiaxing.freeDesign.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8.
 */
public class PageTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private String[] mTitles ;

    public PageTabAdapter(FragmentManager fm,List<Fragment> fragments,String[] titles) {
        super(fm);
        this.mFragments = fragments;
        this.mTitles =titles;
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
        return mTitles[position];
        
    }
}
