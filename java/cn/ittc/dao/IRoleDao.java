package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Role;

/**
 * <p>Title: IRoleDao</p>
 * <p>Description:��ɫDao </p>
 * @author ��֦��
 * @date 2016��8��1��
 */

public interface IRoleDao extends IBaseDao<Role, Integer> {
	

	
	/**
	 * <p>Title: addRole</p>
	 * <p>Description:����½�ɫ </p>
	 * @author ��֦��
	 * @date 2016��8��1��
	 */
	
	public void  addRole(Role role);
	
	/**
	 * <p>Title: selectAllRole</p>
	 * <p>Description:��ѯ���н�ɫ </p>
	 * @author ��֦��
	 * @date 2016��8��1��
	 */
	
	public List<Role>  selectAllRole();
	
	/**
	 * <p>Title: delectRole</p>
	 * <p>Description:ɾ����ɫ </p>
	 * @author ��֦��
	 * @date 2016��8��1��
	 */
	
	public void delectRole(Role role);

	
	public Role addRoleByRoleName(Role role);
	
	public void addRolePermission(Role role ,int[] arg);
	
	public Role selectRoleByRoleName(Role role);
}
