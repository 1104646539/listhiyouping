
package com.lishi.baijiaxing.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

public class BeanActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		InitView();
	}

	private void InitView() {
		// 取消标题
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	@Override
	protected void onResume() {
		super.onResume();
		//强制竖屏
		if (getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}
	
}
