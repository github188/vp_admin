package cn.ittc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.IPermissionDao;
import cn.ittc.entity.Permission;
import cn.ittc.entity.Role;
import cn.ittc.entity.User;

@Repository("permissionDao")
public class PermissionDao extends BaseHibernateDao<Permission, Integer> implements IPermissionDao {

	@Resource
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addPermission(Permission permission) {

		getSession().saveOrUpdate(permission);
	}

	@Override
	public void deletePermission(Permission permission) {
		getSession().delete(permission);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> selectAllPermission() {
		Criteria criteria = getSession().createCriteria(Permission.class);
		List<Permission> list = criteria.list();
		return list;

	}

	@Override
	public List<Permission> selectPermissionByRoleId(Permission permission) {
		/*
		 * getSession().createCriteria(Permission.class).createAlias("Role",role
		 * ).setFechMode("bs",FetchMode.JOIN).add(Restrictions.like("role.id",
		 * "%%")).list();
		 * 
		 * Criteria PermissionCriteria =
		 * getSession().createCriteria(Permission.class); Criteria RoleCriteria
		 * = PermissionCriteria.createCriteria("role");
		 * RoleCriteria.add(Restrictions.eq("roleId",permission.getRole() ));
		 * List rooms = roomCriteria.list(); Iterator iterator =
		 * rooms.iterator();
		 */

		return null;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Role> selectPermissionByRidUid() {

		Criteria roleCriteria = getSession().createCriteria(Role.class);
		List<Role> roleList = roleCriteria.list();
		return roleList;
//		StringBuffer hql = new StringBuffer();
//		hql.append("select distinct u.role.roleId from User u Where u.flag = 1 AND u.superAdmin =0");
//		List<Integer> list = new ArrayList<Integer>();
//		List<Role> rolelist = new ArrayList<Role>();
//		Query query = getSession().createQuery(hql.toString());
//		list = query.list();
//		for (Role role : roleList) {
//			Map<String, Object> maps = new HashMap<String, Object>();
//			Map<String, Object> usermaps = new HashMap<String, Object>();
//			int roleId = role.getRoleId();

//			if (list.contains(role.getRoleId())) {
//				rolelist.add(role);
//			}

//		}

		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getPermissionByUser(User user) {
		String sql = "SELECT t_permission.* FROM t_permission JOIN t_role_permission ON t_permission.permissionId=t_role_permission.permissionId JOIN t_role ON t_role.roleId=t_role_permission.roleId JOIN t_user ON t_user.roleId=t_role.roleId WHERE t_user.username=:username";
		Query query = getSession().createSQLQuery(sql).addScalar("permissionName", StandardBasicTypes.STRING)
				.setString("username", user.getUserName());
		List<String> list = query.list();
		return list;
	}

}
