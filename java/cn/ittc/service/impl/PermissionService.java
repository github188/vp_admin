package cn.ittc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ittc.dao.IPermissionDao;
import cn.ittc.dao.IRoleDao;
import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.entity.RolePermission;
import cn.ittc.entity.User;
import cn.ittc.entity.base.EasyUIPageResult;
import cn.ittc.service.IPermissionService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
@Service(value="permissionService")
public class PermissionService implements IPermissionService{

	@Resource
	private IPermissionDao permissionDao;
	@Resource
	private IRoleDao roleDao;

	@Override
	public JSONObject selectAllPermission() {
		
		List<Permission> list =permissionDao.selectAllPermission();
		JSONObject jSONObject = new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jSONObject.element("data", list,jsonConfig);
		return jSONObject;
	}

	

	




	@Override
	public JSONObject getPermissionTree() {
		
		List<Role> list =null ;
		JSONObject jSONObject =new JSONObject();
		list =roleDao.getAllObjects(Role.class);
		//组装tree
		List<Map<Object, Object>> tree=new ArrayList<Map<Object, Object>>();
		//角色
		List<Integer> roleList=new ArrayList<Integer>();
		for(Role role :list)
		{
			roleList.add(role.getRoleId());
			Map<Object, Object> map=new HashMap<Object, Object>();
			map.put("id", role.getRoleId());
			map.put("text", role.getRoleName());
			List<Map<String, Object>> permissionList=new ArrayList<Map<String, Object>>();
			
			for(RolePermission rolepermission :role.getRolepermissions()){
				Permission permission =rolepermission.getPermission();
				Map<String,Object> children = new HashMap<String, Object>();
				children.put("id", permission.getPermissionId());
				children.put("text", permission.getPermissionName());
				permissionList.add(children);
			}
			map.put("children", permissionList);
			tree.add(map);
		}
		

		jSONObject.element("success", tree);
		return jSONObject;
	}



	/* （no Javadoc）
	 * <p>Title: getPermissionByUser</p>
	 * <p>Description: </p>
	 * @return
	 * @see cn.ittc.service.IPermissionService#getPermissionByUser()
	 * @author 焦冬冬
	 * @date 2016年8月25日
	 */
	@Override
	public JSONObject getPermissionByUser(User user) {
		List<String> list = permissionDao.getPermissionByUser(user);
		JSONObject jsonObject=new JSONObject();
		jsonObject.element("data", list);
		return jsonObject;
	}






	@Override
	public JSONObject selectPermissionById(Role role, int pageNum, int pageSize) {
		
		String hql ="FROM Role r WHERE r.roleId="+role.getRoleId();
		EasyUIPageResult<Role> easyUIPageResult = roleDao.queryEasyUIByPage(hql, pageNum, pageSize);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.fromObject(easyUIPageResult, jsonConfig);
		return jsonObject;
	}








	@Override
	public JSONObject selectPermissionByRidUid(int pageNum, int pageSize) {
//		System.out.println("进入SErVICE");
		/*List list = permissionDao.selectPermissionByRidUid();*/
		
		String hql ="FROM Role";
		EasyUIPageResult<Role> easyUIPageResult = roleDao.queryEasyUIByPage(hql, pageNum, pageSize);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject = JSONObject.fromObject(easyUIPageResult, jsonConfig);
		return jsonObject;
		
//		JSONObject  jSONObject =new JSONObject();
//		JsonConfig jsonConfig=new JsonConfig();
//		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		jSONObject.element("rows", list,jsonConfig);
//		jSONObject.element("total", list.size());
//		System.out.println(jSONObject);
		
		
	}

	
	

}
