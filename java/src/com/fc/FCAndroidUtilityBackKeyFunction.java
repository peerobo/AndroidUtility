package com.fc;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class FCAndroidUtilityBackKeyFunction implements FREFunction {

	public static String NAME = "setBackKeyHandle";
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		boolean state = false;
		try {
			state = arg1[0].getAsBool();
		} catch (Exception e) {// TODO Auto-generated catch block
			Log.w("FCAndroidUtility immersive error:", e.getMessage());
		}
		FCMainActivity.self.needHandleBackKey = state;
		return null;
	}

}
