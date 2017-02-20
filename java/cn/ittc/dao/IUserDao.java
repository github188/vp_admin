/**
 * <p>Title: IUserDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月11日
 * @version 1.0.0
 */
package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Role;
import cn.ittc.entity.User;

/**
 * <p>Title: IUserDao</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月11日
 */
public interface IUserDao extends IBaseDao<User, Integer>{

	/**
	 * 
	 * <p>Title: addUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @author 焦冬冬
	 * @date 2016年7月12日
	 */
	public void addUser(User user);
	/**
	 * 
	 * <p>Title: modifyUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @author 焦冬冬
	 * @date 2016年7月12日
	 */
	public void modifyUser(User user);
	/**
	 * 
	 * <p>Title: deleteUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @author 焦冬冬
	 * @date 2016年7月12日
	 */
	public void deleteUser(User user);

	/**
	 * 
	 * <p>Title: getUserByParam</p>
	 * <p>Description: </p>
	 * @param user
	 * @return
	 * @author 焦冬冬
	 * @date 2016年7月12日
	 */
	public User getUserByParam(User user);
	
	/**
	 * 
	 * <p>Title: modifyUserflag</p>
	 * <p>Description: </p>
	 * @param user
	 * @author yangzhiyu
	 * @date 2016年8月16日
	 */
	public void modifyUserflag(User user);
	
	/**
	 * 
	 * <p>Title: selectgrid</p>
	 * <p>Description:查询出user中flag!=1，superAdmin!=1的用户 </p>
	 * @param 
	 * @return
	 * @author 杨枝雨
	 * @date 2016年8月16日
	 */
	
	public List<User> selectgrid();
	
	
	/**
	 * 
	 * <p>Title: modifyUserRoleId</p>
	 * <p>Description:权限管理中删除权限需要先修改用户的角色 </p>
	 * @param 
	 * @return
	 * @author 杨枝雨
	 * @date 2016年8月25日
	 */
	public void modifyUserRoleId(Role role);
}
