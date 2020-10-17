# HelloAlertFGS

Sample app for ISM Push to Talk/SOS intents using a foreground service.

To start it using following `adb` command:

```
adb shell am broadcast -a com.lavawerk.test.helloalertfgs.START \
                       -n com.lavawerk.test.helloalertfgs/.CmdReceiver
```
