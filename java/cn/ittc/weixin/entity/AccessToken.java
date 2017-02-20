/**
 * <p>Title: AccessToken.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月30日
 * @version 1.0.0
 */
package cn.ittc.weixin.entity;

/**
 * <p>Title: AccessToken</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月30日
 */
public class AccessToken {
	// 获取到的凭证  
    private String accessToken="put in";  
    // 凭证有效时间，单位：s 
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