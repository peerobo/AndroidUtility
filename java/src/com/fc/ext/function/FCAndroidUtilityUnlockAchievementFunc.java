package com.fc.ext.function;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.fc.FCAndroidUtilityHelper;

public class FCAndroidUtilityUnlockAchievementFunc implements FREFunction{
	public static final String NAME="unlockAchievement";
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		String id = "";		
		try {
			id = arg1[0].getAsString();
		} catch (Exception e) {
			Log.w("GooglePlay", "Error unlock achievement:" + e.getMessage());
		}
		if(FCAndroidUtilityHelper.enableGooglePlay)
			FCAndroidUtilityHelper.act.unlockAchievement(id);
		return null;
	}

}
