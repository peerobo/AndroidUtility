package com.fc;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

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
		fs.put(FCAndroidUtilityBackKeyFunction.NAME, new FCAndroidUtilityBackKeyFunction());
		fs.put(FCAndroidUtilityVersionFunction.NAME, new FCAndroidUtilityVersionFunction());
		return fs;
	}

}
