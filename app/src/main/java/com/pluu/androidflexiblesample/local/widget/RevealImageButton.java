package com.pluu.androidflexiblesample.local.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageButton;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by nohhs on 2015-08-13.
 */
public class RevealImageButton extends ImageButton {

	public static final int STATE_NOT_STARTED = 0;
	public static final int STATE_FILL_STARTED = 1;
	public static final int STATE_FINISHED = 2;

	private static final Interpolator INTERPOLATOR = new AccelerateInterpolator();
	private static final int FILL_TIME = 750;

	private int state = STATE_NOT_STARTED;

	private Paint fillPaint;
	private int currentRadius;
	AnimatorSet revealAnimator;

	private int startLocationX;
	private int startLocationY;

	public RevealImageButton(Context context) {
		super(context);
		init();
	}

	public RevealImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RevealImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	@TargetApi(21)
	public RevealImageButton(Context context, AttributeSet attrs, int defStyleAttr,
							 int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	private void init() {
		fillPaint = new Paint();
		fillPaint.setStyle(Paint.Style.FILL);
		fillPaint.setColor(Color.WHITE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		if (state == STATE_FILL_STARTED) {
			canvas.drawCircle(startLocationX, startLocationY, currentRadius, fillPaint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (MotionEvent.ACTION_DOWN == MotionEventCompat.getActionMasked(event)) {
			changeState(STATE_FILL_STARTED);
			startLocationX = (int) event.getX();
			startLocationY = (int) event.getY();
			initAnimator();
			revealAnimator.start();
		}
		return super.onTouchEvent(event);
	}

	private void initAnimator() {
		if (revealAnimator == null) {
			revealAnimator = new AnimatorSet();
			revealAnimator.setDuration(FILL_TIME);
			revealAnimator.setInterpolator(INTERPOLATOR);
			revealAnimator.playTogether(
				ObjectAnimator.ofInt(this, "currentRadius", 0, getWidth() + getHeight()),
				ObjectAnimator.ofInt(fillPaint, "alpha", 100, 25));

			revealAnimator.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					changeState(STATE_FINISHED);
				}
			});
		}
	}

	private void changeState(int state) {
		if (this.state == state) {
			return;
		}

		this.state = state;
	}

	public void setCurrentRadius(int radius) {
		this.currentRadius = radius;
		invalidate();
	}
}
