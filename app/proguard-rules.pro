# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\JiaRH\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
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
-keep class **.R$*{*;}
 #-optimizationpasses 5
 #-dontusemixedcaseclassnames
#-dontskipnonpubliclibraryclasses
 #-dontpreverify
 #-ignorewarnings
 #-verbose
 #-optimizations !code/simplification/arithmetic,!field

-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.**
-keep public class * extends android.os.Handler


-keep public class com.jrh.project.phonehelper.common.*{*;}
-keep public class com.jrh.project.phonehelper.phonebook.models.*{*;}



#-keep class com.alibaba.fastjson.**{ *;}
#-keep class com.google.gson.examples.android.model.**{*;}
# -keep class com.google.protobuf.**{*;}

-keep class android.net.http.**{*;}
-dontwarn android.net.http.**

-keep class org.apache.http.**{*;}
-dontwarn org.apache.http.**


-libraryjars libs/armeabi/libbspatch.so



-dontskipnonpubliclibraryclassmembers

#alipay
#-libraryjars libs/alipaySdk-20151112.jar

#-keep class com.alipay.android.app.IAlixPay{*;}
#-keep class com.alipay.android.app.IAlixPay$Stub{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback{*;}
#-keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
#-keep class com.alipay.sdk.app.PayTask{ public *;}
#-keep class com.alipay.sdk.app.AuthTask{ public *;}


#butterKnife
#-libraryjars libs/butterknife-7.0.1.jar
#
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

#-libraryjars libs/commons-codec-1.7.jar
#-dontwarn org.apache.commons.codec.*
#-keep class org.apache.commons.codec.**{ *;}
#-libraryjars libs/android-support-v4.jar

#-libraryjars libs/httpmime-4.3.5.jar
#-dontwarn org.apache.http.entity.mime.*
#-keep class org.apache.http.entity.mime.**{ *;}

#-libraryjars libs/android-support-v7-appcompat.jar
#-dontwarn android.support.v7.*
#-keep class android.support.v7.**{ *;}



#-libraryjars libs/android-support-v7-appcompat.jar

#-libraryjars libs/httpclient-4.3.5.jar
#-libraryjars libs/android-logging-log4j-1.0.3.jar
#-libraryjars libs/log4j-1.2.17.jar
#-keep class org.apache.log4j.**{*;}
 #-dontwarn org.apache.log4j.*
#-keep class javax.**{*;}
#-dontwarn javax.*

#-libraryjars libs/httpcore-4.3.2.jar
#-libraryjars libs/jikmediaplayer.jar
#-dontwarn tv.danmaku.ijk.media.player.*
#-keep class tv.danmaku.ijk.media.player.**{ *;}


#-libraryjars libs/nineoldandroids-2.4.0.jar
#-dontwarn com.nineoldandroids.*
#-keep class com.nineoldandroids.**{ *;}



#-libraryjars libs/universal-image-loader-1.9.2-SNAPSHOT-with-sources.jar
#-libraryjars libs/volley.jar

#-libraryjars libs/zxing.jar


#-dontwarn com.google.zxing.*
#-keep class com.google.zxing.** { *;}



-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#EventBus
#-libraryjars libs/eventbus.jar
-keepclassmembers class ** {
    public void onEvent*(**);
}



-keepattributes *Annotation*
-keepclassmembers class ** {
    public void on*Event(...);
}
-keepclasseswithmembernames class * {
    native <methods>;
}
-dontwarn  org.eclipse.jdt.annotation.**

# sdk版本小于18时需要以下配置, 建议使用18或以上版本的sdk编译
-dontwarn  android.location.Location
-dontwarn  android.net.wifi.WifiManager

-dontnote ct.**

# umeng

-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
# 添加第三方jar包
#-libraryjars libs/umeng-update-v2.6.0.1.jar
#-libraryjars libs/SocialSDK_QQZone_2.jar

-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**


-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**


-keep class com.facebook.**
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**

-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}

-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}

-keep class com.umeng.message.*{
        public <fields>;
        public <methods>;
}
-keep class com.umeng.message.protobuffer.*{
        public <fields>;
        public <methods>;
}
-keep class com.squareup.wire.*{
        public <fields>;
        public <methods>;
}
-keep class org.android.agoo.impl.*{
        public <fields>;
        public <methods>;
}

-keep class org.android.agoo.service.*{*;}

-keep class org.android.spdy.**{*;}



#-keep class com.alibaba.fastjson.**{ *;}
#-keep public class * extends android.os.AsyncTask


# # -------------------------------------------
# #  ############### volley混淆  ###############
# # -------------------------------------------
#-keep class com.android.volley.**{*;}
#-keep class com.android.volley.toolbox.** {*;}
#-keep class com.android.volley.Response$*{ *;}
#-keep class com.android.volley.Request$*{ *;}
#-keep class com.android.volley.RequestQueue$*{ *;}
#-keep class com.android.volley.toolbox.HurlStack$*{ *;}
#-keep class com.android.volley.toolbox.ImageLoader$*{ *;}
#
#-dontwarn org.apache.http.annotation.*
#-dontwarn org.apache.http.util.*
#-dontwarn org.apache.http.impl.client.*
#-dontwarn org.apache.http.client.methods.*
#-dontwarn org.eclipse.jdt.annotation.*
#-dontwarn com.ta.utdid2.device.*
#-dontwarn org.apache.commons.logging.*
#-dontwarn org.apache.commons.**
#
#-keep class de.greenrobot.event.**{*;}
#-dontwarn de.greenrobot.event.*
#-keep class com.loopj.android.http.**{*;}
#-keep class android.support.v4.**{*;}
#-keep class com.readystatesoftware.viewbadger.**{*;}
#-keep class com.baidu.**{*;}
#-keep class vi.com.gdi.bgl.android.java.**{*;}
#-keep class hirondelle.date4j.**{*;}
#-keep class de.greenrobot.event.**{*;}
#-keep class de.greenrobot.event.util.**{*;}
#-keep class de.greenrobot.dao.**{*;}
#-keep class de.greenrobot.dao.async.**{*;}
#-keep class de.greenrobot.dao.identityscope.**{*;}
#-keep class de.greenrobot.dao.internal.**{*;}
#-keep class de.greenrobot.dao.query.**{*;}
#-keep class com.ant.liao.**{*;}
#-keep class com.google.gson.**{*;}
#-keep class com.google.gson.annotations.**{*;}
#-keep class com.google.gson.internal.**{*;}
#-keep class com.google.gson.internal.bind.**{*;}
#-keep class com.google.gson.reflect.**{*;}
#-keep class com.google.gson.stream.**{*;}



# 声明对native代码不java文件，不进行混淆，因为混淆后，native层的代码会关联错误，native层与java层的代码关联就是靠方法明
-keepclasseswithmembernames class * {
    private native <methods>;
}

# 声明类里面有这种构造函数的类不混淆，这种类就是自定义控件，因为此种自定义控件在xml文件中会用到，如果混淆，将找不到
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

# 声明类里面有这种构造函数的类不混淆，这种类就是自定义控件，因为此种自定义控件在xml文件中会用到，如果混淆，将找不到
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
    private native <methods>;
   public native <methods>;
   protected native <methods>;
   public static native <methods>;
   private static native <methods>;
   static native <methods>;
   native <methods>;
}

# 对序列化的bean不进行混淆，不知道为什么，难道序列化也跟名字有关?
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# 以下类过滤不混淆
-keep public class * extends com.umeng.**
# 以下包不进行过滤
-keep class com.umeng.** { *; }
-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}


-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.

-dontwarn java.nio.file.Files
-dontwarn java.nio.file.Path
-dontwarn java.nio.file.OpenOption
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement