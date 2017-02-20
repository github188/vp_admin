package cn.ittc.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.entity.Ipc;
import cn.ittc.entity.LocMenu;
import cn.ittc.entity.Nvr;
import cn.ittc.service.IIpcService;
import net.sf.json.JSONObject;

/**
 * <p>Title: IpcAction </p>
 * <p> Description: </p>
 * @author 彭坤
 * @date 2016年8月5日
 */
@Controller(value="ipcAction")
public class IpcAction extends ActionSupport{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private IIpcService ipcService;
	private Ipc ipc;
	private LocMenu locMenu;
	private List<Nvr> nvrs;
	//点击node中的IPC不需要显示
//	private Integer node;
	private List<Ipc> list;
	private String jsonData;
	private int pageSize=10;
	private int pageNum=1;
	private Nvr nvr;
	
	
	/*public Integer getNode() {
		return node;
	}
	public void setNode(Integer node) {
		this.node = node;
	}*/
	
	public String getJsonData() {
		return jsonData;
	}
	public Nvr getNvr() {
		return nvr;
	}
	public void setNvr(Nvr nvr) {
		this.nvr = nvr;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public Ipc getIpc() {
		return ipc;
	}
	public void setIpc(Ipc ipc) {
		this.ipc = ipc;
	}
	public List<Ipc> getList() {
		return list;
	}
	public void setList(List<Ipc> list) {
		this.list = list;
	}
	
	public List<Nvr> getNvrs() {
		return nvrs;
	}

	public void setNvrs(List<Nvr> nvrs) {
		this.nvrs = nvrs;
	}
	
	
	public LocMenu getLocMenu() {
		return locMenu;
	}
	public void setLocMenu(LocMenu locMenu) {
		this.locMenu = locMenu;
	}
	/**
	 * 
	 * <p>Title: addIpc</p>
	 * <p>Description: 新增IPC</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月22日
	 */
	public String addIpc(){
		JSONObject jsonObject=new JSONObject();
		try {
			ipcService.addIpc(ipc,nvrs);
			jsonObject.element("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.element("success", false);
		}
		
		nvrs=new ArrayList<Nvr>();//清空nvrs的缓存，解决再次提交，清空上一次数组的内容
		return SUCCESS;
	}
	
	/*
	 * 删除IPC
	 */
	public String deleteIpc(){
		ipcService.deleteIpc(ipc);
		return SUCCESS;
	}
	/*
	 * 获取全部IPC设备
	 */
	public String selectAll(){
		if (nvr.getNvrId()==null) {
			JSONObject jsonObject =ipcService.selectAll(pageNum,pageSize);
			jsonData=jsonObject.toString();	
		}else{
			jsonData=ipcService.selectAllByNvr(nvr,pageNum,pageSize);
		}
		pageSize=10;
		pageNum=1;
		return SUCCESS;
	}
	
	public String go(){
		
		return SUCCESS;
	}
	
	/*
	 * 根据cameraId获取该id的IPC信息
	 */
	public String selectById(){
		JSONObject jsonObject= ipcService.selectById(ipc.getCameraId());
		jsonData=jsonObject.toString();
		return SUCCESS;
	}
	/*
	 * 根据点击的树中IPC的node得到对应IPC信息
	 */
	/*public String selectByNode(){
//		System.out.println("显示node="+node);
		JSONObject jsonObject = ipcService.selectById(node);
		jsonData=jsonObject.toString();
//		System.out.println("selectByNode中的json对象："+jsonData);
		return SUCCESS;
		
	}*/
	
	
	/**
	 * 
	 * <p>Title: getNvrDetailByCameraId</p>
	 * <p>Description: 根据摄像机的ID查询所属情况,全部的nvr和该摄像所在NVR情况</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月19日
	 */
	public String getNvrDetailByCameraId() {

		jsonData = ipcService.getNvrDetailByCameraId(ipc.getCameraId()).toString();
		return SUCCESS;
	}
	/**
	 * 
	 * <p>Title: getLocDetailByCameraId</p>
	 * <p>Description: 根据摄像机的ID查询所属情况,全部的loc和该摄像所在loc情况</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月22日
	 */
	public String getCameraDetailByLocId(){
		jsonData=ipcService.getCameraDetailByLocId(locMenu.getId()).toString();
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>Title: selectAllByLoc</p>
	 * <p>Description: 根据loc的id查询该区域下包含的所有该摄像</p>
	 * @return
	 * @author 彭坤
	 * @date 2016年8月25日
	 */
	public String selectAllByLoc(){
		jsonData = ipcService.selectAllByLoc(locMenu.getId(), pageNum, pageSize);
		pageSize=10;
		pageNum=1;
		return SUCCESS;
		
	}
	
	
}
