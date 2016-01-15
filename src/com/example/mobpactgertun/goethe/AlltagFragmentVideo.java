package com.example.mobpactgertun.goethe;


import com.example.mobpactgertun.R;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AlltagFragmentVideo extends Fragment{
	
	public AlltagFragmentVideo(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_alltag_video, container, false);

       
         
        return rootView;
    }
	
	public static AlltagFragmentVideo newInstance(String text) {

		AlltagFragmentVideo f = new AlltagFragmentVideo();
		Bundle b = new Bundle();
		b.putString("msg", text);
		f.setArguments(b);

		return f;
	}
	
	
}


