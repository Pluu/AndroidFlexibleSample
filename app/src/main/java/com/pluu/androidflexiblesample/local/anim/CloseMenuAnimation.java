package com.pluu.androidflexiblesample.local.anim;

import android.util.Log;
import android.view.View;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by PLUUSYSTEM-NEW on 2015-06-14.
 */
public class CloseMenuAnimation
	implements Animator.AnimatorListener {

	private final String TAG = CloseMenuAnimation.class.getSimpleName();

	private View mainLayout;
	private View subMenuLayout;

	public CloseMenuAnimation(View subMenuLayout, View container, int moveY) {
		if (container == null) {
			return;
		}

		this.subMenuLayout = subMenuLayout;
		this.mainLayout = container;

		ValueAnimator animator = ValueAnimator.ofInt(-moveY, 0);
		animator.addListener(this);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				if (mainLayout == null) {
					return;
				}

				mainLayout.scrollTo(0, (Integer) animation.getAnimatedValue());
			}
		});
		animator.start();

		Log.d(TAG, "Start Animation");
	}

	public static CloseMenuAnimation start(View subMenuLayout, View container, int moveY) {
		return new CloseMenuAnimation(subMenuLayout, container, moveY);
	}

	@Override
	public void onAnimationStart(Animator animation) {
		if (mainLayout == null) {
			return;
		}
		mainLayout.clearAnimation();
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		if (mainLayout != null) {
			mainLayout.clearAnimation();
			mainLayout = null;
		}
		subMenuLayout.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onAnimationCancel(Animator animation) {	}

	@Override
	public void onAnimationRepeat(Animator animation) {	}
}
