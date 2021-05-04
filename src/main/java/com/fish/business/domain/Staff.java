package com.fish.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @ClassName Staff
 * @Description 员工实体类
 * @Author 柚子茶
 * @Date 2021/3/3 18:49
 * @Version 1.0
 */
public class Staff implements Serializable {

    private static final long serialVersionUID = 2359522041327873729L;

    /** 员工ID */
    private Integer staffId;

    /** 员工工号 */
    private Integer staffNumber;

    /** 员工姓名 */
    private String staffName;

    /** 员工性别 */
    private Integer staffSex;

    /** 员工手机号 */
    private String staffPhone;

    /** 员工工作状态 */
    private Integer staffWorkState;

    /** 员工在职状态 */
    private Integer staffServiceState;

    /** 员工入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date staffCreateTime;

    /** 创建人的ID */
    private Integer createUserId;

    /** 创建人的名称 */
    private String createName;

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Integer getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(Integer staffNumber) {
        this.staffNumber = staffNumber;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public Integer getStaffSex() {
        return staffSex;
    }

    public void setStaffSex(Integer staffSex) {
        this.staffSex = staffSex;
    }

    public String getStaffPhone() {
        return staffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        this.staffPhone = staffPhone == null ? null : staffPhone.trim();
    }

    public Integer getStaffWorkState() {
        return staffWorkState;
    }

    public void setStaffWorkState(Integer staffWorkState) {
        this.staffWorkState = staffWorkState;
    }

    public Integer getStaffServiceState() {
        return staffServiceState;
    }

    public void setStaffServiceState(Integer staffServiceState) {
        this.staffServiceState = staffServiceState;
    }

    public Date getStaffCreateTime() {
        return staffCreateTime;
    }

    public void setStaffCreateTime(Date staffCreateTime) {
        this.staffCreateTime = staffCreateTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", staffNumber=" + staffNumber +
                ", staffName='" + staffName + '\'' +
                ", staffSex=" + staffSex +
                ", staffPhone='" + staffPhone + '\'' +
                ", staffWorkState=" + staffWorkState +
                ", staffServiceState=" + staffServiceState +
                ", staffCreateTime=" + staffCreateTime +
                ", createUserId=" + createUserId +
                ", createName='" + createName + '\'' +
                '}';
    }
}