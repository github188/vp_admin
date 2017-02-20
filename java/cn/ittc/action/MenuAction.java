/**
 * <p>Title: MenuAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月28日
 * @version 1.0.0
 */
package cn.ittc.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>Title: MenuAction</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月28日
 */
@Controller(value="menuAction")
public class MenuAction extends ActionSupport{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	public String execute(){
		return SUCCESS;
	}
}
