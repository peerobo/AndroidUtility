package com.fc.ext.function;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.fc.FCAndroidUtilityHelper;

public class FCAndroidUtilitySetScoreFunc implements FREFunction {
	public static final String NAME="setScore";
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		String id = "";	
		int score = 0;
		try {
			id = arg1[0].getAsString();
			score = arg1[1].getAsInt();
		} catch (Exception e) {
			Log.w("GooglePlay", "Error set score:" + e.getMessage());
		}
		if(FCAndroidUtilityHelper.enableGooglePlay)
			FCAndroidUtilityHelper.act.reportScore(id, score);
		return null;
	}

}
