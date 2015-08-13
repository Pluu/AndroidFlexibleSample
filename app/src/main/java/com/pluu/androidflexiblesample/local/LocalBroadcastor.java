package com.pluu.androidflexiblesample.local;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by nohhs on 2015-08-13.
 */
public class LocalBroadcastor {
	public static final String ACTION = "com.pluu.LOCAL_BROADCAST_ACTION";

	private Context context;
	private Intent intent;

	private LocalBroadcastor(Context context) {
		this.context = context;
		this.intent = new Intent();
		this.intent.setAction(ACTION);
	}

	public static LocalBroadcastor newInstance(Context context) {
		return new LocalBroadcastor(context);
	}

	public LocalBroadcastor put(String paramString, int paramInt) {
		if (this.intent == null) {
			return this;
		}
		this.intent.putExtra(paramString, paramInt);
		return this;
	}

	public LocalBroadcastor put(String paramString1, String paramString2) {
		if (this.intent == null) {
			return this;
		}
		this.intent.putExtra(paramString1, paramString2);
		return this;
	}

	public LocalBroadcastor put(String paramString, boolean paramBoolean) {
		if (this.intent == null) {
			return this;
		}
		this.intent.putExtra(paramString, paramBoolean);
		return this;
	}

	public LocalBroadcastor put(String paramString, String[] paramArrayOfString) {
		if (this.intent == null) {
			return this;
		}
		this.intent.putExtra(paramString, paramArrayOfString);
		return this;
	}

	public void send()
	{
		if ((this.context == null)
			|| (this.intent == null)
			|| ((this.context != null) && ((this.context instanceof Activity)) && (((Activity)this.context).isFinishing()))) {
			this.context = null;
			this.intent = null;
			return;
		}
		LocalBroadcastManager.getInstance(this.context).sendBroadcast(this.intent);
		this.context = null;
		this.intent = null;
	}
}
