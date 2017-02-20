/**
 * <p>Title: CoreService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.ittc.weixin.entity.autoreply.MessageResponse;
import cn.ittc.weixin.entity.msg.Article;
import cn.ittc.weixin.entity.msg.NewsMessage;
import cn.ittc.weixin.entity.pojo.WechatAsk;
import cn.ittc.weixin.reboot.TulingApiProcess;
import cn.ittc.weixin.utils.MessageUtil;

/**
 * <p>Title: CoreService</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
@Service(value ="coreService")
public class CoreService implements ICoreService{
//	static Logger logger = LoggerFactory.getLogger(CoreService.class);
	
	@Resource
	private  IAskService askService;

	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return
	 */
	public  String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// Ĭ�Ϸ��ص��ı���Ϣ����
			String respContent = "�������쳣�����Ժ��ԣ�";

			// xml�������
			// ������Ϣ������MessageUtil����΢�ŷ�����xml��ʽ����Ϣ�������Ľ������HashMap�
			Map<String, String> requestMap = MessageUtil.parseXml(request);

//			String url=requestMap.get("url");
//			System.out.println(url);
//			System.out.println(requestMap.get("MsgId"));
			// ��HashMap��ȡ����Ϣ�е��ֶΣ�
			// ���ͷ��ʺţ�open_id��
			String fromUserName = requestMap.get("FromUserName");
			// �����ʺ�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");
			// ��Ϣ����
			String content = requestMap.get("Content");
			// ��HashMap��ȡ����Ϣ�е��ֶΣ�
			System.out.println("fromUserName is:" +fromUserName+" toUserName is:" +toUserName+" msgType is:" +msgType);
//			logger.info("fromUserName is:" +fromUserName+" toUserName is:" +toUserName+" msgType is:" +msgType);

			//����ͼ����Ϣ  
            NewsMessage newsMessage = new NewsMessage();  
            newsMessage.setToUserName(fromUserName);  
            newsMessage.setFromUserName(toUserName);  
            newsMessage.setCreateTime(new Date().getTime());  
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            newsMessage.setFuncFlag(0);
			List<Article> articleList = null; 
			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//΢����������˲��� 2015-3-31
				if (content != null) {
					List<WechatAsk> wechatAsks = askService.getWechatAsks(content);
					if (wechatAsks == null || wechatAsks.size() == 0) {
						respContent = TulingApiProcess.getTulingResult(content);
					} else {
						String typeName = wechatAsks.get(0).getTypeName();
						if ("text".equalsIgnoreCase(typeName)) {//����
							return MessageResponse.getTextMessage(fromUserName, toUserName,
									wechatAsks.get(0).getContent());
						} else if ("news".equalsIgnoreCase(typeName)) {//ͼ��
							articleList = new ArrayList<Article>();
							for (WechatAsk wechatAsk : wechatAsks) {
								if (wechatAsk.getTypeName().equalsIgnoreCase("news")) {
									Article article = new Article();
									article.setTitle(wechatAsk.getTitle());
									article.setDescription(wechatAsk.getContent());
									article.setPicUrl(wechatAsk.getPicUrl());
									article.setUrl(wechatAsk.getUrl());
									articleList.add(article);
								}
								
							}
							// ����ͼ����Ϣ����
							newsMessage.setArticleCount(articleList.size());
							// ����ͼ����Ϣ������ͼ�ļ���
							newsMessage.setArticles(articleList);
							// ��ͼ����Ϣ����ת����xml�ַ���
							respMessage = MessageUtil.newsMessageToXml(newsMessage);
							return respMessage;
						}
					}
				}

					if (respContent == "" || null == respContent) {
						MessageResponse.getTextMessage(fromUserName,
								toUserName, "�������ʱ�޷��ظ������Ժ����ԣ�");
					}
					// return FormatXmlProcess.formatXmlAnswer(toUserName,
					// fromUserName, respContent);
					return MessageResponse.getTextMessage(fromUserName,
							toUserName, respContent);
//				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// �¼�����
				String eventType = requestMap.get("Event");// �¼�����

				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// ����(��ע)
					respContent = "��ӭ��ע��Լ�ɽ��󣬻ظ��ؼ��֣���ȡ��Ϣ��";
					return MessageResponse.getTextMessage(fromUserName,
							toUserName, respContent);
//					
////					System.out.println(WechatUtil.getUserByOpenId(fromUserName));
//					
//					userService.addWechatUser(WechatUtil.getUserByOpenId(fromUserName));
//					
//					Article article = new Article();  
//		            article.setTitle("��ӭ��ע����װ��ȫ����׷�ٷ���ƽ̨");  
//		            article.setDescription("��ӭ��ע������������������������Ŀ��");  
//		            article.setPicUrl("http://www.ittc.sh.cn/shjm/images/dalogo.jpg");  
//		            article.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMjgxOTUyNQ==&mid=401394556&idx=1&sn=ff3d5e54c96ff4ae92c702452f361dbb#rd");  
//		            articleList.add(article);  
//		            // ����ͼ����Ϣ����
//		            newsMessage.setArticleCount(articleList.size());  
//		            // ����ͼ����Ϣ������ͼ�ļ���  
//		            newsMessage.setArticles(articleList);  
//		            // ��ͼ����Ϣ����ת����xml�ַ���  
//		            respMessage = MessageUtil.newsMessageToXml(newsMessage);
//		            return respMessage;
//
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// ȡ������
					// TODO ȡ�����ĺ��û����ղ������ںŷ��͵���Ϣ����˲���Ҫ�ظ���Ϣ
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// �Զ���˵�����¼�
//					String eventKey = requestMap.get("EventKey");// �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ
////					logger.info("eventKey is:" + eventKey);
//					return MenuClickService.getClickResponse(eventKey,
//							fromUserName, toUserName);
//				
				}else if(eventType.equalsIgnoreCase(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){//������Ϣ
//					String lat=requestMap.get("Latitude");
//					String lon=requestMap.get("Longitude");
//					WechatUser wechatUser=new WechatUser();
//					wechatUser.setOpenId(fromUserName);
//					wechatUser.setLat(lat);
//					wechatUser.setLon(lon);
//					System.out.println("���� lon"+lon+",γ��lat "+lat);
//					userService.updateLocal(wechatUser);
				}
			}
			//����΢������ʶ����� 2015-3-30
			else if(msgType.equals("voice"))
			{
				String recvMessage = requestMap.get("Recognition");
				//respContent = "�յ����������������"+recvMessage;
				if(recvMessage!=null){
					respContent = TulingApiProcess.getTulingResult(recvMessage);
				}else{
					respContent = "��˵��̫ģ���ˣ��ܲ�������˵���أ�";
				}
				return MessageResponse.getTextMessage(fromUserName , toUserName , respContent); 
			}
			//���չ���
			else if(msgType.equals("pic_sysphoto"))
			{
				
			}
			else
			{
				return MessageResponse.getTextMessage(fromUserName , toUserName , "����Ϊ��"); 
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
