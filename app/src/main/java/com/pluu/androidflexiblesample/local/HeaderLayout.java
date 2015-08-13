package com.pluu.androidflexiblesample.local;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.OnClick;
import com.pluu.androidflexiblesample.R;

/**
 * Created by nohhs on 2015-08-13.
 */
public class HeaderLayout extends LinearLayout {

	public HeaderLayout(Context context) {
		super(context);
		init(context);
	}

	public HeaderLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	@TargetApi(21)
	public HeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	@TargetApi(21)
	public HeaderLayout(Context context, AttributeSet attrs, int defStyleAttr,
						int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init(context);
	}

	private void init(Context context) {
		View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
			.inflate(R.layout.layout_header, this, true);
		ButterKnife.bind(view);
	}

	@OnClick({R.id.header1, R.id.header2, R.id.header3, R.id.header4, R.id.header5})
	public void onHeaderClick() {
		LocalBroadcastor
			.newInstance(getContext())
			.send();
	}
}
