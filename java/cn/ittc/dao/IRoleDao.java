package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Role;

/**
 * <p>Title: IRoleDao</p>
 * <p>Description:角色Dao </p>
 * @author 杨枝雨
 * @date 2016年8月1日
 */

public interface IRoleDao extends IBaseDao<Role, Integer> {
	

	
	/**
	 * <p>Title: addRole</p>
	 * <p>Description:添加新角色 </p>
	 * @author 杨枝雨
	 * @date 2016年8月1日
	 */
	
	public void  addRole(Role role);
	
	/**
	 * <p>Title: selectAllRole</p>
	 * <p>Description:查询所有角色 </p>
	 * @author 杨枝雨
	 * @date 2016年8月1日
	 */
	
	public List<Role>  selectAllRole();
	
	/**
	 * <p>Title: delectRole</p>
	 * <p>Description:删除角色 </p>
	 * @author 杨枝雨
	 * @date 2016年8月1日
	 */
	
	public void delectRole(Role role);

	
	public Role addRoleByRoleName(Role role);
	
	public void addRolePermission(Role role ,int[] arg);
	
	public Role selectRoleByRoleName(Role role);
}
