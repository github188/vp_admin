/**
 * <p>Title: WechatAskDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��31��
 * @version 1.0.0
 */
package cn.ittc.weixin.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.ittc.dao.impl.BaseHibernateDao;
import cn.ittc.weixin.entity.pojo.WechatAsk;

/**
 * <p>Title: WechatAskDao</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��31��
 */
@Repository("wechatAskDao")
public class WechatAskDao extends BaseHibernateDao<WechatAsk, Integer> implements IWechatAskDao{

	/* ��no Javadoc��
	 * <p>Title: getWechatAskByAskInfo</p>
	 * <p>Description: </p>
	 * @param askInfo
	 * @return
	 * @see cn.ittc.weixin.dao.IWechatAskDao#getWechatAskByAskInfo(java.lang.String)
	 * @author ������
	 * @date 2016��8��31��
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<WechatAsk> getWechatAskByAskInfo(String askInfo) {
//		String hql="FROM WechatAsk WHERE askInfo like :askInfo";
//		Query query=getSession().createQuery(hql);
//		query.setParameter("askInfo", "%"+askInfo+"%");
		//HQLģ��ƥ��ؼ��֣�MySQL���ַ������ӷ�ʽ
		String hql="FROM WechatAsk WHERE :askInfo like CONCAT('%',askinfo,'%')";
		Query query=getSession().createQuery(hql);
		query.setParameter("askInfo", askInfo);
		return query.list();
	}

	
	
	

}
