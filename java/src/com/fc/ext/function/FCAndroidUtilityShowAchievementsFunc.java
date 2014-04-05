package com.fc.ext.function;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.fc.FCAndroidUtilityHelper;

public class FCAndroidUtilityShowAchievementsFunc implements FREFunction {
	public static final String NAME="showAchievements";
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		if(FCAndroidUtilityHelper.enableGooglePlay)
			FCAndroidUtilityHelper.act.showAchivements();
		return null;
	}

}
