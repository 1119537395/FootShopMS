package com.fish.business.service;

import com.fish.business.domain.Customer;
import com.fish.business.vo.CustomerVo;
import com.fish.system.utils.DataGridView;

/**
 * @InterfaceName CustomerService
 * @Description 客户的业务层接口
 * @Author 柚子茶
 * @Date 2021/2/25 18:58
 * @Version 1.0
 */
public interface CustomerService {

	/**
	 * @param customerVo 客户信息封装实体类
	 * @return DataGridView
	 * @description 查询客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 13:41
	 **/
	DataGridView queryCustomerDataByAll(CustomerVo customerVo);


	/**
	 * @param customerVo
	 * @return void
	 * @description 添加客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 13:43
	 **/
	void addCustomerData(CustomerVo customerVo);

	/**
	 * @param customerVo
	 * @return void
	 * @description 修改客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 13:45
	 **/
	void updateCustomerData(CustomerVo customerVo);

	/**
	 * @param customerVo
	 * @return void
	 * @description 删除客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 13:52
	 **/
	void deleteCustomerData(CustomerVo customerVo);

	/**
	 * @param customerVo
	 * @return void
	 * @description 批量删除客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 15:18
	 **/
	void deleteBatchCustomerData(CustomerVo customerVo);

	/**
	 * @param custPhone
	 * @return Customer
	 * @description 根据手机号查询客户信息
	 * @author 柚子茶
	 * @date 2021/3/2 16:51
	 **/
	Customer checkCustPhone(String custPhone);

	/**
	 * @param custId
	 * @return Customer
	 * @description 根据ID查询客户信息
	 * @author 柚子茶
	 * @date 2021/3/9 12:27
	 **/
	Customer queryCustomerDataById(Integer custId);
}
