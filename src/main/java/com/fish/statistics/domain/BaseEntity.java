package com.fish.statistics.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName BaseEntity
 * @Description 封装Echarts图表所需要的数据格式
 * @Author 柚子茶
 * @Date 2021/3/8 21:49
 * @Version 1.0
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -565836848859348018L;

	/** 名称 */
	private String name;

	/** 数值 */
	private Object value;

	private BigDecimal totalAmount;

	/** 空参构造器 */
	public BaseEntity() {}

	/**
	 * Echarts图表数据构造器
	 * @param name  名称
	 * @param value 数值
	 */
	public BaseEntity(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	public BaseEntity(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "BaseEntity{" +
				"name='" + name + '\'' +
				", value=" + value +
				", totalAmount=" + totalAmount +
				'}';
	}
}
