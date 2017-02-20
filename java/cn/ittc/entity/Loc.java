package cn.ittc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * <p>Title: Location</p>
 * <p>Description: ipc区域</p>
 * @author 彭坤
 * @date 2016年8月10日
 */
@Entity
@Table(name="t_loc")
public class Loc {
	private Integer locId;
	private String locName;
	private List<Ipc> ipcs = new ArrayList<Ipc>();
	
	@Id
	@GeneratedValue//PK，自增长
	public Integer getLocId() {
		return locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}
	
	@Column(name="locName",length=30,nullable=false, unique=true)
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}

	
	
	@ManyToMany(targetEntity = Ipc.class, fetch = FetchType.EAGER)
	@JoinTable(
			name = "t_loc_ipc", 
			joinColumns = @JoinColumn(name = "locId"), 
			inverseJoinColumns = @JoinColumn(name = "cameraId"))
	public List<Ipc> getIpcs() {
		return ipcs;
	}
	public void setIpcs(List<Ipc> ipcs) {
		this.ipcs = ipcs;
	}
	
	
	
	
	
	
	
}
