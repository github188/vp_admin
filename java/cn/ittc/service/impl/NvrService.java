package cn.ittc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ittc.dao.INvrDao;
import cn.ittc.dao.impl.IpcDao;
import cn.ittc.entity.Nvr;
import cn.ittc.entity.NvrIpc;
import cn.ittc.entity.base.EasyUIPageResult;
import cn.ittc.service.INvrService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * <p> Title: NvrService </p>
 * <p> Description: </p>
 * @author ����
 * @date 2016��8��3��
 */
@Service(value = "nvrService")
public class NvrService implements INvrService {

	@Resource
	private INvrDao nvrDao;

	@Resource
	private IpcDao ipcDao;

	/**
	 * <p> Title:addNvr</p>
	 * <p> Description:����nvr�豸 </p>
	 * @author ����
	 * @date 2016��8��15��
	 */
	@Override
	public void addNvr(Nvr nvr) {
	//	System.out.println("---service----" + nvr.getNvrId());
	//	System.out.println(nvr.getNvrIp());
		nvrDao.addNvr(nvr);
	}

	/**
	 * <p>Title:modifyNvr </p>
	 * <p>Description:�޸�nvr�豸��Ϣ</p>
	 * @author ����
	 * @date 2016��8��3��
	 */
	@Override
	public void modifyNvr(Nvr nvr) {
		// TODO Auto-generated method stub

	}

	/**
	 * <p> Title:deleteNvr </p>
	 * <p> Description:����idɾ��nvr�豸</p>
	 * @author ����
	 * @date 2016��8��3��
	 */
	@Override
	public boolean deleteNvr(Nvr nvr) {
		Nvr nvr2 = nvrDao.getObjectById(Nvr.class, nvr.getNvrId());
		if (nvr2.getNvrIpcs().size() == 0) {
			nvrDao.deleteObject(nvr2);
			return true;
		}
		return false;

	}

	/**
	 * <p> Title:selectAll </p>
	 * <p> Description:��ȡ���е�nvr��Ϣ </p>
	 * @return list
	 * @author ����
	 * @date 2016��8��3��
	 */
	@Override
	public JSONObject selectAll() {
	//	List<Nvr> list = nvrDao.getAllObjects(Nvr.class);
		JSONObject jsonObject = new JSONObject();
		List<Nvr> nvrs = nvrDao.getAllObjects(Nvr.class);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		jsonObject.element("rows", nvrs, jsonConfig);
		jsonObject.element("total", nvrs.size());
		
		return jsonObject;
	}
	/**
	 * 
	 * <p>Title: selectAllWithOut</p>
	 * <p>Description: </p>
	 * @return
	 * @author ������
	 * @date 2016��8��19��
	 */
	public JSONObject selectAllWithOut(int pageNum,int pageSize) {
		String hql="FROM Nvr WHERE nvrId!=-1";
		EasyUIPageResult<Nvr> easyUIPageResult=nvrDao.queryEasyUIByPage(hql,pageNum,pageSize);
		JSONObject jsonObject=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonObject=JSONObject.fromObject(easyUIPageResult,jsonConfig);
		return jsonObject;
	}

	/**
	 * <p> Title:selectById </p>
	 * <p> Description:����nvr��id��ѯ��Ӧnvr��Ϣ </p>
	 * 
	 * @return nvrDao.getObjectById(Nvr.class, id)
	 * @author ����
	 * @date 2016��8��3��
	 */

	@Override
	public JSONObject selectById(int id) {
		JSONObject jsonObject = new JSONObject();
//		List<Nvr> list = new ArrayList<Nvr>();
		 Nvr a = nvrDao.getObjectById(Nvr.class, id);
		 
//		list.add(a);
		
//		jsonObject=JSONObject.fromObject(a);
		
		
		JsonConfig jsonConfig = new JsonConfig();
		
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		jsonObject.element("rows", list,jsonConfig);
//		jsonObject.element("total", list.size());
		jsonObject.element("data", a,jsonConfig);
//		list= (List<Nvr>) nvrDao.getObjectById(Nvr.class, id);
		return jsonObject;
	}

	/**
	 * <p>Title: getIpcByNvr </p>
	 * <p>Description:����nvr��Ϣ�õ���nvr������IPC�豸���������</p>
	 * 
	 * @return jsonObject
	 * @author ����
	 * @date 2016��8��10��
	 */
	@Override
	public JSONObject getIpcByNvr() {
		
		List<Nvr> list = null;
		JSONObject jsonObject = new JSONObject();
		list = nvrDao.getAllObjects(Nvr.class);
		// ��װ��
		List<Map<Object, Object>> tree = new ArrayList<Map<Object, Object>>();
		// ���е�nvr
		List<Integer> nvrList = new ArrayList<Integer>();

		for (Nvr nvr : list) {
			if (nvrList.contains(nvr.getNvrId())) {  //|| (nvr.getNvrId()==-1)
				continue;
			}
			nvrList.add(nvr.getNvrId());
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("id", nvr.getNvrId());
			map.put("text", nvr.getNvrName());
//			map.put("ip", nvr.getNvrIp());
//			map.put("name", nvr.getNvrName());
//			map.put("pwd", nvr.getNvrLoginPwd());
//			map.put("port", nvr.getNvrPort());
			// ����ipc
			List<Map<String, Object>> ipcList = new ArrayList<Map<String, Object>>();
			for (NvrIpc nvrIpc : nvr.getNvrIpcs()) {
				Map<String, Object> children = new HashMap<String, Object>();
				children.put("text", nvrIpc.getIpc().getCameraName());
				children.put("id", nvrIpc.getIpc().getCameraId());
//				children.put("name", nvrIpc.getIpc().getCameraLoginName());
//				children.put("pwd", nvrIpc.getIpc().getCameraPwd());
//				children.put("port", nvrIpc.getIpc().getCameraPort());
//				children.put("channel", nvrIpc.getChannel());
				children.put("iconCls", "icon-ipc");
				ipcList.add(children);
			}
			map.put("children", ipcList);
			tree.add(map);

		}

		jsonObject.element("success", tree);
		return jsonObject;
	}

	@Override
	public String getUnTeamIpc() {
		Nvr nvr = nvrDao.getObjectById(Nvr.class, -1);
		Map<Object, Object> map = new HashMap<Object,Object>();
		map.put("id", nvr.getNvrId());
		map.put("text", nvr.getNvrName());
		//����tree��list����
		List<Map<Object, Object>> tree = new ArrayList<Map<Object, Object>>();
		//����tree�е��ӽڵ��list����
		List<Map<String, Object>> ipclist = new ArrayList<Map<String, Object>>();
		for(NvrIpc nvrIpc : nvr.getNvrIpcs()){
			Map<String, Object> children = new HashMap<String, Object>();
			children.put("id", nvrIpc.getIpc().getCameraId());
			children.put("text", nvrIpc.getIpc().getCameraName());
			ipclist.add(children);
		}
		map.put("children", ipclist);
		tree.add(map);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("success", tree);
		System.out.println("+++++++++++++++++++++++++++++"+jsonObject.toString());
		return jsonObject.toString();
	}

}
