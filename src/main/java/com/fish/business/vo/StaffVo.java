package com.fish.business.vo;

import com.fish.business.domain.Staff;

/**
 * @ClassName StaffVo
 * @Description 员工实体类属性扩展
 * @Author 柚子茶
 * @Date 2021/3/4 16:00
 * @Version 1.0
 */
public class StaffVo extends Staff {

	/**
	 * 分页参数
	 */
	private Integer page;

	/**
	 * 分页参数
	 */
	private Integer limit;


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


}
