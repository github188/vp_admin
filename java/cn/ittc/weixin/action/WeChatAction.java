/**
 * <p>Title: WeChatAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月31日
 * @version 1.0.0
 */
package cn.ittc.weixin.action;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.weixin.service.ICoreService;
import cn.ittc.weixin.utils.SignUtil;

/**
 * <p>Title: WeChatAction</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月31日
 */
@Controller("wechatAction")
public class WeChatAction extends ActionSupport{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ICoreService coreService;
	
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;

	/* （no Javadoc）
	 * <p>Title: execute</p>
	 * <p>Description: </p>
	 * @return
	 * @throws Exception
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 * @author 焦冬冬
	 * @date 2016年8月31日
	 */
	@Override
	public String execute() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		String method = request.getMethod();
		
		if (method.equals("POST")) {

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");

//			String respMessage = new CoreService().processRequest(request);
			String respMessage =coreService.processRequest(request);
			PrintWriter out = response.getWriter();
			out.print(respMessage);
			out.close();

		} else {

//			String signature = request.getParameter("signature");// 微信加密签名
//			String timestamp = request.getParameter("timestamp");// 时间戳
//			String nonce = request.getParameter("nonce");// 随机数
//			String echostr = request.getParameter("echostr");//

			PrintWriter writer = response.getWriter();
			if(SignUtil.checkSignature(signature, timestamp, nonce)){
				writer.write(echostr);
			}else{
				writer.write("checkError");
			}
			
			writer.close();
			writer = null;

		}
		return null;
	}

	/**
	 * @return coreService
	 */
	public ICoreService getCoreService() {
		return coreService;
	}

	/**
	 * @param coreService the coreService to set
	 */
	public void setCoreService(ICoreService coreService) {
		this.coreService = coreService;
	}

	/**
	 * @return signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return nonce
	 */
	public String getNonce() {
		return nonce;
	}

	/**
	 * @param nonce the nonce to set
	 */
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	/**
	 * @return echostr
	 */
	public String getEchostr() {
		return echostr;
	}

	/**
	 * @param echostr the echostr to set
	 */
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	
	
	

}
