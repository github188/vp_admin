package cn.ittc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import cn.ittc.dao.IRolePermissionDao;
import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.entity.RolePermission;




@Repository ("rolePermissionDao") 
public class RolePermissionDao extends BaseHibernateDao<RolePermission, Integer> implements IRolePermissionDao{

	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	

	@Override
	public void addRolePermission(Role role ,List<Permission> perlist) {
		
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RolePermission> selectAll() {


		Criteria rpCriteria = getSession().createCriteria(RolePermission.class);
		List<RolePermission> rplist =rpCriteria.list();
		
		return rplist;
	}

	@Override
	public void saveRolePermission(RolePermission rolepermission) {
		
		getSession().save(rolepermission);
		
		
	}

	@Override
	public void deleteRolePermissionByRoleName(Role role) {
		
		StringBuffer hql =new StringBuffer() ;
		//纯sql语句不是hql
		hql.append("delete from t_role_permission where t_role_permission.roleId =").append(role.getRoleId());
		getSession().createSQLQuery(hql.toString()).executeUpdate();
	}

	/* （no Javadoc）
	 * <p>Title: deleteByHQL</p>
	 * <p>Description: </p>
	 * @param hql
	 * @see cn.ittc.dao.IRolePermissionDao#deleteByHQL(java.lang.String)
	 * @author 焦冬冬
	 * @date 2016年8月26日
	 */
	@Override
	public void deleteByHQL(String hql){
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}
	
	public List<RolePermission> selectByRoleId(Role roles){
		Criteria criteria =getSession().createCriteria(RolePermission.class)
				.add(Restrictions.eq("role.roleId", roles.getRoleId()));
		List<RolePermission> list =criteria.list();
		List idList =new ArrayList();
		for(int i= 0; i<list.size();i++){
			//System.out.println(list.get(i).getPermission().getPermissionId());
			idList.add(i, list.get(i).getPermission().getPermissionId());
		}
		
		return idList;
	}
}
