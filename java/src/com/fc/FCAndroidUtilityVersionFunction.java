package com.fc;

import android.os.Build;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;

public class FCAndroidUtilityVersionFunction implements FREFunction {

	public static final String NAME = "getVersionInt";
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		// TODO Auto-generated method stub
		FREObject retObj = null;
		try {
			retObj = FREObject.newObject(Build.VERSION.SDK_INT);
		} catch (FREWrongThreadException e) {
			// TODO Auto-generated catch block
			Log.w("get Android version error", e.getMessage());
		}
		
		return retObj;
	}

}
