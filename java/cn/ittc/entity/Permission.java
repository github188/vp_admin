package cn.ittc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <p>Title: Permission</p>
 * <p>Description:权限表 </p>
 * @author 杨枝雨
 * @date 2016年8月1日
 */


@Entity 
@Table(name="t_permission")
public class Permission {

	private int permissionId;
	private String  permissionName;
	private List<RolePermission> rolepermissions = new ArrayList<RolePermission>();
	
	
	
	@Id
	@GeneratedValue	//采用数据库自增方式生成主键
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	
	@Column (name="permissionName",length=20,nullable=false,unique=true)
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "permission")
	public List<RolePermission> getRolepermissions() {
		return rolepermissions;
	}
	public void setRolepermissions(List<RolePermission> rolepermissions) {
		this.rolepermissions = rolepermissions;
	}

	
	



	

	
		
}
