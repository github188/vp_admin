/**
 * <p>Title: UserAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月11日
 * @version 1.0.0
 */
package cn.ittc.action;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.entity.Dept;
import cn.ittc.entity.Role;
import cn.ittc.entity.User;
import cn.ittc.entity.base.PageResultSet;
import cn.ittc.service.IUserService;
import net.sf.json.JSONObject;
/**
 * <p>Title: UserAction</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月11日
 */
 
@Controller(value="userAction")
public class UserAction extends ActionSupport{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Resource
	private IUserService userService;
	private String jsonData;

//	private PageResult<User> pageResult;
	private PageResultSet<User> pageResultSet;

//	private Page page;
	private User user;
	private Dept dept;
	private Role role;
	private List<User> list;
	private int pageSize=10;
	private int pageNum=1;

	public String addUser()
	{
		
		return "success";
	}
	
	public String deleteUser(){
		
		userService.deleteUser(user);
		
		return SUCCESS;
	}
	
	
	public String select(){
		list=userService.selectAll();
		return SUCCESS;
	}
	
	public String selectById(){
		user=userService.selectById(user.getUserId());
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>Title: getUserByDept</p>
	 * <p>Description: </p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月2日
	 */
	public String getUserByDept(){
		
		JSONObject jsonObject=userService.getUserByDept(dept.getDeptId());
		jsonData=jsonObject.toString();

		return SUCCESS;
	}
	/**
	 * 
	 * <p>Title: addDept</p>
	 * <p>Description: 添加部门</p>
	 * @return
	 * @author 杨枝雨
	 * @date 2016年8月9日
	 */
	public String addDept() {
		userService.addDept(dept);
		return SUCCESS;
	}
		
		
	/**
	 * 
	 * <p>Title: createUser</p>
	 * <p>Description: 添加新建的用户</p>
	 * @return
	 * @author 杨枝雨
	 * @date 2016年8月8日
	 */
	public String createUser() {

		/*JSONObject jsonObject = new JSONObject();
		jsonObject.element("success", userService.addUser(user));
		jsonData=jsonObject.toString();

		return SUCCESS;*/

		JSONObject jsonObject=new JSONObject();
		try {
			userService.addUser(user);
			jsonObject.element("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.element("success", true);
		}
		jsonData=jsonObject.toString();
		return SUCCESS;

	}

		

		

	/**
	 * 
	 * <p>Title: modifyUser</p>
	 * <p>Description: 非物理删除用户</p>
	 * @return
	 * @author 杨枝雨
	 * @date 2016年8月16日
	 */
	public String modifyUser() {
		userService.modifyUserflag(user);
		return SUCCESS;
	}
		
	/**
	 * 
	 * <p>Title: getUserByPage</p>
	 * <p>Description: 分页查询</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月16日
	 */
	public String getUserByPage() {
		try {
			if(dept==null||dept.getDeptId()==null){
				jsonData=userService.findEasyUIByPage(pageNum, pageSize);
			}else{
				jsonData=userService.findEasyUIByPage(dept,pageNum, pageSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	public String goByDept(){
//		System.out.println("go:"+dept.getDeptId());
		return SUCCESS;
	}
		
		
		

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getList() {
		return list;
	}

	public void setList(List<User> list) {
		this.list = list;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return pageResultSet
	 */
	public PageResultSet<User> getPageResultSet() {
		return pageResultSet;
	}

	/**
	 * @param pageResultSet the pageResultSet to set
	 */
	public void setPageResultSet(PageResultSet<User> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}

	/**
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	


//	public Page getPage() {
//		return page;
//	}
//
//	public void setPage(Page page) {
//		this.page = page;
//	}


		
		
}
