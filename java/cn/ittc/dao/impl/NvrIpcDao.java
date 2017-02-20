/**
 * <p>Title: NvrIpcDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月19日
 * @version 1.0.0
 */
package cn.ittc.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.INvrIpcDao;
import cn.ittc.entity.NvrIpc;
import cn.ittc.entity.base.EasyUIPageResult;

/**
 * <p>Title: NvrIpcDao</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月19日
 */
@Repository("nvrIpcDao")
public class NvrIpcDao extends BaseHibernateDao<NvrIpc, Integer> implements INvrIpcDao {
	/**
	 * 
	 * <p>Title: deleteByParam</p>
	 * <p>Description: </p>
	 * @param nvrIpc
	 * @author 焦冬冬
	 * @date 2016年8月19日
	 */
	public void deleteByParam(NvrIpc nvrIpc){
		StringBuffer hql = new StringBuffer("delete NvrIpc n WHERE 1=1");
		if (nvrIpc.getIpc().getCameraId() != null) {
			hql.append(" AND n.ipc.cameraId=:cameraId ");
		}
		if (nvrIpc.getNvr().getNvrId() != null) {
			hql.append(" AND n.nvr.nvrId=:nvrId ");
		}

		Query query = getSession().createQuery(hql.toString());
		if (nvrIpc.getIpc().getCameraId() != null) {
			query.setInteger("cameraId", nvrIpc.getIpc().getCameraId());
		}
		if (nvrIpc.getNvr().getNvrId() != null) {
			query.setInteger("nvrId", nvrIpc.getNvr().getNvrId());
		}
		//用于执行 INSERT、UPDATE 或 DELETE 语句以及 SQL DDL（数据定义语言）语句，
		query.executeUpdate();
		
	}
	
	/**
	 * 
	 * <p>Title: deleteByHQL</p>
	 * <p>Description: </p>
	 * @param hql
	 * @author 焦冬冬
	 * @date 2016年8月19日
	 */
	public void deleteByHQL(String hql){
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public EasyUIPageResult<NvrIpc> getAll(String hql){
		Query query = getSession().createQuery(hql);
		System.out.println("----------------------------------++++++++++"+query.list());
		EasyUIPageResult<NvrIpc> easyUIPageResult = new EasyUIPageResult<NvrIpc>();
		easyUIPageResult.setRows(query.list());
		return easyUIPageResult;
	}

}
