# HelloAlertFGS

Sample app for ISM Push to Talk/SOS intents using a foreground service.

To start it, use following `adb` command:

```
adb shell am broadcast -a com.lavawerk.test.helloalertfgs.START \
                       -n com.lavawerk.test.helloalertfgs/.CmdReceiver
```

<img src="/demo/sos_screenshot.png" width="250"/> <img  src="/demo/PTT_screenshot.png" width="250"/> 

If you want to test other intents please add them in the `IntentFilter`
in `FGService.java`.
