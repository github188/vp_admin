/**
 * <p>Title: IDeptDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��2��
 * @version 1.0.0
 */
package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Dept;

/**
 * <p>Title: IDeptDao</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��2��
 */
public interface IDeptDao extends IBaseDao<Dept, Integer>{


	/**
	 * <p>Title: selectAllDept</p>
	 * <p>Description:��ѯ���в�����Ϣ </p>
	 * @author ��֦��
	 * @date 2016��8��4��
	 */
	
	public List<Dept> selectAllDept();
	

	/**
	 * <p>Title: selectDept</p>
	 * <p>Description:��ѯָ��������Ϣ </p>
	 * @author ��֦��
	 * @date 2016��8��4��
	 */
	
	
	public List<Dept> selectDept(Dept dept);

	/**
	 * <p>Title: deleteDept</p>
	 * <p>Description:ɾ��������Ϣ </p>
	 * @author ��֦��
	 * @date 2016��8��9��
	 */
	public void deleteDept(Dept dept);
}
