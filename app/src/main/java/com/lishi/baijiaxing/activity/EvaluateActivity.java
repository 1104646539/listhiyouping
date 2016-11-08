package com.lishi.baijiaxing.activity;

import android.os.Bundle;
import android.widget.RatingBar;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.shoppingCart.model.StoreBean;

/**
 * 评论
 */
public class EvaluateActivity extends BaseActivity {
    private StoreBean mStoreBean;
    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        findId();
        initView();
    }

    private void findId() {
        mRatingBar = (RatingBar) findViewById(R.id.ratingBar_evaluate1);
    }

    private void initView() {
        mStoreBean = getIntent().getParcelableExtra("data");
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                System.out.println(rating + "___________");
            }
        });
    }
}
