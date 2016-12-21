package com.lishi.baijiaxing.customize.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.customize.model.NormsBean;
import com.lishi.baijiaxing.home.widget.MyGridLayoutManager;
import com.lishi.baijiaxing.view.MyGridView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/27.
 */
public class MyNormsView extends LinearLayout {
    private Context mContent;
    private NormsBean mNormsBean;

    public MyNormsView(Context content, NormsBean normsBean) {
        super(content);
        this.mContent = content;
        this.mNormsBean = normsBean;
        init();
    }

    private void init() {
        View root = LayoutInflater.from(mContent).inflate(R.layout.customize_config_item, null);
        TextView classifyName = (TextView) root.findViewById(R.id.customize_config_classifyName);
        classifyName.setText(mNormsBean.getClassifyName());

        MyGridView gridView = (MyGridView) root.findViewById(R.id.customize_config_gridView);
        NormsGridAdapter adapter = new NormsGridAdapter(mNormsBean);
        gridView.setAdapter(adapter);

        LinearLayout.LayoutParams lpg = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(root, lpg);
    }

    class NormsGridAdapter extends BaseAdapter {
        NormsBean mNorms;

        public NormsGridAdapter(NormsBean norms) {
            mNorms = norms;
        }

        @Override
        public int getCount() {
            return mNorms.getClassifys().size();
        }

        @Override
        public Object getItem(int position) {
            return mNorms.getClassifys().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            NormGridViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContent).inflate(R.layout.customize_norms_item, parent, false);
                viewHolder = new NormGridViewHolder();
                viewHolder.tv_norms = (TextView) convertView.findViewById(R.id.customize_norms_classify);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (NormGridViewHolder) convertView.getTag();
            }
            viewHolder.tv_norms.setText(mNorms.getClassifys().get(position).getNormsName());
            if (mNorms.getCheckableIndex() == position) {
                viewHolder.tv_norms.setBackgroundResource(R.drawable.customzie_config_checkable);
            } else {
                viewHolder.tv_norms.setBackgroundResource(R.drawable.customzie_config_nocheckable);
            }

            viewHolder.tv_norms.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != mNorms.getCheckableIndex()) {
                        mNorms.setCheckableIndex(position);
                        notifyDataSetChanged();
                    }
                }
            });
            return convertView;
        }

        class NormGridViewHolder {
            TextView tv_norms;
        }
    }
}
