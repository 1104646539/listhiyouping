package com.lishi.baijiaxing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.adapter.MyCollectCommodityAdapter;
import com.lishi.baijiaxing.adapter.MyCollectStoreAdapter;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;
import com.lishi.baijiaxing.view.MyListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class Fragment_MyCollect_Store extends Fragment {
    private MyListView mMyListView;
    private List<StoreBean> storeBeans;
    private MyCollectStoreAdapter adapter;
    private LinearLayout ll_mycollect_store_cancel;
    private TextView tv_cancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mycollect_store, null, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        initData();
        mMyListView = (MyListView) view.findViewById(R.id.listview_mycollect_store);
        ll_mycollect_store_cancel = (LinearLayout) view.findViewById(R.id.ll_mycollect_store_cancel);
        tv_cancel = (TextView) view.findViewById(R.id.tv_mycollect_store_cancel);

        adapter = new MyCollectStoreAdapter(getActivity(), storeBeans);
        mMyListView.setAdapter(adapter);
        adapter.setOnCheckChangeListener(new MyCollectCommodityAdapter.OnCheckChangeListener() {
            @Override
            public void onCheckChangeListener(int position, boolean checked) {
                storeBeans.get(position).setChecked(checked);
                adapter.notifyDataSetChanged();
            }
        });
        
        tv_cancel.setClickable(true);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
//                ll_mycollect_commodity_cancel.setVisibility(View.GONE);
//                ChangeAll();
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void initData() {
        storeBeans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            StoreBean store = new StoreBean(false, 0, "百姓图腾", "", null);
            storeBeans.add(store);
        }
        for (int i = 0; i < 3; i++) {
            StoreBean store = new StoreBean(false, 0, "特步旗舰店", "", null);
            storeBeans.add(store);
        }

    }

    /**
     * 删除数据
     */
    private void deleteData() {
        Iterator<StoreBean> cc = storeBeans.listIterator();
        while (cc.hasNext()) {
            StoreBean storeBean = cc.next();
            if (storeBean.isChecked()) {
                cc.remove();
            }
        }
    }

    public void CheckChange(boolean flag) {
        adapter.ShowCheck(flag);
        if (flag) {
            ll_mycollect_store_cancel.setVisibility(View.VISIBLE);
        } else {
            ll_mycollect_store_cancel.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();

    }

    /**
     * 取消所有选中状态
     */
    public void ChangeAll() {
        for (int i = 0; i < storeBeans.size(); i++) {
            storeBeans.get(i).setChecked(false);
        }
    }

}
