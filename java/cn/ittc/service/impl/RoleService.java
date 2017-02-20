package cn.ittc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ittc.dao.IRoleDao;
import cn.ittc.dao.IRolePermissionDao;
import cn.ittc.dao.IUserDao;
import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.entity.RolePermission;
import cn.ittc.entity.User;
import cn.ittc.service.IRoleService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * <p>Title: RoleService</p>
 * <p>Description: </p>
 * @author 杨枝雨
 * @date 2016年8月1日
 */

@Service(value="roleService")
public class RoleService implements IRoleService{

	@Resource
	private IRoleDao roleDao;
	@Resource
	private IRolePermissionDao rolePermissionDao;
	@Resource
	private IUserDao userDao;
	
	
	
	@Override
	public void addRole(Role role) {
		roleDao.addRole(role);	
	}
	
	public boolean  addRole(Role role,List<Permission> permissions){
		try {
			if (role.getRoleId() != null) {
				roleDao.updateObject(role);
				rolePermissionDao.deleteByHQL("DELETE FROM RolePermission r where r.role.roleId=" + role.getRoleId());
			} else {
				roleDao.saveObject(role);
			}

			for (Permission permission : permissions) {
				RolePermission rolePermission=new RolePermission(role,permission);
				rolePermissionDao.saveObject(rolePermission);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	@Override
	public List<Role> selectAllRoles() {
		List<Role> list =roleDao.selectAllRole();
		return list;
	}

	@Override
	public void delectRole(Role role) {
		
		//更新User中的角色,roleId=null
//		userDao.executeHQL("UPDATE u.role=null FROM User u WHERE u.role.roleId="+role.getRoleId());
		
		List<User> users=role.getUsers();
		for (User user : users) {
			user.setRole(null);
			userDao.updateObject(user);
		}
		
		rolePermissionDao.deleteByHQL("DELETE FROM RolePermission r where r.role.roleId=" + role.getRoleId());
		
		roleDao.deleteObject(role);
		
	}
	/**
	 * 修改User中的roleId外键，删除中间表RolePermission中role数据，最后再删除role
	 */
	public void deleteRole(Role role){
		
		// SET u.role.roleId=null则用户没有role， SET u.role.roleId=1为普通用户
		//userDao.executeHQL("UPDATE User u SET u.role.roleId=null WHERE u.role.roleId="+role.getRoleId());
		userDao.executeHQL("UPDATE User u SET u.role.roleId=1 WHERE u.role.roleId="+role.getRoleId());
		//删除角色权限RolePermission中的Role为role的数据
		rolePermissionDao.deleteByHQL("DELETE FROM RolePermission r where r.role.roleId=" + role.getRoleId());
		
		roleDao.deleteObject(role);
	}
	@Override
	public JSONObject selectAllRole() {
		
			List<Role> list = roleDao.selectAllRole();
			
			JsonConfig jsonConfig=new JsonConfig();
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			
			JSONObject jsonObject =new JSONObject();
			jsonObject.element("data", list,jsonConfig);
			//System.out.println(jsonObject);
			return jsonObject;
			
		}

	@Override
	public Role addRoleByRoleName(Role role) {
		Role roles =roleDao.addRoleByRoleName(role);
		return roles;
	}

	@Override
	public Role selectRoleByRoleName(Role role) {
		Role roles =roleDao.selectRoleByRoleName(role);
		return roles;
	}

	
	

}
