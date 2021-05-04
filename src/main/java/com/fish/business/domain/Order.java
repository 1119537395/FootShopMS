package com.fish.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Order
 * @Description 订单实体类
 * @Author 柚子茶
 * @Date 2021/3/7 18:21
 * @Version 1.0
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 9157372664036423281L;

    /** 订单编号 */
    private String orderId;

    /** 客户ID */
    private Integer custId;

    /** 房间ID */
    private Integer roomId;

    /** 服务人员ID */
    private Integer staffId;

    /** 足疗项目ID */
    private Integer pedicureId;

    /** 收费金额 */
    private BigDecimal chargeAmount;

    /** 订单状态 */
    private Integer orderState;

    /** 备注信息 */
    private String orderInfo;

    /** 操作人 */
    private String operName;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /** 客户名称 */
    private String custName;

    /** 客户手机号 */
    private String custPhone;

    /** 项目名称 */
    private String pedicureName;

    /** 房间号 */
    private Integer roomNumber;

    /** 员工名称 */
    private String staffName;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getPedicureId() {
        return pedicureId;
    }

    public void setPedicureId(Integer pedicureId) {
        this.pedicureId = pedicureId;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo == null ? null : orderInfo.trim();
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName == null ? null : operName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getPedicureName() {
        return pedicureName;
    }

    public void setPedicureName(String pedicureName) {
        this.pedicureName = pedicureName;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", custId=" + custId +
                ", roomId=" + roomId +
                ", staffId=" + staffId +
                ", pedicureId=" + pedicureId +
                ", chargeAmount=" + chargeAmount +
                ", orderState=" + orderState +
                ", orderInfo='" + orderInfo + '\'' +
                ", operName='" + operName + '\'' +
                ", createTime=" + createTime +
                ", custName='" + custName + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", pedicureName='" + pedicureName + '\'' +
                ", roomNumber=" + roomNumber +
                ", staffName='" + staffName + '\'' +
                '}';
    }
}