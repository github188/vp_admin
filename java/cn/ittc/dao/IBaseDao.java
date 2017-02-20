/**
 * <p>Title: IBaseDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��7��11��
 * @version 1.0.0
 */
package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.base.EasyUIPageResult;
import cn.ittc.entity.base.PageResultSet;

/**
 * <p>Title: IBaseDao</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��7��11��
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
	 * <p>Description: ��ҳ��ѯ</p>
	 * @param hql ��ѯ���
	 * @param currentPage ��ǰҳ�� 
	 * @param pageSize  ҳ�Ĵ�С
	 * @return
	 * @author ������
	 * @date 2016��7��14��
	 */
	public PageResultSet<T> findByPage(final Class<T> entity, final int currentPage,
			final int pageSize);
	/**
	 * 
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @param pageResult
	 * @return
	 * @author ������
	 * @date 2016��8��16��
	 */
	public EasyUIPageResult<T> findByPage(EasyUIPageResult<T> pageResult);

	/**
	 * 
	 * <p>Title: findByPage</p>
	 * <p>Description: ����HQL��ѯ��ҳ</p>
	 * @param hql
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @author ������
	 * @date 2016��8��17��
	 */
	public EasyUIPageResult<T> queryEasyUIByPage(String hql,int pageNum,int pageSize);
	/**
	 * 
	 * <p>Title: executeHQL</p>
	 * <p>Description: </p>
	 * @param hql
	 * @author ������
	 * @date 2016��8��26��
	 */
	public void executeHQL(String hql);
	
	
}
