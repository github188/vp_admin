package cn.ittc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.ILocDao;
import cn.ittc.entity.Loc;

/**
 * <p>Title: LocDao</p>
 * <p> Description:IPCλ�� </p>
 * 
 * @author ����
 * @date 2016��8��10��
 */
@Repository("locDao")
public class LocDao extends BaseHibernateDao<Loc, Integer> implements ILocDao {

	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	/**
	 * <p>Title: selectAll </p>
	 * <p> Description:��ȡ���е�λ����Ϣ </p>
	 * @author ����
	 * @date 2016��8��10��
	 */
	@SuppressWarnings("unchecked")
	public List<Loc> selectAll() {
		Criteria criteria = getSession().createCriteria(Loc.class);
		List<Loc> list = criteria.list();
		return list;
	}

	/**
	 * <p>Title: selectById</p>
	 * <p> Description:����λ�õ�id��ѯλ����Ϣ </p>
	 * 
	 * @author ����
	 * @date 2016��8��10��
	 */
	@Override
	public Loc selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
