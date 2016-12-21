package com.lishi.baijiaxing.details.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.adapter.CommentAdapter;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.bean.CommentBean;
import com.lishi.baijiaxing.details.presenter.CommodityCommentPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 详情——评论
 * Created by Administrator on 2016/7/12.
 */
public class Fragment_Commodity_Comment extends BaseFragmentV4 implements View.OnClickListener, CommodityCommentView {
    private ListView mListView;
    private List<CommentBean> mCommentBeen;
    private TextView tv_all, tv_all_num, tv_good, tv_good_num, tv_ordinary, tv_ordinary_num, tv_disappointing, tv_disappointing_num, tv_photo, tv_photo_num;
    private int page = 0;
    private List<TextView> tv = new ArrayList<>();
    private List<TextView> tv_num = new ArrayList<>();
    private CommentAdapter adapter;
    private static Fragment_Commodity_Comment mFragment_commodity_comment;
    private boolean isPrepare;
    private View view;
    private CommodityCommentPresenterImpl mCommodityCommentPresenterImpl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details_comment, container, false);
        return view;
    }

    private void initView() {
//        initData();
        findView();
        ClickEnevt();
        adapter = new CommentAdapter(getActivity(), mCommentBeen);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }

    public static Fragment_Commodity_Comment newInstance() {
//        if (mFragment_commodity_comment == null) {
//            mFragment_commodity_comment = new Fragment_Commodity_Comment();
//        }
        return new Fragment_Commodity_Comment();
    }

    private void ClickEnevt() {
        tv_all.setClickable(true);
        tv_all_num.setClickable(true);
        tv_good.setClickable(true);
        tv_good_num.setClickable(true);
        tv_ordinary.setClickable(true);
        tv_ordinary_num.setClickable(true);
        tv_disappointing.setClickable(true);
        tv_disappointing_num.setClickable(true);
        tv_photo.setClickable(true);
        tv_photo_num.setClickable(true);

        tv_all.setOnClickListener(this);
        tv_all_num.setOnClickListener(this);
        tv_good.setOnClickListener(this);
        tv_good_num.setOnClickListener(this);
        tv_ordinary.setOnClickListener(this);
        tv_ordinary_num.setOnClickListener(this);
        tv_disappointing.setOnClickListener(this);
        tv_disappointing_num.setOnClickListener(this);
        tv_photo.setOnClickListener(this);
        tv_photo_num.setOnClickListener(this);
    }

    private void findView() {
        mListView = (ListView) view.findViewById(R.id.listview_comment);

        tv_all = (TextView) view.findViewById(R.id.tv_comment_all);
        tv_all_num = (TextView) view.findViewById(R.id.tv_comment_all_num);
        tv_good = (TextView) view.findViewById(R.id.tv_comment_good);
        tv_good_num = (TextView) view.findViewById(R.id.tv_comment_good_num);
        tv_ordinary = (TextView) view.findViewById(R.id.tv_comment_ordinary);
        tv_ordinary_num = (TextView) view.findViewById(R.id.tv_comment_ordinary_num);
        tv_disappointing = (TextView) view.findViewById(R.id.tv_comment_disappointing);
        tv_disappointing_num = (TextView) view.findViewById(R.id.tv_comment_disappointing_num);
        tv_photo = (TextView) view.findViewById(R.id.tv_comment_photo);
        tv_photo_num = (TextView) view.findViewById(R.id.tv_comment_photo_num);

        tv.add(tv_all);
        tv.add(tv_good);
        tv.add(tv_ordinary);
        tv.add(tv_disappointing);
        tv.add(tv_photo);
        tv_num.add(tv_all_num);
        tv_num.add(tv_good_num);
        tv_num.add(tv_ordinary_num);
        tv_num.add(tv_disappointing_num);
        tv_num.add(tv_photo_num);

    }

//    private void initData() {
//        mCommentBeen = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            if (i < 3) {
//                CommentBean c = new CommentBean(null, R.drawable.user_photo, "咸鱼这名也不给起" + i, R.drawable.user_leve, "2016-7-9", R.drawable.star, "东西不错哦。下次再来买", "2016-7-1");
//                mCommentBeen.add(c);
//            } else {
//                CommentBean c = new CommentBean(null, R.drawable.user_photo, "咸鱼这名也不给起" + i, R.drawable.user_leve, "2016-7-9", R.drawable.star, "很一般", "2016-7-1");
//                mCommentBeen.add(c);
//            }
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_comment_all:
            case R.id.tv_comment_all_num:
                page = 0;
                changePage(page);
                break;
            case R.id.tv_comment_good:
            case R.id.tv_comment_good_num:
                page = 1;
                changePage(page);
                break;
            case R.id.tv_comment_ordinary:
            case R.id.tv_comment_ordinary_num:
                page = 2;
                changePage(page);
                break;
            case R.id.tv_comment_disappointing:
            case R.id.tv_comment_disappointing_num:
                page = 3;
                changePage(page);
                break;
            case R.id.tv_comment_photo:
            case R.id.tv_comment_photo_num:
                page = 4;
                changePage(page);
                break;
        }

    }

    /**
     * 更换评论分类
     *
     * @param page
     */
    private void changePage(int page) {
        for (int i = 0; i < tv.size(); i++) {
            if (page == i) {
                tv.get(page).setTextColor(Color.rgb(237, 41, 42));
                tv_num.get(page).setTextColor(Color.rgb(237, 41, 42));
            } else {
                tv.get(i).setTextColor(Color.rgb(0, 0, 0));
                tv_num.get(i).setTextColor(Color.rgb(0, 0, 0));
            }
        }

        for (int i = 0; i < mCommentBeen.size(); i++) {
            if (page == 0) {
                mCommentBeen.get(i).setImgs(null);
                if (i < 3) {
                    mCommentBeen.get(i).setComment_text("东西不错哦。下次再来买");
                } else {
                    mCommentBeen.get(i).setComment_text("很一般");
                }
            } else if (page == 1) {
                mCommentBeen.get(i).setImgs(null);
                mCommentBeen.get(i).setComment_text("东西不错哦。下次再来买");

            } else if (page == 2) {
                mCommentBeen.get(i).setImgs(null);
                mCommentBeen.get(i).setComment_text("很一般");
            } else if (page == 3) {
                mCommentBeen.get(i).setImgs(null);
                mCommentBeen.get(i).setComment_text("太差了");

            } else if (page == 4) {
                mCommentBeen.get(i).setComment_text("看图");
                List<String> imgs = new ArrayList<>();
                for (int j = 0; j < 2; j++) {
                    imgs.add("http://www.bx5000.com/data/upload/shop/store/goods/1/1_05167225956232429_240.jpg");
                }
                mCommentBeen.get(i).setImgs(imgs);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter = null;
        mListView = null;
        tv = null;
        tv_num = null;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isPrepare || !isVisible) {
            return;
        }
        if (mCommodityCommentPresenterImpl == null) {
            mCommodityCommentPresenterImpl = new CommodityCommentPresenterImpl(this);
        }
        mCommodityCommentPresenterImpl.loadData();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(ArrayList<CommentBean> data) {
        this.mCommentBeen = data;
        initView();
        Log.i("评论加载成功", "Size=" + data.size());
    }

    @Override
    public void loadDataFailed(String error) {

    }
}
