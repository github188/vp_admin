/**
 * <p>Title: StringUtil.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月31日
 * @version 1.0.0
 */
package cn.ittc.weixin.utils;

import java.util.Random;

/**
 * <p>Title: StringUtil</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月31日
 */
public class StringUtil {

	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }

}
