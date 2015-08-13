package com.pluu.androidflexiblesample.viewholder;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.pluu.androidflexiblesample.R;

/**
 * Created by nohhs on 2015-08-12.
 */
public class SampleAdapter extends BaseAdapter {

	private final List<ViewHolderItem> list;

	public SampleAdapter(List<ViewHolderItem> list) {this.list = list;}

	@Override
	public int getCount() {
		return list != null ? list.size() : 0;
	}

	@Override
	public ViewHolderItem getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater
				.from(parent.getContext())
				.inflate(R.layout.list_view_holder_item, parent, false);
		}

		ViewHolderItem item = getItem(position);

		TextView tv = ViewHolder.get(convertView, R.id.text);
		tv.setText(item.title);

		if (item.filterColor != -1) {
			ImageView img = ViewHolder.get(convertView, R.id.imageView);
			img.setColorFilter(item.filterColor);
		}

		tv = ViewHolder.get(convertView, R.id.text2);
		if (item.title2 != null) {
			tv.setText(item.title2);
		}
		tv.setVisibility(item.title2 != null ? View.VISIBLE : View.GONE);

		return convertView;
	}

	private static class ViewHolder {
		@SuppressWarnings("unchecked")
		public static <T extends View> T get(View view, int id) {
			SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
			if (viewHolder == null) {
				viewHolder = new SparseArray<>();
				view.setTag(viewHolder);
			}
			View childView = viewHolder.get(id);
			if (childView == null) {
				childView = view.findViewById(id);
				viewHolder.put(id, childView);
			}
			return (T) childView;
		}
	}
}
