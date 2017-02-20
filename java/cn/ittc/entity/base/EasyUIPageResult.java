/**
 * <p>Title: PageResult.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author ������
 * @date 2016��8��16��
 * @version 1.0.0
 */
package cn.ittc.entity.base;

import java.util.List;

/**
 * <p>Title: PageResult</p>
 * <p>Description: </p>
 * @author ������
 * @date 2016��8��16��
 */
public class EasyUIPageResult<T> {
	private List<T> rows; // ��ǰҳ��������Ϣ
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
