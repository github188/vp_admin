/**
 * <p>Title: IAskService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月31日
 * @version 1.0.0
 */
package cn.ittc.weixin.service;

import java.util.List;

import cn.ittc.weixin.entity.pojo.WechatAsk;

/**
 * <p>Title: IAskService</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月31日
 */
public interface IAskService {
	/**
	 * 
	 * <p>Title: getWechatAsks</p>
	 * <p>Description: </p>
	 * @return
	 * @author 焦冬冬
	 * @date 2016年8月31日
	 */
	public List<WechatAsk> getWechatAsks(String askInfo);

}
