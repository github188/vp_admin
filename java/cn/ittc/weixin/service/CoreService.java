/**
 * <p>Title: CoreService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月31日
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
 * @author 焦冬冬
 * @date 2016年8月31日
 */
@Service(value ="coreService")
public class CoreService implements ICoreService{
//	static Logger logger = LoggerFactory.getLogger(CoreService.class);
	
	@Resource
	private  IAskService askService;

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public  String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			// 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> requestMap = MessageUtil.parseXml(request);

//			String url=requestMap.get("url");
//			System.out.println(url);
//			System.out.println(requestMap.get("MsgId"));
			// 从HashMap中取出消息中的字段；
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息内容
			String content = requestMap.get("Content");
			// 从HashMap中取出消息中的字段；
			System.out.println("fromUserName is:" +fromUserName+" toUserName is:" +toUserName+" msgType is:" +msgType);
//			logger.info("fromUserName is:" +fromUserName+" toUserName is:" +toUserName+" msgType is:" +msgType);

			//创建图文消息  
            NewsMessage newsMessage = new NewsMessage();  
            newsMessage.setToUserName(fromUserName);  
            newsMessage.setFromUserName(toUserName);  
            newsMessage.setCreateTime(new Date().getTime());  
            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
            newsMessage.setFuncFlag(0);
			List<Article> articleList = null; 
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//微信聊天机器人测试 2015-3-31
				if (content != null) {
					List<WechatAsk> wechatAsks = askService.getWechatAsks(content);
					if (wechatAsks == null || wechatAsks.size() == 0) {
						respContent = TulingApiProcess.getTulingResult(content);
					} else {
						String typeName = wechatAsks.get(0).getTypeName();
						if ("text".equalsIgnoreCase(typeName)) {//文字
							return MessageResponse.getTextMessage(fromUserName, toUserName,
									wechatAsks.get(0).getContent());
						} else if ("news".equalsIgnoreCase(typeName)) {//图文
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
							// 设置图文消息个数
							newsMessage.setArticleCount(articleList.size());
							// 设置图文消息包含的图文集合
							newsMessage.setArticles(articleList);
							// 将图文消息对象转换成xml字符串
							respMessage = MessageUtil.newsMessageToXml(newsMessage);
							return respMessage;
						}
					}
				}

					if (respContent == "" || null == respContent) {
						MessageResponse.getTextMessage(fromUserName,
								toUserName, "服务号暂时无法回复，请稍后再试！");
					}
					// return FormatXmlProcess.formatXmlAnswer(toUserName,
					// fromUserName, respContent);
					return MessageResponse.getTextMessage(fromUserName,
							toUserName, respContent);
//				}
			} else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
				String eventType = requestMap.get("Event");// 事件类型

				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅(关注)
					respContent = "欢迎关注相约仙交大，回复关键字，获取信息！";
					return MessageResponse.getTextMessage(fromUserName,
							toUserName, respContent);
//					
////					System.out.println(WechatUtil.getUserByOpenId(fromUserName));
//					
//					userService.addWechatUser(WechatUtil.getUserByOpenId(fromUserName));
//					
//					Article article = new Article();  
//		            article.setTitle("欢迎关注警务装备全生命追踪服务平台");  
//		            article.setDescription("欢迎关注安防技术防范技术部科研项目组");  
//		            article.setPicUrl("http://www.ittc.sh.cn/shjm/images/dalogo.jpg");  
//		            article.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMjgxOTUyNQ==&mid=401394556&idx=1&sn=ff3d5e54c96ff4ae92c702452f361dbb#rd");  
//		            articleList.add(article);  
//		            // 设置图文消息个数
//		            newsMessage.setArticleCount(articleList.size());  
//		            // 设置图文消息包含的图文集合  
//		            newsMessage.setArticles(articleList);  
//		            // 将图文消息对象转换成xml字符串  
//		            respMessage = MessageUtil.newsMessageToXml(newsMessage);
//		            return respMessage;
//
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
//					String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
////					logger.info("eventKey is:" + eventKey);
//					return MenuClickService.getClickResponse(eventKey,
//							fromUserName, toUserName);
//				
				}else if(eventType.equalsIgnoreCase(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){//地理信息
//					String lat=requestMap.get("Latitude");
//					String lon=requestMap.get("Longitude");
//					WechatUser wechatUser=new WechatUser();
//					wechatUser.setOpenId(fromUserName);
//					wechatUser.setLat(lat);
//					wechatUser.setLon(lon);
//					System.out.println("经度 lon"+lon+",纬度lat "+lat);
//					userService.updateLocal(wechatUser);
				}
			}
			//开启微信声音识别测试 2015-3-30
			else if(msgType.equals("voice"))
			{
				String recvMessage = requestMap.get("Recognition");
				//respContent = "收到的语音解析结果："+recvMessage;
				if(recvMessage!=null){
					respContent = TulingApiProcess.getTulingResult(recvMessage);
				}else{
					respContent = "您说的太模糊了，能不能重新说下呢？";
				}
				return MessageResponse.getTextMessage(fromUserName , toUserName , respContent); 
			}
			//拍照功能
			else if(msgType.equals("pic_sysphoto"))
			{
				
			}
			else
			{
				return MessageResponse.getTextMessage(fromUserName , toUserName , "返回为空"); 
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
