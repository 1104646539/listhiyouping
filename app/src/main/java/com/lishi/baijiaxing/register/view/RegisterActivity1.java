package com.lishi.baijiaxing.register.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.register.RegisterManger;
import com.lishi.baijiaxing.view.TopNavigationBar;

/**
 * 注册第1步，填手机号
 */
public class RegisterActivity1 extends BaseActivity implements View.OnClickListener {
    private TextView tv_register1_next;
    private EditText ed_phoneNumber;
    private TopNavigationBar mTopNavigationBar;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterManger.getInstantion().addActivity(this);

        findId();
        initView();
    }

    private void initView() {
        tv_register1_next.setOnClickListener(this);

        mTopNavigationBar.setOnTopClick(new TopNavigationBar.OnTopClick() {
            @Override
            public void onTopLeftClick(View view) {
                finish();
            }

            @Override
            public void onTopRightClick(View view) {

            }
        });
    }

    private void findId() {
        tv_register1_next = (TextView) findViewById(R.id.tv_register1_next);
        ed_phoneNumber = (EditText) findViewById(R.id.edit_code_register1);
        mTopNavigationBar = (TopNavigationBar) findViewById(R.id.navigation_register1);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox_register1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register1_next:

                if (ed_phoneNumber.getText().toString().length() > 0 && mCheckBox.isChecked()) {
                    double phoneNumber = Double.valueOf(ed_phoneNumber.getText().toString());
                    Intent startRegisterActivity1 = new Intent(this, RegisterActivity2.class);
                    startRegisterActivity1.putExtra("number", phoneNumber);
                    startActivity(startRegisterActivity1);
                }
                break;
        }
    }
}
