package com.xsx.domain;

import java.util.List;


/**
 * 分页
 * @author xsx
 *
 * @param <T>
 */
public class Page<T> implements java.io.Serializable{
  public static final int DEFAULT_PAGE_SIZE = 10;

  protected int pageNo = 1; // 当前页, 默认为第1页
  protected int pageSize = DEFAULT_PAGE_SIZE; // 每页记录数
  protected long total = -1; // 总记录数, 默认为-1, 表示需要查询
  protected int totalPages = -1; // 总页数, 默认为-1, 表示需要计算

  protected List<T> rows; // 当前页记录List形式

  public int getPageNo() {
    return pageNo;
  }

  public void setPageNo(int pageNo) {
    this.pageNo = pageNo;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
    computeTotalPage();
  }

  public long getTotal() {
    return total;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotal(long total) {
    this.total = total;
    computeTotalPage();
  }

  protected void computeTotalPage() {
    if (getPageSize() > 0 && getTotal() > -1) {
      this.totalPages = (int) (getTotal() % getPageSize() == 0 ? getTotal() / getPageSize() : getTotal() / getPageSize() + 1);
    }
  }

  public List<T> getRows() {
    return rows;
  }

  public void setRows(List<T> rows) {
    this.rows = rows;
  }

//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder().append("Page [pageNo=").append(pageNo).append(", pageSize=").append(pageSize)
//				.append(", totalRecord=").append(total < 0 ? "null" : total).append(", totalPage=")
//				.append(totalPage < 0 ? "null" : totalPage).append(", curPageObjects=").append(rows == null ? "null" : rows.size()).append("]");
//		return builder.toString();
//	}


  public void setPageNumber(Integer pageNumber) {
    this.pageNo = pageNumber;
  }

  public int getLimit() {
    return this.pageSize;
  }
}
