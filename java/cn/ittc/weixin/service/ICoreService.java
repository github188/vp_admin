/**
 * <p>Title: ICoreService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * <p>Title: ICoreService</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
@Service(value = "coreService")
public interface ICoreService {
	
	public  String processRequest(HttpServletRequest request);

}
