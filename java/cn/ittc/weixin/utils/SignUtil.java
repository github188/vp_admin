/**
 * <p>Title: SignUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import cn.ittc.weixin.entity.JsapiTicket;
import cn.ittc.weixin.utils.Constants;
import cn.ittc.weixin.utils.StringUtil;
import cn.ittc.weixin.utils.TokenListener;

/**
 * <p>Title: SignUtil</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
public class SignUtil {
private static String token=Constants.TOKEN;
	
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr = new String[] { token, timestamp, nonce };
		// ��token��timestap��nonce�������������ֵ�����
		Arrays.sort(arr);

		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// �����������ַ���ƴ�ӳ�һ���ַ���sha1����
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);

		} catch (Exception e) {
			e.printStackTrace();
		}
		content = null;
		System.out.println("�����������ַ���:" + tmpStr);

		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;

	}
	/**
	 * 
	 * <p>Title: byteToStr</p>
	 * <p>Description: ���ֽ�����ת��Ϊʮ�������ַ���</p>
	 * @param byteArray
	 * @return
	 * @author ������
	 * @date 2015��12��3��
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	
	/**
	 * 
	 * <p>Title: byteToHexStr</p>
	 * <p>Description:���ֽ�ת��Ϊʮ�������ַ��� </p>
	 * @param mByte
	 * @return
	 * @author ������
	 * @date 2015��12��3��
	 */
	private static String byteToHexStr(byte mByte){
		
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
	
	public static Map<String, String> createShareSignture(String url) {
		// ���ýӿڻ�ȡaccess_token
		JsapiTicket ticket = TokenListener.jsapiTicket;
		Map<String, String> ret = new HashMap<String, String>();
		String timestamp = String.valueOf(System.currentTimeMillis());//��ǰʱ���
		String nonce_str = StringUtil.getRandomString(16);
		String jsapi_ticket = ticket.getJsapi_ticket();
		String string1 = "";
		String signature = "";

		// ע���������������ȫ��Сд���ұ�������
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
