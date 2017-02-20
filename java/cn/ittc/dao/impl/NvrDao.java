package cn.ittc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.INvrDao;
import cn.ittc.entity.Nvr;

/**
 * <p> Title: NvrDao</p>
 * <p>Description: </p>
 * 
 * @author ����
 * @date 2016��8��3��
 */
@Repository("nvrDao")
public class NvrDao extends BaseHibernateDao<Nvr, Integer> implements INvrDao {

	@Resource
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * <p>Title:modifyNvr</p>
	 * <p> Description:�޸�nvr�豸��Ϣ</p>
	 * 
	 * @author ����
	 * @date 2016��8��3��
	 */
	@Override
	public void modifyNvr(Nvr nvr) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * <p>Title:deleteNvr </p>
	 * <p> Description:����nvr��idɾ����Ӧ��nvr�豸 </p>
	 * 
	 * @author ����
	 * @date 2016��8��3��
	 */
	@Override
	public void addNvr(Nvr nvr) {
		getSession().saveOrUpdate(nvr);
	}
	
	

	/**
	 * <p>Title:deleteNvr </p>
	 * <p> Description:����nvr��idɾ����Ӧ��nvr�豸 </p>
	 * 
	 * @author ����
	 * @date 2016��8��3��
	 */
	@Override
	public void deleteNvr(Nvr nvr) {
		getSession().delete(nvr);

	}

	/**
	 * <p> Title:getNvrByParam </p>
	 * <p> Description:����nvr��id��ȡ��Ӧnvr�豸��Ϣ </p>
	 * @author ����
	 * @date 2016��8��3��
	 */
	@Override
	public List<Nvr> getNvrByParam(Nvr nvr) {
		
		return null;
	}
	/**
	 * 
	 */
	public List<Nvr> getNvrByHQL(){
		
		return null;
	}


	
}
