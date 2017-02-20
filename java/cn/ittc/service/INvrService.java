package cn.ittc.service;

import cn.ittc.entity.Nvr;
import net.sf.json.JSONObject;

/**
 * <p>Title: INvrService</p>
 * <p>Description:</p>
 * 
 * @author 彭坤
 * @date 2016年8月3日
 */

public interface INvrService {
	/**
	 * <p>Title:addNvr </p>
	 * <p> Description:新增nvr设备 </p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	public void addNvr(Nvr nvr);

	/**
	 * <p> Title:modifyNvr </p>
	 * <p> Description:修改nvr设备信息</p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	public void modifyNvr(Nvr nvr);

	/**
	 * <p> Title:deleteNvr </p>
	 * <p> Description:根据id删除nvr设备</p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	public boolean deleteNvr(Nvr nvr);

	/**
	 * <p> Title:selectAll</p>
	 * <p> Description:获取所有的nvr信息 </p>
	 * 
	 * @return list
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	public JSONObject selectAll();
	/**
	 * 
	 * <p>Title: selectAllWithOut</p>
	 * <p>Description:int pageNum,int pageSize </p>
	 * @param pageNum 第几页
	 * @param pageSize 每页显示条数
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月19日
	 */
	public JSONObject selectAllWithOut(int pageNum,int pageSize);
	/**
	 * <p>Title:selectById </p>
	 * <p>Description:根据nvr的id查询对应nvr信息 </p>
	 * 
	 * @return nvrDao.getObjectById(Nvr.class, id)
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	public JSONObject selectById(int id);

	/**
	 * <p>Title: getIpcByNvr </p>
	 * <p>Description:根据nvr信息得到该nvr包含的IPC设备，并组成树 </p>
	 * 
	 * @return jsonObject
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	public JSONObject getIpcByNvr();
	/**
	 * <p>Title: getUnTeamIpc </p>
	 * <p>Description:查询所有的未分组的IPC，组装成树 </p>
	 * 
	 * @return String
	 * @author 彭坤
	 * @date 2016年8月10日
	 */
	public String getUnTeamIpc();

	

}
