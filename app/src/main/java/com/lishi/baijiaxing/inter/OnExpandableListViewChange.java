package com.lishi.baijiaxing.inter;

import com.lishi.baijiaxing.shoppingCart.model.StoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车状态变更接口
 * Created by Administrator on 2016/6/14.
 */
public interface OnExpandableListViewChange {
    public void onStoreCheckedChange(int group,boolean isChecked);//按下选中店铺
    public void onCommodityCheckedChange(int group,int child,boolean isChecked);//按下选中商品
    public void onNumberChange(ArrayList<StoreBean> storeBeens);//数量变更
    
}
