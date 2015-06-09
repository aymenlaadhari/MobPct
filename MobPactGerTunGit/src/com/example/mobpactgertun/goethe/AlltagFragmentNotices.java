package com.example.mobpactgertun.goethe;

import com.example.mobpactgertun.R;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AlltagFragmentNotices extends Fragment{
	
	public AlltagFragmentNotices(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_alltag_notices, container, false);

       
         
        return rootView;
    }
	
	public static AlltagFragmentNotices newInstance(String text) {

		AlltagFragmentNotices f = new AlltagFragmentNotices();
		Bundle b = new Bundle();
		b.putString("msg", text);
		f.setArguments(b);

		return f;
	}
	
}