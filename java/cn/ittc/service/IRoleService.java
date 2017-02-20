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
	 * @author ������
	 * @date 2016��8��26��
	 */
	public boolean  addRole(Role role,List<Permission> permissions);
	public List<Role>  selectAllRoles();
	public void delectRole(Role role);
	/**
	 * 
	 * <p>Title: deleteRole</p>
	 * <p>Description: </p>
	 * @param role
	 * @author ������
	 * @date 2016��8��26��
	 */
	public void deleteRole(Role role);
	public JSONObject selectAllRole();
	public Role addRoleByRoleName(Role role);
	public Role selectRoleByRoleName(Role role);
}
