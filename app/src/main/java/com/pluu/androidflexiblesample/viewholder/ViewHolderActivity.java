package com.pluu.androidflexiblesample.viewholder;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.pluu.androidflexiblesample.BaseActivity;
import com.pluu.androidflexiblesample.R;

public class ViewHolderActivity extends BaseActivity {

	@Bind(R.id.listView)
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_holder);
		ButterKnife.bind(this);
		enableHoneAsUp();

		final int SIZE = 50;
		List<ViewHolderItem> list = new ArrayList<>(SIZE);
		ViewHolderItem item;

		Random r = new Random(System.currentTimeMillis());

		for (int i = 0; i < SIZE; i++) {
			item = new ViewHolderItem();
			item.title = "Title" + i;
			item.title2 = "Content" + i;
			item.filterColor = Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			list.add(item);
		}

		listView.setAdapter(new SampleAdapter(list));
	}
}
