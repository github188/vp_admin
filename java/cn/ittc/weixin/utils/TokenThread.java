/**
 * <p>Title: TokenThread.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.utils;

import cn.ittc.utils.PropertiesUtil;
import cn.ittc.weixin.entity.AccessToken;

/**
 * <p>Title: TokenThread</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
public class TokenThread implements Runnable{

	public static String appid="";
	public static String appsecret="";
	public static AccessToken accessToken=null;
	
	

	@Override
	public void run() {
		while(true){
			accessToken=WechatUtil.getAcessToken(appid, appsecret);
			if(accessToken==null){
				System.out.println("=======ACCESS_TOKEN=========");
				return;
			}
			PropertiesUtil propertiesUtil=new PropertiesUtil(Constants.CONF_PROPERTIES_PATH);
			System.err.println("���ԣ�"+propertiesUtil.getProperty("ACCESS_TOKEN"));
			propertiesUtil.writeProperties("ACCESS_TOKEN", accessToken.getAccessToken());
			try {
				if (null != accessToken) {
					Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
				}else{
					Thread.sleep(60*1000);
				}
			} catch (InterruptedException e) {
				
				try {
					Thread.sleep(60*1000);
				} catch (InterruptedException e2) {
					
				}
			}
			
		}
		
	}

}
