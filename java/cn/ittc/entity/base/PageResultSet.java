/**
 * <p>Title: PageResultSet.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��7��14��
 * @version 1.0.0
 */
package cn.ittc.entity.base;

import java.util.List;

/**
 * <p>Title: PageResultSet</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��7��14��
 */
public class PageResultSet<T> {

	private List<T> list; // ��ǰҳ��������Ϣ
	private PageInfo pageInfo; // ��ǰҳ����Ϣ

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}
	
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
	
}
