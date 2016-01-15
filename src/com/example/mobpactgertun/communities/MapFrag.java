package com.example.mobpactgertun.communities;

import java.util.List;

import util.GPSManager;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobpactgertun.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFrag extends Fragment {
	MapView mMapView;
	private GoogleMap googleMap;
	List<LatLng> latLngs;
	double lat[] = { 50.9421902, 51.7110616 };
	double longt[] = { 9.0881345, 11.7248532 };
	private GPSManager gpsManager = null;
	LocationListener mLocationListener;
	LocationManager locationManager;
	private String provider;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// inflate and return the layout
		View v = inflater.inflate(R.layout.fragment_map, container, false);
		mMapView = (MapView) v.findViewById(R.id.mapView);
		mMapView.onCreate(savedInstanceState);
		mLocationListener = new LocationListener() {
			@Override
			public void onLocationChanged(Location location) {
				// your code here
				location = ((LocationManager) mLocationListener)
						.getLastKnownLocation(provider);
				double lat = location.getLatitude();
				double lng = location.getLongitude();
				Log.e("I shouldn't be here" + String.valueOf(lat) + ","
						+ String.valueOf(lng), provider);
				Toast.makeText(getActivity(),
						Double.toString(location.getLatitude()),
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}
		};

		mMapView.onResume();// needed to get the map to display immediately

		try {
			MapsInitializer.initialize(getActivity().getApplicationContext());
		} catch (Exception e) {
			e.printStackTrace();
		}

		googleMap = mMapView.getMap();
		googleMap.setMyLocationEnabled(true);
		googleMap.setBuildingsEnabled(true);
		googleMap.setIndoorEnabled(true);

		/*
		 * Get location
		 */

		locationManager = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				400, 1, mLocationListener);

		// LocationManager locMan = (LocationManager)
		// getActivity().getSystemService(Context.LOCATION_SERVICE);
		// if (!locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
		// buildAlertMessageNoGps();
		// if (locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
		// Fragment mapFragment = MapFrag.newInstance("mapMain");
		// getActivity().getFragmentManager().beginTransaction()
		// .replace(R.id.frame_container, mapFragment).commit();
		//
		// }
		//
		// } else if (locMan.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
		// Criteria crit = new Criteria();
		//
		// Location loc = locMan.getLastKnownLocation(locMan.getBestProvider(
		// crit, false));
		//
		// if (loc != null) {
		// LatLng latLng = new LatLng(loc.getLatitude(),
		// loc.getLongitude());
		//
		// CameraPosition camPos = new CameraPosition.Builder()
		//
		// .target(new LatLng(loc.getLatitude(), loc.getLongitude()))
		//
		// .zoom(12.8f)
		//
		// .build();
		//
		// CameraUpdate camUpdate = CameraUpdateFactory
		// .newCameraPosition(camPos);
		//
		// googleMap.moveCamera(camUpdate);
		// googleMap.addMarker(new MarkerOptions().position(latLng).title(
		// "Hello"));
		//
		// }
		//
		// }
		//
		// gpsManager = new GPSManager();
		// gpsManager.startListening(getActivity().getApplicationContext());
		// gpsManager.setGPSCallback(this);
		// init();

		// // latitude and longitude
		// double latitude = 44.8531751;
		// double longitude = 5.825195;
		//
		//
		// // create marker
		// MarkerOptions marker = new MarkerOptions().position(
		// new LatLng(latitude, longitude)).title("Hello Maps");
		// latLngs = new ArrayList<LatLng>();
		//
		//
		// // Changing marker icon
		// marker.icon(BitmapDescriptorFactory
		// .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
		//
		// // adding marker
		// googleMap.addMarker(marker);
		// CameraPosition cameraPosition = new CameraPosition.Builder()
		// .target(new LatLng(latitude, longitude)).zoom(8).build();
		// googleMap.animateCamera(CameraUpdateFactory
		// .newCameraPosition(cameraPosition));
		// addMarkers();

		// Perform any camera updates here
		return v;
	}

	private void addMarkers() {

		if (googleMap != null) {
			for (int i = 0; i < 2; i++) {

				LatLng latLng = new LatLng(lat[i], longt[i]);
				googleMap.addMarker(new MarkerOptions().position(latLng));

			}

		}
	}

	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}

	public static MapFrag newInstance(String text) {
		MapFrag f = new MapFrag();
		Bundle b = new Bundle();
		b.putString("msg", text);
		f.setArguments(b);

		return f;
	}

	/*
	 * Alert user to enable GPS if disabled
	 */
	private void buildAlertMessageNoGps() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				getActivity());
		builder.setMessage(
				"Your GPS seems to be disabled, do you want to enable it?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(
									@SuppressWarnings("unused") final DialogInterface dialog,
									@SuppressWarnings("unused") final int id) {
								startActivity(new Intent(
										android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(final DialogInterface dialog,
							@SuppressWarnings("unused") final int id) {
						dialog.cancel();
					}
				});
		final AlertDialog alert = builder.create();
		alert.show();
	}

}
