/**
 * <p>Title: DeptDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月2日
 * @version 1.0.0
 */
package cn.ittc.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.IDeptDao;
import cn.ittc.entity.Dept;

/**
 * <p>Title: DeptDao</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月2日
 */
@Repository("deptDao")
public class DeptDao extends BaseHibernateDao<Dept, Integer> implements IDeptDao{

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> selectAllDept() {
		Criteria criteria = getSession().createCriteria(Dept.class);
		List<Dept> list = criteria.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dept> selectDept(Dept dept) {
		StringBuilder hql= new StringBuilder("from Dept WHERE 1=1 ");
		if(dept!=null&&!"".equals(dept.getDeptName())){
			hql.append(" AND deptName=:deptName ");
		}
		if(dept!=null&&!"0".equals(dept.getDeptId())){
			hql.append(" AND deptId=:deptId ");
		}
		Query query=getSession().createQuery(hql.toString());
		if(dept!=null&&!"".equals(dept.getDeptName())){
			query.setParameter("deptName", dept.getDeptName());
		}
		if(dept!=null&&!"".equals(dept.getDeptId())){
			query.setParameter("deptId", dept.getDeptId());
		}
		
		if(query.list().size()==0){
			return null;
		}
		return (List<Dept>) query.list().get(0);
		
	}

	@Override
	public void deleteDept(Dept dept) {
		getSession().delete(dept);
		
	}

	


}
