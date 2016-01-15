package com.example.mobpactgertun.goethe;

import java.util.ArrayList;

import com.example.mobpactgertun.R;

import adapter.GridviewAdapter;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;



public class GoetheFragment extends Fragment {
	private static final int RightToLeft = 1;
	private static final int LeftToRight = 2;
	private static final int DURATION = 5000;

	private ValueAnimator mCurrentAnimator;
	private final Matrix mMatrix = new Matrix();
	private ImageView mImageView;
	private float mScaleFactor;
	private int mDirection = RightToLeft;
	private RectF mDisplayRect = new RectF();
	private GridviewAdapter mAdapter;
	private ArrayList<String> listCountry;
	private ArrayList<Integer> listFlag;

	private GridView gridView;

	public GoetheFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_goethe, container,
				false);

		// mImageView = (ImageView) rootView.findViewById(R.id.imageView1);
		//
		// mImageView.post(new Runnable() {
		// @Override
		// public void run() {
		// mScaleFactor = (float) mImageView.getHeight()
		// / (float) mImageView.getDrawable().getIntrinsicHeight();
		// mMatrix.postScale(mScaleFactor, mScaleFactor);
		// mImageView.setImageMatrix(mMatrix);
		// animate();
		// }
		// });

		prepareList();

		// prepared arraylist and passed it to the Adapter class
		mAdapter = new GridviewAdapter(getActivity(), listCountry, listFlag);

		// Set custom adapter to gridview
		gridView = (GridView) rootView.findViewById(R.id.gridView1);
		gridView.setAdapter(mAdapter);

		// Implement On Item click listener
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				
				 switch (position) {
				case 0:
					Intent intentVideoAlltag = new Intent(getActivity(),VideoBeruf.class);
					intentVideoAlltag.putExtra("intent", "Alltag");
					startActivity(intentVideoAlltag);					
					break;
					case 1:
						Intent intentVideoBeruf = new Intent(getActivity(),VideoBeruf.class);
						intentVideoBeruf.putExtra("intent", "Beruf");
						startActivity(intentVideoBeruf);
						break;
					case 2 :
						Intent intentVideoMagazine = new Intent(getActivity(),VideoBeruf.class);
						intentVideoMagazine.putExtra("intent", "Magazine");
						startActivity(intentVideoMagazine);
						
						break;
				case 3:
					Intent intentDic = new Intent(getActivity(), Dictionnaire.class);
					startActivity(intentDic);
					
					break;

				default:
					break;
				}
				 
			}
		});
		return rootView;
	}
	
	public void prepareList() {
		listCountry = new ArrayList<String>();

		listCountry.add("Alltag");
		listCountry.add("Beruf");
		listCountry.add("Magazine");
		listCountry.add("Wörterbuch");

		listFlag = new ArrayList<Integer>();

		listFlag.add(R.drawable.ic_supervisor_account_white_48dp);
		listFlag.add(R.drawable.ic_work_white_48dp);
		listFlag.add(R.drawable.ic_assignment_returned_white_48dp);
		listFlag.add(R.drawable.ic_language_white_48dp);

	}

	private void animate() {
		updateDisplayRect();
		if (mDirection == RightToLeft) {
			animate(mDisplayRect.left, mDisplayRect.left
					- (mDisplayRect.right - mImageView.getWidth()));
		} else {
			animate(mDisplayRect.left, 0.0f);
		}
	}

	private void animate(float from, float to) {
		mCurrentAnimator = ValueAnimator.ofFloat(from, to);
		mCurrentAnimator
				.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						float value = (Float) animation.getAnimatedValue();

						mMatrix.reset();
						mMatrix.postScale(mScaleFactor, mScaleFactor);
						mMatrix.postTranslate(value, 0);

						mImageView.setImageMatrix(mMatrix);

					}
				});
		mCurrentAnimator.setDuration(DURATION);
		mCurrentAnimator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				if (mDirection == RightToLeft)
					mDirection = LeftToRight;
				else
					mDirection = RightToLeft;

				animate();
			}
		});
		mCurrentAnimator.start();
	}

	private void updateDisplayRect() {
		mDisplayRect.set(0, 0, mImageView.getDrawable().getIntrinsicWidth(),
				mImageView.getDrawable().getIntrinsicHeight());
		mMatrix.mapRect(mDisplayRect);
	}

	

	

}
