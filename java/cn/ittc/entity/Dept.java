/**
 * <p>Title: Dept.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月2日
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
 * <p>Title: Dept</p>
 * <p>Description: 用户部门</p>
 * @author 焦冬冬
 * @date 2016年8月2日
 */
@Entity
@Table(name = "t_dept")
public class Dept {
	private Integer deptId;
	private String deptName;
	
//	private Set<User> users= new HashSet<User>();
	
	private List<User> users=new ArrayList<User>();
	
	@Id
	//采用数据库自增方式生成主键
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue
	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	
	@Column(name="deptname", length=30, nullable=false, unique=true)
	public String getDeptName() {
		return deptName;
	}
	
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dept")
//	@Transient
	public List<User> getUsers() {
		return users;
	}

	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dept")
//	@Transient
//	public Set<User> getUsers() {
//		return users;
//	}
//	
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
	
	
	

}
