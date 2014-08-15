/**
 * 
 */
package com.yourstory.app;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.text.TextUtils;

/**
 * @author sujeetkumarmehta
 * 
 */
public class YourStoryApplication extends Application {

	private static YourStoryApplication mInstaceOfYourStoryApplication;
	
	private RequestQueue requestQueue;

	private  final static  String TAG=YourStoryApplication.class.getSimpleName();

	public YourStoryApplication() {
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mInstaceOfYourStoryApplication = this;
	}

	public static synchronized YourStoryApplication getmInstaceOfYourStoryApplication() {
		return mInstaceOfYourStoryApplication;
	}
	
	public RequestQueue getRequestQueue() {
		if (requestQueue==null) {
			requestQueue= Volley.newRequestQueue(mInstaceOfYourStoryApplication);
		}
		return requestQueue;
	}
	
	

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		// set the default tag if tag is empty
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (requestQueue != null) {
			requestQueue.cancelAll(tag);
		}
	}

}
