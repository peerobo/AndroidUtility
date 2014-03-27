call bat\SetupSDK.bat
set SRC=src
set SWC=FCAndroidUtility.swc
call compc -external-library-path %FLEX_SDK%\frameworks\libs\air\airglobal.swc -source-path %SRC% -include-sources %SRC%\* -optimize -output bin\%SWC%
pause