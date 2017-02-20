/**
 * <p>Title: WechatUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��30��
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
 * @author ������
 * @date 2016��8��30��
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
					System.err.println("��ȡtokenʧ�ܣ�");
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
	 * ����https���󲢻�ȡ���
	 * @param requestUrl �����ַ
	 * @param requestMethod ����ʽ��GET��POST��
	 * @param outputStr �ύ������
	 * @return JSONObject(ͨ��JSONObject.get(key)�ķ�ʽ��ȡjson���������ֵ)
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// ������SSLContext�����еõ�SSLSocketFactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// ��������ʽ��GET/POST��
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// ����������Ҫ�ύʱ
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// ע������ʽ����ֹ��������
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// �����ص�������ת�����ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
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
	 * ��ȡjs����api��ʹ��ticket
	 * @param accessToken ��ȡ����accesstoken
	 * @return
	 */
	public static JsapiTicket getJsapiTicket(String accessToken) {
		JsapiTicket jsapiTicket = null;
		String requestUrl = Constants.GET_JSAPI_TICKET_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		// �������ɹ�
		if (null != jsonObject) {
			try {
				jsapiTicket = new JsapiTicket();
				jsapiTicket.setJsapi_ticket(jsonObject.getString("ticket"));
				jsapiTicket.setExpiresIn(jsonObject.getInt("expires_in"));
			} catch (JSONException e) {
				jsapiTicket = null;
				// ��ȡtokenʧ��
				log.error("��ȡticketʧ�� errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return jsapiTicket;
	}
	
	/**
	 * 
	 * <p>Title: getJsonByCode</p>
	 * <p>Description: ���ݻص����ص�code��ȡopenid��json</p>
	 * @param code
	 * @return
	 * @author ������
	 * @date 2016��3��14��
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
	 * <p>Description: ����openId��ѯ��ע��˿����Ϣ</p>
	 * @param openId
	 * @return
	 * @author ������
	 * @date 2016��3��14��
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
	 * <p>Description:���ݻص�������code��ѯ��ע�ķ�˿ </p>
	 * @param code
	 * @return
	 * @author ������
	 * @date 2016��3��14��
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
