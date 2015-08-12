package com.pluu.androidflexiblesample.eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.androidflexiblesample.R;
import com.pluu.androidflexiblesample.eventbus.event.OttoBusHolder;
import com.pluu.androidflexiblesample.eventbus.event.ResponseEvent;
import com.squareup.otto.Subscribe;

public class OttoActivity extends AppCompatActivity {

	@Bind(R.id.textView)
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_otto);
		ButterKnife.bind(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		OttoBusHolder.get().register(this);
	}

	@Override
	public void onPause() {
		OttoBusHolder.get().unregister(this);
		super.onPause();
	}

	@Subscribe
	public void responseNetwork(ResponseEvent result) {
		textView.setText(result.getObj());
	}

}
