package com.lishi.baijiaxing.activity;

import com.lishi.baijiaxing.R;
import com.lishi.baijiaxing.base.BaseActivity;
import com.lishi.baijiaxing.shoppingCart.view.Fragment_Cart;
import com.lishi.baijiaxing.classify.view.Fragment_Classify;
import com.lishi.baijiaxing.home.view.Fragment_Home;
import com.lishi.baijiaxing.personal.view.Fragment_Personal;
import com.lishi.baijiaxing.inter.ClassityChange;
import com.lishi.baijiaxing.utils.NetUtils;
import com.lishi.baijiaxing.utils.ShoppingBadgeUtil;
import com.lishi.baijiaxing.view.BadgeView;
import com.tencent.tauth.Tencent;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements OnClickListener, ClassityChange {
    // 导航控件
    private ImageView iv_home;
    private ImageView iv_classity;
    private ImageView iv_cart;
    private ImageView iv_my;
    private ImageView[] imageViews;
    public static BadgeView mBadgeView;
    private TextView tv_home, tv_classity, tv_cart, tv_my;
    private LinearLayout mLayout_main1, mLayout_main2, mLayout_main3, mLayout_main4;
    private TextView[] mTextViews;
    private int[] images_on = new int[]{R.drawable.navigation_home_clickble, R.drawable.navigation_classity_clickble,
            R.drawable.navigation_cart_clickble, R.drawable.navigation_my_clickble};
    private int[] images_gray_on = new int[]{R.drawable.navigation_home, R.drawable.navigation_classity,
            R.drawable.navigation_cart, R.drawable.navigation_my};
    private Fragment_Home fragment_Home;
    private Fragment_Classify fragment_Classify;
    private Fragment_Cart fragment_cart;
    private Fragment_Personal fragment_My;
    private Fragment[] mFragments = null;
    private int pagenum = 0;
    private HashMap<String, Fragment> mFragmentHashMap = new HashMap<String, Fragment>();
    private String[] mStrings = new String[]{"fragment_Home", "fragment_Classify", "fragment_cart", "fragment_My"};
    private int close = 0;
    public static Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!NetUtils.isConnected(this)) {
            Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
        }
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ShoppingBadgeUtil.getInstance().getBadgeCount() != 0) {
            MainActivity.mBadgeView.setBadgeCount(ShoppingBadgeUtil.getInstance().getBadgeCount());
        }
    }

    private void initView() {
        iv_home = (ImageView) findViewById(R.id.iv_main_navi1);
        iv_classity = (ImageView) findViewById(R.id.iv_main_navi2);
        iv_cart = (ImageView) findViewById(R.id.iv_main_navi3);
        iv_my = (ImageView) findViewById(R.id.iv_main_navi4);
        imageViews = new ImageView[]{iv_home, iv_classity, iv_cart, iv_my};
        mBadgeView = new BadgeView(this);
        mBadgeView.setTargetView(iv_cart);
        mBadgeView.setBadgeCount(ShoppingBadgeUtil.getInstance().getBadgeCount());

        mLayout_main1 = (LinearLayout) findViewById(R.id.ll_main_1);
        mLayout_main2 = (LinearLayout) findViewById(R.id.ll_main_2);
        mLayout_main3 = (LinearLayout) findViewById(R.id.ll_main_3);
        mLayout_main4 = (LinearLayout) findViewById(R.id.ll_main_4);

        mLayout_main1.setOnClickListener(this);
        mLayout_main2.setOnClickListener(this);
        mLayout_main3.setOnClickListener(this);
        mLayout_main4.setOnClickListener(this);

        tv_home = (TextView) findViewById(R.id.tv_main_home);
        tv_classity = (TextView) findViewById(R.id.tv_main_classity);
        tv_cart = (TextView) findViewById(R.id.tv_main_cart);
        tv_my = (TextView) findViewById(R.id.tv_main_my);
        mTextViews = new TextView[]{tv_home, tv_classity, tv_cart, tv_my};
        iv_home.setOnClickListener(this);
        iv_classity.setOnClickListener(this);
        iv_cart.setOnClickListener(this);
        iv_my.setOnClickListener(this);
        tv_home.setOnClickListener(this);
        tv_classity.setOnClickListener(this);
        tv_cart.setOnClickListener(this);
        tv_my.setOnClickListener(this);

        //默认显示的的Fragment
        fragment_Home = new Fragment_Home();
        getFragmentManager().beginTransaction().add(R.id.ll_main, fragment_Home).commit();
        mFragmentHashMap.put("fragment_Home", fragment_Home);
        changeimg(pagenum);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_main_navi1:
            case R.id.tv_main_home:
            case R.id.ll_main_1:
                if (pagenum != 0) {
                    if (fragment_Home == null) {
                        fragment_Home = new Fragment_Home();
                        mFragmentHashMap.put("fragment_Home", fragment_Home);
                    }
                    Fragment fragment = mFragmentHashMap.get(mStrings[pagenum]);
                    if (fragment == null) {
                        Log.i("assf", "from为空___________________");
                    } else if (fragment_Home == null) {
                        Log.i("assf", "to为空___________________");
                    }
                    switchFragment(fragment, fragment_Home);
                    pagenum = 0;
                }

                break;
            case R.id.iv_main_navi2:
            case R.id.tv_main_classity:
            case R.id.ll_main_2:
                if (pagenum != 1) {
                    if (fragment_Classify == null) {
                        fragment_Classify = new Fragment_Classify();
                        mFragmentHashMap.put("fragment_Classify", fragment_Classify);
                    }
                    Fragment fragment = mFragmentHashMap.get(mStrings[pagenum]);
                    if (fragment == null) {
                        Log.i("assf", "from为空___________________");
                    } else if (fragment_Classify == null) {
                        Log.i("assf", "to为空___________________");
                    }
                    switchFragment(fragment, fragment_Classify);
                    pagenum = 1;
                }

                break;
            case R.id.iv_main_navi3:
            case R.id.tv_main_cart:
            case R.id.ll_main_3:
                if (pagenum != 2) {
                    if (fragment_cart == null) {
                        fragment_cart = new Fragment_Cart();
                        mFragmentHashMap.put("fragment_cart", fragment_cart);
                    }
                    Fragment fragment = mFragmentHashMap.get(mStrings[pagenum]);
                    if (fragment == null) {
                        Log.i("assf", "from为空___________________");
                    } else if (fragment_cart == null) {
                        Log.i("assf", "to为空___________________");
                    }
                    switchFragment(fragment, fragment_cart);
                    pagenum = 2;
                }

                break;
            case R.id.iv_main_navi4:
            case R.id.tv_main_my:
            case R.id.ll_main_4:
                if (pagenum != 3) {
                    if (fragment_My == null) {
                        fragment_My = new Fragment_Personal();
                        mFragmentHashMap.put("fragment_My", fragment_My);
                    }
                    Fragment fragment = mFragmentHashMap.get(mStrings[pagenum]);
                    if (fragment == null) {
                        Log.i("assf", "from为空___________________");
                    } else if (fragment_My == null) {
                        Log.i("assf", "to为空___________________");
                    }
                    switchFragment(fragment, fragment_My);
                    pagenum = 3;
                }
                break;
        }
        changeimg(pagenum);
    }

    private void changeimg(int num) {
        int size = imageViews.length;
        for (int i = 0; i < size; i++) {
            if (num == i) {
                imageViews[i].setImageResource(images_on[i]);
                mTextViews[i].setTextColor(Color.rgb(221, 39, 38));
            } else {
                imageViews[i].setImageResource(images_gray_on[i]);
                mTextViews[i].setTextColor(Color.rgb(150, 150, 150));
            }

        }
    }

    @Override
    public void onActivityChange() {
        if (fragment_Classify != null) {
//            fragment_Classify.
        }
    }

    public void switchFragment(Fragment from, Fragment to) {
        if (from == null || to == null) {
            Log.i("asdf", "有一个为空__________");
            return;
        }
        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
        if (!to.isAdded()) {
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.hide(from).add(R.id.ll_main, to).commit();
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(from).show(to).commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragmentHashMap.clear();
        mFragmentHashMap = null;
        mTencent.logout(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            //3s内按两次back才退出应用，第一次时拦截
            case KeyEvent.KEYCODE_BACK:
                close++;
                Toast.makeText(this, "在按一次退出应用", Toast.LENGTH_SHORT).show();
                if (close <= 1) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 3; i++) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            close = 0;
                        }
                    }).start();
                    return false;
                } else if (close > 1) {
                    return super.onKeyDown(keyCode, event);
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
