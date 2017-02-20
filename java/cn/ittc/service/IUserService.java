/**
 * <p>Title: IUserService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月11日
 * @version 1.0.0
 */
package cn.ittc.service;

import java.util.List;

import cn.ittc.entity.Dept;
import cn.ittc.entity.Role;
import cn.ittc.entity.User;
import cn.ittc.entity.base.PageResultSet;
import net.sf.json.JSONObject;

/**
 * <p>Title: IUserService</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月11日
 */
public interface IUserService {
	public boolean addUser(User user);

	public void modifyUser(User user);

	public void deleteUser(User user);
	
	public List<User> selectAll();
	
	public User selectById(int id);
	
	public boolean CheckLogin(User user);
	
	/**
	 * 
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月16日
	 */
	public PageResultSet<User> findByPage( int currentPage, int pageSize);
	
	/**
	 * 
	 * <p>Title: findPage</p>
	 * <p>Description: </p>
	 * @param pageResult
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月16日
	 */
	public String findEasyUIByPage(int pageNum,int pageSize);
	
	public String findEasyUIByPage(Dept dept,int pageNum,int pageSize);
	/**
	 * 
	 * <p>Title: getUserByDept</p>
	 * <p>Description: 根据部门查询用户</p>
	 * @param deptId
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月2日
	 */
	public JSONObject getUserByDept(int deptId);
	/**
	 * 
	 * <p>Title: addDept</p>
	 * <p>Description: 添加部门</p>
	 * @param dept
	 * @author 焦冬冬
	 * @date 2016年8月3日
	 */
	public void addDept(Dept dept);

	public List<Dept> selectDept(Dept dept);

	/**
	 * 
	 * <p>Title: modifyUserflag</p>
	 * <p>Description: </p>
	 * @param user
	 * @author 杨枝雨
	 * @date 2016年8月16日
	 */
	public void modifyUserflag(User user);

	public void modifyUserRoleId(Role role);
	
	

	
}
