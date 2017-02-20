/**
 * <p>Title: ILocMenu.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月23日
 * @version 1.0.0
 */
package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.LocMenu;

/**
 * <p>Title: ILocMenu</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月23日
 */
public interface ILocMenuDao extends IBaseDao<LocMenu,Integer > {
	/**
	 * 
	 * <p>Title: getRootMenu</p>
	 * <p>Description: 查询所有的根目录</p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月23日
	 */
	public List<LocMenu> getRootMenu();

}
