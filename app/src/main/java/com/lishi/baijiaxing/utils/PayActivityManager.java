package com.lishi.baijiaxing.utils;

import com.facebook.stetho.websocket.WebSocketHandler;
import com.lishi.baijiaxing.pay.PayActivity;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/12/15.
 */


public class PayActivityManager {
    private WeakReference<PayActivity> mPayActivityWeakReference;

    public static PayActivityManager getInstance() {
        return PayActivityManagerHolder.mPayActivityManager;
    }

    public void setPayActivity(PayActivity payActivity) {
        if (mPayActivityWeakReference == null) {
            if (payActivity != null) {
                mPayActivityWeakReference = new WeakReference<PayActivity>(payActivity);
            }
        }
    }

    public void clear() {
        mPayActivityWeakReference = null;
    }

    public PayActivity getPayActivity() {
        if (mPayActivityWeakReference != null) {
            return mPayActivityWeakReference.get();
        }
        return null;
    }

    private static class PayActivityManagerHolder {
        private final static PayActivityManager mPayActivityManager = new PayActivityManager();
    }
}
