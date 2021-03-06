package com.mygame.pure.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.ab.util.AbSharedUtil;
import com.mygame.pure.R;
import com.mygame.pure.utils.Constants;

public class SplashScreenActivity extends Activity {
	private ImageView ivSplash;
	private final String mPageName = "SplashScreenActivity";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.splashscreen_activity);

		ivSplash = (ImageView) findViewById(R.id.iv_splash);

		AlphaAnimation alphaAnimation = new AlphaAnimation((float) 0.1, 1);
		alphaAnimation.setDuration(1000);
		alphaAnimation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if (AbSharedUtil.getBoolean(SplashScreenActivity.this,
						Constants.FIRST_START, true)) {
					AbSharedUtil.putBoolean(SplashScreenActivity.this,
							Constants.FIRST_START, false);
					Intent intent = new Intent(SplashScreenActivity.this,
							FirstbootPageActivity.class);
					startActivity(intent); //
				} else {
					AbSharedUtil.putBoolean(SplashScreenActivity.this,
							Constants.FIRST_START, false);
					Intent intent = new Intent(SplashScreenActivity.this,
							ActMain.class);
					startActivity(intent); //
				}
				// startActivityByKey(IntentAction.ACTION_HOME);
				finish();

			}
		});
		ivSplash.setAnimation(alphaAnimation);
		ivSplash.setVisibility(View.VISIBLE);
	}

}
