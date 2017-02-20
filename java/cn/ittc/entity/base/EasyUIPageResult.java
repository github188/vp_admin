/**
 * <p>Title: PageResult.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年8月16日
 * @version 1.0.0
 */
package cn.ittc.entity.base;

import java.util.List;

/**
 * <p>Title: PageResult</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年8月16日
 */
public class EasyUIPageResult<T> {
	private List<T> rows; // 当前页的数据信息
	private int total;
	/**
	 * @return rows
	 */
	public List<T> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	/**
	 * @return total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	

}
