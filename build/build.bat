set PATH=%PATH%;D:\iosdev\fasdk\sdkwin\bin
set SWC_DIR=
set SWC=%SWC_DIR%FCAndroidUtility.swc
set XML=%SWC_DIR%extension.xml
set ANDROID_PLATFORM= -platform Android-ARM -C %SWC_DIR%android\ .
set IOS_PLATFORM= -platform iPhone-ARM -C %SWC_DIR%iphone\ . -platformoptions %SWC_DIR%iphone\option.xml
set DEFAULT_PLATFORM= -platform default -C %SWC_DIR%default\ .
set OUTPUT=FCAndroidUtility.ane
set SIGNING=-storetype pkcs12 -keystore confirmdialog.p12 -keypass peerobo
call adt -package %SIGNING% -target ane "%OUTPUT%" "%XML%" -swc "%SWC%" %ANDROID_PLATFORM% %DEFAULT_PLATFORM%
pause