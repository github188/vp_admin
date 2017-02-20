/**
 * <p>Title: INvrIpcDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月19日
 * @version 1.0.0
 */
package cn.ittc.dao;

import cn.ittc.entity.NvrIpc;
import cn.ittc.entity.base.EasyUIPageResult;

/**
 * <p>Title: INvrIpcDao</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月19日
 */
public interface INvrIpcDao extends IBaseDao<NvrIpc, Integer>{
	
	/**
	 * 
	 * <p>Title: deleteByParam</p>
	 * <p>Description: 根据变量删除</p>
	 * @param nvrIpc
	 * @author 焦冬冬
	 * @date 2016年8月19日
	 */
	public void deleteByParam(NvrIpc nvrIpc);
	
	/**
	 * 
	 * <p>Title: deleteByHQL</p>
	 * <p>Description: </p>
	 * @param hql
	 * @author 焦冬冬
	 * @date 2016年8月19日
	 */
	public void deleteByHQL(String hql);
	/**
	 * 
	 * <p>Title: getAll</p>
	 * <p>Description: </p>
	 * @param hql
	 * @author 彭坤
	 * @date 2016年8月19日
	 */
	public EasyUIPageResult<NvrIpc> getAll(String hql);

}
