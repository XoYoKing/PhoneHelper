//package com.jrh.project.phonehelper.common.umeng;
//
//import android.app.Activity;
//import android.graphics.Bitmap;
//import cn.vko.ring.Constants;
//import cn.vko.ring.common.listener.IShareListener;
//import cn.vko.ring.utils.ImageUtils;
//
//import com.umeng.socialize.bean.SHARE_MEDIA;
//import com.umeng.socialize.bean.SocializeEntity;
//import com.umeng.socialize.controller.UMServiceFactory;
//import com.umeng.socialize.controller.UMSocialService;
//import com.umeng.socialize.controller.listener.SocializeListeners.SnsPostListener;
//import com.umeng.socialize.media.QQShareContent;
//import com.umeng.socialize.media.QZoneShareContent;
//import com.umeng.socialize.media.UMImage;
//import com.umeng.socialize.media.UMVideo;
//import com.umeng.socialize.sso.QZoneSsoHandler;
//import com.umeng.socialize.sso.UMQQSsoHandler;
//import com.umeng.socialize.weixin.controller.UMWXHandler;
//import com.umeng.socialize.weixin.media.CircleShareContent;
//import com.umeng.socialize.weixin.media.WeiXinShareContent;
//
///**
// * 分享
// *
// * @author jiaRh
// */
//public abstract class BaseShare {
//	private static UMSocialService mController;
//	public Activity mAct;
//	private IShareListener shareListener;
//
//	public static UMSocialService getUMservice() {
//		if (mController == null) {
//			mController = UMServiceFactory
//					.getUMSocialService(Constants.YOUMENG_SHARE);
//		}
//		return mController;
//	}
//
//	/**
//	 * @param path
//	 *            media 路径
//	 */
//	protected void shareImgByPath(String path) {
//		getUMservice().setShareMedia(getImg(path, 0, 0));
//	}
//
//	/**
//	 * @param imgId
//	 */
//	protected void shareImgById(int imgId) {
//		getUMservice().setShareMedia(new UMImage(mAct, imgId));
//	}
//
//	/**
//	 * @param imgLink
//	 */
//	protected void shareImgByLink(String imgLink) {
//		getUMservice().setShareMedia(new UMImage(mAct, imgLink));
//	}
//
//	/**
//	 * @param videoLink
//	 */
//	protected void shareVideoByLink(String videoLink, String videoImgLink) {
//		// 设置分享视频
//		UMVideo umVideo = new UMVideo(videoLink);
//		// 设置视频缩略图
//		umVideo.setThumb(videoImgLink);
//		getUMservice().setShareMedia(umVideo);
//	}
//
//	private UMImage getImg(String path, int width, int height) {
//		if (width <= 0 || height <= 0) {
//			width = 720;
//			height = 1280;
//		}
//		return new UMImage(mAct, ImageUtils.getInstance().getBitmapForLocal(
//				path, width, height));
//	}
//
//	/**
//	 * 初始化SDK，添加一些平台
//	 */
//	public void initSocialSDK(Activity act) {
//		mAct = act;
//		initQQ();
//		initWX();
//	}
//
//	/**
//	 * 微信
//	 */
//	public void initWX() {
//		setWX();
//		setwxCircle();
//	}
//
//	/**
//	 * qq
//	 *
//	 * @param act
//	 */
//	public void initQQ() {
//		setQQ();
//		setQzone();
//	}
//
//	private void setwxCircle() {
//		// 支持微信朋友圈
//		UMWXHandler wxCircleHandler = new UMWXHandler(mAct, Constants.WXAPPID,
//				Constants.WXAPPSECRET);
//		wxCircleHandler.setToCircle(true);
//		wxCircleHandler.addToSocialSDK();
//	}
//
//	private void setWX() {
//		// 添加微信平台
//		UMWXHandler wxHandler = new UMWXHandler(mAct, Constants.WXAPPID,
//				Constants.WXAPPSECRET);
//		wxHandler.addToSocialSDK();
//		wxHandler.setRefreshTokenAvailable(false);
//	}
//
//	private void setQzone() {
//		// 参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
//		QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(mAct,
//				Constants.QQAPPID, Constants.QQAPPKEY);
//		qZoneSsoHandler.addToSocialSDK();
//	}
//
//	private void setQQ() {
//		// 参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
//		UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(mAct,
//				Constants.QQAPPID, Constants.QQAPPKEY);
//		qqSsoHandler.addToSocialSDK();
//	}
//
//	/**
//	 * @Title: wxcontent
//	 * @Description: TODO
//	 * @param title
//	 * @param content
//	 * @param imgId
//	 * @param imgLink
//	 * @param imgPath
//	 * @param url
//	 * @return: void
//	 */
//	protected void shapeByImgId(String title, String content, int imgId,String url) {
//		wxcontent(title, content, new UMImage(mAct, imgId), url);
//		qqcontent(title, content, new UMImage(mAct, imgId), url);
//	}
//
//	protected void shapeByImgLink(String title, String content,
//			String imgLink, String url) {
//		wxcontent(title, content, new UMImage(mAct, imgLink), url);
//		qqcontent(title, content, new UMImage(mAct, imgLink), url);
//	}
//
//	protected void shapeByImgPath(String title, String content,
//			String imgPath, String url) {
//		wxcontent(title, content, getImg(imgPath, 0, 0), url);
//		qqcontent(title, content, getImg(imgPath, 0, 0), url);
//	}
//
//	protected void shapeByBitmap(String title, String content, Bitmap bm,String url) {
//		wxcontent(title, content, new UMImage(mAct, bm), url);
//		qqcontent(title, content, new UMImage(mAct, bm), url);
//	}
//
//	protected void wxcontent(String title, String content, UMImage bm,
//			String url) {
//		WeiXinShareContent weixinContent = new WeiXinShareContent();
//		// 设置微信朋友圈分享内容
//		CircleShareContent circleMedia = new CircleShareContent();
//		// 设置title
//		weixinContent.setTitle(title);
//		weixinContent.setShareContent(content);
//		// 设置分享内容跳转URL
//		weixinContent.setTargetUrl(url);
//		circleMedia.setShareContent(content);
//		circleMedia.setTitle(title);
//		circleMedia.setTargetUrl(url);
//
//		weixinContent.setShareMedia(bm);
//		circleMedia.setShareMedia(bm);
//
//		getUMservice().setShareMedia(weixinContent);
//		getUMservice().setShareMedia(circleMedia);
//	}
//
//	/**
//	 * @Title: qqcontent
//	 * @Description: TODO
//	 * @param title
//	 * @param content
//	 * @param imgId
//	 * @param imgLink
//	 * @param imgPath
//	 * @param url
//	 * @return: void
//	 */
//	protected void qqcontent(String title, String content, UMImage bm, String url) {
//		QQShareContent qqShareContent = new QQShareContent();
//		QZoneShareContent qzone = new QZoneShareContent();
//		// 设置分享文字
//		qqShareContent.setShareContent(content);
//		// 设置分享title
//		qqShareContent.setTitle(title);
//		// 设置点击分享内容的跳转链接
//		qqShareContent.setTargetUrl(url);
//		// 设置分享文字
//		qzone.setShareContent(content);
//		// 设置点击消息的跳转URL
//		qzone.setTargetUrl(url);
//		// 设置分享内容的标题
//		qzone.setTitle(title);
////		if (!TextUtils.isEmpty(String.valueOf(imgId)) && imgId != 0) {
////			qqShareContent.setShareMedia(new UMImage(mAct, imgId));
////			qzone.setShareMedia(new UMImage(mAct, imgId));
////		} else if (!TextUtils.isEmpty(imgPath)) {
////
////			qqShareContent.setShareMedia(getImg(imgPath, 0, 0));
////			qzone.setShareMedia(getImg(imgPath, 0, 0));
////		} else if (!TextUtils.isEmpty(imgLink)) {
////			qqShareContent.setShareMedia(new UMImage(mAct, imgLink));
////			qzone.setShareMedia(new UMImage(mAct, imgLink));
////		}
//
//		qqShareContent.setShareMedia(bm);
//		qzone.setShareMedia(bm);
//
//		getUMservice().setShareMedia(qqShareContent);
//		getUMservice().setShareMedia(qzone);
//	}
//
//	protected void postShare(SHARE_MEDIA p) {
//		getUMservice().postShare(mAct, p, mShareListener);
//	}
//
//	public void setShareListener(IShareListener shareListener) {
//		this.shareListener = shareListener;
//	}
//
//	/**
//	 * 分享监听器
//	 */
//	SnsPostListener mShareListener = new SnsPostListener() {
//		@Override
//		public void onStart() {
//			if (shareListener != null) {
//				shareListener.onShareStart();
//			}
//		}
//
//		@Override
//		public void onComplete(SHARE_MEDIA platform, int stCode,
//				SocializeEntity entity) {
//			if (shareListener != null) {
//				shareListener.onComplete(platform, stCode, entity);
//			}
//		}
//	};
//}
