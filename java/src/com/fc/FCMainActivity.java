package com.fc;

import com.adobe.fre.FREContext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class FCMainActivity extends Activity {
	static public FCMainActivity self;
	static public FREContext context;
	
	public boolean needHandleBackKey;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().addFlags(
	        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
	        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
		self = this;
		needHandleBackKey = false;
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		context.dispatchStatusEventAsync("stop", "ok");
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		context.dispatchStatusEventAsync("init", "ok");
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		context.dispatchStatusEventAsync("resume", "ok");
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		context.dispatchStatusEventAsync("restart", "ok");
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		context.dispatchStatusEventAsync("pause", "ok");
	}
	
	@Override
	public void onBackPressed() {
		if(needHandleBackKey)
		{
			context.dispatchStatusEventAsync("backkey", "ok");
		}
		else
		{
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
		return;
	}
}
