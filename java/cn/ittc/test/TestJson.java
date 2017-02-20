/**
 * <p>Title: TestJson.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月2日
 * @version 1.0.0
 */
package cn.ittc.test;

import java.util.ArrayList;
import java.util.List;

import cn.ittc.entity.Dept;
import cn.ittc.entity.User;
import net.sf.json.JSONObject;

/**
 * <p>Title: TestJson</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月2日
 */
public class TestJson {
	public static void main(String[] args) {
		List<User> list=new ArrayList<User>();
		
		JSONObject jsonObject=new JSONObject();
//		jsonObject.element("test", 12);
//		jsonObject.element("list", list);
//		System.out.println(jsonObject);
		Dept dept=new Dept();
		dept.setDeptId(1);
		dept.setDeptName("产品研发部");
		User user=new User();
		user.setPassword("123");
		user.setDept(dept);
		list.add(user);
		jsonObject.element("success", list);
		System.out.println(jsonObject);
	}

}
