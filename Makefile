

all: app/release/telman-0.0.1.apk

app/release/telman-0.0.1.apk: ./app/build/outputs/apk/release/app-release-unsigned.apk
	jarsigner -verbose -keystore ~/.android/debug.keystore app/build/outputs/apk/release/app-release-unsigned.apk androiddebugkey
	jarsigner -verbose -verify -certs app/build/outputs/apk/release/app-release-unsigned.apk
	mv app/build/outputs/apk/release/app-release-unsigned.apk app/build/outputs/apk/release/app-release-unaligned.apk
	rm -rf app/release/telman-0.0.1.apk
	zipalign -v 4 app/build/outputs/apk/release/app-release-unaligned.apk app/release/telman-0.0.1.apk



./app/build/outputs/apk/release/app-release-unsigned.apk: app/src/main/java/com/example/telman/MainActivity.java app/src/main/AndroidManifest.xml app/src/main/res/layout/activity_telman.xml
	ANDROID_HOME=${ANDROID_SDK} ./gradlew build

clean:
	ANDROID_HOME=${ANDROID_SDK} ./gradlew clean
	rm -rf app/build/

realclean: clean
	rm -rf app/release/telman-0.0.1.apk

