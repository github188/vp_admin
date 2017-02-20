package cn.ittc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import cn.ittc.dao.IIpcDao;
import cn.ittc.dao.ILocMenuDao;
import cn.ittc.dao.INvrDao;
import cn.ittc.dao.INvrIpcDao;
import cn.ittc.dao.impl.BaseHibernateDao;
import cn.ittc.entity.Ipc;
import cn.ittc.entity.LocMenu;
import cn.ittc.entity.Nvr;
import cn.ittc.entity.NvrIpc;
import cn.ittc.entity.base.EasyUIPageResult;
import cn.ittc.service.IIpcService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * <p>Title: IpcService</p>
 * <p> Description: </p>
 * 
 * @author ����
 * @date 2016��8��5��
 */
@Service(value = "ipcService")
public class IpcService extends BaseHibernateDao<Ipc, Integer> implements IIpcService {
	@Resource
	private IIpcDao ipcDao;
	@Resource
	private INvrDao nvrDao;
	@Resource
	private INvrIpcDao nvrIpcDao; 
//	@Resource
//	private ILocDao locDao;
	
	@Resource
	private ILocMenuDao locMenuDao;
	
	/**
	 * <p> Title: addIpc</p>
	 * <p>Description:���IPC��Ϣ </p>
	 * @author ����
	 * @date 2016��8��5��
	 */
	@Override
	public void addIpc(Ipc ipc) {
		ipcDao.saveOrUpdate(ipc);
	}

	@Override
	public void addIpc(Ipc ipc,List<Nvr> nvrs){
		ipcDao.saveOrUpdate(ipc);
		nvrIpcDao.deleteByHQL("DELETE NvrIpc n WHERE n.ipc.cameraId="+ipc.getCameraId());
		
		if(nvrs==null||nvrs.size()==0){//����δ����
			NvrIpc nvrIpc=new NvrIpc();
			nvrIpc.setIpc(ipc);
			Nvr nvr=new Nvr();
			nvr.setNvrId(-1);
			nvrIpc.setNvr(nvr);
			nvrIpcDao.saveObject(nvrIpc);
		}else{
			for (Nvr nvr : nvrs) {
				NvrIpc nvrIpc=new NvrIpc();
				nvrIpc.setIpc(ipc);
				nvrIpc.setNvr(nvr);
				nvrIpcDao.saveObject(nvrIpc);
			}
		}
	}
	/**
	 * <p>Title: modifyIpc</p>
	 * <p>Description:�޸Ķ�ӦIPC��Ϣ </p>
	 * @author ����
	 * @date 2016��8��5��
	 */
	@Override
	public void modifyIpc(Ipc ipc) {
		// TODO Auto-generated method stub

	}

	/**
	 * <p>Title: deleteIpc </p>
	 * <p> Description:����IPC��id ɾ����Ӧ��IPC�豸 </p>
	 * @author ����
	 * @date 2016��8��5��
	 */
	@Override
	public void deleteIpc(Ipc ipc) {
		//ɾ��nvr��IPC��Ӧ���м����Ϣ
		String hql = "DELETE NvrIpc n WHERE n.ipc.cameraId="+ipc.getCameraId();
		Query query = getSession().createQuery(hql);
		//����ִ�� INSERT��UPDATE �� DELETE ����Լ� SQL DDL�����ݶ������ԣ���䣬
		query.executeUpdate();
		//ɾ��IPC��loc�м����Ϣ
		String sql = "DELETE FROM t_menu_ipc WHERE t_menu_ipc.cameraId="+ipc.getCameraId();
		Query query2 = getSession().createSQLQuery(sql);
		query2.executeUpdate();
		//ɾ��IPC
		String hqlIpc = "DELETE Ipc i WHERE i.cameraId="+ipc.getCameraId();
		Query queryIpc = getSession().createQuery(hqlIpc);
		queryIpc.executeUpdate();
	}

	/**
	 * <p> Title: selectAll </p>
	 * <p>Description:��ȡ���е�IPC�豸</p>
	 * @return JSONObject
	 * @param pageNum
	 * @param pageSize
	 * @author ����
	 * @date 2016��8��5��
	 */
	@Override
	public JSONObject selectAll(int pageNum,int pageSize) {
		String hql = "FROM Ipc";
		EasyUIPageResult<Ipc> easyUIPageResult = ipcDao.queryEasyUIByPage(hql, pageNum, pageSize);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject=JSONObject.fromObject(easyUIPageResult,jsonConfig);
		return jsonObject;
	}

	/**
	 * <p>Title: selectById </p>
	 * <p> Description:����IPC��id��ȡ��Ӧ��IPC�豸��Ϣ </p>
	 * @author ����
	 * @date 2016��8��5��
	 */
	@Override
	public JSONObject selectById(int id) {
		System.out.println("�Ѿ���SERVICE��");
		List<Ipc> list = new ArrayList<Ipc>();
		Ipc ipc = ipcDao.getObjectById(Ipc.class, id);
		System.out.println(ipc);
		list.add(ipc);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		JSONObject jsonObject = new JSONObject();
		jsonObject.element("rows", list, jsonConfig);
		jsonObject.element("total", list.size());
		System.out.println(jsonObject);

		return jsonObject;
	}

	/**
	 * 
	 * <p>Title: getDetailByCameraId</p>
	 * <p>Description: </p>
	 * @param cameraId
	 * @return
	 * @author ������
	 * @date 2016��8��18��
	 */
	public JSONObject getNvrDetailByCameraId(int cameraId) {
		JSONObject jsonObject=new JSONObject();
		List<Nvr> nvrs=nvrDao.getAllObjects(Nvr.class);
		
		List<NvrIpc> nvrIpcs=new ArrayList<NvrIpc>();
		if(cameraId>0){
			Ipc ipc=ipcDao.getObjectById(Ipc.class, cameraId);
			nvrIpcs=ipc.getNvrIpcs();
		}
		
		List<Map<String, Object>> listMap=new ArrayList<Map<String, Object>>();
		for (Nvr nvr : nvrs) {
			Map<String, Object> map=new HashMap<String, Object>();
			for (NvrIpc nvrIpc : nvrIpcs) {
				if(nvrIpc.getNvr().getNvrId().equals(nvr.getNvrId())){
					map.put("selected", "selected");
				}
			}
			map.put("id", nvr.getNvrId());
			map.put("name", nvr.getNvrName());
			listMap.add(map);
		}
		jsonObject.element("success", listMap);
		return jsonObject;
	}

	/* ��no Javadoc��
	 * <p>Title: getCameraDetailByLocId</p>
	 * <p>Description: </p>
	 * @param locId
	 * @return
	 * @see cn.ittc.service.IIpcService#getCameraDetailByLocId(java.lang.Integer)
	 * @author ������
	 * @date 2016��8��22��
	 */
	@Override
	public JSONObject getCameraDetailByLocId(Integer id) {
		JSONObject jsonObject=new JSONObject();
		List<Ipc> ipcs=ipcDao.getAllIpcName();
		List<Ipc> ipcs2=new ArrayList<Ipc>();
		if(id!=null&&id>=1){
			LocMenu locMenu = locMenuDao.getObjectById(LocMenu.class, id);
//			Loc loc=locDao.getObjectById(Loc.class, locId);
			ipcs2=locMenu.getIpcs();
		}
		List<Map<String, Object>> listMap=new ArrayList<Map<String, Object>>();
		for (Ipc ipc : ipcs) {
			Map<String, Object> map=new HashMap<String, Object>();
			for (Ipc ipc2 : ipcs2) {
				if(ipc.getCameraId().equals(ipc2.getCameraId())){
					map.put("checked", "checked");
				}
			}
			map.put("id", ipc.getCameraId());
			map.put("name", ipc.getCameraName());
			listMap.add(map);
		}
		jsonObject.element("success", listMap);
		return jsonObject;
	}

	/* ��no Javadoc��
	 * <p>Title: getIpcDetail</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 * @see cn.ittc.service.IIpcService#getIpcDetail(java.lang.Integer)
	 * @author ������
	 * @date 2016��8��25��
	 */
	@Override
	public JSONObject getIpcDetail(Integer id) {
		Ipc ipc=ipcDao.getObjectById(Ipc.class, id);
		Map<String, Object> map=new HashMap<String, Object>();
		Map<String, Object> map1=new HashMap<String, Object>();
		map1.put("name", ipc.getCameraLoginName());
		map1.put("pwd", ipc.getCameraPwd());
		map1.put("ip", ipc.getCameraIp());
		map1.put("port", ipc.getCameraPort());
		map.put("ipc", map1);
		for ( NvrIpc nvrIpc : ipc.getNvrIpcs()) {
			Nvr nvr= nvrIpc.getNvr();
			if(nvr!=null){
				Map<String, Object> map2=new HashMap<String, Object>();
				map2.put("pwd", nvr.getNvrLoginPwd());
				map2.put("name", nvr.getNvrLoginName());
				map2.put("channel", ipc.getNvrIpcs().get(0).getChannel());
				map2.put("ip", nvr.getNvrIp());
				map2.put("port", nvr.getNvrPort());
				map.put("nvr", map2);
			}
		}
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.element("data", map);
		return jsonObject;
	}


	@Override
	public String selectAllByNvr(Nvr nvr, int pageNum, int pageSize) {

		String hql="FROM NvrIpc n WHERE n.nvr.nvrId="+nvr.getNvrId();
		EasyUIPageResult<NvrIpc> easyUIPageResult = nvrIpcDao.getAll(hql);
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//		List<Ipc> list = new ArrayList<Ipc>();
		String ipchql="From Ipc i WHERE i.cameraId=";//+1+" OR i.cameraId="+2;
		
		if (easyUIPageResult.getRows().size()==0) {
			ipchql += 0+"";
		}else {
			for (int j = 0; j < easyUIPageResult.getRows().size(); j++) {
				if (j != easyUIPageResult.getRows().size()-1) {
					ipchql +=easyUIPageResult.getRows().get(j).getIpc().getCameraId()+" OR i.cameraId=";
				}else {
					ipchql +=easyUIPageResult.getRows().get(j).getIpc().getCameraId()+"";
				}
			}
		}
		
		
		EasyUIPageResult<Ipc> easyUIPageResultIpc = ipcDao.queryEasyUIByPage(ipchql, pageNum, pageSize);
		jsonObject = JSONObject.fromObject(easyUIPageResultIpc, jsonConfig);

		return jsonObject.toString();
	}

	@Override
	public String selectAllByLoc(int id, int pageNum, int pageSize) {
		
		LocMenu locMenu = locMenuDao.getObjectById(LocMenu.class, id);
//		Loc loc = locDao.getObjectById(Loc.class, id);
		String hql ="FROM Ipc i WHERE i.cameraId=";
		int k =0;
		if (locMenu.getIpcs().size()!=0) {
			for(Ipc ipc : locMenu.getIpcs()){
				k=k+1;
				if (k!=(locMenu.getIpcs().size())) {
					hql += ipc.getCameraId() + " OR i.cameraId=";
				}else{
					hql += ipc.getCameraId();
				}
			}
		}else {
			hql += 0+"";
		}
		
		System.out.println(hql);
		EasyUIPageResult<Ipc> easyUIPageResult = ipcDao.queryEasyUIByPage(hql, pageNum, pageSize);
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonObject= JSONObject.fromObject(easyUIPageResult,jsonConfig);
		return jsonObject.toString();
	}

}
