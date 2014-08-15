package com.yourstory.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.yourstory.MainActivity;
import com.yourstory.R;
import com.yourstory.adapter.FeedAdapter;
import com.yourstory.app.YourStoryApplication;
import com.yourstory.model.RowItem;

public class PlaceholderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private static final String TAG = PlaceholderFragment.class.getSimpleName();
	/**
	 * Returns a new instance of this fragment for the given section number.
	 */

	FeedAdapter adapter;
	List<RowItem> rowItems;

	LinearLayout pagerLayout;
	ViewPager pager;
	private static final String url = "http://api.androidhive.info/json/movies.json";

	ArrayList<String> titles = new ArrayList<String>();
	ArrayList<String> images = new ArrayList<String>();
	ArrayList<String> authors = new ArrayList<String>();
	ArrayList<String> permalink = new ArrayList<String>();
	ArrayList<String> content = new ArrayList<String>();

	public static PlaceholderFragment newInstance(int sectionNumber) {
		PlaceholderFragment fragment = new PlaceholderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public PlaceholderFragment() {
	}

	View rootView;

	ListView mainFeedListView;
	
	ProgressBar mProgressBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_main, container, false);
		rowItems = new ArrayList<RowItem>();
		mainFeedListView = (ListView) rootView.findViewById(R.id.listView1);
		mProgressBar=(ProgressBar)rootView.findViewById(R.id.progressBarOnMainFeedPage);
		
		adapter = new FeedAdapter(getActivity(),
				R.layout.main_list_view_row_content_view, rowItems);
		mainFeedListView.setAdapter(adapter);
		

		return rootView;
	}


	private void onfetchFeed() {
		
			JsonArrayRequest movieReq = new JsonArrayRequest(
					url,
					new Response.Listener<JSONArray>() {
						@Override
						public void onResponse(JSONArray response) {

							try {
								VolleyLog.d(TAG, "Sucess: " + response.toString());
								System.out.println("the response"+ response);
								titles.clear();
								images.clear();
								authors.clear();
								permalink.clear();
								content.clear();
								for (int i = 0; i < response.length(); i++) {
									JSONObject jsonObject = response
											.getJSONObject(i);

									titles.add(jsonObject.getString("title"));
									images.add(jsonObject
											.getString("image"));
//									authors.add(jsonObject.getString("author"));
//									permalink.add(jsonObject
//											.getString("permalink"));
				//					content.add(jsonObject.getString("content"));
									RowItem item = new RowItem(jsonObject
											.getString("title"), jsonObject
											.getString("image"));
									rowItems.add(item);
								}
							} catch (JSONException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							// notifying list adapter about data changes
							// so that it renders the list view with updated
							// data
							adapter.notifyDataSetChanged();
							mProgressBar.setVisibility(View.GONE);
						}
					}, new Response.ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError error) {
							VolleyLog.d(TAG, "Error: " + error.getMessage());

						}
					});

			// Adding request to request queue
			YourStoryApplication.getmInstaceOfYourStoryApplication()
					.addToRequestQueue(movieReq);
			
		
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
		onfetchFeed();
		System.out.println("On attach called ");
	}
}
