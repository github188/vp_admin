/**
 * <p>Title: LocMenu.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月23日
 * @version 1.0.0
 */
package cn.ittc.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * <p>Title: LocMenu</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月23日
 */
@Entity
@Table(name="t_locmenu")
public class LocMenu {
	
	private Integer id;//主键
	private String name;
	private Integer grade; //菜单等级  
    private Integer morder; //同一级菜单中的顺序
    private LocMenu parent;  
    //子菜单列表  
    private List<LocMenu> children = new ArrayList<LocMenu>();
    
    private List<Ipc> ipcs=new ArrayList<Ipc>();
	
    @Id
	@GeneratedValue//PK，自增长
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(nullable = false, unique = true) 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column(nullable = true, unique = false) 
	public Integer getGrade() {
		return grade;
	}
	
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	@Column(nullable = true, unique = false) 
	public Integer getMorder() {
		return morder;
	}

	public void setMorder(Integer morder) {
		this.morder = morder;
	}
	
	@ManyToOne
	@JoinColumn(name="pid")
	public LocMenu getParent() {
		return parent;
	}

	public void setParent(LocMenu parent) {
		this.parent = parent;
	}

	@OneToMany(targetEntity=LocMenu.class,mappedBy="parent")
	@OrderBy("morder")
	public List<LocMenu> getChildren() {
		return children;
	}

	public void setChildren(List<LocMenu> children) {
		this.children = children;
	}

	@ManyToMany(targetEntity=Ipc.class)
	@JoinTable(
			name = "t_menu_ipc", 
			joinColumns = @JoinColumn(name = "id"), 
			inverseJoinColumns = @JoinColumn(name = "cameraId"))
	public List<Ipc> getIpcs() {
		return ipcs;
	}


	public void setIpcs(List<Ipc> ipcs) {
		this.ipcs = ipcs;
	} 
    
    
    
    
}
