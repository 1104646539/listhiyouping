package com.lishi.baijiaxing.yiyuan.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseFragmentV4;
import com.lishi.baijiaxing.free.adapter.FreeCommentAdapter;
import com.lishi.baijiaxing.free.model.FreeCommentBean;
import com.lishi.baijiaxing.free.model.ReplyBean;
import com.lishi.baijiaxing.free.presenter.FreeCommentPresenterImpl;
import com.lishi.baijiaxing.free.view.FreeCommentView;
import com.lishi.baijiaxing.utils.DividerItemDecoration;

import java.util.ArrayList;

/**
 * 一元拼商品详情——评论
 * Created by Administrator on 2016/10/19.
 */
public class Fragment_YiYuanComment extends BaseFragmentV4 implements View.OnClickListener, FreeCommentView {
    private View mView;
    private static Fragment_YiYuanComment mFragment_freeDetails_comment;
    private boolean isPrepare;
    private ArrayList<FreeCommentBean> fcbs;
    private RecyclerView mRecyclerView;
    private TextView tv_send;
    private EditText mEdit;
    private String toName;
    private String myName = "110";
    private int mPosition = 0;
    private FreeCommentAdapter adapter;
    private InputMethodManager imm;

    private FreeCommentPresenterImpl mFreeCommentPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_freedetails_comment, container, false);
        return mView;
    }

    public static Fragment_YiYuanComment newInstance() {
        if (mFragment_freeDetails_comment == null) {
            mFragment_freeDetails_comment = new Fragment_YiYuanComment();
        }
        return mFragment_freeDetails_comment;
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isPrepare || !isVisible) {
            return;
        }
        if (mFreeCommentPresenter == null) {
            mFreeCommentPresenter = new FreeCommentPresenterImpl(this);
        }
        mFreeCommentPresenter.loadData();
    }

    private void initView() {
        initData();
        findId();

        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        adapter = new FreeCommentAdapter(getActivity(), fcbs);
        mRecyclerView.setAdapter(adapter);

        tv_send.setOnClickListener(this);
        mEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count >= 10) {
                    tv_send.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        adapter.setOnReplyClickLister(new FreeCommentAdapter.OnReplyClickLister() {
            @Override
            public void onReplyClickLister(View view, int position) {
                toName = fcbs.get(position).getName();
                mPosition = position;
                mEdit.setHint("回复 " + toName);
                imm.showSoftInputFromInputMethod(mEdit.getWindowToken(), 0);
            }

            @Override
            public void onOntherReplyClickLister(View view, int postion) {
                toName = "";
                mPosition = 0;
                mEdit.setHint("评论攒好运");
            }
        });


    }

    private void findId() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_comment);
        tv_send = (TextView) mView.findViewById(R.id.free_comment_send);
        mEdit = (EditText) mView.findViewById(R.id.free_comment_edit);
    }

    private void initData() {
//        fcbs = new ArrayList<>();
//        for (int i = 0; i < 15; i++) {
//            ArrayList<ReplyBean> mReplyBean = new ArrayList<>();
//            for (int j = 0; j < 2; j++) {
//                ReplyBean rb = new ReplyBean("199****2222", "1111", "评论的真好");
//                mReplyBean.add(rb);
//            }
//            FreeCommentBean fcb = new FreeCommentBean("186****1121", "", "2016-10-08 17:55:55", "这个真的好", mReplyBean, 2);
//            fcbs.add(fcb);
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.free_comment_send:
                if (mEdit.getText().length() >= 10) {
                    if (toName == null || toName.equals("")) {
                        fcbs.add(0, new FreeCommentBean(myName, "", "2016-10-20  15:05:20", mEdit.getText().toString(), new ArrayList<ReplyBean>(), 0));
                    } else {
                        if (fcbs.get(mPosition).getReplyBeans() == null || fcbs.get(mPosition).getReplyBeans().size() == 0) {
                            ArrayList<ReplyBean> rbs = new ArrayList<>();
                            fcbs.get(mPosition).setReplyBeans(rbs);
                        }
                        fcbs.get(mPosition).getReplyBeans().add(new ReplyBean(myName, "", mEdit.getText().toString()));
                    }
                    mEdit.setText("");
                    imm.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "评论字数不能小于10", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void closeDialog() {

    }

    @Override
    public void loadDataSuccess(ArrayList<FreeCommentBean> data) {
        this.fcbs = data;
        initView();
    }

    @Override
    public void loadDataFailed(String error) {

    }
}
