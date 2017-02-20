/**
 * <p>Title: IWechatAskDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.dao;

import java.util.List;

import cn.ittc.dao.IBaseDao;
import cn.ittc.weixin.entity.pojo.WechatAsk;

/**
 * <p>Title: IWechatAskDao</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
public interface IWechatAskDao extends IBaseDao<WechatAsk, Integer>{
	
	public List<WechatAsk> getWechatAskByAskInfo(String askInfo);

}
