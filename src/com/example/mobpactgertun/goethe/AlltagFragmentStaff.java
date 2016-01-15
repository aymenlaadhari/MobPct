package com.example.mobpactgertun.goethe;

import com.example.mobpactgertun.R;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AlltagFragmentStaff extends Fragment{
	
	public AlltagFragmentStaff(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_alltag_staff, container, false);

       
         
        return rootView;
    }
	
	public static AlltagFragmentStaff newInstance(String text) {

		AlltagFragmentStaff f = new AlltagFragmentStaff();
		Bundle b = new Bundle();
		b.putString("msg", text);
		f.setArguments(b);

		return f;
	}
}
