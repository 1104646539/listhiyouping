package com.lishi.baijiaxing.userManager.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.orderAddressManage.view.DeliveryAddressActivity;
import com.lishi.baijiaxing.orderAddressManage.view.EditAddressActivity;
import com.lishi.baijiaxing.retrievePassword.view.RetrieveActivity1;
import com.lishi.baijiaxing.userManager.adapter.UserManagerAdapter;
import com.lishi.baijiaxing.userManager.model.UserListBean;
import com.lishi.baijiaxing.utils.DividerItemDecoration;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;

/**
 * 帐号管理
 */
public class UserManagerActivity extends BaseActivity implements YiYuanHotAdapter.OnItemClickListener {

    private TopNavigationBar mTopNavigationBar;
    private RecyclerView mRecyclerView;
    private UserListBean mUserListBean;
    private UserManagerAdapter adapter;
    /**
     * 修改昵称
     */
    private static final int TYPE_EDIT_NICKNAME = 0;
    /**
     * 修改性别
     */
    private static final int TYPE_EDIT_SEX = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);
        init();


    }

    private void init() {
        findId();
        initData();

        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new UserManagerAdapter(this, mUserListBean);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void initData() {
        mUserListBean = new UserListBean("咸鱼这名也不给起", "", "咸鱼这名也不给起", "男");
    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_userManager);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_userManager);
    }

    @Override
    public void onClickListener(View view, int position) {
        switch (position) {
            case 0://头像
                break;
            case 1://会员名
                break;
            case 2://昵称
                Intent startEditNickNameActivity = new Intent(this, EditNickNameActivity.class);
                startEditNickNameActivity.putExtra("nickName", mUserListBean.getNickname());
                startActivityForResult(startEditNickNameActivity, TYPE_EDIT_NICKNAME);
                break;
            case 3://性别
                Intent startEditSexActivity = new Intent(this, EditSexActivity.class);
                startActivityForResult(startEditSexActivity, TYPE_EDIT_SEX);
                break;
            case 4://收货地址
                Intent startAddressActivity = new Intent(this, DeliveryAddressActivity.class);
                startActivity(startAddressActivity);
                break;
            case 5://修改密码
                Intent startEditPassWordActivity = new Intent(this, EditPasswordActivity.class);
                startActivity(startEditPassWordActivity);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == TYPE_EDIT_NICKNAME) {//修改昵称
                String nickName = data.getStringExtra("resultName");
                mUserListBean.setNickname(nickName);
                adapter.notifyItemChanged(2);
            } else if (requestCode == TYPE_EDIT_SEX) {//修改性别
                String sex = data.getStringExtra("resultSex");
                mUserListBean.setSex(sex);
                adapter.notifyItemChanged(3);
            }
        }
    }
}
