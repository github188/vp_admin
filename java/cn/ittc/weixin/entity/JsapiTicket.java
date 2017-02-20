/**
 * <p>Title: JsapiTicket.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月30日
 * @version 1.0.0
 */
package cn.ittc.weixin.entity;

/**
 * <p>Title: JsapiTicket</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月30日
 */
public class JsapiTicket {
	
	// 获取到的凭证
	private String jsapi_ticket;
	// 凭证有效时间，单位：秒
	private int expiresIn;
	/**
	 * @return jsapi_ticket
	 */
	public String getJsapi_ticket() {
		return jsapi_ticket;
	}
	/**
	 * @param jsapi_ticket the jsapi_ticket to set
	 */
	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}
	/**
	 * @return expiresIn
	 */
	public int getExpiresIn() {
		return expiresIn;
	}
	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	
}
