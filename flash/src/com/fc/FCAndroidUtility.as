package com.fc 
{
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * ...
	 * @author ndp
	 */
	public class FCAndroidUtility extends EventDispatcher 
	{
		private static var ins:FCAndroidUtility;
		public static function get instance():FCAndroidUtility
		{
			if (!ins)
				ins = new FCAndroidUtility(new Enforcer);
			return ins;
		}
		
		//private var extCxt:ExtensionContext;		
		private var _isHandleBackKey:Boolean;
		public var doneInit:Boolean;
		public var onInit:Function;
		public var onResume:Function;
		public var onPause:Function;
		public var onStop:Function;
		public var onRestart:Function;
		public var onBackKeyHandle:Function;
		public var onAdDone:Function;
		public var onAdNotDone:Function;
		public var onAdStart:Function;
		public function FCAndroidUtility(enf:Enforcer) 
		{
			super();
			//extCxt = ExtensionContext.createExtensionContext("com.fc.AndroidUtility", "");
			//extCxt.addEventListener(StatusEvent.STATUS, onNativeResponse);
			doneInit = false;
		}
		
		//private function onNativeResponse(e:StatusEvent):void 
		//{
			//switch(e.code)
			//{
				//case "init":
					//if (onInit is Function)
						//onInit();
					//doneInit = true;
				//break;
				//case "backkey":
					//if (onBackKeyHandle is Function)
						//onBackKeyHandle();
				//break;
				//case "resume":
					//if (onResume is Function)
						//onResume();
				//break;
				//case "pause":
					//if (onPause is Function)
						//onPause();
				//break;
				//case "stop":
					//if (onStop is Function)
						//onStop();
				//break;
				//case "restart":
					//if (onRestart is Function)
						//onRestart();
				//break;
				//case "adVideo":
					//switch(e.level)
					//{
						//case "done":
							//if (onAdDone is Function)
								//onAdDone();
						//break;
						//case "notdone":
							//if (onAdNotDone is Function)
								//onAdNotDone();
						//break;
						//case "start":
							//if (onAdStart is Function)
								//onAdStart();
						//break;
					//}
				//break;
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
		
		public function init(adID:String = ""):void
		{
			//extCxt.call("init", adID);			
			doneInit = false;
		}
		
		public function showVideoAd():Boolean
		{
			var retbool:Boolean = false;
			//retbool = extCxt.call("showVideoAd");	
			return retbool;
		}
		
		public function isVideoAdAvailable():Boolean
		{
			var retbool:Boolean = false;
			//retbool = extCxt.call("isVideoAdAvailable");	
			return retbool;
		}
		
		public function set isHandleBackKey(isHandleBackKey:Boolean):void
		{
			this._isHandleBackKey = isHandleBackKey;
			//extCxt.call("setBackKeyHandle", isHandleBackKey);
		}
		
		public function get isHandleBackKey():Boolean
		{
			return _isHandleBackKey;
		}
	}

}
class Enforcer {
	
}