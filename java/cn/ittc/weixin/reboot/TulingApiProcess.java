/**
 * <p>Title: TulingApiProcess.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.reboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * <p>Title: TulingApiProcess</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
public class TulingApiProcess {
		/**
		 * ����ͼ�������api�ӿڣ���ȡ���ܻظ����ݣ�������ȡ�Լ�������
		 * @param content
		 * @return
		 */
	public static String getTulingResult(String content) {
		// ͼ����������ݿ�ӿ�
		String APIKEY = "438cc658c632496fa4dc33511cb2674d";
		StringBuffer sb = new StringBuffer();
		String result = "";
		try {
			String INFO = URLEncoder.encode(content, "utf-8");
			String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
			URL getUrl = new URL(getURL);
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();

			// ȡ������������ʹ��Reader��ȡ
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));

			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			// �Ͽ�����
			connection.disconnect();
			// JSONObject json = new JSONObject(sb.toString());
			JSONObject json = new JSONObject();
			json = JSONObject.fromObject(sb.toString());
			result = json.getString("text");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
		
		/**
		 * ����ͼ�������ƽ̨�ӿ� 
		 * ������@param args
		 * ������@throws IOException
		 * ���ߣ�herosky
		 */
	public static void main(String[] args) throws IOException {

		String APIKEY = "438cc658c632496fa4dc33511cb2674d";
		// String INFO = URLEncoder.encode("�Ϸ�����", "utf-8");
		String INFO = "�Ϻ�����";
		// String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY
		// + "&info=" + INFO;
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
		URL getUrl = new URL(getURL);
		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
		connection.connect();

		// ȡ������������ʹ��Reader��ȡ
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = "";
		String result = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		// �Ͽ�����
		connection.disconnect();
		System.out.println(sb);
		JSONObject json;
		try {
			// json = new JSONObject(sb.toString());
			json = new JSONObject();

			json = JSONObject.fromObject(sb.toString());

			if (100000 == json.getInt("code")) {
				result = json.getString("text");
			}
			result = new String(result.getBytes(), "UTF-8");
			System.out.println(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
