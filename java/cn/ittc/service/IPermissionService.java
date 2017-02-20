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
	 * <p>Description: 根据用户查询其权限</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月25日
	 */
	public JSONObject getPermissionByUser(User user);

	public JSONObject selectPermissionById(Role role, int pageNum, int pageSize);

}
