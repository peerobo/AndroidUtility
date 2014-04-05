package com.fc.ext.function;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.fc.FCAndroidUtilityActivity;
import com.fc.FCAndroidUtilityHelper;

public class FCAndroidUtilityInitFunc implements FREFunction {
	public static final String NAME = "init";

	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		Log.w("FCAndroidUtility", "start init extension");		
		Activity appAct = arg0.getActivity();
		FCAndroidUtilityHelper.rootAct = appAct; 
		boolean state = false;
		try {
			state = arg1[0].getAsBool();
		} catch (Exception e) {
			Log.w("FCAndroidUtility init", "Error:" + e.getMessage());
		}
		FCAndroidUtilityHelper.enableGooglePlay = state;
		FCAndroidUtilityHelper.context = arg0;
		if (FCAndroidUtilityHelper.act == null) {
			if (state) {
				Intent intent = new Intent(appAct,
						FCAndroidUtilityActivity.class);				
				appAct.startActivity(intent);
			}
		} else {
			if (FCAndroidUtilityHelper.enableGooglePlay) {
				Intent intent = FCAndroidUtilityHelper.act.getIntent();
				FCAndroidUtilityHelper.act.finish();
				FCAndroidUtilityHelper.act.startActivity(intent);
			}
		}
		return null;
	}

}
