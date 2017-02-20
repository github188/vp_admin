/**
 * <p>Title: LoginAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��7��11��
 * @version 1.0.0
 */
package cn.ittc.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.entity.User;
import cn.ittc.service.IUserService;

/**
 * <p>Title: LoginAction</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��7��11��
 */
@Controller(value="loginAction")
public class LoginAction extends ActionSupport{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private IUserService userService;
	
	private User user;
	private String verifyCode;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String execute() throws Exception {



		Map<String, Object> session = ActionContext.getContext().getSession();
//		System.out.println("��ʵ��֤�룺"+session.get("verifyCode").toString());
//		System.out.println("������֤�룺"+verifyCode);
		if(!session.get("verifyCode").toString().equalsIgnoreCase(verifyCode)){
			return ERROR;
		}
		if(userService.CheckLogin(user)){
			session.put("user.name", user.getUserName());
			return SUCCESS;
		}
//		System.out.println("��¼ʧ�ܣ��û���=" + user.getName());
		return ERROR;
	}

}
