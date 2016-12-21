package com.lishi.baijiaxing.customize.view;

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
import com.lishi.baijiaxing.customize.presenter.CustomizeCommentPresenterImpl;
import com.lishi.baijiaxing.free.adapter.FreeCommentAdapter;
import com.lishi.baijiaxing.free.model.FreeCommentBean;
import com.lishi.baijiaxing.free.model.ReplyBean;
import com.lishi.baijiaxing.utils.DividerItemDecoration;

import java.util.ArrayList;

/**个性定制评论
 * Created by Administrator on 2016/10/28.
 */
public class Fragment_CustomizeComment extends BaseFragmentV4 implements View.OnClickListener, CustomizeCommentView {
    private static Fragment_CustomizeComment mFragment_CustomizeComment;
    private View mView;
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
    private CustomizeCommentPresenterImpl mCustomizeCommentPresenter;

    public static Fragment_CustomizeComment newInstance() {
        if (mFragment_CustomizeComment == null) {
            mFragment_CustomizeComment = new Fragment_CustomizeComment();
        }
        return mFragment_CustomizeComment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_customize_comment, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepare = true;
        lazyLoad();
    }

    @Override
    public void onInvisible() {

    }

    @Override
    public void lazyLoad() {
        if (!isPrepare || !isVisible) {
            return;
        }
        if (mCustomizeCommentPresenter == null) {
            mCustomizeCommentPresenter = new CustomizeCommentPresenterImpl(this);
        }
        mCustomizeCommentPresenter.loadData();
    }

    private void initView() {
        findId();
        initData();

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
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView_customize_comment);
        tv_send = (TextView) mView.findViewById(R.id.customize_comment_send);
        mEdit = (EditText) mView.findViewById(R.id.customize_comment_edit);
    }

    private void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customize_comment_send:
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
        fcbs = data;
        initView();
    }

    @Override
    public void loadDataFailed(String error) {

    }
}
