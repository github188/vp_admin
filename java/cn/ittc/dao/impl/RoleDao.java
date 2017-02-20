package cn.ittc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.IRoleDao;
import cn.ittc.entity.Role;

@Repository("roleDao")
public class RoleDao extends BaseHibernateDao<Role, Integer> implements IRoleDao {

	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	
	
	
	
	@Override
	public void addRole(Role role) {
		
		getSession().saveOrUpdate(role);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> selectAllRole() {
		Criteria criteria = getSession().createCriteria(Role.class);
		List<Role> list=criteria.list();
		return list;
	}
	
	
	@Override
	public void delectRole(Role role) {
		System.out.println("½øÈëdelectRoleDao");
		//getSession().delete(role);
		StringBuffer hql =new StringBuffer();
		hql.append("delete from t_role where t_role.roleId =").append(role.getRoleId());
		getSession().createSQLQuery(hql.toString()).executeUpdate();
		
		/*Criteria criteria = getSession().createCriteria(Role.class);
		List <Role > list =criteria.list();
		for(int i =0;i<list.size();i++)
		{
			System.out.println("i:"+i+"--------"+list.get(i).getRoleId()+"and"+list.get(i).getRoleName());
		}*/
}

	@Override
	public Role addRoleByRoleName(Role role) {
		
		getSession().saveOrUpdate(role);
		Criteria criteria = getSession().createCriteria(Role.class)
				.add(Restrictions.eq("roleName", role.getRoleName()));
		Role roles =(Role) criteria.list().get(0);
		return roles;
	}

	@Override
	public void addRolePermission(Role role, int[] arg) {
		
		role.getRolepermissions();
		
	}

	@Override
	public Role selectRoleByRoleName(Role role) {
		Criteria criteria = getSession().createCriteria(Role.class)
				.add(Restrictions.eq("roleName", role.getRoleName()));
		Role roles =(Role) criteria.list().get(0);
		return roles;
		
	}
}
