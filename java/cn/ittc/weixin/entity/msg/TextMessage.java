/**
 * <p>Title: TextMessage.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.entity.msg;

/**
 * <p>Title: TextMessage</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
public class TextMessage extends BaseMessage{

	// ��Ϣ����  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }   
}
