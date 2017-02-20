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
 * @author ����
 * @date 2016��8��5��
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
	//���node�е�IPC����Ҫ��ʾ
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
	 * <p>Description: ����IPC</p>
	 * @return
	 * @author ������
	 * @date 2016��8��22��
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
		
		nvrs=new ArrayList<Nvr>();//���nvrs�Ļ��棬����ٴ��ύ�������һ�����������
		return SUCCESS;
	}
	
	/*
	 * ɾ��IPC
	 */
	public String deleteIpc(){
		ipcService.deleteIpc(ipc);
		return SUCCESS;
	}
	/*
	 * ��ȡȫ��IPC�豸
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
	 * ����cameraId��ȡ��id��IPC��Ϣ
	 */
	public String selectById(){
		JSONObject jsonObject= ipcService.selectById(ipc.getCameraId());
		jsonData=jsonObject.toString();
		return SUCCESS;
	}
	/*
	 * ���ݵ��������IPC��node�õ���ӦIPC��Ϣ
	 */
	/*public String selectByNode(){
//		System.out.println("��ʾnode="+node);
		JSONObject jsonObject = ipcService.selectById(node);
		jsonData=jsonObject.toString();
//		System.out.println("selectByNode�е�json����"+jsonData);
		return SUCCESS;
		
	}*/
	
	
	/**
	 * 
	 * <p>Title: getNvrDetailByCameraId</p>
	 * <p>Description: �����������ID��ѯ�������,ȫ����nvr�͸���������NVR���</p>
	 * @return
	 * @author ������
	 * @date 2016��8��19��
	 */
	public String getNvrDetailByCameraId() {

		jsonData = ipcService.getNvrDetailByCameraId(ipc.getCameraId()).toString();
		return SUCCESS;
	}
	/**
	 * 
	 * <p>Title: getLocDetailByCameraId</p>
	 * <p>Description: �����������ID��ѯ�������,ȫ����loc�͸���������loc���</p>
	 * @return
	 * @author ������
	 * @date 2016��8��22��
	 */
	public String getCameraDetailByLocId(){
		jsonData=ipcService.getCameraDetailByLocId(locMenu.getId()).toString();
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>Title: selectAllByLoc</p>
	 * <p>Description: ����loc��id��ѯ�������°��������и�����</p>
	 * @return
	 * @author ����
	 * @date 2016��8��25��
	 */
	public String selectAllByLoc(){
		jsonData = ipcService.selectAllByLoc(locMenu.getId(), pageNum, pageSize);
		pageSize=10;
		pageNum=1;
		return SUCCESS;
		
	}
	
	
}
