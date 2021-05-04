package com.fish.business.service.impl;

import com.fish.business.dao.StaffDao;
import com.fish.business.domain.Staff;
import com.fish.business.service.StaffService;
import com.fish.business.vo.StaffVo;
import com.fish.system.domain.User;
import com.fish.system.utils.DataGridView;
import com.fish.system.utils.WebUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName StaffServiceImpl
 * @Description 员工信息管理业务层接口实现类
 * @Author 柚子茶
 * @Date 2021/3/4 16:02
 * @Version 1.0
 */
@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao staffDao;

	/**
	 * @param staffVo 员工信息实体类
	 * @return DataGridView
	 * @description 查询员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:00
	 **/
	@Override
	public DataGridView queryStaffInfo(StaffVo staffVo) {
		//开启分页
		Page<Object> page = PageHelper.startPage(staffVo.getPage(), staffVo.getLimit());
		List<Staff> staffList = this.staffDao.selectStaffInfoByList(staffVo);
		return new DataGridView(page.getTotal(), staffList);
	}

	/**
	 * @param staffVo
	 * @return void
	 * @description 添加员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:00
	 **/
	@Override
	public void addStaffInfo(StaffVo staffVo) {
		// 设置为新增员工为在职状态
		staffVo.setStaffServiceState(1);
		// 设置新增员工为空闲休息中
		staffVo.setStaffWorkState(0);
		staffVo.setStaffCreateTime(new Date());
		// 获取到当前登录的用户
		User user = (User) WebUtils.getHttpSession().getAttribute("user");
		// 设置创建人
		staffVo.setCreateUserId(user.getUserId());
		this.staffDao.insertSelective(staffVo);
	}

	/**
	 * @param staffVo
	 * @return void
	 * @description 修改员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:00
	 **/
	@Override
	public void updateStaffInfo(StaffVo staffVo) {
		this.staffDao.updateByPrimaryKeySelective(staffVo);
	}

	/**
	 * @param staffVo
	 * @return void
	 * @description 注销员工信息
	 * @author 柚子茶
	 * @date 2021/3/4 17:00
	 **/
	@Override
	public void deleteStaffInfo(StaffVo staffVo) {
		this.staffDao.deleteByPrimaryKey(staffVo.getStaffId());
	}

	/**
	 * @return List<Staff>
	 * @description 查询员工姓名
	 * @author 柚子茶
	 * @date 2021/3/7 21:06
	 **/
	@Override
	public List<Staff> initStaffNameInfo() {
		StaffVo staffVo = new StaffVo();
		staffVo.setStaffServiceState(1);
		staffVo.setStaffWorkState(0);
		return this.staffDao.selectStaffInfoByList(staffVo);
	}

	/**
	 * @param staffVo
	 * @return boolean
	 * @description 校验员工号是否存在
	 * @author 柚子茶
	 * @date 2021/3/9 14:09
	 **/
	@Override
	public boolean checkStaffNumber(StaffVo staffVo) {
		if (null != staffVo.getStaffId()) {
			Staff staff = this.staffDao.selectByPrimaryKey(staffVo.getStaffId());
			if (staff.getStaffNumber().equals(staffVo.getStaffNumber())) {
				// 相等说明用户没有对工号进行修改
				return false;
			} else {
				// 不相等说明用户对工号进行了修改
				Staff staff1 = this.staffDao.selectStaffInfoByNumber(staffVo.getStaffNumber());
				if (null != staff1) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			Staff staff2 = this.staffDao.selectStaffInfoByNumber(staffVo.getStaffNumber());
			if (null != staff2) {
				return true;
			} else {
				return false;
			}
		}
	}



}
