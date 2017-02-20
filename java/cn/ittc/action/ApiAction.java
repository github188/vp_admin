/**
 * <p>Title: ApiAction.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月25日
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
 * <p>Description: CS平台对接API</p>
 * @author 焦冬冬
 * @date 2016年8月25日
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
	 * <p>Description: 根据账号密码验证</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月25日
	 */
	private boolean checkUser() {
		JSONObject jsonObject = new JSONObject();
		User user = new User(name, pwd);
		boolean b=userService.CheckLogin(user);
		if(!b){
			jsonObject.element("data",b);
			jsonObject.element("info","没有操作权限！");
		}
		jsonData = jsonObject.toString();
		return b;
	}
	/**
	 * 
	 * <p>Title: login</p>
	 * <p>Description: 登陆验证</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月25日
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
	 * <p>Description: 查询权限</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月25日
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
	 * <p>Description: 查询所有IPC的tree列表</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月25日
	 */
	public String ipclist() {
		if (checkUser()) {
			if (id!=null&&id == 1) {//多层目录
				jsonData = locService.getAllLocaMenu(null).toString();
			} else if(id!=null&&id==2) {//NVR和IPC二层目录
				jsonData =nvrService.getIpcByNvr().toString();
			}else{
				JSONObject jsonObject = new JSONObject();

				jsonObject.element("data", false);
				jsonObject.element("info", "类型不存在！");

				jsonData = jsonObject.toString();
			}
		}
		return SUCCESS;
	}
	/**
	 * 
	 * <p>Title: ipcdetial</p>
	 * <p>Description: 根据IPC的ID查询IPC的明细（IPC+NVR）</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月25日
	 */
	public String ipcdetial(){
		if (checkUser()) {
			jsonData = ipcService.getIpcDetail(id).toString();
		}
		return SUCCESS;
	}

}
