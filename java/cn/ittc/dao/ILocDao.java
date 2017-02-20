package cn.ittc.dao;

import java.util.List;

import cn.ittc.entity.Loc;

/**
 * <p>Title: ILocDao </p>
 * <p>Description:IPC位置</p>
 * @author 彭坤
 * @date 2016年8月10日
 */
public interface ILocDao extends IBaseDao<Loc, Integer>{
	
	public List<Loc> selectAll();

	public Loc selectById(int id);
	
	
}
