/**
 * <p>Title: INvrIpcDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��19��
 * @version 1.0.0
 */
package cn.ittc.dao;

import cn.ittc.entity.NvrIpc;
import cn.ittc.entity.base.EasyUIPageResult;

/**
 * <p>Title: INvrIpcDao</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��19��
 */
public interface INvrIpcDao extends IBaseDao<NvrIpc, Integer>{
	
	/**
	 * 
	 * <p>Title: deleteByParam</p>
	 * <p>Description: ���ݱ���ɾ��</p>
	 * @param nvrIpc
	 * @author ������
	 * @date 2016��8��19��
	 */
	public void deleteByParam(NvrIpc nvrIpc);
	
	/**
	 * 
	 * <p>Title: deleteByHQL</p>
	 * <p>Description: </p>
	 * @param hql
	 * @author ������
	 * @date 2016��8��19��
	 */
	public void deleteByHQL(String hql);
	/**
	 * 
	 * <p>Title: getAll</p>
	 * <p>Description: </p>
	 * @param hql
	 * @author ����
	 * @date 2016��8��19��
	 */
	public EasyUIPageResult<NvrIpc> getAll(String hql);

}
