package com.example.mobpactgertun.goethe;


import com.example.mobpactgertun.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class DictionFragmentFR extends Fragment{
	
	public DictionFragmentFR(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_diction_fr, container, false);

        Toast.makeText(getActivity(), "Fragment FR", Toast.LENGTH_SHORT).show();

		return rootView;
	}
	
	public static DictionFragmentFR newInstance(String text) {

		DictionFragmentFR f = new DictionFragmentFR();
		Bundle b = new Bundle();
		b.putString("msg", text);
		f.setArguments(b);

		return f;
	}

}
