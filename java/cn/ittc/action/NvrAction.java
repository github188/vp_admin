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
 * @author 彭坤
 * @date 2016年8月3日
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
	 * <p> Description:增加nvr设备 </p>
	 * 
	 * @return  "success"
	 * @author 彭坤
	 * @date 2016年8月9日
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
	 * <p> Description:根据nvr的id删除nvr设备</p>
	 * @return
	 * @author 彭坤
	 * @date 2016年8月9日
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
	 * <p>Description:得到所有的nvr信息</p>
	 * 
	 * @return
	 * @author 彭坤
	 * @date 2016年8月9日
	 */
	public String selectAll() {
		JSONObject jsonObject = nvrService.selectAll();
		jsonData= jsonObject.toString();
		return SUCCESS;

	}

	/**
	 * 
	 * <p>Title: selectAllWithOut</p>
	 * <p>Description: 查询所有的NVR组，除了未分组</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月19日
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
	 * <p> Description:根据nvr的id查询nvr信息</p>
	 * @return
	 * @author 彭坤
	 * @date 2016年8月9日
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
	 * <p>Description:根据nvr信息查询该nvr连接的IPC设备</p>
	 * @return
	 * @author 彭坤
	 * @date 2016年8月9日
	 */
	public String getIpcByNvr() {
		JSONObject jsonObject = nvrService.getIpcByNvr();
		jsonData = jsonObject.toString();
		return SUCCESS;

	}
	
	/**
	 * <p> Title: getUnTeamIpc </p>
	 * <p>Description: 查询所有的未分组的IPC，组装成树</p>
	 * @return
	 * @author 彭坤
	 * @date 2016年8月25日
	 */
	public String getUnTeamIpc(){
		jsonData = nvrService.getUnTeamIpc();
		System.out.println(jsonData+".................................................");
		return SUCCESS;
		
	}


}
