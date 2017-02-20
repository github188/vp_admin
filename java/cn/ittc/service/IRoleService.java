package cn.ittc.service;

import java.util.List;

import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import net.sf.json.JSONObject;

public interface IRoleService {

	public void  addRole(Role role);
	/**
	 * 
	 * <p>Title: addRole</p>
	 * <p>Description: </p>
	 * @param role
	 * @param permissions
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月26日
	 */
	public boolean  addRole(Role role,List<Permission> permissions);
	public List<Role>  selectAllRoles();
	public void delectRole(Role role);
	/**
	 * 
	 * <p>Title: deleteRole</p>
	 * <p>Description: </p>
	 * @param role
	 * @author 焦冬冬
	 * @date 2016年8月26日
	 */
	public void deleteRole(Role role);
	public JSONObject selectAllRole();
	public Role addRoleByRoleName(Role role);
	public Role selectRoleByRoleName(Role role);
}
