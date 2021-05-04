package com.fish.business.dao;

import com.fish.business.domain.Order;

import java.util.List;

/**
 * @InterfaceName OrderDao
 * @Description 订单(Order)数据访问层
 * @Author 柚子茶
 * @Date 2021/3/7 18:24
 * @Version 1.0
 */
public interface OrderDao {

	/**
	 * @param orderId 订单编号
	 * @return int
	 * @description 删除订单信息
	 * @author 柚子茶
	 * @date 2021/3/7 18:31
	 **/
	int deleteByPrimaryKey(String orderId);

	/**
	 * @param record 订单实体类
	 * @return int
	 * @description 添加订单信息
	 * @author 柚子茶
	 * @date 2021/3/7 18:31
	 **/
	int insert(Order record);

	/**
	 * @param record
	 * @return int
	 * @description 添加订单信息
	 * @author 柚子茶
	 * @date 2021/3/7 18:31
	 **/
	int insertSelective(Order record);

	/**
	 * @param orderId
	 * @return Order
	 * @description 查询订单信息
	 * @author 柚子茶
	 * @date 2021/3/7 18:31
	 **/
	Order selectByPrimaryKey(String orderId);

	/**
	 * @param record
	 * @return int
	 * @description 更新订单信息
	 * @author 柚子茶
	 * @date 2021/3/7 18:32
	 **/
	int updateByPrimaryKeySelective(Order record);

	/**
	 * @param record
	 * @return int
	 * @description 更新订单信息
	 * @author 柚子茶
	 * @date 2021/3/7 18:32
	 **/
	int updateByPrimaryKey(Order record);


	/**
	 * @param order 订单实体类
	 * @return List<Order>
	 * @description 查询订单信息
	 * @author 柚子茶
	 * @date 2021/3/8 12:37
	 **/
	List<Order> selectOrderInfoByList(Order order);
}