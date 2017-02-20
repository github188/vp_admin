/**
 * <p>Title: Role.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月20日
 * @version 1.0.0
 */
package cn.ittc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "t_role")
public class Role {
	
	private Integer roleId;
	private String roleName;
	

	private List<User> users=new ArrayList<User>();
	private List<RolePermission> rolepermissions = new ArrayList<RolePermission>();
	
	
	@Id
	@GeneratedValue	//采用数据库自增方式生成主键
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	@Column(name="rolename", length=30, nullable=false, unique=true)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	//级联操作：cascade = CascadeType.ALL
	//延迟加载：fetch = FetchType.LAZY
	//映射：mappedBy = "category"
	//一对多方式
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "role")
	//@Transient
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "role")
	public List<RolePermission> getRolepermissions() {
		return rolepermissions;
	}

	public void setRolepermissions(List<RolePermission> rolepermissions) {
		this.rolepermissions = rolepermissions;
	}


	
	
	
	
	

}
