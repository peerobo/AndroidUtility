AndroidUtility
==============

Immersive, Handle Back Key, Show Video Ad


Please include these permissions in Android Manifest:
```xml
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>			
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>			
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>

<application android:enabled="true">				
  <activity android:name="com.fc.FCMainActivity" android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />
  <activity android:name="com.vungle.sdk.VungleAdvert" android:configChanges="keyboardHidden|orientation|screenSize" android:theme="@android:style/Theme.NoTitleBar.Fullscreen" />
	<service android:name="com.vungle.sdk.VungleIntentService"/>
</application>
```
And in extensions tag:
```xml
<extensions>        
  <extensionID>com.fc.AndroidUtility</extensionID>
</extensions>
```
