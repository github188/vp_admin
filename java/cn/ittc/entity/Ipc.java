package cn.ittc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <p>Title: Ipc</p>
 * <p> Description:IPC设备表</p>
 * @author 彭坤
 * @date 2016年8月5日
 */
@Entity
@Table(name = "t_ipc")
public class Ipc {
	private Integer cameraId;
	private String cameraName;
	private String cameraIp;
	private Integer cameraPort;
	private String cameraLoginName;
	private String cameraPwd;
	private String cameraMac;

	private Integer channelId;
	private List<NvrIpc> nvrIpcs = new ArrayList<NvrIpc>();
	
	private List<Loc> locs=new ArrayList<Loc>();


	public Ipc(Integer cameraId,String cameraName) {
		this.cameraId=cameraId;
		this.cameraName=cameraName;
	}

	public Ipc() {

	}
	
	@Id
	@GeneratedValue // 采用数据库自增方式生成主键
	public Integer getCameraId() {
		return cameraId;
	}

	public void setCameraId(Integer cameraId) {
		this.cameraId = cameraId;
	}

	@Column(name = "cameraName", length = 30, nullable = true, unique = false)
	public String getCameraName() {
		return cameraName;
	}

	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}

	@Column(name = "cameraIp", length = 30, nullable = true, unique = false)
	public String getCameraIp() {
		return cameraIp;
	}

	public void setCameraIp(String cameraIp) {
		this.cameraIp = cameraIp;
	}

	@Column(name = "cameraPort", length = 30, nullable = true, unique = false)
	public Integer getCameraPort() {
		return cameraPort;
	}

	public void setCameraPort(Integer cameraPort) {
		this.cameraPort = cameraPort;
	}

	@Column(name = "cameraLoginName", length = 30, nullable = false, unique = false)
	public String getCameraLoginName() {
		return cameraLoginName;
	}

	public void setCameraLoginName(String cameraLoginName) {
		this.cameraLoginName = cameraLoginName;
	}

	@Column(name = "cameraPwd", length = 30, nullable = false, unique = false)
	public String getCameraPwd() {
		return cameraPwd;
	}

	public void setCameraPwd(String cameraPwd) {
		this.cameraPwd = cameraPwd;
	}

	@Column(name = "cameraMac", length = 30, nullable = true, unique = false)
	public String getCameraMac() {
		return cameraMac;
	}

	public void setCameraMac(String cameraMac) {
		this.cameraMac = cameraMac;
	}

	@Column(name = "channelId", length = 30, nullable = true, unique = false)
	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}


	// @ManyToMany(targetEntity=Nvr.class,mappedBy="ipcs",cascade=CascadeType.ALL)
	// @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.REFRESH})
	// @JoinTable(
	// name = "t_nvr_ipc",
	// joinColumns = @JoinColumn(name = "cameraId"),
	// inverseJoinColumns = @JoinColumn(name = "nvrId"))
//	@Transient
	@OneToMany(mappedBy="ipc")
	public List<NvrIpc> getNvrIpcs() {
		return nvrIpcs;
	}


	public void setNvrIpcs(List<NvrIpc> nvrIpcs) {
		this.nvrIpcs = nvrIpcs;
	}

	@ManyToMany(mappedBy="ipcs")
	public List<Loc> getLocs() {
		return locs;
	}

	public void setLocs(List<Loc> locs) {
		this.locs = locs;
	}

	

	

}
