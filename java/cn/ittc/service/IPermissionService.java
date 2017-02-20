package cn.ittc.service;

import cn.ittc.entity.Role;
import cn.ittc.entity.User;
import net.sf.json.JSONObject;

public interface IPermissionService {

	public JSONObject selectAllPermission();
	public JSONObject selectPermissionByRidUid(int pageNum, int pageSize);
	public JSONObject getPermissionTree();
	
	/**
	 * 
	 * <p>Title: getPermissionByUser</p>
	 * <p>Description: �����û���ѯ��Ȩ��</p>
	 * @return
	 * @author ������
	 * @date 2016��8��25��
	 */
	public JSONObject getPermissionByUser(User user);

	public JSONObject selectPermissionById(Role role, int pageNum, int pageSize);

}
