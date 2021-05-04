package com.fish.business.controller;

import com.fish.business.service.StaffService;
import com.fish.business.vo.StaffVo;
import com.fish.system.utils.CommonReturnType;
import com.fish.system.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName StaffController
 * @Description
 * @Author 柚子茶
 * @Date 2021/3/4 16:02
 * @Version 1.0
 */
@RestController
@RequestMapping("staff")
public class StaffController {

	@Autowired
	private StaffService staffService;

	/**
	 * @param staffVo 员工信息实体类
	 * @return DataGridView
	 * @description 查询员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 16:49
	 **/
	@RequestMapping("loadStaffInfo")
	public DataGridView loadStaffInfo(StaffVo staffVo) {
		return this.staffService.queryStaffInfo(staffVo);
	}


	/**
	 * @param staffVo
	 * @return CommonReturnType
	 * @description 添加员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 16:51
	 **/
	@RequestMapping("addStaffInfo")
	public CommonReturnType addStaffInfo(StaffVo staffVo) {
		try {
			this.staffService.addStaffInfo(staffVo);
			return CommonReturnType.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.ADD_FAILURE;
		}
	}

	/**
	 * @param staffVo
	 * @return CommonReturnType
	 * @description 修改员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 16:52
	 **/
	@RequestMapping("updateStaffInfo")
	public CommonReturnType updateStaffInfo(StaffVo staffVo) {
		try {
			this.staffService.updateStaffInfo(staffVo);
			return CommonReturnType.MODIFY_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.MODIFY_FAILURE;
		}
	}

	/**
	 * @param staffVo
	 * @return CommonReturnType
	 * @description 删除员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 16:55
	 **/
	@RequestMapping("deleteStaffInfo")
	public CommonReturnType deleteStaffInfo(StaffVo staffVo) {
		try {
			this.staffService.deleteStaffInfo(staffVo);
			return CommonReturnType.LOGOUT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.LOGOUT_FAILURE;
		}
	}

	/**
	 * @param staffVo 员工实体类
	 * @return boolean
	 * @description 校验员工号是否存在
	 * @author 柚子茶
	 * @date 2021/3/9 14:08
	 **/
	@RequestMapping("checkStaffNumber")
	public boolean checkStaffNumber(StaffVo staffVo) {
		return this.staffService.checkStaffNumber(staffVo);
	}


}
