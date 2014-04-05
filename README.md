AndroidUtility
==============

Immersive, Google Play Games


Please include these permissions in Android Manifest:
```xml
<application android:enabled="true">
	<meta-data android:name="com.google.android.gms.games.APP_ID" android:value="\ SERVICE_ID" />
	<meta-data android:name="com.google.android.gms.version" android:value="@integer/google_play_services_version" />
	<activity android:name="com.fc.FCAndroidUtilityActivity" android:theme="@android:style/Theme.Translucent.NoTitleBar.Fullscreen" />
</application>
```
And in extensions tag:
```xml
<extensions>        
  <extensionID>com.fc.AndroidUtility</extensionID>
</extensions>
```
Change SERVICE_ID to your app/service id.

ANE file download in the build folder.
