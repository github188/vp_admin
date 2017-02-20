/**
 * <p>Title: TestAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月21日
 * @version 1.0.0
 */
package cn.ittc.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.entity.User;
import cn.ittc.entity.base.PageInfo;
import cn.ittc.entity.base.PageResultSet;
import cn.ittc.service.IUserService;

/**
 * <p>Title: TestAction</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月21日
 */
@Controller(value="testAction")
public class TestAction extends ActionSupport{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private IUserService userService;
	
	private PageResultSet<User> pageResultSet;

	public PageResultSet<User> getPageResultSet() {
		return pageResultSet;
	}


	public void setPageResultSet(PageResultSet<User> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}

	public String test() {
		if(pageResultSet==null){
			pageResultSet=new PageResultSet<User>();
			if(pageResultSet.getPageInfo()==null){
				pageResultSet.setPageInfo(new PageInfo());
			}
		}
		
		pageResultSet = userService.findByPage(pageResultSet.getPageInfo().getCurrentPage(),
				pageResultSet.getPageInfo().getPageSize());
		return SUCCESS;
	}
	
	public String testRole(){
		
		return SUCCESS;
	}

}
