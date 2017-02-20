package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Loc;

/**
 * <p>Title: ILocDao </p>
 * <p>Description:IPCλ��</p>
 * @author ����
 * @date 2016��8��10��
 */
public interface ILocDao extends IBaseDao<Loc, Integer>{
	
	public List<Loc> selectAll();

	public Loc selectById(int id);
	
	
}
