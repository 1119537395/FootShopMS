package com.fish.business.service.impl;

import com.fish.business.dao.CustomerDao;
import com.fish.business.domain.Customer;
import com.fish.business.service.CustomerService;
import com.fish.business.vo.CustomerVo;
import com.fish.system.utils.DataGridView;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CustomerServiceImpl
 * @Description 客户的业务层接口实现类
 * @Author 柚子茶
 * @Date 2021/2/25 18:59
 * @Version 1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;


	/**
	 * @param customerVo
	 * @return DataGridView
	 * @description 查询客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 15:19
	 **/
	@Override
	public DataGridView queryCustomerDataByAll(CustomerVo customerVo) {
		//开启分页
		Page<Object> page = PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
		List<Customer> customerList = this.customerDao.queryCustomerDataByAll(customerVo);
		return new DataGridView(page.getTotal(), customerList);
	}

	/**
	 * @param customerVo
	 * @return void
	 * @description 添加客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 15:19
	 **/
	@Override
	public void addCustomerData(CustomerVo customerVo) {
		customerVo.setCreateTime(new Date());
		this.customerDao.insertCustomerData(customerVo);
	}

	/**
	 * @param customerVo
	 * @return void
	 * @description 更新客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 15:19
	 **/
	@Override
	public void updateCustomerData(CustomerVo customerVo) {
		this.customerDao.updateCustomerDataById(customerVo);
	}

	/**
	 * @param customerVo
	 * @return void
	 * @description 删除客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 15:20
	 **/
	@Override
	public void deleteCustomerData(CustomerVo customerVo) {
		this.customerDao.deleteCustomerDataById(customerVo.getCustId());
	}

	/**
	 * @param customerVo
	 * @return void
	 * @description 批量删除客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 15:20
	 **/
	@Override
	public void deleteBatchCustomerData(CustomerVo customerVo) {
		Integer[] ids = customerVo.getIds();
		for (Integer custId : ids) {
			this.customerDao.deleteCustomerDataById(custId);
		}
	}

	/**
	 * @param custPhone
	 * @return Customer
	 * @description 根据手机号查询客户信息
	 * @author 柚子茶
	 * @date 2021/3/2 16:52
	 **/
	@Override
	public Customer checkCustPhone(String custPhone) {
		return this.customerDao.checkCustPhone(custPhone);
	}

	/**
	 * @param custId 客户ID
	 * @return Customer
	 * @description 根据客户ID查询客户信息
	 * @author 柚子茶
	 * @date 2021/3/9 12:28
	 **/
	@Override
	public Customer queryCustomerDataById(Integer custId) {
		return this.customerDao.queryCustomerDataById(custId);
	}
}
