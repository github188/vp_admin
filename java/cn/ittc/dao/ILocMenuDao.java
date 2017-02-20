/**
 * <p>Title: ILocMenu.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��23��
 * @version 1.0.0
 */
package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.LocMenu;

/**
 * <p>Title: ILocMenu</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��23��
 */
public interface ILocMenuDao extends IBaseDao<LocMenu,Integer > {
	/**
	 * 
	 * <p>Title: getRootMenu</p>
	 * <p>Description: ��ѯ���еĸ�Ŀ¼</p>
	 * @return
	 * @author ������
	 * @date 2016��8��23��
	 */
	public List<LocMenu> getRootMenu();

}
