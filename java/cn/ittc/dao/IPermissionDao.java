package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.entity.User;


/**
 * <p>Title: IPermissionDao</p>
 * <p>Description:Ȩ��Dao </p>
 * @author ��֦��
 * @date 2016��8��3��
 */

public interface IPermissionDao extends IBaseDao<Permission, Integer> {

	/**
	 * <p>Title: addPermission</p>
	 * <p>Description:���Ȩ�� </p>
	 * @author ��֦��
	 * @date 2016��8��3��
	 */
	public void addPermission(Permission permission);
	
	/**
	 * <p>Title: deletePermission</p>
	 * <p>Description:ɾ��Ȩ�� </p>
	 * @author ��֦��
	 * @date 2016��8��3��
	 */
	
	public void deletePermission(Permission permission);
	

	/**
	 * <p>Title: selectAllPermission</p>
	 * <p>Description:�鿴���е�Ȩ�� </p>
	 * @author ��֦��
	 * @date 2016��8��3��
	 */
	
	public List<Permission> selectAllPermission();
	
	/**
	 * <p>Title: selectPermissionByRoleId</p>
	 * <p>Description:���ݽ�ɫID��ѯȨ�� </p>
	 * @author ��֦��
	 * @date 2016��8��3��
	 */
	
	public List<Permission> selectPermissionByRoleId(Permission permission);
	

	public  List<Role> selectPermissionByRidUid();
	/**
	 * 
	 * <p>Title: getPermissionByUser</p>
	 * <p>Description: �����û���ѯȨ��</p>
	 * @param user
	 * @return
	 * @author ������
	 * @date 2016��8��25��
	 */
	public List<String> getPermissionByUser(User user);

	
	

}
