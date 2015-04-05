package com.imooc.music.ui;

import android.app.Activity;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.imooc.music.R;

public class MainActivity extends Activity implements OnClickListener {

	// 唱片相关动画

	private Animation mPanAnim;
	private LinearInterpolator mPanLin;

	private Animation mBarInAnim;
	private LinearInterpolator mBarInLin;

	private Animation mBarOutAnim;
	private LinearInterpolator mBarOutLin;

	// 播放按钮的触发
	private ImageButton mPlayButton;
	private ImageView mPlayBar;
	private ImageView mDiskLight;
	// 是否在播放状态
	private boolean isPlaying = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPanAnim = AnimationUtils.loadAnimation(this, R.anim.rotate);
		mPanLin = new LinearInterpolator();
		mPanAnim.setInterpolator(mPanLin);
		mPanAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mPlayBar.startAnimation(mBarOutAnim);
			}
		});

		mBarInAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_45);
		mBarInLin = new LinearInterpolator();
		mBarInAnim.setInterpolator(mBarInLin);
		mBarInAnim.setFillAfter(true);
		mBarInAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				mDiskLight.startAnimation(mPanAnim);
			}
		});

		mBarOutAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_d_45);
		mBarOutLin = new LinearInterpolator();
		mBarOutAnim.setInterpolator(mBarOutLin);
		mBarOutAnim.setFillAfter(true);
		mBarOutAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				isPlaying = false;
				mPlayButton.setVisibility(View.VISIBLE);
			}
		});

		mPlayButton = (ImageButton) findViewById(R.id.ibt_play);
		mPlayButton.setOnClickListener(this);

		mPlayBar = (ImageView) findViewById(R.id.iv_playbar);
		mDiskLight = (ImageView) findViewById(R.id.iv_disk_light);
	}

	@Override
	public void onClick(View v) {
		handlePlayButton();
	}

	// 动画启动
	private void handlePlayButton() {
		if (mPlayButton != null) {
			if (!isPlaying) {
				isPlaying = true;
				mPlayButton.setVisibility(View.INVISIBLE);
				mPlayBar.startAnimation(mBarInAnim);
			}
		}
	}
//	对于中断事件的处理
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mDiskLight.clearAnimation();
		super.onPause();
	}
}
