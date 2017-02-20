package cn.ittc.service;

import java.util.List;

import cn.ittc.entity.Ipc;
import cn.ittc.entity.Loc;
import cn.ittc.entity.LocMenu;
import net.sf.json.JSONObject;

/**
 * <p>Title: ILocService</p>
 * <p> Description:</p>
 * @author 彭坤
 * @date 2016年8月10日
 */
public interface ILocService {
	/**
	 * <p> Title:addLoc</p>
	 * <p>Description: 新增位置 </p>
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	public void addLoc(LocMenu locMenu);

	/**
	 * <p>Title:deleteLoc </p>
	 * <p> Description: 删除位置</p>
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	public void deleteLoc(LocMenu locMenu);

	/**
	 * <p> Title:selectAll </p>
	 * <p> Description: 获取全部位置信息 </p>
	 * @return locDao.getAllObjects(Loc.class)
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	public List<Loc> selectAll();

	/**
	 * <p> Title:selectById </p>
	 * <p> Description: 根据位置的id获取位置信息 </p>
	 * @return locDao.getObjectById(LocMenu.class, id)
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	public String selectById(int id);

	/**
	 * <p> Title:getIpcByLoc </p>
	 * <p> Description: 根据位置得到该位置下包含的IPC设备信息，并组成树返回 </p>
	 * @return jsonObject
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	public JSONObject getIpcByLoc();
	/**
	 * 
	 * <p>Title: addIpc</p>
	 * <p>Description: </p>
	 * @param ipc
	 * @author 焦冬冬
	 * @date 2016年8月24日
	 */
	public void addIpc(Ipc ipc);
	/**
	 * 
	 * <p>Title: getAllIpc</p>
	 * <p>Description: 查询所有的摄像机</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月22日
	 */
	public JSONObject getAllIpc(Integer locId);
	
	
	/**
	 * 
	 * <p>Title: getAllLocaMenu</p>
	 * <p>Description: </p>
	 * @param locMenu
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月23日
	 */
	public JSONObject getAllLocaMenu(LocMenu locMenu);

	


}
