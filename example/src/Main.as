package 
{
	import com.fc.FCAndroidUtility;	
	import flash.desktop.NativeApplication;
	import flash.display.Sprite;
	import flash.display.StageAlign;
	import flash.display.StageDisplayState;
	import flash.display.StageScaleMode;
	import flash.events.Event;
	import flash.events.GeolocationEvent;
	import flash.events.KeyboardEvent;
	import flash.events.StatusEvent;
	import flash.geom.Rectangle;
	import flash.media.StageWebView;
	import flash.net.URLLoader;
	import flash.net.URLRequest;
	import flash.sensors.Geolocation;
	import flash.text.TextField;
	import flash.ui.Keyboard;
	import flash.ui.Multitouch;
	import flash.ui.MultitouchInputMode;
	import flash.utils.setTimeout;
	import starling.core.Starling;
	
	/**
	 * ...
	 * @author ndp
	 */
	public class Main extends Sprite 
	{
		private static var txt:TextField;
		private var starling:Starling;		
		private var geo:Geolocation;
		private var isInit:Boolean;
		
		public function Main():void 
		{
			isInit = false;
			stage.scaleMode = StageScaleMode.NO_SCALE;
			stage.align = StageAlign.TOP_LEFT;			
			//NativeApplication.nativeApplication.addEventListener(Event.DEACTIVATE, deactivate);
			NativeApplication.nativeApplication.addEventListener(Event.ACTIVATE, activate);
			stage.displayState = StageDisplayState.FULL_SCREEN_INTERACTIVE;			
			// touch or gesture?
			Multitouch.inputMode = MultitouchInputMode.TOUCH_POINT;			
			
			// new to AIR? please read *carefully* the readme.txt files!		
			
			txt = new TextField();
			txt.mouseEnabled = false;
			txt.text = "";
			addChild(txt);
			txt.multiline = true;
			txt.wordWrap = true;
			txt.width = stage.fullScreenWidth;
			txt.height = stage.fullScreenHeight;			
		}
	
		private function activate(e:Event):void 
		{
			// entry point
			log("activate");
			CONFIG::isAndroid {
				NativeApplication.nativeApplication.addEventListener(KeyboardEvent.KEY_DOWN, onAppKeyPressed);				
				if(!FCAndroidUtility.instance.isInit)
				{
					FCAndroidUtility.instance.init(true);					
					FCAndroidUtility.instance.addEventListener(FCAndroidUtility.ACHIEVEMENT_WRONG, onGPResponse);
					FCAndroidUtility.instance.addEventListener(FCAndroidUtility.ACHIVEMENT_WND_SHOWN, onGPResponse);
					FCAndroidUtility.instance.addEventListener(FCAndroidUtility.LEADERBOARD_WND_SHOWN, onGPResponse);
					FCAndroidUtility.instance.addEventListener(FCAndroidUtility.LICENSE_ERROR, onGPResponse);
					FCAndroidUtility.instance.addEventListener(FCAndroidUtility.NETWORK_ERROR, onGPResponse);
					FCAndroidUtility.instance.addEventListener(FCAndroidUtility.NOT_SIGN_IN, onGPResponse);
					FCAndroidUtility.instance.addEventListener(FCAndroidUtility.SERVICE_ERROR, onGPResponse);
					FCAndroidUtility.instance.addEventListener(FCAndroidUtility.SIGN_IN_FAILED, onGPResponse);
					FCAndroidUtility.instance.addEventListener(FCAndroidUtility.SIGN_IN_OK, onGPResponse);
					stage.addEventListener(Event.RESIZE, onResize);
				}								
				FCAndroidUtility.instance.setImmersive(true);								
				
			}
			CONFIG::isIOS{
				startStarling();
			}
		}
		
		private function onGPResponse(e:Event):void 
		{
			log(e.type);
		}
		
		private function onAppKeyPressed(e:KeyboardEvent):void 
		{
			if (e.keyCode == Keyboard.BACK)
			{
				e.preventDefault();
				e.stopImmediatePropagation();
				onBackKeyPressed();				
			}
		}		
		
		private function onResize(e:Event):void 
		{
			stage.removeEventListener(Event.RESIZE, onResize);	
			
			log("add geo");
			if (Geolocation.isSupported) 
            { 
                geo = new Geolocation(); 
                if (!geo.muted) 
                { 
                    geo.addEventListener(GeolocationEvent.UPDATE, geoUpdateHandler); 
                } 
                geo.addEventListener(StatusEvent.STATUS, geoStatusHandler);
            } 
            else 
            { 
                log("Geolocation not supported"); 
            } 
			
		}
		
		 public function geoUpdateHandler(event:GeolocationEvent):void 
        { 
            log("latitude : " + event.latitude.toString()); 
            log("longitude : " + event.longitude.toString());
			geo.removeEventListener(GeolocationEvent.UPDATE, geoUpdateHandler);
			var urlRequest:URLRequest = new URLRequest("http://api.openweathermap.org/data/2.5/weather?lat=" + event.latitude.toString() + "&lon=" + event.longitude.toString());
			var urlLoader:URLLoader = new URLLoader(urlRequest);
			urlLoader.addEventListener(Event.COMPLETE, onComplete);
        } 
		
		private function onComplete(e:Event):void 
		{
			var str:String = (e.currentTarget as URLLoader).data;
			var weatherObj:Object = JSON.parse(str);
			switch(weatherObj.weather[0].main)
			{
				case "Rain":
				case "Thunderstorm":
				case "Drizzle":
				case "Extreme":
					
				break;
				case "Snow":
					
				break;
				default:
					
			}
		}
        
        public function geoStatusHandler(event:StatusEvent):void 
        { 
			log("geo state change");
            if (geo.muted)
                geo.removeEventListener(GeolocationEvent.UPDATE, geoUpdateHandler);
            else
                geo.addEventListener(GeolocationEvent.UPDATE, geoStatusHandler);
        }
		
		private function startStarling():void
		{		
			log("add geo");
			if (Geolocation.isSupported) 
            { 
                geo = new Geolocation(); 
                if (!geo.muted) 
                { 
                    geo.addEventListener(GeolocationEvent.UPDATE, geoUpdateHandler); 
                } 
                geo.addEventListener(StatusEvent.STATUS, geoStatusHandler);
            } 
            else 
            { 
                log("Geolocation not supported"); 
            } 
		}
		
		private function onBackKeyPressed():void 
		{
			txt.appendText("\nback key pressed");
			FCAndroidUtility.instance.gpShowLeaderboard();
		}
		
		public static function log(...args):void
		{
			txt.appendText("\n"+args.join(" "));
		}
	}
	
}