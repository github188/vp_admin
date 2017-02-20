package cn.ittc.service;

import java.util.List;

import cn.ittc.entity.Ipc;

import cn.ittc.entity.Nvr;
import net.sf.json.JSONObject;

/**
 * <p> Title: IIpcService</p>
 * <p> Description:</p>
 * 
 * @author 彭坤
 * @date 2016年8月5日
 */
public interface IIpcService {
	/**
	 * <p>Title: addIpc</p>
	 * <p> Description:添加IPC信息 </p>
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	public void addIpc(Ipc ipc);
	/**
	 * 
	 * <p>Title: addIpc</p>
	 * <p>Description: </p>
	 * @param ipc
	 * @param nvrs
	 * @author 焦冬冬
	 * @date 2016年8月19日
	 */
	public void addIpc(Ipc ipc,List<Nvr> nvrs);

	/**
	 * <p> Title: modifyIpc </p>
	 * <p>Description:修改对应IPC信息</p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	public void modifyIpc(Ipc ipc);

	/**
	 * <p> Title: deleteIpc</p>
	 * <p>Description:根据IPC的id 删除对应的IPC设备</p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	public void deleteIpc(Ipc ipc);

	/**
	 * <p> Title: selectAll </p>
	 * <p>Description:获取所有的IPC设备 </p>
	 * @return JSONObject
	 * @author 彭坤
	 * @param pageSize 
	 * @param pageNum 
	 * @date 2016年8月5日
	 */
	public JSONObject selectAll(int pageNum, int pageSize);

	/**
	 * <p> Title: selectById</p>
	 * <p> Description:根据IPC的id获取对应的IPC设备信息</p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	public JSONObject selectById(int id);
	/**
	 * 
	 * <p>Title: getNvrDetailByCameraId</p>
	 * <p>Description: 根据IPC查询所有的NVR和所属的NVR组</p>
	 * @param cameraId
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月18日
	 */
	public JSONObject getNvrDetailByCameraId(int cameraId);
	/**
	 * 
	 * <p>Title: getCameraDetailByLocId</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月22日
	 */
	public JSONObject getCameraDetailByLocId(Integer id);
	/**
	 * 
	 * <p>Title: selectAllByNvr</p>
	 * <p>Description: 由nvr的id查该nvr 的children</p>
	 * @param nvr
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @author 彭坤
	 * @date 2016年8月22日
	 */
	public String selectAllByNvr(Nvr nvr, int pageNum, int pageSize);
	
	
	/**
	 * 
	 * <p>Title: selectAllByLoc</p>
	 * <p>Description: 由loc的id查该loc的children</p>
	 * @param id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @author 彭坤
	 * @date 2016年8月24日
	 */
	public String selectAllByLoc(int id, int pageNum, int pageSize);
	/**
	 * 
	 * <p>Title: getIpcDetail</p>
	 * <p>Description: 根据IPC的编号查询IPC的明细包括NVR</p>
	 * @param id
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月25日
	 */
	public JSONObject getIpcDetail(Integer id);

}
