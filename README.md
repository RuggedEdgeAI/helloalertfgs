# HelloAlertFGS

Sample app for ISM Push to Talk/SOS intents using a foreground service.

To start it, use following `adb` command:

```
adb shell am broadcast -a com.lavawerk.test.helloalertfgs.START \
                       -n com.lavawerk.test.helloalertfgs/.CmdReceiver
```

If you want to test other intents please add them in the `IntentFilter`
in `FGService.java`.
