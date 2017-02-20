/**
 * <p>Title: TokenListener.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: Consmation</p>
 * @author 焦冬�?
 * @date 2016�?�?9�?
 * @version 1.0.0
 */
package cn.ittc.weixin.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ittc.weixin.entity.AccessToken;
import cn.ittc.weixin.entity.JsapiTicket;


/**
 * <p>Title: TokenListener</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��2��29��
 */
public class TokenListener implements ServletContextListener{

	private static Logger log = LoggerFactory.getLogger(TokenListener.class);
	public static AccessToken accessToken = null;
	public static JsapiTicket jsapiTicket = null;
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}


	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		new Thread(new Runnable(){
//			WechatConfig config = (WechatConfig)OSCacheServiceImpl.getInstance("wechatConfig").get("hospitalWechat");
			public void run() {
				while (true) {
					try {
						if(Constants.APPID != null && !Constants.APPID.equals("")){
							accessToken = WechatUtil.getAcessToken(Constants.APPID, Constants.SECRET);
							if (null != accessToken) {
								log.info("��ȡaccess_token�ɹ�����Чʱ��{}�� token:{}", accessToken.getExpiresIn(), accessToken.getAccessToken());
								jsapiTicket = WechatUtil.getJsapiTicket(accessToken.getAccessToken());
								if (null != jsapiTicket) {
									log.info("��ȡjsapiTicket�ɹ�����Чʱ��{}�� token:{}", jsapiTicket.getExpiresIn(), jsapiTicket.getJsapi_ticket());
								}
								// ����7000��
								Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
							} else {
								// ���access_tokenΪnull��60����ٻ�ȡ
								Thread.sleep(60 * 1000);
							}
						}else{
							log.info("�÷��������ں����ó����޷�У���������");
						}
						
						
					} catch (InterruptedException e) {
						try {
							Thread.sleep(60 * 1000);
						} catch (InterruptedException e1) {
							log.error("{}", e1);
						}
						log.error("{}", e);
					}
				}
			}
		}).start();
		
	}

}
