package com.fish.business.vo;

import com.fish.business.domain.Customer;

/**
 * @ClassName CustomerVo
 * @Description 客户(Customer)的实体类属性扩展
 * @Author 柚子茶
 * @Date 2021/2/25 18:59
 * @Version 1.0
 */
public class CustomerVo extends Customer {

	/**
	 * 分页参数
	 */
	private Integer page;

	/**
	 * 分页参数
	 */
	private Integer limit;


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
