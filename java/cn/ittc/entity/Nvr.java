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
 * @author ����
 * @date 2016��8��3��
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
	private String brand;//Ʒ��
	private String storage;//Ӳ�̴�С
//	private Integer channelId;//ͨ����

	// private Ipc ipc2;

	private List<NvrIpc> nvrIpcs = new ArrayList<NvrIpc>();

	// ���
	@Id
	@GeneratedValue
	public Integer getNvrId() {
		return nvrId;
	}

	public void setNvrId(Integer nvrId) {
		this.nvrId = nvrId;
	}

	// ��¼NVR��IP��ַ
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

	// �˿ں�
	@Column(name = "nvrPort", length = 30, nullable = true, unique = false)
	public Integer getNvrPort() {
		return nvrPort;
	}

	public void setNvrPort(Integer nvrPort) {
		this.nvrPort = nvrPort;
	}

	// ��¼��
	@Column(name = "nvrLoginName", length = 30, nullable = true, unique = false)
	public String getNvrLoginName() {
		return nvrLoginName;
	}

	public void setNvrLoginName(String nvrLoginName) {
		this.nvrLoginName = nvrLoginName;
	}

	// ��½����
	@Column(name = "nvrLoginPwd", length = 30, nullable = true, unique = false)
	public String getNvrLoginPwd() {
		return nvrLoginPwd;
	}

	public void setNvrLoginPwd(String nvrLoginPwd) {
		this.nvrLoginPwd = nvrLoginPwd;
	}

	// Ʒ��
	@Column(name = "brand", length = 30, nullable = true, unique = false)
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	// ʣ��洢�ռ�
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
