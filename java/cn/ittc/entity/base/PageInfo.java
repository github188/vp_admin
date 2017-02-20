/**
 * <p>Title: PageInfo.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ittc</p>
 * @author 焦冬冬
 * @date 2016年7月14日
 * @version 1.0.0
 */
package cn.ittc.entity.base;

/**
 * <p>Title: PageInfo</p>
 * <p>Description: </p>
 * @author 焦冬冬
 * @date 2016年7月14日
 */
public class PageInfo {

	private int totalRow; // 总共记录数
	private int totalPage; // 总共页数
	private int currentPage = 1; // 当前页，默认为1
	private int pageSize = 10; // 页的大小
	private boolean hasPrevious;//是否有上一页
	private boolean hasNext;//是否有下一页
	private boolean bof;
	private boolean eof;

	public PageInfo() {
	}

	/*
	 * 构造方法
	 * @param totalRow 总记录数
	 * @param pageSize 页的大小
	 * @param page 页码
	 */
	public PageInfo(int totalRow, int pageSize, int page) {
		this.totalRow = totalRow;
		this.pageSize = pageSize;
		// 根据页大小和总记录数计算出总页数
		this.totalPage = countTotalPage(this.pageSize, this.totalRow);

		// 修正当前页
		setCurrentPage(page);
		init();
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	public int getCurrentPage() {

		return this.currentPage;
	}

	// 修正计算当前页
	public void setCurrentPage(int currentPage) {
		if (currentPage > this.totalPage) {
			this.currentPage = this.totalPage;
		} else if (currentPage < 1) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
	}

	// 获取分页大小
	public int getPageSize() {
		return pageSize;
	}

	// 设置分页大小
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// 获取当前页记录的开始索引
	public int getBeginIndex() {
		int beginIndex = (currentPage - 1) * pageSize; // 索引下标从0开始
		return beginIndex;
	}

	// 计算总页数
	public int countTotalPage(int pageSize, int totalRow) {
		int totalPage = totalRow % pageSize == 0 ? totalRow / pageSize
				: totalRow / pageSize + 1;
		return totalPage;
	}

	// 返回下一页的页码
	public int getNextPage() {
		if (currentPage + 1 >= this.totalPage) { // 如果当前页已经是最后页 则返回最大页
			return this.totalPage;
		}
		return currentPage + 1;
	}

	// 返回前一页的页码
	public int getPreviousPage() {
		if (currentPage - 1 <= 1) {
			return 1;
		} else {
			return currentPage - 1;
		}
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public boolean isBof() {
		return bof;

	}

	public boolean isEof() {
		return eof;

	}

	public boolean hasNext() {
		return currentPage < this.totalPage;
	}

	public boolean hasPrevious() {
		return currentPage > 1;

	}

	public boolean isFirst() {
		return currentPage == 1;
	}

	public boolean isLast() {
		return currentPage >= this.totalPage;
	}

	// 初始化信息

	private void init() {
		this.hasNext = hasNext();
		this.hasPrevious = hasPrevious();
		this.bof = isFirst();
		this.eof = isLast();
	}
}
