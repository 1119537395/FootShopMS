package com.fish.business.dao;

import com.fish.business.domain.Customer;
import com.fish.business.vo.CustomerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName CustomerDao
 * @Description 客户(Customer)数据访问层
 * @Author 柚子茶
 * @Date 2021/2/25 18:57
 * @Version 1.0
 */
public interface CustomerDao {


	/**
	 * @param customerVo
	 * @return List<Customer>
	 * @description 查询客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 15:22
	 **/
	List<Customer> queryCustomerDataByAll(CustomerVo customerVo);


	/**
	 * @param customerVo
	 * @return void
	 * @description 添加客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 17:09
	 **/
	void insertCustomerData(CustomerVo customerVo);

	/**
	 * @param customerVo
	 * @return void
	 * @description 更新客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 17:22
	 **/
	void updateCustomerDataById(CustomerVo customerVo);

	/**
	 * @param custId
	 * @return void
	 * @description 删除客户信息
	 * @author 柚子茶
	 * @date 2021/2/28 17:27
	 **/
	void deleteCustomerDataById(@Param("custId") Integer custId);

	/**
	 * @param custPhone
	 * @return Customer
	 * @description 根据手机号查询客户信息
	 * @author 柚子茶
	 * @date 2021/3/2 16:52
	 **/
	Customer checkCustPhone(@Param("custPhone") String custPhone);

	/**
	 * @param custId 客户ID
	 * @return Customer
	 * @description 根据客户ID查询客户信息
	 * @author 柚子茶
	 * @date 2021/3/9 12:29
	 **/
	Customer queryCustomerDataById(@Param("custId") Integer custId);
}
