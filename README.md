# telman: a simple Android TelephonyManager lister

# 1. Introduction

telman is an app that lists info from android TelephonyManager.


# 2. Example

Run the app. You will see something like this:

![Example Image](https://github.com/chemag/telman/raw/master/doc/pixel2.png "Telman running on a Pixel 2")

You can scroll the text to get the full telephony information.


# 3. Install Instructions

Download the apk from [here](https://github.com/chemag/telman/blob/master/app/release/telman-0.0.1.apk).

Install the app:

```
$ adb install telman-0.0.1.apk
Success
```

You may need to install any old copies.

```
$ adb shell pm uninstall com.example.telman
Success
```

Run it.


# 4. Build Instructions [optional]

If you prefer to run your own bits:


Build the apk
```
$ ANDROID_HOME=${ANDROID_SDK} ./gradlew build
```


Sign and zip-align the apk (you need an android keystore key -- mine is called
"androiddebugkey", and is located at `~/.android/debug.keystore`).

```
$ jarsigner -verbose -keystore ~/.android/debug.keystore app/build/outputs/apk/release/app-release-unsigned.apk androiddebugkey
$ jarsigner -verbose -verify -certs app/build/outputs/apk/release/app-release-unsigned.apk
$ mv app/build/outputs/apk/release/app-release-unsigned.apk app/build/outputs/apk/release/app-release-unaligned.apk
$ rm -rf app/release/telman-0.0.1.apk
$ zipalign -v 4 app/build/outputs/apk/release/app-release-unaligned.apk app/release/telman-0.0.1.apk
```

