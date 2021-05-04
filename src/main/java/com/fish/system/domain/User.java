package com.fish.system.domain;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description 用户(user)实体类
 * @Author 柚子茶
 * @Date 2021/2/24 11:07
 * @Version 1.0
 */
public class User implements Serializable {

	private static final long serialVersionUID = 5321992841184396183L;

	/** 用户ID */
	private Integer userId;

	/** 用户性别 */
	private Integer userSex;

	/** 用户昵称 */
	private String userName;

	/** 用户手机号 */
	private String userPhone;

	/** 账户名 */
	private String userAccount;

	/** 密码 */
	private String userPassword;

	/** 用户类型 0：管理员 1：前台营业员 */
	private Integer userType;

	/** 用户头像保存地址 */
	// TODO 该功能未实现
	private String userAvatar;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", userSex=" + userSex +
				", userName='" + userName + '\'' +
				", userPhone='" + userPhone + '\'' +
				", userAccount='" + userAccount + '\'' +
				", userPassword='" + userPassword + '\'' +
				", userType=" + userType +
				", userAvatar='" + userAvatar + '\'' +
				'}';
	}
}
