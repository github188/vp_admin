package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.entity.RolePermission;

public interface IRolePermissionDao extends IBaseDao<RolePermission, Integer> {
	
	public void  addRolePermission(Role role ,List<Permission> perlist);
	
	public List<RolePermission> selectAll();
	
	public void saveRolePermission(RolePermission rolepermission);
	
	public void deleteRolePermissionByRoleName(Role role);
	/**
	 * 
	 * <p>Title: deleteByHQL</p>
	 * <p>Description: </p>
	 * @param hql
	 * @author ½¹¶¬¶¬
	 * @date 2016Äê8ÔÂ26ÈÕ
	 */
	public void deleteByHQL(String hql);
	public List<RolePermission> selectByRoleId(Role roles);
}
