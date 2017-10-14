/**
 * 
 */
package com.tutorial.gandalf;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页信息，当数据较多时，前端通常会采用分页的形式来进行展现，该类用于封装一个分页所需要包含的信息
 *
 * @author Cc
 */
public class PageInfo<T> implements Serializable {

	/**
	 * 序列化版本号ID
	 */
	private static final long serialVersionUID = 1991688472966826571L;

	/**
	 * 每页记录条数，默认为15条，该值不会小于1
	 */
	private int pageSize = 15;

	/**
	 * 当前页码，该值不会小于1
	 */
	private int page = 1;

	/**
	 * 总记录数，该值不会小于0
	 */
	private int itemCount = 0;

	/**
	 * 总页数，该值不会小于1
	 */
	private int totalPage = 1;

	/**
	 * 当前页所包含的数据列表
	 */
	@SuppressWarnings("unchecked")
	private List<T> items = Collections.EMPTY_LIST;

	/**
	 * 空构造函数
	 */
	public PageInfo() {

	}

	/**
	 * 使用指定的当前页码及每页记录条数创建{@code PageInfo}实例
	 * 
	 * @param page
	 * @param pageSize
	 */
	public PageInfo(int page, int pageSize) {
		setPage(page);
		setPageSize(pageSize);
	}

	/**
	 * 获取每页记录条数，默认为15条，该值不会小于1
	 * 
	 * @return 每页记录条数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页记录条数，默认为15条，该值不会小于1
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if (pageSize >= 1) // 仅当参数大于等于1时，才进行设置
			this.pageSize = pageSize;
	}

	/**
	 * 获取当前页码，该值不会小于1
	 * 
	 * @return 当前页码
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 设置当前页码，该值不会小于1
	 * 
	 * @param page
	 */
	public void setPage(int page) {
		if (page >= 1)
			this.page = page;
	}

	/**
	 * 获取总记录数，该值不会小于0
	 * 
	 * @return 总记录数
	 */
	public int getItemCount() {
		return itemCount;
	}

	/**
	 * 设置总记录数，该值不会小于0，调用该方法将会同时计算{@code totalPage}的值并进行设置
	 * 
	 * @param itemCount
	 */
	public void setItemCount(int itemCount) {
		if (itemCount >= 0) {
			this.itemCount = itemCount;
			this.totalPage = ((int) Math.ceil((double) itemCount / pageSize));
			this.totalPage = (this.totalPage >= 1 ? this.totalPage : 1);
		}
	}

	/**
	 * 获取总页数，该值不会小于1，该值在调用{@link #setItemCount(int)}方法后根据当前的{@code pageSize}自动计算
	 * 
	 * @return 总页数
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * 获取当前页所包含的数据列表，该方法不会返回{@code null}
	 * 
	 * @return 当前页所包含的数据列表
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * 设置当前页所包含的数据列表
	 * 
	 * @param items
	 */
	@SuppressWarnings("unchecked")
	public void setItems(List<T> items) {
		this.items = items != null ? items : Collections.EMPTY_LIST;
	}

	/**
	 * 获取查询的偏移坐标，该值不会小于0, 不会大于itemCount
	 * 
	 * @return 查询的偏移坐标
	 */
	public int getOffset() {
		int offset = (page - 1) * pageSize;
		return offset > itemCount ? itemCount : offset;
	}

	@Override
	public String toString() {
		return "PageInfo [pageSize=" + pageSize + ", page=" + page + ", itemCount=" + itemCount + ", totalPage="
				+ totalPage + ", items=" + items + "]";
	}

}
