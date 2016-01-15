package com.example.mobpactgertun.goethe;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import app.AppController;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mobpactgertun.R;


public class DictionFragmentDE extends Fragment{
	private ProgressDialog pDialog;
	private String jsonResponse;
	//private String apiDic = "https://glosbe.com/gapi/translate?from=pol&dest=eng&format=json&phrase=witaj&pretty=true";
	private String apiDic = "https://glosbe.com/gapi/translate?from=%1$s&dest=%2$s&format=json&phrase=%3$s&pretty=true";

	TextView textView;
	public DictionFragmentDE(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_diction_de, container, false);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Searching...");
        textView = (TextView) rootView.findViewById(R.id.textView1);

		makeGetObject("pol","eng","witaj");
        return rootView;
		
	}
	
	public static DictionFragmentDE newInstance(String text) {

		DictionFragmentDE f = new DictionFragmentDE();
		Bundle b = new Bundle();
		b.putString("msg", text);
		f.setArguments(b);

		return f;
	}
	
	/**
	 * Method to make json Array request for runners informations where json
	 * response starts wtih [
	 * */
	public void makeGetRunnerInfo() {
		showpDialog();

		JsonArrayRequest req = new JsonArrayRequest(apiDic,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						

						// Parsing json array response
						// loop through each json object
						jsonResponse = "";
//							for (int i = 0; i < response.length(); i++) {
//
//								JSONObject runner = (JSONObject) response
//										.get(i);
//
//								String FirstName = runner
//										.getString("FirstName");
//								String LastName = runner.getString("LastName");
//								String Birthday = runner.getString("Birthday");
//								String IsMale = runner.getString("IsMale");
//								String Height = runner.getString("Height");
//								String Weight = runner.getString("Weight");
//								String FacebookID = runner
//										.getString("FacebookID");
//
//								jsonResponse += "FirstName: " + FirstName
//										+ "\n\n";
//								jsonResponse += "LastName: " + LastName
//										+ "\n\n";
//								jsonResponse += "Birthday: " + Birthday
//										+ "\n\n";
//								jsonResponse += "Height: " + Height + "\n\n";
//								jsonResponse += "Weight: " + Weight + "\n\n";
//								jsonResponse += "FacebookID: " + FacebookID
//										+ "\n\n";
//								jsonResponse += "IsMale: " + IsMale + "\n\n\n";
//
//							}
						

						Toast.makeText(getActivity(),
								response.toString(),
								Toast.LENGTH_LONG).show();

						hidepDialog();
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						
						Toast.makeText(getActivity(),
								error.getMessage(), Toast.LENGTH_SHORT).show();
						hidepDialog();
					}
				});

		AppController.getInstance().addToRequestQueue(req);
	}
	
	
	/**
	 * Method to make json abject 
	 */
	public void makeGetObject(String from, String dest, String phrase)
	{
		String uri = String.format(apiDic, from, dest, phrase);
		pDialog.show();
		//showpDialog();
		JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET,
				uri, null,
                new Response.Listener<JSONObject>() {
 
                    @Override
                    public void onResponse(JSONObject response) {
                    	Toast.makeText(getActivity(),
								response.toString(),
								Toast.LENGTH_LONG).show();
                    	textView.setText(response.toString());
                    	
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
 
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       
                        // hide the progress dialog
                        pDialog.hide();
                    }
                });
 
// Adding request to request queue
		AppController.getInstance().addToRequestQueue(jsonObjReq);
	}
	private void showpDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hidepDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}

}
