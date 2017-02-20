/**
 * <p>Title: User.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月11日
 * @version 1.0.0
 */
package cn.ittc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * <p>Title: User</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月11日
 */
@Entity
@Table(name = "t_user")
public class User {
	
	private Integer userId;
	private String userName;
	private String realName;
	private String password;
	private String position;//职位 
	
	private int superAdmin =0;//超級管理員 0是否，1是

	

	private int flag =1;//是否删除，0删除，1可用

	private Role role;
	

	private Dept dept;
	
	public User(){}
	
	public User(String userName,String password){
		this.userName=userName;
		this.password=password;
	}
	
	@Id
	@GeneratedValue
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	@Column(name="username", length=30, nullable=false, unique=true)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="realname", length=30, nullable=false, unique=true)
	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	@Column(name="password", length=20, nullable=false)
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonIgnore
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	@Column(name="position", length=10)
	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}




	@Column(name="superAdmin", length=11,columnDefinition="INT default 0")
	public int getSuperAdmin() {
		return superAdmin;
	}


	public void setSuperAdmin(int superAdmin) {
		this.superAdmin = superAdmin;
	}

	@Column(name="flag", length=11,columnDefinition="INT default 1")
	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	//延迟加载：多对一方式
	//关联信息：外键name = "roleId"
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleId")
	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptId")
	public Dept getDept() {
		return dept;
	}




	public void setDept(Dept dept) {
		this.dept = dept;
	}
	
	
	
}
