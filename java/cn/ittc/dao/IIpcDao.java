package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Ipc;

/**
 * <p>Title: IIpcDao </p>
 * <p>Description: </p> 
 * @author 彭坤
 * @date 2016年8月5日
 */
public interface IIpcDao extends IBaseDao<Ipc, Integer> {
	/**
	 * <p> Title:modifyIpc</p>
	 * <p> Description: </p>
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	public void modifyIpc(Ipc ipc);

	/**
	 * <p>Title:deleteIpc </p>
	 * <p> Description:</p>
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	public void deleteIpc(Ipc ipc);

	/**
	 * <p> Title:selectAll </p>
	 * <p>Description: </p>
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	public List<Ipc> selectAll();

	/**
	 * <p>Title:getIpcByParam </p>
	 * <p> Description: </p>
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	public Ipc getIpcByParam(Ipc ipc);
	
	/**
	 * 
	 * <p>Title: getAllIpcName</p>
	 * <p>Description: </p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月22日
	 */
	public List<Ipc> getAllIpcName();

}
