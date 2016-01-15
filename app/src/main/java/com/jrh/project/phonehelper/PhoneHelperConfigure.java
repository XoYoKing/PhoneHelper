package com.jrh.project.phonehelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PhoneHelperConfigure {
	private final static String SHARED_NAME = "shared_name";
	private SharedPreferences settings;
	private static PhoneHelperConfigure configure = null;

	private PhoneHelperConfigure(Context context) {
		settings = context.getSharedPreferences(SHARED_NAME, 0);
	}

	public static PhoneHelperConfigure getConfigure(Context context) {
		if (null == configure) {
			configure = new PhoneHelperConfigure(context);
		}
		return configure;
	}

	public void put(String key, String value) {
		Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public void put(String key, boolean value) {
		Editor editor = settings.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public void put(String key, int value) {
		Editor editor = settings.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public String getString(String key) {
		if (settings != null) {
			return settings.getString(key, "");
		}
		return "";

	}

	public int getInt(String key, int defaultValue) {
		if (settings != null) {
			return settings.getInt(key, defaultValue);
		}
		return defaultValue;
	}

	public String getString(String key, String defaultValue) {

		if (settings.contains(key)) {
			return settings.getString(key, defaultValue);
		}
		return defaultValue;
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		if (settings.contains(key)) {
			return settings.getBoolean(key, defaultValue);
		}
		return defaultValue;
	}

//	public void putUserInfo(UserInfo user) {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ObjectOutputStream oos;
//		try {
//			oos = new ObjectOutputStream(baos);
//			// 将Product对象放到OutputStream中
//			oos.writeObject(user);
//			// 将Product对象转换成byte数组，并将其进行base64编码
//			String productBase64 = new String(Base64.encodeBase64(baos
//					.toByteArray()));
//			Editor editor = settings.edit();
//			// 将编码后的字符串写到base64.xml文件中
//			editor.putString("USER", productBase64);
//			editor.commit();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public UserInfo getUserInfo() {
//		String productBase64 = settings.getString("USER", "");
//		// 对Base64格式的字符串进行解码
//		byte[] base64Bytes = Base64.decodeBase64(productBase64.getBytes());
//		ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
//		ObjectInputStream ois;
//		try {
//			ois = new ObjectInputStream(bais);
//			UserInfo user = (UserInfo) ois.readObject();
//			return user;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// 从ObjectInputStream中读取Product对象
//		return null;
//
//	}
	public void clearConfigure(){
		if (settings != null) {
			settings.edit().clear();
			settings.edit().commit();
		}
	}
}
