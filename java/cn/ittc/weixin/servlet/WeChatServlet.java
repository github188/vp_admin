/**
 * <p>Title: WeChatServlet.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ittc.weixin.service.ICoreService;
import cn.ittc.weixin.utils.SignUtil;

/**
 * <p>Title: WeChatServlet</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
public class WeChatServlet extends HttpServlet{


	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private ICoreService coreService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String signature = request.getParameter("signature");// ΢�ż���ǩ��
		String timestamp = request.getParameter("timestamp");// ʱ���
		String nonce = request.getParameter("nonce");// �����
		String echostr = request.getParameter("echostr");//

		PrintWriter writer = response.getWriter();
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			writer.write(echostr);
		}else{
			writer.write("checkError");
		}
		
		writer.close();
		writer = null;
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");

//		String respMessage = new CoreService().processRequest(request);
		String respMessage =coreService.processRequest(request);
		PrintWriter out = response.getWriter();
		out.print(respMessage);
		out.close();
	}
	
	

}
