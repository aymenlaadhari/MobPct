package com.example.mobpactgertun;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import model.FeedItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import adapter.FeedListAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import app.AppController;

import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;


public class WhatsHotFragment extends Fragment {
	private static final String TAG = WhatsHotFragment.class.getSimpleName();
	private ListView listView;
	private FeedListAdapter listAdapter;
	private List<FeedItem> feedItems;
	//private String URL_FEED = "http://api.androidhive.info/feed/feed.json";
      private String URL_FEED1 = "http://192.168.1.101:8083/MobPact/ServiceWeb/Feeds/getfeeds";
     
	
	public WhatsHotFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_whats_hot, container, false);
        listView = (ListView) rootView.findViewById(R.id.list);
        feedItems = new ArrayList<FeedItem>();
        listAdapter = new FeedListAdapter(getActivity(), feedItems);
		listView.setAdapter(listAdapter);
		
		// We first check for cached request
				Cache cache = AppController.getInstance().getRequestQueue().getCache();
				Entry entry = cache.get(URL_FEED1);
				if (entry != null) {
					// fetch the data from cache
					try {
						String data = new String(entry.data, "UTF-8");
						try {
							
							parseJsonFeed(new JSONObject(data));
						} catch (JSONException e) {
							e.printStackTrace();
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

				} else {
					// making fresh volley request and getting json
					JsonObjectRequest jsonReq = new JsonObjectRequest(Method.GET,
							URL_FEED1, null, new Response.Listener<JSONObject>() {

								@Override
								public void onResponse(JSONObject response) {
									VolleyLog.d(TAG, "Response: " + response.toString());
									if (response != null) {
										parseJsonFeed(response);
									}
								}
							}, new Response.ErrorListener() {

								@Override
								public void onErrorResponse(VolleyError error) {
									VolleyLog.d(TAG, "Error: " + error.getMessage());
									Toast.makeText(getActivity(), "No data", Toast.LENGTH_LONG).show();
									
								}
							});

					// Adding request to volley request queue
					AppController.getInstance().addToRequestQueue(jsonReq);
				}
         
        return rootView;
    }
	
	/**
	 * Parsing json reponse and passing the data to feed view list adapter
	 * */
	private void parseJsonFeed(JSONObject response) {
		try {
			//JSONArray feedArray = response.getJSONArray("feed");
			JSONArray feedArray = response.getJSONArray("feeds");

			for (int i = 0; i < feedArray.length(); i++) {
				JSONObject feedObj = (JSONObject) feedArray.get(i);

				FeedItem item = new FeedItem();
				item.setId(feedObj.getInt("id"));
				item.setName(feedObj.getString("name"));

				// Image might be null sometimes
				String image = feedObj.isNull("image") ? null : feedObj
						.getString("image");
				
				item.setImge(image);
				item.setStatus(feedObj.getString("status"));
				
				//item.setProfilePic(feedObj.getString("profilePic"));
				item.setProfilePic(feedObj.getString("profilepic"));
				//item.setTimeStamp(feedObj.getString("timeStamp"));
				item.setTimeStamp(feedObj.getString("timestamp"));

				// url might be null sometimes
				String feedUrl = feedObj.isNull("url") ? null : feedObj
						.getString("url");
				item.setUrl(feedUrl);

				feedItems.add(item);
			}

			// notify data changes to list adapater
			listAdapter.notifyDataSetChanged();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
