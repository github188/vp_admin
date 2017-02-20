/**
 * <p>Title: Constants.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: Consmation</p>
 * @author 焦冬冬
 * @date 2015年12月7日
 * @version 1.0.0
 */
package cn.ittc.weixin.utils;

/**
 * <p>Title: Constants</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2015年12月7日
 */
public class Constants {
	/**
	 * APPID
	 */
	public static String APPID = "wx892c6f657102bc26";//"wxb4fabe0f6acffbf4";
//	public static String APPID = "wx4e0b26e128f7321d";
	
	/**
	 * SECRET
	 */
	public static String SECRET = "e573c62c6d966c66634716923b2cb25d";
//	public static String SECRET = "4c5761a8b5fc202b1fe597b48a8ea3b9";
	/**
	 * conf.properties文件路径
	 */
	public static String CONF_PROPERTIES_PATH = "/datasource/wx.properties";
//	public static String serviceUrl="http://dev.ittc.sh.cn/ittc_mobile/";
//	public static String serviceUrl= (new PropertiesUtil(CONF_PROPERTIES_PATH)).getProperty("serviceUrl");
	public static String serviceUrl="";
	/**
	 * 获取ACCESS_TOKEN接口
	 */
	public static String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	public static String GET_JSAPI_TICKET_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	public static String ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	public static String GET_USER_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	public static String OAUTH2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
			+ APPID
			+ "&redirect_uri="
			+ serviceUrl
			+ "wx_TAG.action?tag=TAG&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
	/**
	 * ACCESS_TOKEN有效时间(单位：ms)
	 */
	public static int EFFECTIVE_TIME = 700000;

	/**
	 * 微信接入token ，用于验证微信接口
	 */
	public static String TOKEN = "shenyun";
	

}
