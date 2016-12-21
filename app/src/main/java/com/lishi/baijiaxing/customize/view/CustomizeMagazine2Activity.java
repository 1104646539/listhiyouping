package com.lishi.baijiaxing.customize.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.customize.adapter.Magazine2Adapter;
import com.lishi.baijiaxing.utils.CallPhone;
import com.lishi.baijiaxing.view.TopNavigationBar;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.List;

/**
 * 杂志定制->定制杂志页面
 */
public class CustomizeMagazine2Activity extends BaseActivity implements Magazine2Adapter.OnMagazine2ClickListener {
    private RecyclerView mRecyclerView;
    private TopNavigationBar mTopNavigationBar;
    private int REQUEST_CODE = 1;
    private Magazine2Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_magazine2);
        init();


    }

    private void init() {
        findId();
        initView();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new Magazine2Adapter(this);
        mRecyclerView.setAdapter(adapter);

        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
        adapter.setOnMagazine2ClickListener(this);
    }

    private void findId() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_magazine2);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_customize_magazine2);
    }

    @Override
    public void onUpload() {
        startPhotoSelectActivity();
    }

    @Override
    public void onSubmit() {

    }

    @Override
    public void onService() {
        CallPhone.dialPhoneNumber(this,CallPhone.SERVICE);
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

//                .backResId(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha)

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

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            String path = pathList.get(0);
            adapter.setUploadPath(path);
            adapter.notifyItemChanged(2);
        }

    }
}
