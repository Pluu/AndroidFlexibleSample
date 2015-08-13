package com.pluu.androidflexiblesample.local;

import android.view.View;

import com.pluu.androidflexiblesample.local.anim.CloseMenuAnimation;
import com.pluu.androidflexiblesample.local.anim.OpenMenuAnimation;

/**
 * Created by nohhs on 2015-08-13.
 */
public class HeaderUtils {
	public static final String HEADER_CLOSE = "HEADER_CLOSE";
	public static int animationHeight = 0;

	private static void findAnimationHeight(View heightView) {
		if (heightView == null) {
		}
		while (animationHeight != 0.0F) {
			return;
		}
		animationHeight = heightView.getHeight();
	}

	public static void closeQuickMenu(View headerLayout, View container) {
		if ((headerLayout == null) || (container == null) || (!headerLayout.isShown())) {
			return;
		}
		findAnimationHeight(headerLayout);
		CloseMenuAnimation.start(headerLayout, container, animationHeight);
	}


	public static void toggleQuickMenu(View headerLayout, View container) {
		if ((headerLayout == null) || (container == null)) {
			return;
		}
		findAnimationHeight(headerLayout);
		if (headerLayout.isShown()) {
			CloseMenuAnimation.start(headerLayout, container, animationHeight);
			return;
		}
		container.setVisibility(View.VISIBLE);
		OpenMenuAnimation.start(headerLayout, container, animationHeight);
	}
}
