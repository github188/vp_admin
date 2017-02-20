/**
 * <p>Title: MenuAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��30��
 * @version 1.0.0
 */
package cn.ittc.weixin.action;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.weixin.entity.menu.CreateMenu;

/**
 * <p>Title: MenuAction</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��2��29��
 */
public class MenuAction extends ActionSupport{
	
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	public String create(){
		try {
			CreateMenu.createMenu();
			msg="�˵������ɹ���";
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	public String delete(){
		
		try {
			CreateMenu.delete();
			msg="ɾ���ɹ���";
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public String getMsg() {
		return msg;
	}

	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	
}
