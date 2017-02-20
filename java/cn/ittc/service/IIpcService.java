package cn.ittc.service;

import java.util.List;

import cn.ittc.entity.Ipc;

import cn.ittc.entity.Nvr;
import net.sf.json.JSONObject;

/**
 * <p> Title: IIpcService</p>
 * <p> Description:</p>
 * 
 * @author ����
 * @date 2016��8��5��
 */
public interface IIpcService {
	/**
	 * <p>Title: addIpc</p>
	 * <p> Description:���IPC��Ϣ </p>
	 * @author ����
	 * @date 2016��8��5��
	 */
	public void addIpc(Ipc ipc);
	/**
	 * 
	 * <p>Title: addIpc</p>
	 * <p>Description: </p>
	 * @param ipc
	 * @param nvrs
	 * @author ������
	 * @date 2016��8��19��
	 */
	public void addIpc(Ipc ipc,List<Nvr> nvrs);

	/**
	 * <p> Title: modifyIpc </p>
	 * <p>Description:�޸Ķ�ӦIPC��Ϣ</p>
	 * 
	 * @author ����
	 * @date 2016��8��5��
	 */
	public void modifyIpc(Ipc ipc);

	/**
	 * <p> Title: deleteIpc</p>
	 * <p>Description:����IPC��id ɾ����Ӧ��IPC�豸</p>
	 * 
	 * @author ����
	 * @date 2016��8��5��
	 */
	public void deleteIpc(Ipc ipc);

	/**
	 * <p> Title: selectAll </p>
	 * <p>Description:��ȡ���е�IPC�豸 </p>
	 * @return JSONObject
	 * @author ����
	 * @param pageSize 
	 * @param pageNum 
	 * @date 2016��8��5��
	 */
	public JSONObject selectAll(int pageNum, int pageSize);

	/**
	 * <p> Title: selectById</p>
	 * <p> Description:����IPC��id��ȡ��Ӧ��IPC�豸��Ϣ</p>
	 * 
	 * @author ����
	 * @date 2016��8��5��
	 */
	public JSONObject selectById(int id);
	/**
	 * 
	 * <p>Title: getNvrDetailByCameraId</p>
	 * <p>Description: ����IPC��ѯ���е�NVR��������NVR��</p>
	 * @param cameraId
	 * @return
	 * @author ������
	 * @date 2016��8��18��
	 */
	public JSONObject getNvrDetailByCameraId(int cameraId);
	/**
	 * 
	 * <p>Title: getCameraDetailByLocId</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 * @author ������
	 * @date 2016��8��22��
	 */
	public JSONObject getCameraDetailByLocId(Integer id);
	/**
	 * 
	 * <p>Title: selectAllByNvr</p>
	 * <p>Description: ��nvr��id���nvr ��children</p>
	 * @param nvr
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @author ����
	 * @date 2016��8��22��
	 */
	public String selectAllByNvr(Nvr nvr, int pageNum, int pageSize);
	
	
	/**
	 * 
	 * <p>Title: selectAllByLoc</p>
	 * <p>Description: ��loc��id���loc��children</p>
	 * @param id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @author ����
	 * @date 2016��8��24��
	 */
	public String selectAllByLoc(int id, int pageNum, int pageSize);
	/**
	 * 
	 * <p>Title: getIpcDetail</p>
	 * <p>Description: ����IPC�ı�Ų�ѯIPC����ϸ����NVR</p>
	 * @param id
	 * @return
	 * @author ������
	 * @date 2016��8��25��
	 */
	public JSONObject getIpcDetail(Integer id);

}
