package com.example.mobpactgertun;

import util.Config;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class HomeFragment extends YouTubePlayerFragment implements
YouTubePlayer.OnInitializedListener {
	private YouTubePlayerView youTubeView;
	private static final int RECOVERY_DIALOG_REQUEST = 1;
	private View rootView ;
	
	
	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
//		getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
//	       getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
 
       rootView = inflater.inflate(R.layout.fragment_home, container, false);
        
        youTubeView = (YouTubePlayerView) rootView.findViewById(R.id.youtube_view);
        // Initializing video player with developer key
        youTubeView.initialize(Config.DEVELOPER_KEY, this);
         
        return rootView;
    }

	@Override
	public void onInitializationFailure(Provider arg0,
			 YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(getActivity(), RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
        }
		
	}

	@Override
	public void onInitializationSuccess(Provider arg0,  YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
 
            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            player.loadVideo(Config.YOUTUBE_VIDEO_CODE);
 
            // Hiding player controls
            player.setPlayerStyle(PlayerStyle.CHROMELESS);
        }
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
	}


	private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) rootView.findViewById(R.id.youtube_view);
    }


}
