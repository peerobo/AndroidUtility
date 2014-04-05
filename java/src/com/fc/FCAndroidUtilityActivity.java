package com.fc;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;
import com.google.example.games.basegameutils.BaseGameActivity;

public class FCAndroidUtilityActivity extends BaseGameActivity {
	
	private static final int SHOW_ACHIEVEMENTS = 1;
	private static final int SHOW_LEADERBOARDS = 2;
	private static final int REPORT_SCORE = 3;
	private static final int REPORT_ACHIEVEMENT = 4;
	
	private static int followAction;
	private static String idArg;
	private static int scoreArg;

	@Override
	protected void onCreate(Bundle b) {
		// TODO Auto-generated method stub
		super.onCreate(b);		
		enableDebugLog(true);
		if(followAction < 1)
			followAction = -1;
		FCAndroidUtilityHelper.act = this;
		setRequestedClients(BaseGameActivity.CLIENT_GAMES);		
	}
	
	private void resumeToRoot(){
		Intent intent = FCAndroidUtilityHelper.rootAct.getIntent();
		intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}
	
	private void resumeToGP(int action){
		followAction = action;
		Intent intent = FCAndroidUtilityHelper.act.getIntent();
		intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		startActivity(intent);
	}

	@Override
	public void onSignInFailed() {
		// TODO Auto-generated method stub		
		FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "signinFailed");		
	}

	@Override
	public void onSignInSucceeded() {
		// TODO Auto-generated method stub
		FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "signinOK");
		if(followAction == -1)
		{
			resumeToRoot();
		}
		else
		{
			if(followAction == SHOW_ACHIEVEMENTS)
			{				
				Intent intent = Games.Achievements.getAchievementsIntent(getApiClient());
				startActivityForResult(intent,SHOW_ACHIEVEMENTS);							
			}
			else if(followAction == SHOW_LEADERBOARDS)
			{
				Intent intent = Games.Leaderboards.getAllLeaderboardsIntent(getApiClient());
				startActivityForResult(intent, SHOW_LEADERBOARDS);
			}
			else if(followAction == REPORT_SCORE)
			{
				doReportScore();
				idArg = null;
				scoreArg = 0;
			}
			else if(followAction == REPORT_ACHIEVEMENT)
			{
				doUnlockAchievement();
				idArg = null;				
			}
			followAction = -1;
		}
	}
	
	public void doUnlockAchievement(){
		PendingResult<UpdateAchievementResult> res = Games.Achievements.unlockImmediate(getApiClient(),idArg);		
		res.setResultCallback(new ResultCallback<UpdateAchievementResult>(){
			
		        @Override
		        public void onResult(UpdateAchievementResult result) {
		           int status = result.getStatus().getStatusCode();
		           switch(status)
		           {
		           		case GamesStatusCodes.STATUS_NETWORK_ERROR_NO_DATA:
		           		case GamesStatusCodes.STATUS_NETWORK_ERROR_STALE_DATA:
		           		case GamesStatusCodes.STATUS_CLIENT_RECONNECT_REQUIRED :
		           			FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "networkError");
		           			break;
		           		case GamesStatusCodes.STATUS_LICENSE_CHECK_FAILED:
		           			FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "licenseError");
		           			break;
		           		case GamesStatusCodes.STATUS_ACHIEVEMENT_UNKNOWN:
		           		case GamesStatusCodes.STATUS_ACHIEVEMENT_NOT_INCREMENTAL:
		           			FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "achievementWrong");
		           			break;
		           		case GamesStatusCodes.STATUS_ACHIEVEMENT_UNLOCK_FAILURE:
		           		case GamesStatusCodes.STATUS_INTERNAL_ERROR:
		           			FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "serviceError");
		           			break;
		           }
		           resumeToRoot();
		        }

		});
	}

	public void unlockAchievement(String id){
		idArg = id;
		resumeToGP(REPORT_ACHIEVEMENT);		
	}
	
	public void doReportScore(){
		PendingResult<SubmitScoreResult> res = Games.Leaderboards.submitScoreImmediate(getApiClient(), idArg, scoreArg);
		res.setResultCallback(new ResultCallback<SubmitScoreResult>(){
			
		        @Override
		        public void onResult(SubmitScoreResult result) {
		           int status = result.getStatus().getStatusCode();
		           switch(status)
		           {
		           		case GamesStatusCodes.STATUS_CLIENT_RECONNECT_REQUIRED :			           		
		           			FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "networkError");
		           			break;
		           		case GamesStatusCodes.STATUS_LICENSE_CHECK_FAILED:
		           			FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "licenseError");
		           			break;
		           		case GamesStatusCodes.STATUS_INTERNAL_ERROR:
		           			FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "serviceError");
		           			break;			           			
		           }
		           resumeToRoot();		           
		        }

		});
	}
	
	public void reportScore(String id, int score){
		idArg = id;
		scoreArg = score;
		resumeToGP(REPORT_SCORE);
	}
	
	public void login(){		
		beginUserInitiatedSignIn();
	}
	
	public void logout(){
		signOut();
	}
	
	public void showAchivements(){
		resumeToGP(SHOW_ACHIEVEMENTS);		
	}
	
	public void showLeaderboard(){
		resumeToGP(SHOW_LEADERBOARDS);		
	}
	
	@Override
	protected void onActivityResult(int request, int response, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(request, response, data);
		if(request == SHOW_ACHIEVEMENTS)
			FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "achievementsShown");
		else if(request == SHOW_LEADERBOARDS)
			FCAndroidUtilityHelper.context.dispatchStatusEventAsync("GooglePlay", "leaderboardsShown");
		resumeToRoot();
	}
}
