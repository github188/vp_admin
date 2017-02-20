package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Nvr;

/**
 * <p>Title: INvrDao</p>
 * <p>Description:</p>
 * @author ����
 * @date 2016��8��3��
 */
public interface INvrDao extends IBaseDao<Nvr, Integer>{



	/**
	 * 
	 * <p>Title: modifyNvr</p>
	 * <p>Description:</p>
	 * @param nvr
	 * @author ����
	 * @date 2016��8��3��
	 */
	public void modifyNvr(Nvr nvr);

	/**
	 * 
	 * <p>Title: deleteNvr </p>
	 * <p>Description:</p>
	 * @param nvr
	 * @author ����
	 * @date 2016��8��3��
	 */
	public void deleteNvr(Nvr nvr);

	/**
	 * <p>Title: getNvrByParam</p>
	 * <p>Description:</p>
	 * @param nvr
	 * @author ����
	 * @date 2016��8��3��
	 */
	public List<Nvr> getNvrByParam(Nvr nvr);
	/**
	 * 
	 * <p>Title: addNvr</p>
	 * <p> Description:</p>
	 * @param nvr
	 * @author ����
	 * @date 2016��8��15��
	 */
	public void addNvr(Nvr nvr);
	
	/**
	 * 
	 * <p>Title: getNvrByHQL</p>
	 * <p>Description: </p>
	 * @return
	 * @author ������
	 * @date 2016��8��19��
	 */
	public List<Nvr> getNvrByHQL();

}
