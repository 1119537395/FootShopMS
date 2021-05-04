package com.fish.business.service;

import com.fish.business.domain.Staff;
import com.fish.business.vo.StaffVo;
import com.fish.system.utils.DataGridView;

import java.util.List;

/**
 * @InterfaceName StaffService
 * @Description 员工信息管理业务层接口
 * @Author 柚子茶
 * @Date 2021/3/4 16:01
 * @Version 1.0
 */
public interface StaffService {


	/**
	 * @param staffVo
	 * @return DataGridView
	 * @description 查询员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 16:50
	 **/
	DataGridView queryStaffInfo(StaffVo staffVo);

	/**
	 * @param staffVo
	 * @return void
	 * @description 添加员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 16:51
	 **/
	void addStaffInfo(StaffVo staffVo);

	/**
	 * @param staffVo
	 * @return void
	 * @description 修改员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 16:52
	 **/
	void updateStaffInfo(StaffVo staffVo);

	/**
	 * @param staffVo
	 * @return void
	 * @description 注销员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 16:55
	 **/
	void deleteStaffInfo(StaffVo staffVo);

	/**
	 * @return List<Staff>
	 * @description 查询员工姓名
	 * @author 柚子茶
	 * @date 2021/3/7 21:05
	 **/
	List<Staff> initStaffNameInfo();

	/**
	 * @param staffVo
	 * @return boolean
	 * @description 校验员工号是否存在
	 * @author 柚子茶
	 * @date 2021/3/9 14:09
	 **/
	boolean checkStaffNumber(StaffVo staffVo);
}
