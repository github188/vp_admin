package cn.ittc.service;

import java.util.List;

import cn.ittc.entity.Dept;
import net.sf.json.JSONObject;

/**
 * <p>Title: IDeptService</p>
 * <p>Description: </p>
 * @author —Ó÷¶”Í
 * @date 2016ƒÍ8‘¬5»’
 */


public interface IDeptService {

	public JSONObject selectAllDept();
	
	public List<Dept> selectDept(Dept dept);
	
	public void deleteDept(Dept dept);
}
