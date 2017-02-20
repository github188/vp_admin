/**
 * <p>Title: WechatMenu.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: Consmation</p>
 * @author ç„¦å†¬å†?
 * @date 2016å¹?æœ?4æ—?
 * @version 1.0.0
 */
package cn.ittc.weixin.utils;

/**
 * <p>Title: WechatMenu</p>
 * <p>Description: </p>
 * @author ç„¦å†¬å†?
 * @date 2016å¹?æœ?4æ—?
 */
public enum WechatMenu {
	LOG("log", 1), GPS("gps", 2), BLM("blm", 3), TRACE("trace", 4),RBAC("rbac",5);
	private String name;
	private int index;

	private WechatMenu(String name, int index) {
		this.name = name;
		this.index = index;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	  
	  

}
