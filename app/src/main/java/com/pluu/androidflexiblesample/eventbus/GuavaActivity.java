package com.pluu.androidflexiblesample.eventbus;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.common.eventbus.Subscribe;
import com.pluu.androidflexiblesample.BaseActivity;
import com.pluu.androidflexiblesample.R;
import com.pluu.androidflexiblesample.eventbus.event.GuavaBusHolder;
import com.pluu.androidflexiblesample.eventbus.event.ResponseEvent;

public class GuavaActivity extends BaseActivity {

	@Bind(R.id.textView)
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guava);
		ButterKnife.bind(this);
		enableHoneAsUp();
	}

	@Override
	public void onResume() {
		super.onResume();
		GuavaBusHolder.get().register(this);
	}

	@Override
	public void onPause() {
		GuavaBusHolder.get().unregister(this);
		super.onPause();
	}

	@Subscribe
	public void responseNetwork(ResponseEvent result) {
		textView.setText(result.getObj());
	}

}
