package com.fc;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.fc.ext.function.FCAndroidUtilityGPSigninFunc;
import com.fc.ext.function.FCAndroidUtilityGPSignoutFunc;
import com.fc.ext.function.FCAndroidUtilityImmersiveFunc;
import com.fc.ext.function.FCAndroidUtilityInitFunc;
import com.fc.ext.function.FCAndroidUtilitySetScoreFunc;
import com.fc.ext.function.FCAndroidUtilityShowAchievementsFunc;
import com.fc.ext.function.FCAndroidUtilityShowLeaderboardsFunc;
import com.fc.ext.function.FCAndroidUtilityUnlockAchievementFunc;
import com.fc.ext.function.FCAndroidUtilityVersionFunction;

public class FCAndroidUtilityContext extends FREContext {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		// TODO Auto-generated method stub
		Map<String,FREFunction> fs = new HashMap<String,FREFunction>();
		fs.put(FCAndroidUtilityImmersiveFunc.NAME, new FCAndroidUtilityImmersiveFunc());
		fs.put(FCAndroidUtilityInitFunc.NAME, new FCAndroidUtilityInitFunc());		
		fs.put(FCAndroidUtilityVersionFunction.NAME, new FCAndroidUtilityVersionFunction());
		fs.put(FCAndroidUtilityGPSigninFunc.NAME, new FCAndroidUtilityGPSigninFunc());
		fs.put(FCAndroidUtilityGPSignoutFunc.NAME, new FCAndroidUtilityGPSignoutFunc());
		fs.put(FCAndroidUtilitySetScoreFunc.NAME, new FCAndroidUtilitySetScoreFunc());
		fs.put(FCAndroidUtilityShowAchievementsFunc.NAME, new FCAndroidUtilityShowAchievementsFunc());
		fs.put(FCAndroidUtilityShowLeaderboardsFunc.NAME, new FCAndroidUtilityShowLeaderboardsFunc());
		fs.put(FCAndroidUtilityUnlockAchievementFunc.NAME, new FCAndroidUtilityUnlockAchievementFunc());
		return fs;
	}

}
