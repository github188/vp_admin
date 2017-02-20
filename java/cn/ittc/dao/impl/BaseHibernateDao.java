/**
 * <p>Title: BaseHibernateDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月11日
 * @version 1.0.0
 */
package cn.ittc.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.ittc.dao.IBaseDao;
import cn.ittc.entity.base.EasyUIPageResult;
import cn.ittc.entity.base.PageInfo;
import cn.ittc.entity.base.PageResultSet;

/**
 * <p>Title: BaseHibernateDao</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月11日
 */
public class BaseHibernateDao<T, PK>  implements IBaseDao<T, PK>{

//	private Class<T> entityClass;
	
	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	
//	public BaseHibernateDao(){
//		Type genType=getClass().getGenericSuperclass();
//		Type[] params=((ParameterizedType)genType).getActualTypeArguments();
//		entityClass=(Class)params[0];
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAllObjects(Class<T> entityClass) {
		List<T> list=getSession().createCriteria(entityClass).list();
		return list;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public T getObjectById(Class<T> entityClass, PK id) {
		return	(T) getSession().get(entityClass, (Serializable) id);
	}


	@SuppressWarnings("unchecked")
	@Override
	public T loadObjectById(Class<T> entityClass, PK id) {
		return (T) getSession().load(entityClass, (Serializable) id);
	}

	
	@Override
	public void saveObject(T entity) {
		// getSession().saveOrUpdate(entity);
		getSession().save(entity);
	}

	@Override
	public void deleteObject(T entity) {
		getSession().delete(entity);
	}

	
	@Override
	public void deleteObjectById(PK id) {
		
	}

	
	@Override
	public void updateObject(T entity) {
		getSession().update(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageResultSet<T> findByPage(Class<T> entity, int currentPage, int pageSize) {
		PageResultSet<T> pageResultSet = new PageResultSet<T>();
		Criteria criteria = getSession().createCriteria(entity);
		PageInfo pageInfo = new PageInfo(criteria.list().size(), pageSize, currentPage);
		pageResultSet.setPageInfo(pageInfo);
		pageResultSet.setList(criteria.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list());
		return pageResultSet;
	}

	/* （no Javadoc）
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @param pageResult
	 * @return
	 * @see cn.ittc.dao.IBaseDao#findByPage(cn.ittc.entity.base.PageResult)
	 * @author 焦冬冬
	 * @date 2016年8月16日
	 */
	@SuppressWarnings("unused")
	@Override
	public EasyUIPageResult<T> findByPage(EasyUIPageResult<T> pageResult) {
		EasyUIPageResult<T> result = new EasyUIPageResult<T>();
		Criteria criteria = getSession().createCriteria(pageResult.getClass());
		
		return result;
	}

	/* （no Javadoc）
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @param hql
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @see cn.ittc.dao.IBaseDao#findByPage(java.lang.String, int, int)
	 * @author 焦冬冬
	 * @date 2016年8月17日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EasyUIPageResult<T> queryEasyUIByPage(String hql, int pageNum, int pageSize) {
		EasyUIPageResult<T> easyUIPageResult = new EasyUIPageResult<T>();
		Query query = getSession().createQuery(hql);
		easyUIPageResult.setTotal(query.list().size());
		query = query.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize);
		easyUIPageResult.setRows(query.list());
		return easyUIPageResult;
	}

	/**
	 * 
	 * <p>Title: executeHQL</p>
	 * <p>Description: </p>
	 * @param hql
	 * @author 焦冬冬
	 * @date 2016年8月26日
	 */
	public void executeHQL(String hql){
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}


}
