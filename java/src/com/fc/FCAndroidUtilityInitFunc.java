package com.fc;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class FCAndroidUtilityInitFunc implements FREFunction {
	public static final String NAME = "init";
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		Log.w("FCAndroidUtility", "start init extension");
		Activity mainActivity = arg0.getActivity();
		String adId = "";
		try{
			adId = arg1[0].getAsString();
			if(adId == null)
				adId = "";
		}
		catch(Exception e){
			Log.w("FCAndroidUtility:", e.getMessage());
			adId = "";
		}
		Log.w("FCAndroidUtility", "id ad " + adId);
		FCMainActivity.adId = adId;
		if(FCMainActivity.self == null)
		{
			Intent intent = new Intent(mainActivity,FCMainActivity.class);		
			FCMainActivity.context = arg0;
			mainActivity.startActivity(intent);
		}
		else
		{
			Intent intent = FCMainActivity.self.getIntent();
			FCMainActivity.self.finish();
			FCMainActivity.self.startActivity(intent);
		}
			
		return null;
	}

}
