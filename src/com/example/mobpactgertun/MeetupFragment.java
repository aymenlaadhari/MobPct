package com.example.mobpactgertun;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MeetupFragment extends Fragment {
	
	public MeetupFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_pages, container, false);
        Toast.makeText(getActivity(), "Coming soon", Toast.LENGTH_LONG).show();
         
        return rootView;
    }
}
