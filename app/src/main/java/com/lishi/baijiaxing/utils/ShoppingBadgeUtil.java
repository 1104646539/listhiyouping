package com.lishi.baijiaxing.utils;

/**
 * 购物车提醒类
 * Created by Administrator on 2016/11/10.
 */
public class ShoppingBadgeUtil {
    private int badgeCount = 0;

    public int getBadgeCount() {
        return badgeCount;
    }

    public void setBadgeCount(int badgeCount) {
        this.badgeCount = badgeCount;
    }

    public static ShoppingBadgeUtil getInstance() {
        return ShoppingBadgeUtilViewHolder.shoppingBadgeUtil;
    }

    private ShoppingBadgeUtil() {

    }

    private static class ShoppingBadgeUtilViewHolder {
        private static ShoppingBadgeUtil shoppingBadgeUtil = new ShoppingBadgeUtil();
    }
}
