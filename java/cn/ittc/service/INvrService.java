package cn.ittc.service;

import cn.ittc.entity.Nvr;
import net.sf.json.JSONObject;

/**
 * <p>Title: INvrService</p>
 * <p>Description:</p>
 * 
 * @author ����
 * @date 2016��8��3��
 */

public interface INvrService {
	/**
	 * <p>Title:addNvr </p>
	 * <p> Description:����nvr�豸 </p>
	 * 
	 * @author ����
	 * @date 2016��8��3��
	 */
	public void addNvr(Nvr nvr);

	/**
	 * <p> Title:modifyNvr </p>
	 * <p> Description:�޸�nvr�豸��Ϣ</p>
	 * 
	 * @author ����
	 * @date 2016��8��3��
	 */
	public void modifyNvr(Nvr nvr);

	/**
	 * <p> Title:deleteNvr </p>
	 * <p> Description:����idɾ��nvr�豸</p>
	 * 
	 * @author ����
	 * @date 2016��8��3��
	 */
	public boolean deleteNvr(Nvr nvr);

	/**
	 * <p> Title:selectAll</p>
	 * <p> Description:��ȡ���е�nvr��Ϣ </p>
	 * 
	 * @return list
	 * @author ����
	 * @date 2016��8��3��
	 */
	public JSONObject selectAll();
	/**
	 * 
	 * <p>Title: selectAllWithOut</p>
	 * <p>Description:int pageNum,int pageSize </p>
	 * @param pageNum �ڼ�ҳ
	 * @param pageSize ÿҳ��ʾ����
	 * @return
	 * @author ������
	 * @date 2016��8��19��
	 */
	public JSONObject selectAllWithOut(int pageNum,int pageSize);
	/**
	 * <p>Title:selectById </p>
	 * <p>Description:����nvr��id��ѯ��Ӧnvr��Ϣ </p>
	 * 
	 * @return nvrDao.getObjectById(Nvr.class, id)
	 * @author ����
	 * @date 2016��8��3��
	 */
	public JSONObject selectById(int id);

	/**
	 * <p>Title: getIpcByNvr </p>
	 * <p>Description:����nvr��Ϣ�õ���nvr������IPC�豸��������� </p>
	 * 
	 * @return jsonObject
	 * @author ����
	 * @date 2016��8��10��
	 */
	public JSONObject getIpcByNvr();
	/**
	 * <p>Title: getUnTeamIpc </p>
	 * <p>Description:��ѯ���е�δ�����IPC����װ���� </p>
	 * 
	 * @return String
	 * @author ����
	 * @date 2016��8��10��
	 */
	public String getUnTeamIpc();

	

}
