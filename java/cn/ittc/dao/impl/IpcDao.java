package cn.ittc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.IIpcDao;
import cn.ittc.entity.Ipc;

/**
 * <p>Title: IpcDao</p>
 * <p>Description:IPCDao��</p>
 * 
 * @author ����
 * @date 2016��8��5��
 */
@Repository("ipcDao")
public class IpcDao extends BaseHibernateDao<Ipc, Integer> implements IIpcDao {
	
	
	/**
	 * <p>Title:modifyIpc</p>
	 * <p>Description:�޸�IPC��Ϣ</p>
	 * 
	 * @author ����
	 * @date 2016��8��5��
	 */
	@Override
	public void modifyIpc(Ipc ipc) {
		// TODO Auto-generated method stub

	}

	/**
	 * <p>Title:deleteIpc </p>
	 * <p>Description:ɾ��IPC�豸</p>
	 * 
	 * @author ����
	 * @date 2016��8��5��
	 */
	@Override
	public void deleteIpc(Ipc ipc) {
		getSession().delete(ipc);

	}

	/**
	 * <p>Title:selectAll</p>
	 * <p>Description:�õ����е�IPC�豸 </p>
	 * 
	 * @author ����
	 * @date 2016��8��5��
	 */
	@Override
	public List<Ipc> selectAll() {
		List<Ipc> list = getAllObjects(Ipc.class);
		return list;
	}

	/**
	 * <p>Title:getIpcByParam</p>
	 * <p>Description:����IPC��id��ѯ��Ӧ��IPC��Ϣ</p>
	 * 
	 * @author ����
	 * @date 2016��8��5��
	 */
	@Override
	public Ipc getIpcByParam(Ipc ipc) {
		// TODO Auto-generated method stub
		return null;
	}

	/* ��no Javadoc��
	 * <p>Title: getAllIpcName</p>
	 * <p>Description: </p>
	 * @return
	 * @see cn.ittc.dao.IIpcDao#getAllIpcName()
	 * @author ������
	 * @date 2016��8��22��
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Ipc> getAllIpcName() {
		//ʹ��new������Ľ��ֱ����map�б�
		Query query=getSession().createQuery("SELECT new Ipc( i.cameraId,i.cameraName) FROM Ipc i");
		List<Ipc> ipcs= query.list();
		return ipcs;
	}

}
