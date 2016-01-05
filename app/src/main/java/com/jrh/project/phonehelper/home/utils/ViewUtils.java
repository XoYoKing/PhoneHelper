package com.jrh.project.phonehelper.home.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;


/**
 * @author shikh
 * 
 */
public class ViewUtils {

	private   float mBrightness = 0;
	/**
	 * dp 2 px
	 * 
	 * @param dpVal
	 */
	public static int dp2px(int dpVal, Context context) {

		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}

	/**
	 * sp 2 px
	 * 
	 * @param spVal
	 * @return
	 */
	public static int sp2px(int spVal, Context context) {

		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spVal, context.getResources().getDisplayMetrics());

	}

	public static void startActivity(Context context, Class activity) {
		startActivity(context, activity, Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

	}

	public static void startActivity(Context context, Class activity, int falgs) {

		startActivity(context, activity, null, falgs);

	}

	public static void startForResult(Activity context, Class activity,
			Bundle bundle, int requestCode) {

		Intent intent = new Intent();
		intent.setClass(context, activity);
		if (null != bundle) {
			intent.putExtras(bundle);
		}
		intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		context.startActivityForResult(intent, requestCode);
	}

	public static void startActivity(Context context, Class activity,
			Bundle bundle, int falgs) {

		Intent intent = new Intent();
		intent.setClass(context, activity);
		if (null != bundle) {
			intent.putExtras(bundle);
		}
		intent.setFlags(falgs);
		context.startActivity(intent);
	}

	/**
	 * 获取屏幕宽度和高度，单位为px
	 * 
	 * @param context
	 * @return
	 */
	public static Point getScreenMetrics(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		int h_screen = dm.heightPixels;
		return new Point(w_screen, h_screen);

	}

	/**
	 * 获取屏幕密度
	 * 
	 * @param context
	 * @return
	 */
	public static float getScreenDensity(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.density;

	}
	 public static  void setWindowBright(Activity act,float percent)  
	    {
	        WindowManager.LayoutParams lpa = act.getWindow().getAttributes();  
	        lpa.screenBrightness = percent;  
	        act.getWindow().setAttributes(lpa);  
	  
	     
	  
	    }  

	/**
	 * 同步一下cookie
	 */
	public static void synCookies(Context context, String url, String cookies) {
		CookieSyncManager.createInstance(context);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(true);
//		cookieManager.removeSessionCookie();// 移除
		cookieManager.setCookie(url, cookies);// cookies是在HttpClient中获得的cookie
		CookieSyncManager.getInstance().sync();
	}

}
