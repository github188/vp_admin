/**
 * <p>Title: LocMenu.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��23��
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
 * @author ������
 * @date 2016��8��23��
 */
@Entity
@Table(name="t_locmenu")
public class LocMenu {
	
	private Integer id;//����
	private String name;
	private Integer grade; //�˵��ȼ�  
    private Integer morder; //ͬһ���˵��е�˳��
    private LocMenu parent;  
    //�Ӳ˵��б�  
    private List<LocMenu> children = new ArrayList<LocMenu>();
    
    private List<Ipc> ipcs=new ArrayList<Ipc>();
	
    @Id
	@GeneratedValue//PK��������
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
