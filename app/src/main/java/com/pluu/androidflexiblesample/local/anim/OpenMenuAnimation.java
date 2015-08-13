package com.pluu.androidflexiblesample.local.anim;

import android.util.Log;
import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by PLUUSYSTEM-NEW on 2015-06-14.
 */
public class OpenMenuAnimation
	implements Animator.AnimatorListener {

	private final String TAG = OpenMenuAnimation.class.getSimpleName();
	private View subMenuLayout, mainLayout;
	private int y;

	public OpenMenuAnimation(View subMenuLayout, final View container, int moveY) {
		if (container == null) {
			return;
		}

		this.subMenuLayout = subMenuLayout;
		this.mainLayout = container;

		ValueAnimator animator = ValueAnimator.ofInt(0, moveY);
		animator.addListener(this);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				if (container == null) {
					return;
				}

				container.scrollTo(0, -((Integer) animation.getAnimatedValue()));
			}
		});

		animator.start();
		Log.d(TAG, "Start Animation");
	}

	public static OpenMenuAnimation start(View subMenuLayout,
										  View container,
										  int moveY) {
		return new OpenMenuAnimation(subMenuLayout, container, moveY);
	}

	@Override
	public void onAnimationStart(Animator animation) {
		if (mainLayout == null) {
			return;
		}
		mainLayout.clearAnimation();
		subMenuLayout.setVisibility(View.VISIBLE);
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		if (mainLayout == null) {
			return;
		}
		mainLayout.clearAnimation();
		mainLayout.requestLayout();
		mainLayout = null;
	}

	@Override
	public void onAnimationCancel(Animator animation) {	}

	@Override
	public void onAnimationRepeat(Animator animation) {	}
}
