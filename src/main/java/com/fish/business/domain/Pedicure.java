package com.fish.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @ClassName Room
 * @Description 足疗项目实体类
 * @Author 柚子茶
 * @Date 2021/3/5 11:21
 * @Version 1.0
 */
public class Pedicure implements Serializable {

    private static final long serialVersionUID = -2898590284587846181L;

    /** 足疗项目ID */
    private Integer pedicureId;

    /** 足疗项目名称 */
    private String pedicureName;

    /** 足疗项目内容 */
    private String pedicureContent;

    /** 足疗项目收费类型 */
    private String pedicureChargeType;

    /** 足疗项目收费金额 */
    private BigDecimal pedicureChargeAmount;

    /** 足疗项目上架状态 */
    private Integer pedicureState;

    /** 足疗项目备注信息 */
    private String pedicureInfo;

    /** 创建人ID */
    private Integer createUserId;

    /** 足疗项目创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date pedicureCreateTime;

    /** 创建人名称 */
    private String createName;

    /** 收费类型 */
    private String chargeType;

    /** 服务时间 */
    private Integer serviceTime;

    public Integer getPedicureId() {
        return pedicureId;
    }

    public void setPedicureId(Integer pedicureId) {
        this.pedicureId = pedicureId;
    }

    public String getPedicureName() {
        return pedicureName;
    }

    public void setPedicureName(String pedicureName) {
        this.pedicureName = pedicureName == null ? null : pedicureName.trim();
    }

    public String getPedicureContent() {
        return pedicureContent;
    }

    public void setPedicureContent(String pedicureContent) {
        this.pedicureContent = pedicureContent == null ? null : pedicureContent.trim();
    }

    public String getPedicureChargeType() {
        return pedicureChargeType;
    }

    public void setPedicureChargeType(String pedicureChargeType) {
        this.pedicureChargeType = pedicureChargeType == null ? null : pedicureChargeType.trim();
    }

    public BigDecimal getPedicureChargeAmount() {
        return pedicureChargeAmount;
    }

    public void setPedicureChargeAmount(BigDecimal pedicureChargeAmount) {
        this.pedicureChargeAmount = pedicureChargeAmount;
    }

    public Integer getPedicureState() {
        return pedicureState;
    }

    public void setPedicureState(Integer pedicureState) {
        this.pedicureState = pedicureState;
    }

    public String getPedicureInfo() {
        return pedicureInfo;
    }

    public void setPedicureInfo(String pedicureInfo) {
        this.pedicureInfo = pedicureInfo == null ? null : pedicureInfo.trim();
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getPedicureCreateTime() {
        return pedicureCreateTime;
    }

    public void setPedicureCreateTime(Date pedicureCreateTime) {
        this.pedicureCreateTime = pedicureCreateTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public Integer getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(Integer serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "Pedicure{" +
                "pedicureId=" + pedicureId +
                ", pedicureName='" + pedicureName + '\'' +
                ", pedicureContent='" + pedicureContent + '\'' +
                ", pedicureChargeType='" + pedicureChargeType + '\'' +
                ", pedicureChargeAmount=" + pedicureChargeAmount +
                ", pedicureState=" + pedicureState +
                ", pedicureInfo='" + pedicureInfo + '\'' +
                ", createUserId=" + createUserId +
                ", pedicureCreateTime=" + pedicureCreateTime +
                ", createName='" + createName + '\'' +
                ", chargeType='" + chargeType + '\'' +
                ", serviceTime=" + serviceTime +
                '}';
    }
}