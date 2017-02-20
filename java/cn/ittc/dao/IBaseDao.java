/**
 * <p>Title: IBaseDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月11日
 * @version 1.0.0
 */
package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.base.EasyUIPageResult;
import cn.ittc.entity.base.PageResultSet;

/**
 * <p>Title: IBaseDao</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月11日
 */
public interface IBaseDao<T,PK> {
	
	public List<T> getAllObjects(Class<T> entityClass);

	public T getObjectById(Class<T> entityClass, PK id);

	public T loadObjectById(Class<T> entityClass, PK id);

	public void saveObject(T entity);

	public void deleteObject(T entity);

	public void deleteObjectById(PK id);

	public void updateObject(T entity);
	
	public void saveOrUpdate(T entity);

	/**
	 * 
	 * <p>Title: findByPage</p>
	 * <p>Description: 分页查询</p>
	 * @param hql 查询语句
	 * @param currentPage 当前页面 
	 * @param pageSize  页的大小
	 * @return
	 * @author 焦冬冬
	 * @date 2016年7月14日
	 */
	public PageResultSet<T> findByPage(final Class<T> entity, final int currentPage,
			final int pageSize);
	/**
	 * 
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @param pageResult
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月16日
	 */
	public EasyUIPageResult<T> findByPage(EasyUIPageResult<T> pageResult);

	/**
	 * 
	 * <p>Title: findByPage</p>
	 * <p>Description: 根据HQL查询分页</p>
	 * @param hql
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月17日
	 */
	public EasyUIPageResult<T> queryEasyUIByPage(String hql,int pageNum,int pageSize);
	/**
	 * 
	 * <p>Title: executeHQL</p>
	 * <p>Description: </p>
	 * @param hql
	 * @author 焦冬冬
	 * @date 2016年8月26日
	 */
	public void executeHQL(String hql);
	
	
}
