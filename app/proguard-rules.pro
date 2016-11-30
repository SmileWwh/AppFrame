# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/wwh/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#-ignorewarnings                    # 忽略警告，避免打包时某些警告出现
-optimizationpasses 5               # 指定代码的压缩级别
-dontusemixedcaseclassnames         # 是否使用大小写混合 混淆时不会产生形形色色的类名
-dontskipnonpubliclibraryclasses    # 是否混淆第三方jar
-dontpreverify                      # 混淆时是否做预校验
-verbose                            # 混淆时是否记录日志
-dontoptimize                       # 不优化输入的类文件

-keepattributes *Annotation*, SourceFile, InnerClasses, LineNumberTable, Signature, EnclosingMethod
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*    #优化 混淆时采用的算法

#  *匹配任意字符不包括.  **匹配任意字符
#{ *;}    表示一个类中的所有的东西
# 保持一个完整的包不被混淆
-keep class android.os.**{*;}
-dontwarn android.support.**
-dontwarn android.os.**

-keep class **.R$* { *; }
-keep class **.R{ *; }

# Keep the support library
-keep class android.support.** { *; }
-keep interface android.support.** { *; }

-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.widget
-keep public class * extends android.support.v4.**
-keep interface android.support.v4.app.** { *; }

-keep class android.support.v7.** { *; }
-keep public class * extends android.support.v7.widget
-keep public class * extends android.support.v7.**

#移除Log输出
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

# 保持Parcelable不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# 保持Serializable不被混淆
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
  static final long serialVersionUID;
  private static final java.io.ObjectStreamField[] serialPersistentFields;
  private void writeObject(java.io.ObjectOutputStream);
  private void readObject(java.io.ObjectInputStream);
  java.lang.Object writeReplace();
  java.lang.Object readResolve();
}

#不混淆自定义控件
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    void set*(***);
    *** get*();
}

# 保持枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
# For native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# 未指定成员，仅仅保持类名不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.app.View
-keep public class * extends android.app.IntentService
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.hardware.display.DisplayManager
-keep public class * extends android.os.UserManager
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.app.Fragment

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#保持类成员
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-keepclassmembers class * extends android.content.Context {
  public void *(android.view.View);
  public void *(android.view.MenuItem);
}
#Webview Js
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}

########################################（以上内容不要动）##########################################

# 实体类不混淆（自己项目实体类不被混淆，因为Gson中用到了反射）
-keep class com.wwh.appframe.model.** { *; }
#工具类不混淆
-keep class com.wwh.appframe.tools.** { *; }
# BuildConfig
-keep public class com.wwh.appframe.R$*{
		public static final int *;
}
-keep class com.wwh.appframe.BuildConfig { *; }

# okhttp & Picasso
-dontwarn com.squareup.okhttp.**

##retrofit
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn retrofit2.**
-keep class okhttp3.** { *; }
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

#Otto
-keepattributes *Annotation*
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}
# Gson
-dontwarn com.google.**
-keep class com.google.gson.** {*;}

# Butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# For Guava:
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe

# For RxJava:
-dontwarn rx.**
-dontwarn org.mockito.**
-dontwarn org.junit.**
-dontwarn org.robolectric.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
-keep class sun.misc.Unsafe { *; }
-dontwarn java.lang.invoke.*

