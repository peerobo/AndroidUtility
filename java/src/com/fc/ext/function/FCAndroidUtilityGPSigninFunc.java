package com.fc.ext.function;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.fc.FCAndroidUtilityHelper;

public class FCAndroidUtilityGPSigninFunc implements FREFunction {
	public static final String NAME="gpSignin";
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		if(FCAndroidUtilityHelper.enableGooglePlay)
			FCAndroidUtilityHelper.act.login();
		return null;
	}

}
