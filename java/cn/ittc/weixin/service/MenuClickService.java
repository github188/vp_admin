/**
 * <p>Title: MenuClickService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.service;

import cn.ittc.weixin.entity.autoreply.MessageResponse;
import cn.ittc.weixin.reboot.TulingApiProcess;

/**
 * <p>Title: MenuClickService</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
public class MenuClickService {
	/**
	 * 
	 * ������@param eventKey
	 * ������@param fromUserName
	 * ������@param toUserName
	 * ������@return �����û�����¼���ͨ��΢�����͸��û���Ϣ����תҳ�棬������Ϣ��
	 * ���ߣ�herosky
	 */
	public static String getClickResponse(String eventKey, String fromUserName,
			String toUserName) {
		System.out.println(eventKey);
		
		// TODO �ж�evetKey�¼�����
		if(eventKey.equals("test"))
		{
			
		}else if(eventKey.equalsIgnoreCase("")){
			
		}else{
			System.out.println("�Զ��尴ť��"+eventKey);
		}
		
		String str= TulingApiProcess.getTulingResult(eventKey);
		return MessageResponse.getTextMessage(fromUserName,toUserName,str);
	}

}
