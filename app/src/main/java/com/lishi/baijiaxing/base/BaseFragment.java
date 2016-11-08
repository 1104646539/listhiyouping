package com.lishi.baijiaxing.base;

import android.app.Fragment;

public abstract class BaseFragment extends Fragment {
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
