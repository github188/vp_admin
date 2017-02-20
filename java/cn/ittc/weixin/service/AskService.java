/**
 * <p>Title: AskService.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月31日
 * @version 1.0.0
 */
package cn.ittc.weixin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.ittc.weixin.dao.IWechatAskDao;
import cn.ittc.weixin.entity.pojo.WechatAsk;

/**
 * <p>Title: AskService</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月31日
 */
@Service(value ="askService")
public class AskService implements IAskService{

	@Resource
	private IWechatAskDao  wechatAskDao;
	/* （no Javadoc）
	 * <p>Title: getWechatAsks</p>
	 * <p>Description: </p>
	 * @param askInfo
	 * @return
	 * @see cn.ittc.weixin.service.IAskService#getWechatAsks(java.lang.String)
	 * @author 焦冬冬
	 * @date 2016年8月31日
	 */
	@Override
	public List<WechatAsk> getWechatAsks(String askInfo) {
		return wechatAskDao.getWechatAskByAskInfo(askInfo);
	}

}
