/**
 * <p>Title: IUserService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��7��11��
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
 * @author ������
 * @date 2016��7��11��
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
	 * @author ������
	 * @date 2016��8��16��
	 */
	public PageResultSet<User> findByPage( int currentPage, int pageSize);
	
	/**
	 * 
	 * <p>Title: findPage</p>
	 * <p>Description: </p>
	 * @param pageResult
	 * @return
	 * @author ������
	 * @date 2016��8��16��
	 */
	public String findEasyUIByPage(int pageNum,int pageSize);
	
	public String findEasyUIByPage(Dept dept,int pageNum,int pageSize);
	/**
	 * 
	 * <p>Title: getUserByDept</p>
	 * <p>Description: ���ݲ��Ų�ѯ�û�</p>
	 * @param deptId
	 * @return
	 * @author ������
	 * @date 2016��8��2��
	 */
	public JSONObject getUserByDept(int deptId);
	/**
	 * 
	 * <p>Title: addDept</p>
	 * <p>Description: ��Ӳ���</p>
	 * @param dept
	 * @author ������
	 * @date 2016��8��3��
	 */
	public void addDept(Dept dept);

	public List<Dept> selectDept(Dept dept);

	/**
	 * 
	 * <p>Title: modifyUserflag</p>
	 * <p>Description: </p>
	 * @param user
	 * @author ��֦��
	 * @date 2016��8��16��
	 */
	public void modifyUserflag(User user);

	public void modifyUserRoleId(Role role);
	
	

	
}
