package com.lishi.baijiaxing.userManager.view;

import android.media.ToneGenerator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 *修改密码
 */
public class EditPasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText ed_oldPassword, ed_newPassword, ed_confirmPassword;
    private TextView confirm;
    private TopNavigationBar mTopNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        init();


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
                finish();
            }
        });
        confirm.setOnClickListener(this);
    }

    private void findId() {
        ed_oldPassword = (EditText) findViewById(R.id.edit_password_oldPassword);
        ed_newPassword = (EditText) findViewById(R.id.edit_password_newPassword);
        ed_confirmPassword = (EditText) findViewById(R.id.edit_password_confirmPassword);
        confirm = (TextView) findViewById(R.id.edit_password_confirm);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.topbar_edit_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_password_confirm://确认
                finish();
                break;
        }
    }
}
