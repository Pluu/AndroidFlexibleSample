package com.pluu.androidflexiblesample.eventbus.network;

import java.io.IOException;

import com.pluu.androidflexiblesample.eventbus.event.OttoBusHolder;
import com.pluu.androidflexiblesample.eventbus.event.ResponseEvent;
import com.pluu.androidflexiblesample.eventbus.event.ResponseFailEvent;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by nohhs on 2015-07-17.
 */
public class OttoTestNetwork implements Callback {

	private final OkHttpClient client = new OkHttpClient();

	public void callType1() {
		Request request = new Request.Builder()
			.url("http://www.google.com")
			.build();

		client.newCall(request).enqueue(this);
	}

	@Override
	public void onFailure(Request request, IOException e) {
		OttoBusHolder.get().post(new ResponseFailEvent(e));
	}

	@Override
	public void onResponse(Response response) throws IOException {
		OttoBusHolder.get().post(new ResponseEvent(response.body().string()));
	}
}
