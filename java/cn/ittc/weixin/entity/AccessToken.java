/**
 * <p>Title: AccessToken.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��30��
 * @version 1.0.0
 */
package cn.ittc.weixin.entity;

/**
 * <p>Title: AccessToken</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��30��
 */
public class AccessToken {
	// ��ȡ����ƾ֤  
    private String accessToken="put in";  
    // ƾ֤��Чʱ�䣬��λ��s 
    private int expiresIn=7000;  
    
    public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		System.out.println(accessToken);
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {  
        return expiresIn;  
    }  
  
    public void setExpiresIn(int expiresIn) {  
        this.expiresIn = expiresIn;  
    }  
}  