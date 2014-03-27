package com.fc;

import com.adobe.fre.FREContext;
import com.vungle.sdk.VunglePub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class FCMainActivity extends Activity {
	static public FCMainActivity self;
	static public FREContext context;
	public static String adId;
	
	public boolean needHandleBackKey;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().addFlags(
	        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
	        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
		self = this;
		needHandleBackKey = false;
		
		if(!adId.equals(""))
		{
			VunglePub.init(this, adId);
			VunglePub.setEventListener(new VunglePub.EventListener() {
			    /**
			     * Called when an ad starts.
			     */
			    @Override
			    public void onVungleAdStart() {
			        Log.i("Vungle", "ad start");
			        context.dispatchStatusEventAsync("adVideo", "start");
			    }
			 
			    /**
			     * Called when the user exits ad unit completely (usually the post-roll).
			     */
			    @Override
			    public void onVungleAdEnd() {
			        Log.i("Vungle", "user exited ad");
			        context.dispatchStatusEventAsync("adVideo", "notdone");
			    }
			 
			    /**
			     * Called when the user exits the ad unit completely - but only if the user 
			     * watched at least some portion of the ad.
			     * 
			     * @param watchedSeconds the number of seconds of video that were watched.
			     * @param totalAdSeconds the total length of the ad in seconds.
			     */
			    @Override
			    public void onVungleView(double watchedSeconds, double totalAdSeconds) {
			        final double watchedPercent = watchedSeconds / totalAdSeconds;
			        if (watchedPercent >= 0.9) {
			            Log.i("Vungle", "completed view");
			            context.dispatchStatusEventAsync("adVideo", "done");
			        }
			    }
			});
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		context.dispatchStatusEventAsync("stop", "ok");
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		context.dispatchStatusEventAsync("init", "ok");
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		context.dispatchStatusEventAsync("resume", "ok");
		if(!adId.equals(""))
			VunglePub.onResume();
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		context.dispatchStatusEventAsync("restart", "ok");
		if(!adId.equals(""))
			VunglePub.onPause();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		context.dispatchStatusEventAsync("pause", "ok");
	}
	
	@Override
	public void onBackPressed() {
		if(needHandleBackKey)
		{
			context.dispatchStatusEventAsync("backkey", "ok");
		}
		else
		{
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
		return;
	}
}
