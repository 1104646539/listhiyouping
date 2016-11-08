package com.lishi.baijiaxing.single;

import com.lishi.baijiaxing.shoppingCart.model.StoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/14.
 */
public class CartListEntity {
    public List<StoreBean> getInstance() {
        return StoreBeanHolder.mStoreBeen;
    }

    static class StoreBeanHolder {
        private final static List<StoreBean> mStoreBeen = new ArrayList<StoreBean>();
    }
}
