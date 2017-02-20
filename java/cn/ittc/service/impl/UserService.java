/**
 * <p>Title: UserService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月11日
 * @version 1.0.0
 */
package cn.ittc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ittc.dao.IDeptDao;
import cn.ittc.dao.IRoleDao;
import cn.ittc.dao.IUserDao;
import cn.ittc.entity.Dept;
import cn.ittc.entity.Role;
import cn.ittc.entity.User;
import cn.ittc.entity.base.EasyUIPageResult;
import cn.ittc.entity.base.PageResultSet;
import cn.ittc.service.IUserService;
import cn.ittc.utils.PropertiesUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * <p>Title: UserService</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月11日
 */
@Service(value="userService")
public class UserService implements IUserService {

	@Resource
	private IUserDao userDao;
	
	@Resource
	private IDeptDao deptDao;
	
	@Resource
	private IRoleDao roleDao;
	
	@Override
	public boolean addUser(User user) {
		if (user.getPassword() == null) {// 如果是新用户
			// 获取配置文件里的用户默认pwd
			String pwd = PropertiesUtil.getDefualt("myuser.defualtPWD");
			user.setPassword(pwd);
		}

		userDao.addUser(user);
		return  true;
	}


	@Override
	public void modifyUser(User user) {
		
	}

	@Override
	public void deleteUser(User user) {
		userDao.deleteObject(user);
	}

	

	
	@Override
	public List<User> selectAll() {
		
		return userDao.getAllObjects(User.class);
	}

	public User selectById(int id) {
		return userDao.getObjectById(User.class, id);
	}


	
	@Override
	public boolean CheckLogin(User user) {
		User userOut = userDao.getUserByParam(user);
		if(userOut==null){
			return false;
		}
//		System.out.println(userOut.getUserName()+","+userOut.getPassword());
		if (user.getUserName().equals(userOut.getUserName()) && user.getPassword().equals(userOut.getPassword())) {
			return true;
		}else{
			return false;
		}
		
	}

	@Override
	public PageResultSet<User> findByPage(int currentPage, int pageSize) {
		return userDao.findByPage(User.class, currentPage, pageSize);
		//return null;
	}
	
	
	
	
	@Override
	public String findEasyUIByPage(int pageNum,int pageSize){
		String hql="FROM User WHERE superAdmin!=1";
		JSONObject jsonObject = getEasyUIPageByHQL(hql, pageNum, pageSize);
		return jsonObject.toString();
	}

	public String findEasyUIByPage(Dept dept,int pageNum,int pageSize){
		String hql="FROM User u WHERE u.superAdmin!=1 AND u.dept.deptId="+dept.getDeptId();
		JSONObject jsonObject = getEasyUIPageByHQL(hql, pageNum, pageSize);
		return jsonObject.toString();
	}

	/**
	 * <p>Title: getEasyUIPageByHQL</p>
	 * <p>Description: </p>
	 * @param hql
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月17日
	 */
	private JSONObject getEasyUIPageByHQL(String hql, int pageNum, int pageSize) {
		EasyUIPageResult<User> easyUIPageResult=userDao.queryEasyUIByPage(hql,pageNum,pageSize);
		JSONObject jsonObject=new JSONObject();
		jsonObject.element("total", easyUIPageResult.getTotal());
		List<User> users=easyUIPageResult.getRows();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		for (User user : users) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("userId", user.getUserId());
			map.put("userName", user.getUserName());
			map.put("realName", user.getRealName());
			map.put("flag", user.getFlag());
			Dept dept=new Dept();
			dept.setDeptId(user.getDept().getDeptId());
			dept.setDeptName(user.getDept().getDeptName());
			map.put("dept",dept );
			Role role=new Role();
			role.setRoleId(user.getRole().getRoleId());
			role.setRoleName(user.getRole().getRoleName());
			map.put("role", role);
			map.put("position", user.getPosition());
			list.add(map);
		}
		jsonObject.element("rows",list);
		return jsonObject;
	}
	
	
	@Override
	public JSONObject getUserByDept(int deptId) {
		List<Dept> list=null;
		JSONObject jsonObject=new JSONObject();
		if(deptId==-1){//查询所有的用户以及部门，组装成tree
			list=deptDao.getAllObjects(Dept.class);
			//组装tree
			List<Map<Object, Object>> tree=new ArrayList<Map<Object, Object>>();
			//部门
			List<Integer> deptList=new ArrayList<Integer>();
			for (Dept dept : list) {
				if(deptList.contains(dept.getDeptId())){
					continue;
				}
				deptList.add(dept.getDeptId());
				Map<Object, Object> map=new HashMap<Object, Object>();
				map.put("id", dept.getDeptId());
				map.put("text", dept.getDeptName());
				
				//用户
				List<Map<String, Object>> userList=new ArrayList<Map<String, Object>>();				
				for(User user:dept.getUsers()){
					Map<String, Object> children = new HashMap<String, Object>();
					if(user.getSuperAdmin()!=1){//flag=1表示删除，superAdmin=1表示非管理员
						children.put("text", user.getRealName());
						children.put("id", user.getUserId());
						userList.add(children);
						
					}
					
					
				}
				map.put("children", userList);
				tree.add(map);
			}

			jsonObject.element("success", tree);
		}else if(deptId==-2){//加载所有用户
			
			
			List<User> users=userDao.selectgrid();

			JsonConfig jsonConfig=new JsonConfig();
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			try {
				jsonObject.element("rows", users,jsonConfig);
				jsonObject.element("total", users.size());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		System.out.println(jsonObject);
		return jsonObject;
	}


	@Override
	public void addDept(Dept dept) {
		deptDao.saveObject(dept);
		
	}


	@Override
	public List<Dept> selectDept(Dept dept) {
		List<Dept> list =deptDao.selectDept(dept);
		return list;
	}


	@Override
	public void modifyUserflag(User user) {
		userDao.modifyUserflag(user);
		
	}


	@Override
	public void modifyUserRoleId(Role role) {
		userDao.modifyUserRoleId(role);
		
	}


	
	

}
