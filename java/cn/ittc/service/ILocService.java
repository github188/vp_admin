package cn.ittc.service;

import java.util.List;

import cn.ittc.entity.Ipc;
import cn.ittc.entity.Loc;
import cn.ittc.entity.LocMenu;
import net.sf.json.JSONObject;

/**
 * <p>Title: ILocService</p>
 * <p> Description:</p>
 * @author ����
 * @date 2016��8��10��
 */
public interface ILocService {
	/**
	 * <p> Title:addLoc</p>
	 * <p>Description: ����λ�� </p>
	 * @author ����
	 * @date 2016��8��10��
	 */
	public void addLoc(LocMenu locMenu);

	/**
	 * <p>Title:deleteLoc </p>
	 * <p> Description: ɾ��λ��</p>
	 * @author ����
	 * @date 2016��8��10��
	 */
	public void deleteLoc(LocMenu locMenu);

	/**
	 * <p> Title:selectAll </p>
	 * <p> Description: ��ȡȫ��λ����Ϣ </p>
	 * @return locDao.getAllObjects(Loc.class)
	 * @author ����
	 * @date 2016��8��10��
	 */
	public List<Loc> selectAll();

	/**
	 * <p> Title:selectById </p>
	 * <p> Description: ����λ�õ�id��ȡλ����Ϣ </p>
	 * @return locDao.getObjectById(LocMenu.class, id)
	 * @author ����
	 * @date 2016��8��10��
	 */
	public String selectById(int id);

	/**
	 * <p> Title:getIpcByLoc </p>
	 * <p> Description: ����λ�õõ���λ���°�����IPC�豸��Ϣ������������� </p>
	 * @return jsonObject
	 * @author ����
	 * @date 2016��8��10��
	 */
	public JSONObject getIpcByLoc();
	/**
	 * 
	 * <p>Title: addIpc</p>
	 * <p>Description: </p>
	 * @param ipc
	 * @author ������
	 * @date 2016��8��24��
	 */
	public void addIpc(Ipc ipc);
	/**
	 * 
	 * <p>Title: getAllIpc</p>
	 * <p>Description: ��ѯ���е������</p>
	 * @return
	 * @author ������
	 * @date 2016��8��22��
	 */
	public JSONObject getAllIpc(Integer locId);
	
	
	/**
	 * 
	 * <p>Title: getAllLocaMenu</p>
	 * <p>Description: </p>
	 * @param locMenu
	 * @return
	 * @author ������
	 * @date 2016��8��23��
	 */
	public JSONObject getAllLocaMenu(LocMenu locMenu);

	


}
