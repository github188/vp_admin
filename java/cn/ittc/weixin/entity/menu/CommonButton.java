/**
 * <p>Title: CommonButton.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: Consmation</p>
 * @author 焦冬冬
 * @date 2015年12月7日
 * @version 1.0.0
 */
package cn.ittc.weixin.entity.menu;

import cn.ittc.weixin.entity.menu.Button;

/**
 * <p>Title: CommonButton</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2015年12月7日
 */
public class CommonButton extends Button{
	
	private String type;
	private String key;
	private String url;
	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	
}
