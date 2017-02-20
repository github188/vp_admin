package cn.ittc.service;

import java.util.List;

import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.entity.RolePermission;

public interface IRolePermissionService {
	
	public void addRolePermission(Role role ,List<Permission> perlist);
	
	public void deleteRolePermissionByRoleName(Role role);
	
	public List<RolePermission> selectByRoleId(Role roles);

}
