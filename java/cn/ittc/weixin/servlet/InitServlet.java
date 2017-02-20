/**
 * <p>Title: InitServlet.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.ittc.weixin.utils.TokenThread;

/**
 * <p>Title: InitServlet</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
public class InitServlet extends HttpServlet{

	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		new Thread(new TokenThread()).start();
	}

	
}

