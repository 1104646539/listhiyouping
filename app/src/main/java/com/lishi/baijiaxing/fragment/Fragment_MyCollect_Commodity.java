package com.lishi.baijiaxing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.adapter.MyCollectCommodityAdapter;
import com.lishi.baijiaxing.shoppingCart.model.CommodityBean;
import com.lishi.baijiaxing.view.MyListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class Fragment_MyCollect_Commodity extends Fragment {
    private List<CommodityBean> commodityBeans;
    private MyListView mListView;
    private MyCollectCommodityAdapter adapter;
    private LinearLayout ll_mycollect_commodity_cancel;
    private TextView tv_cancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycollect_commodity, null, false);
        initView(view);
        return view;
    }

    private void initView(final View view) {
        initData();
        mListView = (MyListView) view.findViewById(R.id.listview_mycollect_commodity);
        ll_mycollect_commodity_cancel = (LinearLayout) view.findViewById(R.id.ll_mycollect_commodity_cancel);
        adapter = new MyCollectCommodityAdapter(getActivity(), commodityBeans);
        mListView.setAdapter(adapter);
        adapter.setOnCheckChangeListener(new MyCollectCommodityAdapter.OnCheckChangeListener() {
            @Override
            public void onCheckChangeListener(int position, boolean checked) {
                Log.i("asf", "我的收藏选中了" + checked);
                if (checked) {
                    commodityBeans.get(position).setChecked(true);
                } else {
                    commodityBeans.get(position).setChecked(false);
                }
            }
        });

        tv_cancel = (TextView) view.findViewById(R.id.tv_mycollect_cancel);
        tv_cancel.setClickable(true);
        //按下取消关注
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dsaf", "取消关注");
                deleteData();
//                ll_mycollect_commodity_cancel.setVisibility(View.GONE);
//                ChangeAll();
                adapter.notifyDataSetChanged();
            }
        });


    }

    /**
     * 删除数据
     */
    private void deleteData() {
        Iterator<CommodityBean> cc = commodityBeans.listIterator();
        while (cc.hasNext()) {
            CommodityBean commodityBean = cc.next();
            if (commodityBean.isChecked()) {
                cc.remove();
            }
        }
    }

    private void initData() {
        commodityBeans = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            CommodityBean cbean = new CommodityBean("", "联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救者联想(lenovo）拯救", "颜色：6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸6代新平台15.6寸", 5200 + i, 1000, 2, false);
            commodityBeans.add(cbean);
        }
    }

    public void CheckChange(boolean flag) {
        adapter.ShowCheck(flag);
        if (flag) {
            ll_mycollect_commodity_cancel.setVisibility(View.VISIBLE);
        } else {
            ll_mycollect_commodity_cancel.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();

    }

    /**
     * 取消所有选中状态
     */
    public void ChangeAll() {
        for (int i = 0; i < commodityBeans.size(); i++) {
            commodityBeans.get(i).setChecked(false);
        }
    }
}
