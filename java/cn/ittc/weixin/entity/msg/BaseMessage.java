/**
 * <p>Title: BaseMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月31日
 * @version 1.0.0
 */
package cn.ittc.weixin.entity.msg;

/**
 * <p>Title: BaseMessage</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2015年12月7日
 */
public class  BaseMessage {
	
	// 接收方帐号（收到的OpenID）
    private String ToUserName;  
    // 发送者微信号  
    private String FromUserName;  
    // 消息创建时间 （整型）  
    private long CreateTime;  
    // 消息类型（text/image/voice/video/shortvideo/location/link/music/news)
    private String MsgType;  
    // x0001被标志时，星标刚收到的消
    private int FuncFlag;  
  
    public String getToUserName() {  
        return ToUserName;  
    }  
  
    public void setToUserName(String toUserName) {  
        ToUserName = toUserName;  
    }  
  
    public String getFromUserName() {  
        return FromUserName;  
    }  
  
    public void setFromUserName(String fromUserName) {  
        FromUserName = fromUserName;  
    }  
  
    public long getCreateTime() {  
        return CreateTime;  
    }  
  
    public void setCreateTime(long createTime) {  
        CreateTime = createTime;  
    }  
  
    public String getMsgType() {  
        return MsgType;  
    }  
  
    public void setMsgType(String msgType) {  
        MsgType = msgType;  
    }  
  
    public int getFuncFlag() {  
        return FuncFlag;  
    }  
  
    public void setFuncFlag(int funcFlag) {  
        FuncFlag = funcFlag;  
    } 
}
