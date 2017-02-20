package cn.ittc.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <p>Title: Nvr</p>
 * <p>Description:</p>
 * @author 彭坤
 * @date 2016年8月3日
 */
@Entity
@Table(name = "t_nvr")
public class Nvr implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Integer nvrId;
	private String nvrName;
	private String nvrIp;
	private Integer nvrPort;
	private String nvrLoginName;
	private String nvrLoginPwd;
	private String brand;//品牌
	private String storage;//硬盘大小
//	private Integer channelId;//通道号

	// private Ipc ipc2;

	private List<NvrIpc> nvrIpcs = new ArrayList<NvrIpc>();

	// 序号
	@Id
	@GeneratedValue
	public Integer getNvrId() {
		return nvrId;
	}

	public void setNvrId(Integer nvrId) {
		this.nvrId = nvrId;
	}

	// 记录NVR的IP地址
	@Column(name = "nvrIp", length = 30, nullable = true, unique = false)
	public String getNvrIp() {
		return nvrIp;
	}

	public void setNvrIp(String nvrIp) {
		this.nvrIp = nvrIp;
	}

	
	@Column(name = "nvrName", length = 30, nullable = true, unique = false)
	public String getNvrName() {
		return nvrName;
	}

	public void setNvrName(String nvrName) {
		this.nvrName = nvrName;
	}

	// 端口号
	@Column(name = "nvrPort", length = 30, nullable = true, unique = false)
	public Integer getNvrPort() {
		return nvrPort;
	}

	public void setNvrPort(Integer nvrPort) {
		this.nvrPort = nvrPort;
	}

	// 登录名
	@Column(name = "nvrLoginName", length = 30, nullable = true, unique = false)
	public String getNvrLoginName() {
		return nvrLoginName;
	}

	public void setNvrLoginName(String nvrLoginName) {
		this.nvrLoginName = nvrLoginName;
	}

	// 登陆密码
	@Column(name = "nvrLoginPwd", length = 30, nullable = true, unique = false)
	public String getNvrLoginPwd() {
		return nvrLoginPwd;
	}

	public void setNvrLoginPwd(String nvrLoginPwd) {
		this.nvrLoginPwd = nvrLoginPwd;
	}

	// 品牌
	@Column(name = "brand", length = 30, nullable = true, unique = false)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	// 剩余存储空间
	@Column(name = "storage", length = 30, nullable = true, unique = false)
	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}



	// @ManyToMany(targetEntity = Ipc.class, fetch = FetchType.EAGER)
	// @JoinTable(name = "t_nvr_ipc",
	// joinColumns = @JoinColumn(name = "nvrId"),
	// inverseJoinColumns = @JoinColumn(name = "cameraId"))
	// @ManyToMany(mappedBy="nvr",cascade={CascadeType.PERSIST,CascadeType.MERGE})
//	@Transient
	@OneToMany(mappedBy="nvr")
	public List<NvrIpc> getNvrIpcs() {
		return nvrIpcs;
	}


	public void setNvrIpcs(List<NvrIpc> nvrIpcs) {
		this.nvrIpcs = nvrIpcs;
	}




	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "cameraId") public Ipc getIpc2() { return ipc2; }
	 * 
	 * public void setIpc2(Ipc ipc2) { this.ipc2 = ipc2; }
	 */

}
