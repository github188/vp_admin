/**
 * <p>Title: ICoreService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月31日
 * @version 1.0.0
 */
package cn.ittc.weixin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * <p>Title: ICoreService</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月31日
 */
@Service(value = "coreService")
public interface ICoreService {
	
	public  String processRequest(HttpServletRequest request);

}
