package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Ipc;

/**
 * <p>Title: IIpcDao </p>
 * <p>Description: </p> 
 * @author ����
 * @date 2016��8��5��
 */
public interface IIpcDao extends IBaseDao<Ipc, Integer> {
	/**
	 * <p> Title:modifyIpc</p>
	 * <p> Description: </p>
	 * @author ����
	 * @date 2016��8��5��
	 */
	public void modifyIpc(Ipc ipc);

	/**
	 * <p>Title:deleteIpc </p>
	 * <p> Description:</p>
	 * @author ����
	 * @date 2016��8��5��
	 */
	public void deleteIpc(Ipc ipc);

	/**
	 * <p> Title:selectAll </p>
	 * <p>Description: </p>
	 * @author ����
	 * @date 2016��8��5��
	 */
	public List<Ipc> selectAll();

	/**
	 * <p>Title:getIpcByParam </p>
	 * <p> Description: </p>
	 * @author ����
	 * @date 2016��8��5��
	 */
	public Ipc getIpcByParam(Ipc ipc);
	
	/**
	 * 
	 * <p>Title: getAllIpcName</p>
	 * <p>Description: </p>
	 * @return
	 * @author ������
	 * @date 2016��8��22��
	 */
	public List<Ipc> getAllIpcName();

}
