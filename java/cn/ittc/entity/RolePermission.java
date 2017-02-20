package cn.ittc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <p>Title: RolePermission</p>
 * <p>Description: </p>
 * @author —Ó÷¶”Í
 * @date 2016ƒÍ8‘¬22»’
 */

@Entity
@Table(name="t_role_permission")
public class RolePermission {

	private Integer id ;
	private Role role ;
	private Permission permission;
	
	public RolePermission(Role role, Permission permission) {
		this.role = role;
		this.permission = permission;

	}

	public RolePermission() {

	}
	
	
	@Id
    @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="roleId")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne
	@JoinColumn(name="permissionId")
	public Permission getPermission(){
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	
	
}
