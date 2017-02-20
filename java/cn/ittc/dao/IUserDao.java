/**
 * <p>Title: IUserDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��7��11��
 * @version 1.0.0
 */
package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Role;
import cn.ittc.entity.User;

/**
 * <p>Title: IUserDao</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��7��11��
 */
public interface IUserDao extends IBaseDao<User, Integer>{

	/**
	 * 
	 * <p>Title: addUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @author ������
	 * @date 2016��7��12��
	 */
	public void addUser(User user);
	/**
	 * 
	 * <p>Title: modifyUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @author ������
	 * @date 2016��7��12��
	 */
	public void modifyUser(User user);
	/**
	 * 
	 * <p>Title: deleteUser</p>
	 * <p>Description: </p>
	 * @param user
	 * @author ������
	 * @date 2016��7��12��
	 */
	public void deleteUser(User user);

	/**
	 * 
	 * <p>Title: getUserByParam</p>
	 * <p>Description: </p>
	 * @param user
	 * @return
	 * @author ������
	 * @date 2016��7��12��
	 */
	public User getUserByParam(User user);
	
	/**
	 * 
	 * <p>Title: modifyUserflag</p>
	 * <p>Description: </p>
	 * @param user
	 * @author yangzhiyu
	 * @date 2016��8��16��
	 */
	public void modifyUserflag(User user);
	
	/**
	 * 
	 * <p>Title: selectgrid</p>
	 * <p>Description:��ѯ��user��flag!=1��superAdmin!=1���û� </p>
	 * @param 
	 * @return
	 * @author ��֦��
	 * @date 2016��8��16��
	 */
	
	public List<User> selectgrid();
	
	
	/**
	 * 
	 * <p>Title: modifyUserRoleId</p>
	 * <p>Description:Ȩ�޹�����ɾ��Ȩ����Ҫ���޸��û��Ľ�ɫ </p>
	 * @param 
	 * @return
	 * @author ��֦��
	 * @date 2016��8��25��
	 */
	public void modifyUserRoleId(Role role);
}
