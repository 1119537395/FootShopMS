package com.fish.business.vo;

import com.fish.business.domain.Order;

/**
 * @ClassName OrderVo
 * @Description 订单实体类属性扩展
 * @Author 柚子茶
 * @Date 2021/3/7 15:56
 * @Version 1.0
 */
public class OrderVo extends Order {

	/**
	 * 分页参数
	 */
	private Integer page;

	/**
	 * 分页参数
	 */
	private Integer limit;

	/**
	 * 用于接收多个id
	 */
	private Integer[] ids;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

}
