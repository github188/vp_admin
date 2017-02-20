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
 * <p>Description: ipc�����action��</p>
 * @author ����
 * @date 2016��8��10��
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
	 * <p>Description: �������޸���֯����</p>
	 * @return
	 * @author ������
	 * @date 2016��8��22��
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
		ipcs=new ArrayList<Ipc>();//���ipcs�Ļ��棬����ٴ��ύ�������һ�����������
		return "success";
	}

	/*
	 *ɾ������ 
	 */
	public String deleteLoc() {
		locService.deleteLoc(locMenu);
		return SUCCESS;
	}

	/*
	 * �õ����е�������Ϣ
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
	 * <p>Description: ��������id��ѯ������Ϣ</p>
	 * @return
	 * @author ����
	 * @date 2016��8��31��
	 */
	public String selectById() {
//		locMenu = locService.selectById(locMenu.getId());
		jsonData = locService.selectById(locMenu.getId());
		return SUCCESS;
	}
	/*
	 * ����������Ϣ�õ������������IPC
	 */
	public String getIpcByLoc() {
//		System.out.println("λ�õ�action-----------");
//		JSONObject jsonObject = locService.getIpcByLoc();

//		jsonData = jsonObject.toString();
		jsonData = locService.getAllLocaMenu(null).toString();
//		System.out.println("++++++++++���+++++++++++" + jsonData);
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>Title: getAllIpc</p>
	 * <p>Description: </p>
	 * @return
	 * @author ������
	 * @date 2016��8��22��
	 */
	public String getAllIpc(){
		jsonData=locService.getAllIpc(-1).toString();
		return SUCCESS;
	}
	
	/**
	 * 
	 * <p>Title: testLocMenu</p>
	 * <p>Description:���� </p>
	 * @return
	 * @author ������
	 * @date 2016��8��23��
	 */
	public String testLocMenu(){
		jsonData=locService.getAllLocaMenu(null).toString();
		System.out.println(jsonData);
		return SUCCESS;
	}

	
}
