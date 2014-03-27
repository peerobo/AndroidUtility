package 
{
	import com.fc.FCAndroidUtility;
	import flash.desktop.NativeApplication;
	import flash.display.Sprite;
	import flash.display.StageAlign;
	import flash.display.StageDisplayState;
	import flash.display.StageScaleMode;
	import flash.events.Event;
	import flash.text.TextField;
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
		
		public function Main():void 
		{
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
			log(FCAndroidUtility.instance.getVersionInt());
		}
	
		private function activate(e:Event):void 
		{
			// entry point
			log("activate");
			CONFIG::isAndroid{
				FCAndroidUtility.instance.onInit = onInit;
				FCAndroidUtility.instance.init("com.fc.FastHandEnglish");
			}
			CONFIG::isIOS{
				startStarling();
			}
		}
		
		
		private function onInit():void 
		{								
			log("on init");
			FCAndroidUtility.instance.isHandleBackKey = true;
			FCAndroidUtility.instance.onBackKeyHandle = onBackKeyPressed;
			FCAndroidUtility.instance.setImmersive(true);	
			FCAndroidUtility.instance.onPause = onPause;
			FCAndroidUtility.instance.onResume = onResume;
			FCAndroidUtility.instance.onStop = onStop;	
			FCAndroidUtility.instance.onRestart = onRestart;
			stage.addEventListener(Event.RESIZE, onResize);
		}		
		
		private function onResize(e:Event):void 
		{
			stage.removeEventListener(Event.RESIZE, onResize);			
			showAd();
		}
		
		private function showAd():void 
		{
			if (FCAndroidUtility.instance.isVideoAdAvailable())
			{
				log("show ad");
				FCAndroidUtility.instance.showVideoAd();
			}
			else
			{
				log("wait ad");
				setTimeout(showAd, 5000);
			}
		}
		
		private function onRestart():void 
		{
			log("restart");
		}
		
		private function onPause():void 
		{
			log("pause");
		}
		
		private function onResume():void 
		{
			log("resume");
			if(FCAndroidUtility.instance.doneInit)
				FCAndroidUtility.instance.setImmersive(true);
		}
		
		private function onStop():void 
		{
			log("stop");
		}
		
		private function startStarling():void
		{		
			
		}
		
		private function onBackKeyPressed():void 
		{
			txt.appendText("\nback key pressed");
		}
		
		public static function log(...args):void
		{
			txt.appendText("\n"+args.join(" "));
		}
	}
	
}