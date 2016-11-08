package com.lishi.baijiaxing.details.model;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseModel;
import com.lishi.baijiaxing.customize.model.NormsBean;
import com.lishi.baijiaxing.details.CommodityCommentCallback;
import com.lishi.baijiaxing.details.CommodityDetailsCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/1.
 */
public class CommodityDetailsModelImpl extends BaseModel implements CommodityDetailsModel {
    @Override
    public void loadData(CommodityDetailsCallback callback) {
        ArrayList<Integer> srcIds = new ArrayList<>();
        srcIds.add(R.drawable.free_details_info1);
        srcIds.add(R.drawable.free_details_info2);
        srcIds.add(R.drawable.free_details_info3);
        srcIds.add(R.drawable.free_details_info4);
        srcIds.add(R.drawable.free_details_info5);
        srcIds.add(R.drawable.free_details_info6);

        List<NormsBean> normsBeens = new ArrayList<>();
        List<String> classname = new ArrayList<>();
        classname.add("默认");
        classname.add("默认2");
        NormsBean normsBean = new NormsBean("规格", classname);
        normsBeens.add(normsBean);

        CommodityDetailsBean mCommodityDetailsBean = new CommodityDetailsBean("", "【利世优品】万仟堂陶瓷同心杯带盖过滤 办公茶杯水杯【利世优品】万仟堂陶瓷同心杯带盖过滤 办公茶杯水杯"
                , 250, srcIds, "", normsBeens,60);

        callback.onLoadSuccess(mCommodityDetailsBean);
    }

}
