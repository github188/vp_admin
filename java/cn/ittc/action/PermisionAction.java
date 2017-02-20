package cn.ittc.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.service.IPermissionService;
import net.sf.json.JSONObject;

@Controller(value="permissionAciton")
public class PermisionAction extends ActionSupport{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private IPermissionService permissionService ;
	
	private String jsonData ;
	private Role role;
	private Permission permission ;
	private List<Permission> list ;
	private int pageNum =1;
	private int pageSize =10;
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	public List<Permission> getList() {
		return list;
	}
	public void setList(List<Permission> list) {
		this.list = list;
	}
	
	/**
	 * 
	 * <p>Title: selectAllPermission</p>
	 * <p>Description: 查询所有的角色权限</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月26日
	 */
	public String selectAllPermission() {

		try {
			JSONObject jsonObject = permissionService.selectAllPermission();
			jsonData = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}
	
	public String selectPermissionByRidUid() {
		try {
			if (role==null||role.getRoleId() == null) {
				JSONObject jsonObject = permissionService.selectPermissionByRidUid(pageNum, pageSize);
				jsonData = jsonObject.toString();
			} else {
				JSONObject jsonObject = permissionService.selectPermissionById(role, pageNum, pageSize);
				jsonData = jsonObject.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		pageSize = 10;
		pageNum = 1;
		return SUCCESS;
	}

	
	public String getPermissionTree(){
		
		JSONObject jsonObject =permissionService.getPermissionTree();
		jsonData=jsonObject.toString();
		System.out.println(jsonData);

		return SUCCESS;
		
		
	}
	
	public String goByRole(){
		return SUCCESS;
	}
}
