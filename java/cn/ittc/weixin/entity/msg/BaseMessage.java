/**
 * <p>Title: BaseMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.entity.msg;

/**
 * <p>Title: BaseMessage</p>
 * <p>Description: </p>
 * @author ������
 * @date 2015��12��7��
 */
public class  BaseMessage {
	
	// ���շ��ʺţ��յ���OpenID��
    private String ToUserName;  
    // ������΢�ź�  
    private String FromUserName;  
    // ��Ϣ����ʱ�� �����ͣ�  
    private long CreateTime;  
    // ��Ϣ���ͣ�text/image/voice/video/shortvideo/location/link/music/news)
    private String MsgType;  
    // x0001����־ʱ���Ǳ���յ�����
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
