package cn.ittc.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.entity.Nvr;
import cn.ittc.service.INvrService;
import net.sf.json.JSONObject;

/**
 * <p>Title: NvrAction </p>
 * <p> Description:</p>
 * 
 * @author ����
 * @date 2016��8��3��
 */
@Controller(value = "nvrAction")
public class NvrAction extends ActionSupport {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@Resource
	private INvrService nvrService;
	private Nvr nvr;
	// private Ipc ipc;
	private List<Nvr> list;
	private int pageSize=10;
	private int pageNum=1;
	private String jsonData;

	public Nvr getNvr() {
		return nvr;
	}

	public void setNvr(Nvr nvr) {
		this.nvr = nvr;
	}

	public List<Nvr> getList() {
		return list;
	}

	public void setList(List<Nvr> list) {
		this.list = list;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
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

	/**
	 * 
	 * <p> Title:addNvr</p>
	 * <p> Description:����nvr�豸 </p>
	 * 
	 * @return  "success"
	 * @author ����
	 * @date 2016��8��9��
	 */
	public String addNvr() {
		nvrService.addNvr(nvr);
		JSONObject jsonObject=new JSONObject();
		jsonObject.element("success", true);
		jsonData=jsonObject.toString();
		return SUCCESS;
	}

	/**
	 * 
	 * <p> Title:deleteNvr</p>
	 * <p> Description:����nvr��idɾ��nvr�豸</p>
	 * @return
	 * @author ����
	 * @date 2016��8��9��
	 */
	public String deleteNvr() {
		boolean b=nvrService.deleteNvr(nvr);
		JSONObject jsonObject=new JSONObject();
		jsonObject.element("success", b);
		jsonData=jsonObject.toString();
		return SUCCESS;
	}

	/**
	 * 
	 * <p>Title:selectAll</p>
	 * <p>Description:�õ����е�nvr��Ϣ</p>
	 * 
	 * @return
	 * @author ����
	 * @date 2016��8��9��
	 */
	public String selectAll() {
		JSONObject jsonObject = nvrService.selectAll();
		jsonData= jsonObject.toString();
		return SUCCESS;

	}

	/**
	 * 
	 * <p>Title: selectAllWithOut</p>
	 * <p>Description: ��ѯ���е�NVR�飬����δ����</p>
	 * @return
	 * @author ������
	 * @date 2016��8��19��
	 */
	public String selectAllWithOut(){
		try {
			JSONObject jsonObject = nvrService.selectAllWithOut(pageNum,pageSize);
			jsonData= jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	/**
	 * 
	 * <p>Title:selectById</p>
	 * <p> Description:����nvr��id��ѯnvr��Ϣ</p>
	 * @return
	 * @author ����
	 * @date 2016��8��9��
	 */
	public String selectById() {
		JSONObject jsonObject = nvrService.selectById(nvr.getNvrId());
//		list = nvrService.selectById(nvr.getNvrId());
		jsonData = jsonObject.toString();
		System.out.println(jsonData);
		return SUCCESS;
	}

	/**
	 * <p> Title: getNvrByIpc </p>
	 * <p>Description:����nvr��Ϣ��ѯ��nvr���ӵ�IPC�豸</p>
	 * @return
	 * @author ����
	 * @date 2016��8��9��
	 */
	public String getIpcByNvr() {
		JSONObject jsonObject = nvrService.getIpcByNvr();
		jsonData = jsonObject.toString();
		return SUCCESS;

	}
	
	/**
	 * <p> Title: getUnTeamIpc </p>
	 * <p>Description: ��ѯ���е�δ�����IPC����װ����</p>
	 * @return
	 * @author ����
	 * @date 2016��8��25��
	 */
	public String getUnTeamIpc(){
		jsonData = nvrService.getUnTeamIpc();
		System.out.println(jsonData+".................................................");
		return SUCCESS;
		
	}


}
