package com.fish.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * @ClassName Room
 * @Description 员工实体类
 * @Author 柚子茶
 * @Date 2021/3/4 18:49
 * @Version 1.0
 */
public class Room implements Serializable {

    private static final long serialVersionUID = -3097774803659987471L;

    /** ID */
    private Integer id;

    /** 房间号 */
    private Integer roomNumber;

    /** 房间名称 */
    private String roomName;

    /** 状态 1：未使用 0：使用中 */
    private Integer roomState;

    /** 创建人ID */
    private Integer createUserId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /** 创建人名称 */
    private String createName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public Integer getRoomState() {
        return roomState;
    }

    public void setRoomState(Integer roomState) {
        this.roomState = roomState;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", roomName='" + roomName + '\'' +
                ", roomState=" + roomState +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", createName='" + createName + '\'' +
                '}';
    }
}