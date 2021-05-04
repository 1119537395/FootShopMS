package com.fish.business.dao;

import com.fish.business.domain.Staff;
import com.fish.business.vo.StaffVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @InterfaceName StaffDao
 * @Description 员工(Staff)数据访问层
 * @Author 柚子茶
 * @Date 2021/3/3 18:57
 * @Version 1.0
 */
public interface StaffDao {

	/**
	 * @param staffId
	 * @return int
	 * @description 根据ID删除员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:24
	 **/
	int deleteByPrimaryKey(Integer staffId);

	/**
	 * @param record
	 * @return int
	 * @description 添加员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:24
	 **/
	int insert(Staff record);

	/**
	 * @param record
	 * @return int
	 * @description 根据条件添加员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:25
	 **/
	int insertSelective(Staff record);

	/**
	 * @param staffId
	 * @return Staff
	 * @description 根据ID查询员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:25
	 **/
	Staff selectByPrimaryKey(Integer staffId);

	/**
	 * @param record
	 * @return int
	 * @description 根据条件修改员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:25
	 **/
	int updateByPrimaryKeySelective(Staff record);

	/**
	 * @param record
	 * @return int
	 * @description 修改员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:25
	 **/
	int updateByPrimaryKey(Staff record);

	/**
	 * @param staffVo
	 * @return List<Staff>
	 * @description 查询全部的员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:04
	 **/
	List<Staff> selectStaffInfoByList(StaffVo staffVo);

	/**
	 * @param staffNumber 工号
	 * @return Staff
	 * @description 根据工号查询员工信息
	 * @author 柚子茶
	 * @date 2021/3/9 14:13
	 **/
	Staff selectStaffInfoByNumber(@Param("staffNumber") Integer staffNumber);
}