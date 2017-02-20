/**
 * <p>Title: IDeptDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月2日
 * @version 1.0.0
 */
package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Dept;

/**
 * <p>Title: IDeptDao</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月2日
 */
public interface IDeptDao extends IBaseDao<Dept, Integer>{


	/**
	 * <p>Title: selectAllDept</p>
	 * <p>Description:查询所有部门信息 </p>
	 * @author 杨枝雨
	 * @date 2016年8月4日
	 */
	
	public List<Dept> selectAllDept();
	

	/**
	 * <p>Title: selectDept</p>
	 * <p>Description:查询指定部门信息 </p>
	 * @author 杨枝雨
	 * @date 2016年8月4日
	 */
	
	
	public List<Dept> selectDept(Dept dept);

	/**
	 * <p>Title: deleteDept</p>
	 * <p>Description:删除部门信息 </p>
	 * @author 杨枝雨
	 * @date 2016年8月9日
	 */
	public void deleteDept(Dept dept);
}
