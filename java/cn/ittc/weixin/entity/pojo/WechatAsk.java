/**
 * <p>Title: WechatAsk.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月31日
 * @version 1.0.0
 */
package cn.ittc.weixin.entity.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Title: WechatAsk</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月31日
 */
@Entity
@Table(name = "t_wechatask")
public class WechatAsk {
	private int id;
	private String askInfo;//匹配信息
	private String title;//标题
	// 图文消息描述
	private String content;
	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	private String picUrl;
	// 点击图文消息跳转链接
	private String url;
	private String typeName;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	@Column(name="askinfo", length=30, nullable=false, unique=false)
	public String getAskInfo() {
		return askInfo;
	}


	public void setAskInfo(String askInfo) {
		this.askInfo = askInfo;
	}

	@Column(name="title", length=50, nullable=true, unique=false)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="content", length=300, nullable=true, unique=false)
	public String getContent() {
		return content;
	}

	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="picurl", length=300, nullable=true, unique=false)
	public String getPicUrl() {
		return picUrl;
	}
	


	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Column(name="url", length=300, nullable=true, unique=false)
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="typename", length=30, nullable=true, unique=false)
	public String getTypeName() {
		return typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	

}
