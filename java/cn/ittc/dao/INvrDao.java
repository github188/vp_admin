package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Nvr;

/**
 * <p>Title: INvrDao</p>
 * <p>Description:</p>
 * @author 彭坤
 * @date 2016年8月3日
 */
public interface INvrDao extends IBaseDao<Nvr, Integer>{



	/**
	 * 
	 * <p>Title: modifyNvr</p>
	 * <p>Description:</p>
	 * @param nvr
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	public void modifyNvr(Nvr nvr);

	/**
	 * 
	 * <p>Title: deleteNvr </p>
	 * <p>Description:</p>
	 * @param nvr
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	public void deleteNvr(Nvr nvr);

	/**
	 * <p>Title: getNvrByParam</p>
	 * <p>Description:</p>
	 * @param nvr
	 * @author 彭坤
	 * @date 2016年8月3日
	 */
	public List<Nvr> getNvrByParam(Nvr nvr);
	/**
	 * 
	 * <p>Title: addNvr</p>
	 * <p> Description:</p>
	 * @param nvr
	 * @author 彭坤
	 * @date 2016年8月15日
	 */
	public void addNvr(Nvr nvr);
	
	/**
	 * 
	 * <p>Title: getNvrByHQL</p>
	 * <p>Description: </p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月19日
	 */
	public List<Nvr> getNvrByHQL();

}
