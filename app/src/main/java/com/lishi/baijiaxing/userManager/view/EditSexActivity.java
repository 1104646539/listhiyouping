package com.lishi.baijiaxing.userManager.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;

public class EditSexActivity extends BaseActivity implements View.OnClickListener {
    private TextView editSexItem1;
    private TextView editSexItem2;
    private TextView editSexItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sex);

        init();
    }

    private void init() {
        findId();
        editSexItem1.setOnClickListener(this);
        editSexItem2.setOnClickListener(this);
        editSexItem3.setOnClickListener(this);
    }

    private void findId() {
        editSexItem1 = (TextView) findViewById(R.id.editSex_item1);
        editSexItem2 = (TextView) findViewById(R.id.editSex_item2);
        editSexItem3 = (TextView) findViewById(R.id.editSex_item3);
    }

    @Override
    public void onClick(View v) {
        Intent result = new Intent();
        switch (v.getId()) {
            case R.id.editSex_item1:
                result.putExtra("resultSex", "男");
                break;
            case R.id.editSex_item2:
                result.putExtra("resultSex", "女");
                break;
            case R.id.editSex_item3:
                result.putExtra("resultSex", "保密");
                break;
        }
        setResult(RESULT_OK, result);
        finish();
    }
}
