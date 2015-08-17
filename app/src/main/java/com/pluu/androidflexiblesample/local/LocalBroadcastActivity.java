package com.pluu.androidflexiblesample.local;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.androidflexiblesample.BaseActivity;
import com.pluu.androidflexiblesample.R;

public class LocalBroadcastActivity extends BaseActivity
	implements AdapterView.OnItemClickListener {

	@Bind(R.id.headerLayout)
	HeaderLayout headerLayout;
	@Bind(R.id.contentPanel)
	View contentPanel;
	@Bind(android.R.id.list)
	ListView listView;

	private BroadcastReceiver localBroadcastReceiver = getLocalBroadcastReceiver();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_local_broadcast);
		ButterKnife.bind(this);
		enableHoneAsUp();

		init();
	}

	private void init() {
		final int SIZE = 50;
		List<String> list = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; i++) {
			list.add("Item " + i);
		}

		listView.setAdapter(
			new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list));
		listView.setOnItemClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		LocalBroadcastManager
			.getInstance(this)
			.registerReceiver(localBroadcastReceiver,
							  new IntentFilter(LocalBroadcastor.ACTION));
	}

	@Override
	protected void onPause() {
		super.onPause();
		LocalBroadcastManager
			.getInstance(this)
			.unregisterReceiver(localBroadcastReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_local_broadcast, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (R.id.menuSetting == item.getItemId()) {
			HeaderUtils.toggleQuickMenu(headerLayout, contentPanel);
		}
		return super.onOptionsItemSelected(item);
	}

	private BroadcastReceiver getLocalBroadcastReceiver() {
		return new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				onLocalBroadcastReceive(context, intent);
			}
		};
	}

	private boolean onLocalBroadcastReceive(Context context, Intent intent) {
		if (TextUtils.equals(LocalBroadcastor.ACTION, intent.getAction())) {
			HeaderUtils.closeQuickMenu(headerLayout, contentPanel);
			Toast.makeText(this, "Menu Select", Toast.LENGTH_SHORT).show();
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (headerLayout.isShown()) {
			HeaderUtils.closeQuickMenu(headerLayout, contentPanel);
			return;
		}

		Toast.makeText(this, "Item Click=" + position, Toast.LENGTH_SHORT).show();
	}
}
