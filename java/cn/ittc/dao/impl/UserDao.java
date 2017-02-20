/**
 * <p>Title: UserDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月11日
 * @version 1.0.0
 */
package cn.ittc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.IUserDao;
import cn.ittc.entity.Role;
import cn.ittc.entity.User;
import cn.ittc.entity.base.PageInfo;
import cn.ittc.entity.base.PageResultSet;

/**
 * <p>Title: UserDao</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月11日
 */
@Repository("userDao")
public class UserDao extends BaseHibernateDao<User, Integer> implements IUserDao {
	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addUser(User user) {
		getSession().saveOrUpdate(user);
	}


	@Override
	public void modifyUser(User user) {
	}


	@Override
	public void deleteUser(User user) {
		getSession().delete(user);
		
	}
	


	@Override
	public User getUserByParam(User user) {
		StringBuilder hql= new StringBuilder("from User WHERE (flag=1 OR superAdmin=1) ");
		if(user!=null&&!"".equals(user.getUserName())){
			hql.append(" AND userName=:name ");
		}
		if(user!=null&&!"".equals(user.getPassword())){
			hql.append(" AND password=:password ");
		}
		Query query=getSession().createQuery(hql.toString());
		if(user!=null&&!"".equals(user.getUserName())){
			query.setParameter("name", user.getUserName());
		}
		if(user!=null&&!"".equals(user.getPassword())){
			query.setParameter("password", user.getPassword());
		}
		
		if(query.list().size()==0){
			return null;
		}
		return (User) query.list().get(0);
	}

	@Override
	public void modifyUserflag(User user) {
		
		StringBuffer hql =new StringBuffer();
		//flag=1代表删除
		hql.append("update User u set u.flag = '0'  where u.userId = ' ").append(user.getUserId()).append(" ' ");
		Query query =getSession() .createQuery(hql.toString());
		query.executeUpdate();
	}


	@Override
	@SuppressWarnings("unchecked")
	public List<User> selectgrid() {
		
		Criteria criteria = getSession().createCriteria(User.class)
				.add(Restrictions.ne("flag", 0))
				.add(Restrictions.ne("superAdmin", 1));
		List<User> list=criteria.list();
		
		return list;
	}


	/* （no Javadoc）
	 * <p>Title: getAllObjects</p>
	 * <p>Description:查询flag=1和非超级管理员 </p>
	 * @param entityClass
	 * @return
	 * @see cn.ittc.dao.impl.BaseHibernateDao#getAllObjects(java.lang.Class)
	 * @author 焦冬冬
	 * @date 2016年8月16日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllObjects(Class<User> entityClass) {
		StringBuilder hql= new StringBuilder("from User WHERE flag=1 AND superAdmin=0 ");
		Query query=getSession().createQuery(hql.toString());
		return query.list();
	}

	/* （no Javadoc）
	 * <p>Title: findByPage</p>
	 * <p>Description: </p>
	 * @param entity
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @see cn.ittc.dao.impl.BaseHibernateDao#findByPage(java.lang.Class, int, int)
	 * @author 焦冬冬
	 * @date 2016年8月17日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageResultSet<User> findByPage(Class<User> entity, int currentPage, int pageSize) {
		StringBuilder hql= new StringBuilder("from User WHERE flag=1 AND superAdmin=0 ");
		Query query=getSession().createQuery(hql.toString());
		query=query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize);
		PageResultSet<User> pageResultSet=new PageResultSet<User>();
		pageResultSet.setList(query.list());
		pageResultSet.setPageInfo(new PageInfo(query.list().size(), pageSize, currentPage));
		return pageResultSet;
	}

	@Override
	public void modifyUserRoleId(Role role) {
		StringBuffer sql =new StringBuffer();
		sql.append("UPDATE t_user SET t_user.roleId = (SELECT roleId from t_role where t_role.rolename ='未分组') WHERE t_user.roleId =").append(role.getRoleId());
		getSession().createSQLQuery(sql.toString()).executeUpdate();
	}
	
	


}
