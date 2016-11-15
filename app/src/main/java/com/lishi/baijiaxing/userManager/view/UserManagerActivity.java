package com.lishi.baijiaxing.userManager.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.orderAddressManage.view.DeliveryAddressActivity;
import com.lishi.baijiaxing.userManager.adapter.UserManagerAdapter;
import com.lishi.baijiaxing.utils.DividerItemDecoration;
import com.lishi.baijiaxing.utils.LocalUserInfo;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.lishi.baijiaxing.yiyuan.adapter.YiYuanHotAdapter;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.List;

/**
 * 帐号管理
 */
public class UserManagerActivity extends BaseActivity implements YiYuanHotAdapter.OnItemClickListener {

    private TopNavigationBar mTopNavigationBar;
    private RecyclerView mRecyclerView;
    private UserManagerAdapter adapter;
    /**
     * 修改昵称
     */
    private static final int TYPE_EDIT_NICKNAME = 0;
    /**
     * 修改性别
     */
    private static final int TYPE_EDIT_SEX = 1;
    /**
     * 选择头像
     */
    private static final int REQUEST_CODE = 2;

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
        adapter = new UserManagerAdapter(this);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    private void initData() {
//        mUserListBean = new UserListBean("咸鱼这名也不给起", "", "咸鱼这名也不给起", "男");
    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_userManager);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_userManager);
    }

    @Override
    public void onClickListener(View view, int position) {
        switch (position) {
            case 0://头像
                startPhotoSelectActivity();
                break;
            case 1://会员名
                break;
            case 2://昵称
                Intent startEditNickNameActivity = new Intent(this, EditNickNameActivity.class);
                startEditNickNameActivity.putExtra("nickName", LocalUserInfo.getInstance().getNickName());
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
    // 自定义图片加载器

    private ImageLoader loader = new ImageLoader() {

        @Override

        public void displayImage(Context context, String path, ImageView imageView) {

            // TODO 在这边可以自定义图片加载库来加载ImageView，例如Glide、Picasso、ImageLoader等

            Glide.with(context).load(path).into(imageView);

        }

    };

    private void startPhotoSelectActivity() {


        ImgSelConfig config = new ImgSelConfig.Builder(loader)

                // 是否多选

                .multiSelect(false)

                // “确定”按钮背景色

                .btnBgColor(Color.GRAY)

                // “确定”按钮文字颜色

                .btnTextColor(Color.BLUE)

                // 使用沉浸式状态栏

                .statusBarColor(Color.parseColor("#3F51B5"))

                // 返回图标ResId

                .backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha)

                // 标题

                .title("图片")

                // 标题文字颜色

                .titleColor(Color.WHITE)

                // TitleBar背景色

                .titleBgColor(Color.parseColor("#3F51B5"))

                // 裁剪大小。needCrop为true的时候配置

                .cropSize(1, 1, 200, 200)

                .needCrop(false)

                // 第一个是否显示相机

                .needCamera(false)

                // 最大选择图片数量

                .maxNum(9)

                .build();


// 跳转到图片选择器

        ImgSelActivity.startActivity(this, config, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // 图片选择结果回调

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == TYPE_EDIT_NICKNAME) {//修改昵称
                String nickName = data.getStringExtra("resultName");
                LocalUserInfo.getInstance().setNickName(nickName);
                adapter.notifyItemChanged(2);

                LocalUserInfo.getInstance().setNickName(nickName);
            } else if (requestCode == TYPE_EDIT_SEX) {//修改性别
                String sex = data.getStringExtra("resultSex");
                LocalUserInfo.getInstance().setSex(sex);
                adapter.notifyItemChanged(3);
                LocalUserInfo.getInstance().setSex(sex);
            } else if (requestCode == REQUEST_CODE) {
                List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
                String path = pathList.get(0);
                LocalUserInfo.getInstance().setPhotoUrl(path);
                adapter.notifyDataSetChanged();
                Toast.makeText(UserManagerActivity.this, "" + path, Toast.LENGTH_SHORT).show();
                Log.i("onActivityResult", "选择头像=" + path);
                LocalUserInfo.getInstance().setPhotoUrl(path);
            }
        }

    }
}
