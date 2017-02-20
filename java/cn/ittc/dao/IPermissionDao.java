package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.entity.User;


/**
 * <p>Title: IPermissionDao</p>
 * <p>Description:权限Dao </p>
 * @author 杨枝雨
 * @date 2016年8月3日
 */

public interface IPermissionDao extends IBaseDao<Permission, Integer> {

	/**
	 * <p>Title: addPermission</p>
	 * <p>Description:添加权限 </p>
	 * @author 杨枝雨
	 * @date 2016年8月3日
	 */
	public void addPermission(Permission permission);
	
	/**
	 * <p>Title: deletePermission</p>
	 * <p>Description:删除权限 </p>
	 * @author 杨枝雨
	 * @date 2016年8月3日
	 */
	
	public void deletePermission(Permission permission);
	

	/**
	 * <p>Title: selectAllPermission</p>
	 * <p>Description:查看所有的权限 </p>
	 * @author 杨枝雨
	 * @date 2016年8月3日
	 */
	
	public List<Permission> selectAllPermission();
	
	/**
	 * <p>Title: selectPermissionByRoleId</p>
	 * <p>Description:根据角色ID查询权限 </p>
	 * @author 杨枝雨
	 * @date 2016年8月3日
	 */
	
	public List<Permission> selectPermissionByRoleId(Permission permission);
	

	public  List<Role> selectPermissionByRidUid();
	/**
	 * 
	 * <p>Title: getPermissionByUser</p>
	 * <p>Description: 根据用户查询权限</p>
	 * @param user
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月25日
	 */
	public List<String> getPermissionByUser(User user);

	
	

}
