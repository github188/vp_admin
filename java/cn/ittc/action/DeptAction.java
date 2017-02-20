package cn.ittc.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.ittc.entity.Dept;
import cn.ittc.service.IDeptService;
import net.sf.json.JSONObject;

/**
 * <p>Title: DeptAction</p>
 * <p>Description: </p>
 * @author —Ó÷¶”Í
 * @date 2016ƒÍ8‘¬4»’
 */
@Controller(value = "deptAction")
public class DeptAction extends ActionSupport {
	
	@Resource
	private IDeptService deptService ;
	private static final long serialVersionUID = 1L;
	private Dept dept;
	private List <Dept> list ;
	private String jsonData;
	
	
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public List<Dept> getList() {
		return list;
	}
	public void setList(List<Dept> list) {
		this.list = list;
	}
	
	
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	
	public String getAllDept() {
		try {
			JSONObject jsonObject = deptService.selectAllDept();
			jsonData = jsonObject.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;

	}
	
	
	public String deleteDept(){
		
		deptService.deleteDept(dept);
		
		return SUCCESS;
	}
	
	
	
}
