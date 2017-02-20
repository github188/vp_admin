/**
 * <p>Title: ApiAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��25��
 * @version 1.0.0
 */
package cn.ittc.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.entity.User;
import cn.ittc.service.IIpcService;
import cn.ittc.service.ILocService;
import cn.ittc.service.INvrService;
import cn.ittc.service.IPermissionService;
import cn.ittc.service.IUserService;
import net.sf.json.JSONObject;

/**
 * <p>Title: ApiAction</p>
 * <p>Description: CSƽ̨�Խ�API</p>
 * @author ������
 * @date 2016��8��25��
 */
@Controller("api")
@Scope("prototype")
public class ApiAction extends ActionSupport{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Resource
	private IUserService userService;
	@Resource
	private ILocService locService;
	@Resource
	private IPermissionService permissionService ;
	@Resource
	private IIpcService ipcService;
	@Resource
	private INvrService nvrService;
	
	private String name;
	private String pwd;
	private Integer id;
	private String jsonData;
	private String error="{msg:'error'}";
	

	
	public String getError() {
		return error;
	}

	
	public void setError(String error) {
		this.error = error;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	public String execute(){
		
		jsonData="data:error";
		return SUCCESS;
	}

	/**
	 * 
	 * <p>Title: checkUser</p>
	 * <p>Description: �����˺�������֤</p>
	 * @return
	 * @author ������
	 * @date 2016��8��25��
	 */
	private boolean checkUser() {
		JSONObject jsonObject = new JSONObject();
		User user = new User(name, pwd);
		boolean b=userService.CheckLogin(user);
		if(!b){
			jsonObject.element("data",b);
			jsonObject.element("info","û�в���Ȩ�ޣ�");
		}
		jsonData = jsonObject.toString();
		return b;
	}
	/**
	 * 
	 * <p>Title: login</p>
	 * <p>Description: ��½��֤</p>
	 * @return
	 * @author ������
	 * @date 2016��8��25��
	 */
	public String login() {
		User user = new User(name, pwd);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("data", userService.CheckLogin(user));
		jsonData = jsonObject.toString();
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>Title: permision</p>
	 * <p>Description: ��ѯȨ��</p>
	 * @return
	 * @author ������
	 * @date 2016��8��25��
	 */
	public String permision(){
		if(checkUser()){
			JSONObject jsonObject=new JSONObject();
			jsonObject=permissionService.getPermissionByUser(new User(name,pwd));
			jsonData=jsonObject.toString();
		}
		return SUCCESS;
	}
	/**
	 * 
	 * <p>Title: ipclist</p>
	 * <p>Description: ��ѯ����IPC��tree�б�</p>
	 * @return
	 * @author ������
	 * @date 2016��8��25��
	 */
	public String ipclist() {
		if (checkUser()) {
			if (id!=null&&id == 1) {//���Ŀ¼
				jsonData = locService.getAllLocaMenu(null).toString();
			} else if(id!=null&&id==2) {//NVR��IPC����Ŀ¼
				jsonData =nvrService.getIpcByNvr().toString();
			}else{
				JSONObject jsonObject = new JSONObject();

				jsonObject.element("data", false);
				jsonObject.element("info", "���Ͳ����ڣ�");

				jsonData = jsonObject.toString();
			}
		}
		return SUCCESS;
	}
	/**
	 * 
	 * <p>Title: ipcdetial</p>
	 * <p>Description: ����IPC��ID��ѯIPC����ϸ��IPC+NVR��</p>
	 * @return
	 * @author ������
	 * @date 2016��8��25��
	 */
	public String ipcdetial(){
		if (checkUser()) {
			jsonData = ipcService.getIpcDetail(id).toString();
		}
		return SUCCESS;
	}

}
