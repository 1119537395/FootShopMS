package com.fish.business.controller;

import com.fish.business.domain.Customer;
import com.fish.business.service.CustomerService;
import com.fish.business.vo.CustomerVo;
import com.fish.system.utils.CommonReturnType;
import com.fish.system.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CustomerController
 * @Description 客户管理控制器
 * @Author 柚子茶
 * @Date 2021/2/25 18:56
 * @Version 1.0
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;


	/**
	 * @param customerVo
	 * @return DataGridView
	 * @description 查询所有客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 13:40
	 **/
	@RequestMapping("loadCustomerDataByAll")
	public DataGridView loadCustomerDataByAll(CustomerVo customerVo) {
		return this.customerService.queryCustomerDataByAll(customerVo);
	}


	/**
	 * @param customerVo
	 * @return CommonReturnType
	 * @description 添加客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 13:42
	 **/
	@RequestMapping("addCustomerData")
	public CommonReturnType addCustomerData(CustomerVo customerVo) {
		try {
			this.customerService.addCustomerData(customerVo);
			return CommonReturnType.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.ADD_FAILURE;
		}
	}

	/**
	 * @param customerVo
	 * @return CommonReturnType
	 * @description 修改客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 13:46
	 **/
	@RequestMapping("updateCustomerData")
	public CommonReturnType updateCustomerData(CustomerVo customerVo) {
		try {
			this.customerService.updateCustomerData(customerVo);
			return CommonReturnType.MODIFY_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.MODIFY_FAILURE;
		}
	}

	/**
	 * @param customerVo 客户信息封装类
	 * @return CommonReturnType
	 * @description 校验该手机号是否存在
	 * @author 柚子茶
	 * @date 2021/3/7 21:28
	 **/
	@RequestMapping("checkCustPhone")
	public boolean checkCustPhone(CustomerVo customerVo) {
		if (null != customerVo.getCustId()) {
			Customer customer1 = this.customerService.queryCustomerDataById(customerVo.getCustId());
			if (customer1.getCustPhone().equals(customerVo.getCustPhone())) {
				return false;
			} else {
				Customer customer2 = this.customerService.checkCustPhone(customerVo.getCustPhone());
				if (null != customer2) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			Customer customer3 = this.customerService.checkCustPhone(customerVo.getCustPhone());
			if (null != customer3) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * @param customerVo
	 * @return CommonReturnType
	 * @description 删除客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 15:15
	 **/
	@RequestMapping("deleteCustomerData")
	public CommonReturnType deleteCustomerData(CustomerVo customerVo) {
		try {
			this.customerService.deleteCustomerData(customerVo);
			return CommonReturnType.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.DELETE_FAILURE;
		}
	}

	/**
	 * @param customerVo
	 * @return CommonReturnType
	 * @description 批量删除客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 15:16
	 **/
	@RequestMapping("deleteBatchCustomerData")
	public CommonReturnType deleteBatchCustomerData(CustomerVo customerVo) {
		try {
			this.customerService.deleteBatchCustomerData(customerVo);
			return CommonReturnType.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.DELETE_FAILURE;
		}

	}


}
