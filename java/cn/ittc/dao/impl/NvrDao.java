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
 * @author 彭坤
 * @date 2016年8月3日
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
	 * <p> Description:修改nvr设备信息</p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	@Override
	public void modifyNvr(Nvr nvr) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * <p>Title:deleteNvr </p>
	 * <p> Description:根据nvr的id删除对应的nvr设备 </p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	@Override
	public void addNvr(Nvr nvr) {
		getSession().saveOrUpdate(nvr);
	}
	
	

	/**
	 * <p>Title:deleteNvr </p>
	 * <p> Description:根据nvr的id删除对应的nvr设备 </p>
	 * 
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	@Override
	public void deleteNvr(Nvr nvr) {
		getSession().delete(nvr);

	}

	/**
	 * <p> Title:getNvrByParam </p>
	 * <p> Description:根据nvr的id获取对应nvr设备信息 </p>
	 * @author 彭坤
	 * @date 2016年8月3日
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
