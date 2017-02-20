/**
 * <p>Title: Role.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��7��20��
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
 * <p>Description:Ȩ�ޱ� </p>
 * @author ��֦��
 * @date 2016��8��1��
 */

@Entity
@Table(name = "t_role")
public class Role {
	
	private Integer roleId;
	private String roleName;
	

	private List<User> users=new ArrayList<User>();
	private List<RolePermission> rolepermissions = new ArrayList<RolePermission>();
	
	
	@Id
	@GeneratedValue	//�������ݿ�������ʽ��������
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

	//����������cascade = CascadeType.ALL
	//�ӳټ��أ�fetch = FetchType.LAZY
	//ӳ�䣺mappedBy = "category"
	//һ�Զ෽ʽ
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
