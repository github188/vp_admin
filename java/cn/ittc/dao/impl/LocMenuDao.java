/**
 * <p>Title: LocMenu.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��23��
 * @version 1.0.0
 */
package cn.ittc.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.ILocMenuDao;
import cn.ittc.entity.LocMenu;

/**
 * <p>Title: LocMenu</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��23��
 */
@Repository("locMenuDao")
public class LocMenuDao extends BaseHibernateDao<LocMenu, Integer> implements ILocMenuDao{

	/* ��no Javadoc��
	 * <p>Title: getRootMenu</p>
	 * <p>Description: parent=nullΪ��Ŀ¼</p>
	 * @param hql
	 * @return
	 * @see cn.ittc.dao.ILocMenuDao#getRootMenu(java.lang.String)
	 * @author ������
	 * @date 2016��8��23��
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LocMenu> getRootMenu() {
		Query query=getSession().createQuery("FROM LocMenu WHERE parent=null");
		query.setCacheable(false);
		return query.list();
	}

}
