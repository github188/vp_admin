/**
 * <p>Title: WechatAsk.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
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
 * @author ������
 * @date 2016��8��31��
 */
@Entity
@Table(name = "t_wechatask")
public class WechatAsk {
	private int id;
	private String askInfo;//ƥ����Ϣ
	private String title;//����
	// ͼ����Ϣ����
	private String content;
	// ͼƬ���ӣ�֧��JPG��PNG��ʽ���Ϻõ�Ч��Ϊ��ͼ640*320��Сͼ80*80������ͼƬ���ӵ�������Ҫ�뿪������д�Ļ��������е�Urlһ��
	private String picUrl;
	// ���ͼ����Ϣ��ת����
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
