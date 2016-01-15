///**
// * Copyright © 2015 cn.vko.com. All rights reserved.
// *
// * @Title: UmengShare.java
// * @Prject: VkoCircleS
// * @Package: cn.vko.ring.common.umeng
// * @Description: TODO
// * @author: JiaRH
// * @date: 2015-11-20 上午11:30:02
// * @version: V1.0
// */
//package com.jrh.project.phonehelper.common.umeng;
//
//import com.umeng.socialize.bean.SHARE_MEDIA;
//
//import cn.vko.ring.common.model.ShareContent;
//import cn.vko.ring.common.model.ShareContent.SHARE_PLATFORM;
//import android.app.Activity;
//import android.text.TextUtils;
//
///**
// * @ClassName: UmengShare
// * @Description: TODO
// * @author: JiaRH
// * @date: 2015-11-20 上午11:30:02
// */
//public class UmengShare extends BaseShare {
//
//	private ShareContent shareContent;
//	private static UmengShare instance;
//	private boolean isInit;
//	private SHARE_PLATFORM platform;
//
//	private UmengShare() {
//	};
//
//	public static UmengShare getInstance() {
//		if (instance == null) {
//			instance = new UmengShare();
//		}
//		return instance;
//	}
//
//	public void share(Activity context, ShareContent shareContent) {
//
//		if (shareContent == null) {
//			try {
//				throw new Exception("设置分享内容不能为null");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return;
//		}
//		this.shareContent = shareContent;
//		platform = shareContent.getPlatform();
//		if (!isInit || mAct == null) {
//			initSocialSDK(context);
//			isInit = true;
//		}
//
//		if (platform == SHARE_PLATFORM.WX) {
//			if (TextUtils.isEmpty(shareContent.getShareUrl())) {
//				try {
//					throw new IllegalAccessException(
//							"微信分享必须设置targetURL，需要为http链接格式");
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return;
//			}
//		}
//		if (platform == SHARE_PLATFORM.QQ) {
//			if (TextUtils.isEmpty(shareContent.getShareTitle())
//					&& TextUtils.isEmpty(shareContent.getShareContent())) {
//				if (TextUtils.isEmpty(String.valueOf(shareContent.getImgId()))
//						|| TextUtils.isEmpty(shareContent.getImgPath())) {
//					throw new IllegalArgumentException(
//							"使用纯图片分享必须使用本地图片，不可以使用URL网络图片");
//				}
//			}
//		}
//		addContentAndShare(shareContent);
//	}
//
//	private void addContentAndShare(ShareContent shareContent) {
//		 if (!TextUtils.isEmpty(shareContent.getImgPath())) {
//			shapeByImgPath(shareContent.getShareTitle(),
//					shareContent.getShareContent(), shareContent.getImgPath(),
//					shareContent.getShareUrl());
//		} else if (!TextUtils.isEmpty(shareContent.getImgLink())) {
//			shapeByImgPath(shareContent.getShareTitle(),
//					shareContent.getShareContent(), shareContent.getImgLink(),
//					shareContent.getShareUrl());
//		} else if (shareContent.getBm() != null) {
//			shapeByBitmap(shareContent.getShareTitle(),
//					shareContent.getShareContent(), shareContent.getBm(),
//					shareContent.getShareUrl());
//		}else{
//			shapeByImgId(shareContent.getShareTitle(),
//					shareContent.getShareContent(), shareContent.getImgId(),
//					shareContent.getShareUrl());
//		}
//		switch (platform) {
//		case QQ:
//			postShare(SHARE_MEDIA.QQ);
//			break;
//		case Q_ZONE:
//			postShare(SHARE_MEDIA.QZONE);
//			break;
//		case WX:
//			postShare(SHARE_MEDIA.WEIXIN);
//			break;
//		case WX_CIRCLE:
//			postShare(SHARE_MEDIA.WEIXIN_CIRCLE);
//			break;
//		default:
//			break;
//		}
//	}
//}
