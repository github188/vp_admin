package cn.ittc.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.entity.Ipc;
import cn.ittc.entity.Loc;
import cn.ittc.entity.LocMenu;
import cn.ittc.service.ILocService;
import net.sf.json.JSONObject;

/**
 * <p>Title: LocAction </p>
 * <p>Description: ipc区域的action类</p>
 * @author 彭坤
 * @date 2016年8月10日
 */

@Controller(value = "locAction")
public class LocAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Resource
	private ILocService locService;
	private Ipc ipc;
	private Loc loc;
	private LocMenu locMenu;
	private List<Loc> locs;
	private List<Ipc> ipcs;
	private String jsonData;

	

	public LocMenu getLocMenu() {
		return locMenu;
	}

	public void setLocMenu(LocMenu locMenu) {
		this.locMenu = locMenu;
	}

	public Ipc getIpc() {
		return ipc;
	}

	public void setIpc(Ipc ipc) {
		this.ipc = ipc;
	}

	public Loc getLoc() {
		return loc;
	}

	public void setLoc(Loc loc) {
		this.loc = loc;
	}

	public List<Loc> getLocs() {
		return locs;
	}


	public List<Ipc> getIpcs() {
		return ipcs;
	}

	public void setIpcs(List<Ipc> ipcs) {
		this.ipcs = ipcs;
	}

	public void setLocs(List<Loc> locs) {
		this.locs = locs;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	/**
	 * 
	 * <p>Title: addLoc</p>
	 * <p>Description: 新增和修改组织分组</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月22日
	 */
	public String addLoc() {
		
//		List<Ipc> ipcsTemp=new ArrayList<Ipc>();
//		for (Ipc ipc : ipcs) {
//			Ipc ipcTemp=new Ipc();
//			ipcTemp.setCameraId(ipc.getCameraId());
//			ipcsTemp.add(ipcTemp);
//		}
//		loc.setIpcs(ipcsTemp);
		JSONObject jsonObject=new JSONObject();
		try {
			System.out.println("`````````````````````````````````````````````"+locMenu.getParent().getId()+"---name="+locMenu.getName()+"```````parent="+locMenu.getParent());
//			locMenu.setParent(locMenu.getParent());
			
			locMenu.setIpcs(ipcs);
			if (locMenu.getParent().getId()==null) {
				locMenu.setParent(null);
			}
			locService.addLoc(locMenu);
			jsonObject.element("success", true);
		} catch (Exception e) {
			jsonObject.element("success", false);
		}
		
		jsonData=jsonObject.toString();
		ipcs=new ArrayList<Ipc>();//清空ipcs的缓存，解决再次提交，清空上一次数组的内容
		return "success";
	}

	/*
	 *删除区域 
	 */
	public String deleteLoc() {
		locService.deleteLoc(locMenu);
		return SUCCESS;
	}

	/*
	 * 得到所有的区域信息
	 */
	public String selectAll() {
//		System.out.println("loc action");
		locs = locService.selectAll();
		// JSONArray jsonArray = JSONArray.fromObject(list);
//		System.out.println(list);
		return SUCCESS;
	}
	/**
	 * 
	 * <p>Title: selectById</p>
	 * <p>Description: 根据区域id查询区域信息</p>
	 * @return
	 * @author 彭坤
	 * @date 2016年8月31日
	 */
	public String selectById() {
//		locMenu = locService.selectById(locMenu.getId());
		jsonData = locService.selectById(locMenu.getId());
		return SUCCESS;
	}
	/*
	 * 根据区域信息得到该区域的所有IPC
	 */
	public String getIpcByLoc() {
//		System.out.println("位置的action-----------");
//		JSONObject jsonObject = locService.getIpcByLoc();

//		jsonData = jsonObject.toString();
		jsonData = locService.getAllLocaMenu(null).toString();
//		System.out.println("++++++++++输出+++++++++++" + jsonData);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>Title: getAllIpc</p>
	 * <p>Description: </p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月22日
	 */
	public String getAllIpc(){
		jsonData=locService.getAllIpc(-1).toString();
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>Title: testLocMenu</p>
	 * <p>Description:测试 </p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月23日
	 */
	public String testLocMenu(){
		jsonData=locService.getAllLocaMenu(null).toString();
		System.out.println(jsonData);
		return SUCCESS;
	}

	
}
