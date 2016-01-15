package com.example.mobpactgertun.communities;



import java.util.ArrayList;
import java.util.List;

import com.example.mobpactgertun.FragmentPlace;
import com.example.mobpactgertun.R;
import com.example.mobpactgertun.R.drawable;
import com.example.mobpactgertun.R.id;
import com.example.mobpactgertun.R.layout;

import model.CommunityItems;

import adapter.CommunityListAdapter;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


public class CommunityFragment extends Fragment implements OnItemClickListener{
	
	private String nameList[] = { "Hamburg", "Berlin", "München","Köln"};
	private String createdList[] = { "Hamburg", "Berlin", "München","Köln"};
	private int imageList[] = { R.drawable.hamburg, R.drawable.berlin, R.drawable.munich, R.drawable.koln};
	private ListView listView;
	private List<CommunityItems> communityItems;
	private CommunityListAdapter listAdapter;
	public CommunityFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
        listView = (ListView) rootView.findViewById(R.id.listViewcommunity);
        communityItems = new ArrayList<CommunityItems>();
        listAdapter = new CommunityListAdapter(getActivity(), communityItems); 
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);
        
        init();
        return rootView;
    }

	private void init()
	{
		for(int i = 0; i< nameList.length;i++)
		{
			CommunityItems communityItem = new CommunityItems();
			communityItem.setName(nameList[i]);
			communityItem.setCreated(createdList[i]);
			communityItem.setImage(imageList[i]);
			this.communityItems.add(communityItem);
		}
		
		listAdapter.notifyDataSetChanged();
	}
	
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
	// TODO Auto-generated method stub
		//Fragment fragment = new FragmentPlace();
		Fragment mapFragment = MapFrag.newInstance("mapMain");
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.frame_container, mapFragment).commit();
		
	
		//Toast.makeText(getActivity(), communityItems.get(position).getName()+"--coming soon", Toast.LENGTH_SHORT).show();
	}

}
