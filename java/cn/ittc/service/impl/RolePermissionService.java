package cn.ittc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ittc.dao.IRolePermissionDao;
import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.entity.RolePermission;
import cn.ittc.service.IRolePermissionService;

@Service(value="rolepermissionService")
public class RolePermissionService implements IRolePermissionService{
	@Resource 
	IRolePermissionDao rolepermissionDao;

	@Override
	public void addRolePermission(Role role ,List<Permission> perlist) {
		
		//RolePermission rolepermission =new RolePermission();
	
		for(Permission permission :perlist)
		{
			RolePermission rolepermission =new RolePermission();
			System.out.println(permission.getPermissionId());
			rolepermission.setRole(role);
			rolepermission.setPermission(permission);
			//rolepermissionDao.saveRolePermission(rolepermission);
			rolepermissionDao.saveObject(rolepermission);
		}
	
	}

	@Override
	public void deleteRolePermissionByRoleName(Role role) {
		
		rolepermissionDao.deleteRolePermissionByRoleName(role);
		
	}

	@Override
	public List<RolePermission> selectByRoleId(Role roles) {
		List<RolePermission> list =rolepermissionDao.selectByRoleId(roles);
		return list;
	}
	
}
