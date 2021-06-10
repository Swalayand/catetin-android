#!/bin/bash 

BUILD_TOOLS="/home/u/app/androidtoolcmd" 
PLATFORM="/home/u/app/androidtoolcmd"

rm -rf build;
mkdir -p build/gen build/obj build/apk

echo "Run first aapt"
"${BUILD_TOOLS}/aapt" package -f -m -J build/gen/ -S res -M AndroidManifest.xml -I "${PLATFORM}/android.jar"

# echo "Run kotlinc"
# kotlinc -classpath "${PLATFORM}/android.jar" -d build/obj build/gen/umar/hello/R.java java/umar/hello/MainActivity.kt

echo "Run javac"
javac -source 1.7 -target 1.7 -bootclasspath "${JAVA_HOME}/jre/lib/rt.jar" -classpath "${PLATFORM}/android.jar" -d build/obj build/gen/id/pt2021m/R.java java/id/pt2021m/*.java

echo "Run ${BUILD_TOOLS}/dx"
"${BUILD_TOOLS}/dx" --dex --output=build/apk/classes.dex build/obj/

echo "Run second aapt"
"${BUILD_TOOLS}/aapt" package -f -M AndroidManifest.xml -A assets -S res/ -I "${PLATFORM}/android.jar" -F build/PT2021M.unsigned.apk build/apk/


echo "Run ${BUILD_TOOLS}/zipalign"
"${BUILD_TOOLS}/zipalign" -f -p 4 build/PT2021M.unsigned.apk build/PT2021M.aligned.apk


echo "Run apksigner"
/home/u/app/androidtoolcmd/apksigner  sign --ks keystore.jks --ks-key-alias androidkey --ks-pass pass:android  --key-pass pass:android --v1-signing-enabled true --v1-signer-name CERT --v2-signing-enabled true --out build/PT2021M.apk  build/PT2021M.aligned.apk

# echo "Install to device"
time adb install -r build/PT2021M.apk 

echo "Done!"

# 09-14 01:12:31.977  8360  8360 E AndroidRuntime: Caused by: java.lang.ClassNotFoundException: Didn't find class "kotlin.jvm.internal.Intrinsics" on path: DexPathList[[zip file "/data/app/umar.hello-ucPrILX_ICA6AslQc1-Fpw==/base.apk"],nativeLibraryDirectories=[/data/app/umar.hello-ucPrILX_ICA6AslQc1-Fpw==/lib/arm, /system/lib, /system/vendor/lib]]

# $ java -jar r8/build/libs/r8.jar --lib $ANDROID_HOME/platforms/android-29/android.jar --lib kotlin-stdlib-1.3.61.jar --release --output . --pg-conf rules.txt *.class

# Create new keystore with password 'android'
# keytool -genkey -v -keystore keystore.jks -keyalg RSA -keysize 2048 -validity 10000 -alias androidkey
