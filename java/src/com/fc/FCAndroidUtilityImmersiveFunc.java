package com.fc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class FCAndroidUtilityImmersiveFunc implements FREFunction {

	public static final String NAME = "setImmersive";
	@SuppressLint("NewApi")
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		// TODO Auto-generated method stub
		Activity appAct = FCMainActivity.self;
		boolean state = false;
		try{
			state = arg1[0].getAsBool();
		}
		catch(Exception e){
			Log.w("FCAndroidUtility immersive error:", e.getMessage());
		}
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
		{
			if(state)
			{
				Log.w("FCAndroidUtility immersive view:", appAct.getWindow().getDecorView().toString());
				appAct.getWindow().getDecorView().setSystemUiVisibility(
		                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
		                | View.SYSTEM_UI_FLAG_FULLSCREEN
		                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
			}
			else
			{
				appAct.getWindow().getDecorView().setSystemUiVisibility(
		                View.SYSTEM_UI_FLAG_LAYOUT_STABLE	                
		                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		                | View.SYSTEM_UI_FLAG_FULLSCREEN);
			}
		}
		return null;
	}

}
