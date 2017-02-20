/**
 * <p>Title: PageResultSet.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月14日
 * @version 1.0.0
 */
package cn.ittc.entity.base;

import java.util.List;

/**
 * <p>Title: PageResultSet</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月14日
 */
public class PageResultSet<T> {

	private List<T> list; // 当前页的数据信息
	private PageInfo pageInfo; // 当前页的信息

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
