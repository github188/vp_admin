package cn.ittc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.IIpcDao;
import cn.ittc.entity.Ipc;

/**
 * <p>Title: IpcDao</p>
 * <p>Description:IPCDao类</p>
 * 
 * @author 彭坤
 * @date 2016年8月5日
 */
@Repository("ipcDao")
public class IpcDao extends BaseHibernateDao<Ipc, Integer> implements IIpcDao {
	
	
	/**
	 * <p>Title:modifyIpc</p>
	 * <p>Description:修改IPC信息</p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	@Override
	public void modifyIpc(Ipc ipc) {
		// TODO Auto-generated method stub

	}

	/**
	 * <p>Title:deleteIpc </p>
	 * <p>Description:删除IPC设备</p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	@Override
	public void deleteIpc(Ipc ipc) {
		getSession().delete(ipc);

	}

	/**
	 * <p>Title:selectAll</p>
	 * <p>Description:得到所有的IPC设备 </p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	@Override
	public List<Ipc> selectAll() {
		List<Ipc> list = getAllObjects(Ipc.class);
		return list;
	}

	/**
	 * <p>Title:getIpcByParam</p>
	 * <p>Description:根据IPC的id查询对应的IPC信息</p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月5日
	 */
	@Override
	public Ipc getIpcByParam(Ipc ipc) {
		// TODO Auto-generated method stub
		return null;
	}

	/* （no Javadoc）
	 * <p>Title: getAllIpcName</p>
	 * <p>Description: </p>
	 * @return
	 * @see cn.ittc.dao.IIpcDao#getAllIpcName()
	 * @author 焦冬冬
	 * @date 2016年8月22日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Ipc> getAllIpcName() {
		//使用new，查出的结果直接是map列表
		Query query=getSession().createQuery("SELECT new Ipc( i.cameraId,i.cameraName) FROM Ipc i");
		List<Ipc> ipcs= query.list();
		return ipcs;
	}

}
