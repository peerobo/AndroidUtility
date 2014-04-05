package com.fc.ext.function;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;
import com.fc.FCAndroidUtilityHelper;

public class FCAndroidUtilityImmersiveFunc implements FREFunction {

	public static final String NAME = "setImmersive";

	@SuppressLint("NewApi")
	@Override
	public FREObject call(FREContext arg0, FREObject[] arg1) {
		// TODO Auto-generated method stub
		Activity appAct = arg0.getActivity();
		boolean state = false;
		final View view = appAct.getWindow().getDecorView();
		try {
			state = arg1[0].getAsBool();
		} catch (Exception e) {
			Log.w("FCAndroidUtility immersive error:", e.getMessage());
		}
		FCAndroidUtilityHelper.isImmersive = state;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			if (state) {
				view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
					@Override
					public void onSystemUiVisibilityChange(int visibility) {						
						if (visibility == 0 && FCAndroidUtilityHelper.isImmersive) {
							view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
									| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
									| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
									| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
									| View.SYSTEM_UI_FLAG_FULLSCREEN
									| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
						}
					}
				});
				view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
						| View.SYSTEM_UI_FLAG_FULLSCREEN
						| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
			} else {
				view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
						| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
						| View.SYSTEM_UI_FLAG_FULLSCREEN);
			}
		}
		return null;
	}

}
