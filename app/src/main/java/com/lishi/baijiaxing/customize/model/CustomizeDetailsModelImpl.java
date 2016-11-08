package com.lishi.baijiaxing.customize.model;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.customize.CustomizeDetailsCallback;
import com.lishi.baijiaxing.customize.presenter.CustomizeDetailsPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */
public class CustomizeDetailsModelImpl extends BaseModel implements CustomizeDetailsModel {

    @Override
    public void loadData(CustomizeDetailsCallback callback) {
        List<Integer> srcs = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            srcs.add(R.drawable.customize_details_commodityinfo);
        }
        List<NormsBean> nbs = new ArrayList<>();
        List<String> classify = new ArrayList<>();
        classify.add("标配");
        classify.add("高配");
        NormsBean nb = new NormsBean("规格", classify);
        nbs.add(nb);
        CustomizeCommodityBean mCustomizeCommodityBean = new CustomizeCommodityBean("", "220", 20, 500, 30, "56度恒温紫砂杯 可定制",
                "标准配置", 1, "", srcs, nbs);
        callback.onLoadSuccess(mCustomizeCommodityBean);
    }

    @Override
    public void collectCommodity(CustomizeDetailsCallback callback) {

    }
}
