package com.lishi.baijiaxing.base;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/8/3.
 */
public abstract class BaseFragmentV4 extends Fragment {
    protected boolean isVisible;//fragment是否可见

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    public abstract void onInvisible();

    private void onVisible() {
        lazyLoad();
    }

    public abstract void lazyLoad();
}
