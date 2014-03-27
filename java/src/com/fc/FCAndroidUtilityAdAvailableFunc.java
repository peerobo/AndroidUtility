package com.fc;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.adobe.fre.FREWrongThreadException;
import com.vungle.sdk.VunglePub;

public class FCAndroidUtilityAdAvailableFunc implements FREFunction {

	public static final String NAME = "isVideoAdAvailable";
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		// TODO Auto-generated method stub
		FREObject retVal = null;
		try {
			retVal = FREObject.newObject(VunglePub.isVideoAvailable());
		} catch (FREWrongThreadException e) {
			// TODO Auto-generated catch block
			Log.w("VideoAdAvailable error: ", e.getMessage());
		}
		return retVal;
	}

}
