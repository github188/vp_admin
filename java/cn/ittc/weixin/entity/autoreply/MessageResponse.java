/**
 * <p>Title: MessageResponse.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.entity.autoreply;

import java.util.Date;
import java.util.List;

import cn.ittc.weixin.entity.msg.Article;
import cn.ittc.weixin.entity.msg.NewsMessage;
import cn.ittc.weixin.entity.msg.TextMessage;
import cn.ittc.weixin.utils.MessageUtil;

/**
 * <p>Title: MessageResponse</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
public class MessageResponse {
	/**
	 * �ظ��ı���Ϣ
	 * @param fromUserName
	 * @param toUserName
	 * @param respContent
	 * @return
	 */
	public static String getTextMessage(String fromUserName , String toUserName , String respContent) {
		try {
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			
			textMessage.setContent(respContent);
			return MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * ����ͼ����Ϣ
	 * @param fromUserName
	 * @param toUserName
	 * @param articleList
	 * @return
	 */
	public static String getNewsMessage(String fromUserName , String toUserName , List<Article> articleList) {
		
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setFuncFlag(0);
		
		// ����ͼ����Ϣ����
		newsMessage.setArticleCount(articleList.size());
		// ����ͼ����Ϣ������ͼ�ļ���
		newsMessage.setArticles(articleList);
		// ��ͼ����Ϣ����ת����xml�ַ���
		return MessageUtil.newsMessageToXml(newsMessage);
	}
}

