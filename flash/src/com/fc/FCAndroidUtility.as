package com.fc 
{	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;	
	//import flash.events.StatusEvent;
	//import flash.external.ExtensionContext;
	
	/**
	 * ...
	 * @author ndp
	 */
	public class FCAndroidUtility extends EventDispatcher 
	{
		public static const SIGN_IN_OK:String = "SIGN_IN_OK";
		public static const SIGN_IN_FAILED:String = "SIGN_IN_FAILED";
		public static const NOT_SIGN_IN:String = "NOT_SIGN_IN";
		public static const NETWORK_ERROR:String = "NETWORK_ERROR";
		public static const LICENSE_ERROR:String = "LICENSE_ERROR";
		public static const ACHIEVEMENT_WRONG:String = "ACHIEVEMENT_WRONG";
		public static const SERVICE_ERROR:String = "SERVICE_ERROR";
		public static const ACHIVEMENT_WND_SHOWN:String = "ACHIVEMENT_WND_SHOWN";
		public static const LEADERBOARD_WND_SHOWN:String = "LEADERBOARD_WND_SHOWN";
		
		private static var ins:FCAndroidUtility;
		public static function get instance():FCAndroidUtility
		{
			if (!ins)
				ins = new FCAndroidUtility(new Enforcer);
			return ins;
		}
		public var isInit:Boolean;
		//private var extCxt:ExtensionContext;		
		public function FCAndroidUtility(enf:Enforcer) 
		{
			super();
			//extCxt = ExtensionContext.createExtensionContext("com.fc.AndroidUtility", "");
			//extCxt.addEventListener(StatusEvent.STATUS, onNativeResponse);
		}
		
		//private function onNativeResponse(e:StatusEvent):void 
		//{
			//if(e.code == "GooglePlay")
			//{
				//switch(e.level)
				//{
					//case "signinFailed":
						//dispatchEvent(new Event(SIGN_IN_FAILED));
					//break;
					//case "notSignin":
						//dispatchEvent(new Event(NOT_SIGN_IN));
					//break;
					//case "signinOK":
						//dispatchEvent(new Event(SIGN_IN_OK));
					//break;
					//case "networkError":
						//dispatchEvent(new Event(NETWORK_ERROR));
					//break;
					//case "licenseError":
						//dispatchEvent(new Event(LICENSE_ERROR));
					//break;
					//case "achievementWrong":
						//dispatchEvent(new Event(ACHIEVEMENT_WRONG));
					//break;
					//case "serviceError":
						//dispatchEvent(new Event(SERVICE_ERROR));
					//break;
					//case "achievementsShown":
						//dispatchEvent(new Event(ACHIVEMENT_WND_SHOWN));
					//break;
					//case "leaderboardsShown":
						//dispatchEvent(new Event(LEADERBOARD_WND_SHOWN));
					//break;
				//}
			//}
		//}
		
		public function setImmersive(state:Boolean):void
		{
			//extCxt.call("setImmersive", state);
		}
		
		public function getVersionInt():int
		{
			var id:int = 0;
			//id = extCxt.call("getVersionInt") as int;
			return id;
		}
		
		public function init(enableGooglePlay:Boolean):void
		{			
			//extCxt.call("init", enableGooglePlay);
			isInit = true;
		}
		
		public function gpLogin():void
		{
			//extCxt.call("gpSignin");
		}
		
		public function gpLogout():void
		{
			//extCxt.call("gpSignout");
		}
		
		public function gpUnlockAchievement(achi:String):void
		{
			//extCxt.call("unlockAchievement",achi);
		}
		
		public function gpSetScore(id:String, score:int):void
		{
			//extCxt.call("setScore", id, score);
		}
		
		public function gpShowAchievement():void
		{
			//extCxt.call("showAchievements");
		}
		
		public function gpShowLeaderboard():void
		{
			//extCxt.call("showLeaderboards");
		}		
		
	}

}
class Enforcer {
	
}