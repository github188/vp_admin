/**
 * <p>Title: Constants.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: Consmation</p>
 * @author ������
 * @date 2015��12��7��
 * @version 1.0.0
 */
package cn.ittc.weixin.utils;

/**
 * <p>Title: Constants</p>
 * <p>Description: </p>
 * @author ������
 * @date 2015��12��7��
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
	 * conf.properties�ļ�·��
	 */
	public static String CONF_PROPERTIES_PATH = "/datasource/wx.properties";
//	public static String serviceUrl="http://dev.ittc.sh.cn/ittc_mobile/";
//	public static String serviceUrl= (new PropertiesUtil(CONF_PROPERTIES_PATH)).getProperty("serviceUrl");
	public static String serviceUrl="";
	/**
	 * ��ȡACCESS_TOKEN�ӿ�
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
	 * ACCESS_TOKEN��Чʱ��(��λ��ms)
	 */
	public static int EFFECTIVE_TIME = 700000;

	/**
	 * ΢�Ž���token ��������֤΢�Žӿ�
	 */
	public static String TOKEN = "shenyun";
	

}
