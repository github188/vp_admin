package cn.ittc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ittc.dao.IDeptDao;
import cn.ittc.entity.Dept;
import cn.ittc.service.IDeptService;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

@Service(value ="deptService")

public class DeptService implements IDeptService {

	@Resource
	private IDeptDao deptDao ;
	
	@Override
	public JSONObject selectAllDept() {
		List<Dept> list = deptDao.selectAllDept();
		
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		JSONObject jsonObject =new JSONObject();
		jsonObject.element("data", list,jsonConfig);
		return jsonObject;
	}

	@Override
	public List<Dept> selectDept(Dept dept) {
		List<Dept> list =deptDao.selectDept(dept);
		return list;
	}

	@Override
	public void deleteDept(Dept dept) {
		deptDao.deleteDept(dept);
		
	}
	
	
	
}
