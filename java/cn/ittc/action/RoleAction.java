package cn.ittc.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.entity.User;
import cn.ittc.service.IRolePermissionService;
import cn.ittc.service.IRoleService;
import cn.ittc.service.IUserService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * <p>Title: RoleAction</p>
 * <p>Description:角色控制 </p>
 * @author 杨枝雨
 * @date 2016年8月1日
 */
 
@Controller(value="roleAction")
public class RoleAction extends ActionSupport {
	
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Resource
	private IRoleService roleService;
	@Resource 
	private IRolePermissionService rolepermissionService;
	@Resource 
	private IUserService userService ;
	
    private Role role;
	private List<Role> list;
	private List<Permission> permissions;
	private String jsonData;
	private String permissionId;
	private Permission permission ;
	private User user;
	
	
	
	


	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getList() {
		return list;
	}

	public void setList(List<Role> list) {
		this.list = list;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

		
		
	/**
	 * 
	 * <p>Title: addRole</p>
	 * <p>Description: 新增角色</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月26日
	 */
	public String addRole() {
		JSONObject jsonObject=new JSONObject();
		jsonObject.element("success", roleService.addRole(role,permissions));
		jsonData=jsonObject.toString();
		permissions=new ArrayList<Permission>();
		return "success";
	}

	public String getAllRole() {
		try {

			JSONObject jsonObject = roleService.selectAllRole();

			jsonData = jsonObject.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	
	}
		
		
	/**
	 * 
	 * <p>Title: createRolePermission</p>
	 * <p>Description: 权限修改模块里面新增角色权限功能</p>
	 * @return
	 * @author 杨枝雨
	 * @date 2016年8月23日
	 */
	public String createRolePermission() {
		// 新建传过来的角色名
		Role roles = roleService.addRoleByRoleName(role);
		// 获取权限id数组
		String permissionid = this.getPermissionId();

		String [] permissionidOut =permissionid.split(",");
		
		
		List<Permission> perlist= new ArrayList<Permission>();
		
		for (int i = 0; i < permissionidOut.length; i++) {
			Permission per = new Permission();

			per.setPermissionId(Integer.parseInt(permissionidOut[i].trim()));
			perlist.add(i, per);

		}

		rolepermissionService.addRolePermission(roles, perlist);
		return SUCCESS;

	}
		
	/**
	 * 
	 * <p>Title: deleteRolePermission</p>
	 * <p>Description: 权限修改模块里删除功能</p>
	 * @return
	 * @author 杨枝雨
	 * @date 2016年8月25日
	 */
	public String delectRolePermission() {
		
		
		roleService.deleteRole(role);

		return SUCCESS;

	}
		
		public String updataRolePermission(){
			rolepermissionService.deleteRolePermissionByRoleName(role);
			String permissionid = this.getPermissionId();
			System.out.println(role.getRoleId()+":"+role.getRoleName());
			System.out.println(permissionid);
			String [] permissionidOut =permissionid.split(",");
			
			
			List<Permission> perlist= new ArrayList<Permission>();
			for(int i =0;i<permissionidOut.length;i++)
			{
				Permission per =new Permission();
				per.setPermissionId(Integer.parseInt(permissionidOut[i].trim()));
				perlist.add(i, per);
				
			}
			
			
			rolepermissionService.addRolePermission(role, perlist);
				return SUCCESS;
		}
		
		public String selectRolePermissionByRoleId(){
			
			JSONObject jsonObject=new JSONObject();
			JsonConfig jsonConfig=new JsonConfig();
			//jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			jsonObject.element("success", rolepermissionService.selectByRoleId(role));
			jsonData=jsonObject.toString();
			System.out.println(jsonData);
			return SUCCESS;
		}
}
