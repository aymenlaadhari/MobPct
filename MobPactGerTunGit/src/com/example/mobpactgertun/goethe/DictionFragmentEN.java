package com.example.mobpactgertun.goethe;



import com.example.mobpactgertun.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class DictionFragmentEN extends Fragment{
	
	public DictionFragmentEN(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        View rootView = inflater.inflate(R.layout.fragment_diction_en, container, false);

        Toast.makeText(getActivity(), "Fragment EN", Toast.LENGTH_SHORT).show();
		return rootView;
	}
	
	public static DictionFragmentEN newInstance(String text) {

		DictionFragmentEN f = new DictionFragmentEN();
		Bundle b = new Bundle();
		b.putString("msg", text);
		f.setArguments(b);

		return f;
	}

}

