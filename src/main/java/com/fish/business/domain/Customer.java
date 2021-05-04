package com.fish.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Customer
 * @Description 客户实体类
 * @Author 柚子茶
 * @Date 2021/2/25 18:49
 * @Version 1.0
 */
public class Customer implements Serializable {

	private static final long serialVersionUID = 6128739476364984780L;

	/** 客户ID */
	private Integer custId;

	/** 客户姓名 */
	private String custName;

	/** 客户手机号 */
	private String custPhone;

	/** 客户性别 1: 男 0: 女 */
	private Integer custSex;

	/** 客户地址 */
	private String custAddress;

	/** 客户等级 */
	private String custRank;

	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public Integer getCustSex() {
		return custSex;
	}

	public void setCustSex(Integer custSex) {
		this.custSex = custSex;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustRank() {
		return custRank;
	}

	public void setCustRank(String custRank) {
		this.custRank = custRank;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"custId=" + custId +
				", custName='" + custName + '\'' +
				", custPhone='" + custPhone + '\'' +
				", custSex=" + custSex +
				", custAddress='" + custAddress + '\'' +
				", custRank='" + custRank + '\'' +
				", createTime=" + createTime +
				'}';
	}
}
