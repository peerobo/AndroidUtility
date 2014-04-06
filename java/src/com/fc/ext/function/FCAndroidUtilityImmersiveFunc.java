package com.fc.ext.function;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.fc.FCAndroidUtilityHelper;

public class FCAndroidUtilityImmersiveFunc implements FREFunction {

	public static final String NAME = "setImmersive";
	private Handler mHideHandler;
	Runnable mHideRunnable;
	private View view;
	
	@SuppressLint("NewApi")
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		// TODO Auto-generated method stub
		Activity appAct = arg0.getActivity();
		boolean state = false;
		if(view == null)
			view = appAct.getWindow().getDecorView();
		try {
			state = arg1[0].getAsBool();
		} catch (Exception e) {
			Log.w("FCAndroidUtility immersive error:", e.getMessage());
		}
		FCAndroidUtilityHelper.isImmersive = state;
		
		if(mHideHandler == null){
			mHideHandler = new Handler();
			mHideRunnable = new Runnable() {
				@Override	
				public void run() {	
					hideSystemUi();		
				}
			};
			
			view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
				@Override
				public void onSystemUiVisibilityChange(int visibility) {
					Log.w("Immersive", "system visibility " + String.valueOf(visibility) + ", immersive " + String.valueOf(FCAndroidUtilityHelper.isImmersive) );
					if (visibility == 0 && FCAndroidUtilityHelper.isImmersive) {
						mHideHandler.postDelayed(mHideRunnable, 2000);
					}
					
				}
			});
		}
		
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			if (state) {				
				hideSystemUi();
				
			} else {
				view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_FULLSCREEN);
			}
		}
		return null;
	}
	
	@SuppressLint("NewApi")
	private void hideSystemUi(){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}
	

}
