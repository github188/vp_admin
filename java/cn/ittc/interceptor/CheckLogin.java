/**
 * <p>Title: CheckLogin.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月11日
 * @version 1.0.0
 */
package cn.ittc.interceptor;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * <p>Title: CheckLogin</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月11日
 */
@Component("checkLoginInterceptor")
public class CheckLogin implements Interceptor{

	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;


	@Override
	public void destroy() {
		System.out.println("------CheckLogin.destroy------");
	}


	@Override
	public void init() {
		System.out.println("------CheckLogin.init------");
	}


	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		System.out.println("------CheckLogin.intercept------");

		Map<String, Object> session = ActionContext.getContext().getSession();
		// Map userinfo=arg0.getInvocationContext().getParameters();

		// String username=session.get("user.name").toString();

		// String[] loginUsername=(String[])parameters.get("user.name");

		if (session.get("user.name") != null) {
			return arg0.invoke();
		}

		return "checkLoginFail";
	}

}
