/**
 * <p>Title: WechatUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月30日
 * @version 1.0.0
 */
package cn.ittc.weixin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cn.ittc.weixin.entity.AccessToken;
import cn.ittc.weixin.entity.JsapiTicket;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * <p>Title: WechatUtil</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月30日
 */
public class WechatUtil {
	
	private static Logger log = LoggerFactory.getLogger(WechatUtil.class);
	
	public static AccessToken getAcessToken(String appid, String appsecret) {

		AccessToken accessToken = null;
		JsonParser jsonParser = new JsonParser();
		String requestUrl = Constants.GET_ACCESSTOKEN_URL.replace("APPID",
				appid).replace("APPSECRET", appsecret);
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(requestUrl);
		HttpResponse res;
		try {
			res = client.execute(get);
			String responseContent = null;
			HttpEntity entity = res.getEntity();
			responseContent=EntityUtils.toString(entity,"UTF-8");
			JsonObject json=jsonParser.parse(responseContent).getAsJsonObject();
			if(null!=json){
				try {
					accessToken=new AccessToken();
					accessToken.setAccessToken(json.get("access_token").getAsString());
					accessToken.setExpiresIn(json.get("expires_in").getAsInt());
				} catch (Exception e) {
					accessToken=null;
					System.err.println("获取token失败！");
				}
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return accessToken;

	}
	
	public static JSONObject HttpGet(String url) throws Exception {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse res = client.execute(get);
		if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(res.getEntity(), "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(result);
			return jsonObject;
		}
		return null;
	}
	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	
	/**
	 * 获取js调用api所使用ticket
	 * @param accessToken 获取到的accesstoken
	 * @return
	 */
	public static JsapiTicket getJsapiTicket(String accessToken) {
		JsapiTicket jsapiTicket = null;
		String requestUrl = Constants.GET_JSAPI_TICKET_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				jsapiTicket = new JsapiTicket();
				jsapiTicket.setJsapi_ticket(jsonObject.getString("ticket"));
				jsapiTicket.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				jsapiTicket = null;
				// 获取token失败
				log.error("获取ticket失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return jsapiTicket;
	}
	
	/**
	 * 
	 * <p>Title: getJsonByCode</p>
	 * <p>Description: 根据回调返回的code获取openid等json</p>
	 * @param code
	 * @return
	 * @author 焦冬冬
	 * @date 2016年3月14日
	 */
	public static JSONObject getJsonByCode(String code) {
		String url = Constants.ACCESS_TOKEN_URL.replace("CODE", code)
				.replace("APPID", Constants.APPID)
				.replace("SECRET", Constants.SECRET);
		JSONObject jsonObject = WechatUtil.httpsRequest(url, "GET", null);
		return jsonObject;
	}
	/**
	 * 
	 * <p>Title: getUserByOpenId</p>
	 * <p>Description: 根据openId查询关注粉丝的信息</p>
	 * @param openId
	 * @return
	 * @author 焦冬冬
	 * @date 2016年3月14日
	 */
	public static JSONObject getUserByOpenId(String openId)
	{
		JSONObject jsonObject=new JSONObject();
		try {
			jsonObject = WechatUtil.HttpGet(Constants.GET_USER_URL.replace(
					"OPENID", openId).replace("ACCESS_TOKEN",
					TokenListener.accessToken.getAccessToken()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	/**
	 * 
	 * <p>Title: getUserByCode</p>
	 * <p>Description:根据回调函数的code查询关注的粉丝 </p>
	 * @param code
	 * @return
	 * @author 焦冬冬
	 * @date 2016年3月14日
	 */
	public static JSONObject getUserByCode(String code){
		String url = Constants.ACCESS_TOKEN_URL.replace("CODE", code)
				.replace("APPID", Constants.APPID)
				.replace("SECRET", Constants.SECRET);
		JSONObject jsonObject = WechatUtil.httpsRequest(url, "GET", null);
		String openId=jsonObject.getString("openid");
		try {
			jsonObject = WechatUtil.HttpGet(Constants.GET_USER_URL.replace(
					"OPENID", openId).replace("ACCESS_TOKEN",
					TokenListener.accessToken.getAccessToken()));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return jsonObject;
	}
	public static void main(String[] args) {
		
	}

}
