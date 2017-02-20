/**
 * <p>Title: MenuClickService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月31日
 * @version 1.0.0
 */
package cn.ittc.weixin.service;

import cn.ittc.weixin.entity.autoreply.MessageResponse;
import cn.ittc.weixin.reboot.TulingApiProcess;

/**
 * <p>Title: MenuClickService</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月31日
 */
public class MenuClickService {
	/**
	 * 
	 * 描述：@param eventKey
	 * 描述：@param fromUserName
	 * 描述：@param toUserName
	 * 描述：@return 接受用户点击事件，通过微信推送给用户消息，跳转页面，发送消息等
	 * 作者：herosky
	 */
	public static String getClickResponse(String eventKey, String fromUserName,
			String toUserName) {
		System.out.println(eventKey);
		
		// TODO 判断evetKey事件处理
		if(eventKey.equals("test"))
		{
			
		}else if(eventKey.equalsIgnoreCase("")){
			
		}else{
			System.out.println("自定义按钮："+eventKey);
		}
		
		String str= TulingApiProcess.getTulingResult(eventKey);
		return MessageResponse.getTextMessage(fromUserName,toUserName,str);
	}

}
