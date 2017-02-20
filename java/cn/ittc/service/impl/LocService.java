package cn.ittc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ittc.dao.IIpcDao;
import cn.ittc.dao.ILocDao;
import cn.ittc.dao.ILocMenuDao;
import cn.ittc.entity.Ipc;
import cn.ittc.entity.Loc;
import cn.ittc.entity.LocMenu;
import cn.ittc.service.ILocService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * <p>Title: LocService </p>
 * <p>Description: ipc区域的service类</p>
 * @author 彭坤
 * @date 2016年8月10日
 */
@Service(value = "locService")
public class LocService implements ILocService {

	@Resource
	private ILocDao locDao;

	@Resource
	private IIpcDao ipcDao;
	
	@Resource
	private ILocMenuDao locMenuDao; 

	/**
	 * <p> Title:addLoc </p>
	 * <p>Description: 新增位置</p>
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	@Override
	public void addLoc(LocMenu locMenu) {
		if(locMenu.getId()==null){
			locMenuDao.saveObject(locMenu);
		}else{
			locMenuDao.updateObject(locMenu);
		}

	}

	/**
	 * <p>Title:deleteLoc </p>
	 * <p>Description: 删除位置 </p>
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	@Override
	public void deleteLoc(LocMenu locMenu) {
		locMenuDao.deleteObject(locMenu);

	}

	/**
	 * <p>Title:selectAll </p>
	 * <p>Description: 获取全部位置信息 </p>
	 * @return locDao.getAllObjects(Loc.class)
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	@Override
	public List<Loc> selectAll() {
		System.out.println("到service层");
		return locDao.getAllObjects(Loc.class);
	}

	/**
	 * <p> Title:selectById </p>
	 * <p> Description: 根据位置的id获取位置信息 </p>
	 * @return locDao.getObjectById(Loc.class, id)
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	@Override
	public String selectById(int id) {
		LocMenu locMenu = locMenuDao.getObjectById(LocMenu.class, id);
		JSONObject jsonObject = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonObject.element("data", locMenu, jsonConfig);
		return jsonObject.toString();
	}

	/**
	 * <p> Title:getIpcByLoc </p>
	 * <p> Description: 根据位置得到该位置下包含的IPC设备信息，并组成树返回</p>
	 * @return jsonObject
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	@Override
	public JSONObject getIpcByLoc() {

		List<Loc> list = null;
		JSONObject jsonObject = new JSONObject();

	//	if (locId == -1) {
			list = locDao.getAllObjects(Loc.class);
			// 组装树
			List<Map<Object, Object>> tree = new ArrayList<Map<Object, Object>>();

			// ?????????
			List<Integer> locList = new ArrayList<Integer>();
			for (Loc loc : list) {
				// 判断树种是否有重复????????
				if (locList.contains(loc.getLocId())) {
					continue;
				}
				locList.add(loc.getLocId());// ??????
				// 1、先将所有的loc存储到tree中
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("id", loc.getLocId());
				map.put("text", loc.getLocName());

				// 2、将对应IPC存到loc的子节点种
				List<Map<String, Object>> ipcList = new ArrayList<Map<String, Object>>();
				loc.getLocId();

				for (Ipc ipcs : loc.getIpcs()) {
					Map<String, Object> children = new HashMap<String, Object>();
					children.put("id", ipcs.getCameraId());
					children.put("text", ipcs.getCameraName());
					ipcList.add(children);

				}
				map.put("children", ipcList);
				tree.add(map);

			}

			jsonObject.element("success", tree);
	//	} else if (locId == -2) {
//			List<Ipc> ipcs = ipcDao.getAllObjects(Ipc.class);
//			JsonConfig jsonConfig = new JsonConfig();
//			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//
//			jsonObject.element("rows", ipcs, jsonConfig);
//			jsonObject.element("total", ipcs.size());

	//	}

		return jsonObject;
	}


	@Override
	public void addIpc(Ipc ipc) {
		ipcDao.saveObject(ipc);
		
	}

	@Override
	public JSONObject getAllIpc(Integer locId) {
		try {
			List<Ipc> ipcs = ipcDao.getAllIpcName();
			JSONObject jsonObject=new JSONObject();
			jsonObject.element("success", ipcs);
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public JSONObject getAllLocaMenu(LocMenu locMenu){
		
		JSONObject jsonObject = new JSONObject();
		List<LocMenu> list=locMenuDao.getRootMenu();
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for (LocMenu locMenu2 : list) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", locMenu2.getId());
			map.put("name", locMenu2.getName());
			map.put("children", getLocChildrenMenus(locMenu2));
			listMap.add(map);
		}
		jsonObject.element("success", listMap);
		System.out.println(jsonObject);
		return jsonObject;
	}
	
	List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
	
	/**
	 * 
	 * <p>Title: getLocChildrenMenus</p>
	 * <p>Description: 根据菜单，返回其子菜单</p>
	 * @param locMenu
	 * @return locMenu的子菜单，封装在List<Map>中，若末节点有摄像机，
	 * 则把摄像机信息封装成List<Map>返回,若未节点无摄像，返回NULL，即JSON中的children:[]
	 * @author 焦冬冬
	 * @date 2016年8月24日
	 */
	private List<Map<String, Object>> getLocChildrenMenus(LocMenu locMenu) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (LocMenu locMenu2 : locMenu.getChildren()) {
			/**
			 * 若是末节点，则不执行这个for循环,则list为Null,JSON中children:[]
			 */
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", locMenu2.getId());
			map.put("name", locMenu2.getName());
			map.put("children", getLocChildrenMenus(locMenu2));
			list.add(map);
		}
		//如果是末节点，则查询是否有摄像，有摄像机则把摄像机信息封装成children返回
		if(locMenu.getIpcs()!=null&&locMenu.getIpcs().size()>0){
			List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
			for (Ipc ipc : locMenu.getIpcs()) {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("cameraId", ipc.getCameraId());
				map.put("name", ipc.getCameraName());
				map.put("iconCls", "icon-ipc");
//				map.put("ip", ipc.getCameraIp());
//				map.put("loginName", ipc.getCameraLoginName());
//				map.put("pwd", ipc.getCameraPwd());
//				map.put("nvrName", ipc.getNvrIpcs().get(0).getNvr().getNvrLoginName());
//				map.put("nvrPwd", ipc.getNvrIpcs().get(0).getNvr().getNvrLoginPwd());
//				map.put("nvrChannel", ipc.getChannelId());
				list2.add(map);
			}	
			return list2;
		}
		return list;
		
	}

}
