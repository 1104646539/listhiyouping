package com.lishi.baijiaxing.userManager.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 * 修改昵称
 */
public class EditNickNameActivity extends BaseActivity {
    private TopNavigationBar mTopNavigationBar;
    private EditText mEditText;
    private String nickName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nick_name);

        init();

        nickName = getIntent().getStringExtra("nickName");
        if (nickName != null) {
            mEditText.setText(nickName);
        }
    }

    private void init() {
        findId();
        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {
                nickName = mEditText.getText().toString();
                if (nickName.length() > 0 && nickName.length() <= 12) {
                    Intent finishResult = new Intent(EditNickNameActivity.this, UserManagerActivity.class);
                    finishResult.putExtra("resultName", nickName);
                    setResult(RESULT_OK, finishResult);
                }
                finish();
            }
        });
    }

    private void findId() {
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_editNickName);
        mEditText = (EditText) findViewById(R.id.editNickName_edit);
    }
}
