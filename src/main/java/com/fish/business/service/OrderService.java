package com.fish.business.service;

import com.fish.business.vo.OrderVo;
import com.fish.system.utils.DataGridView;

import java.util.Map;

/**
 * @InterfaceName OrderService
 * @Description 订单业务层接口
 * @Author 柚子茶
 * @Date 2021/3/7 15:57
 * @Version 1.0
 */
public interface OrderService {


	/**
	 * @return Map<String, String>
	 * @description 获取订单编号和操作人员名称
	 * @author 柚子茶
	 * @date 2021/3/7 19:14
	 **/
	Map<String, String> initOrderNumberInfo();


	/**
	 * @param orderVo
	 * @return void
	 * @description 添加预定订单信息
	 * @author 柚子茶
	 * @date 2021/3/7 23:27
	 **/
	void addOrderInfo(OrderVo orderVo);

	/**
	 * @param orderVo
	 * @return void
	 * @description 添加订单信息
	 * @author 柚子茶
	 * @date 2021/3/8 11:18
	 **/
	void addOrderInfoById(OrderVo orderVo);

	/**
	 * @param orderVo
	 * @return DataGridView
	 * @description 查询订单信息
	 * @author 柚子茶
	 * @date 2021/3/8 12:34
	 **/
	DataGridView queryOrderInfo(OrderVo orderVo);

	/**
	 * @param orderVo
	 * @return void
	 * @description 处理用户预定订单
	 * @author 柚子茶
	 * @date 2021/3/8 14:21
	 **/
	void updateHandleOrderInfo(OrderVo orderVo);

	/**
	 * @param orderVo
	 * @return void
	 * @description 订单结算
	 * @author 柚子茶
	 * @date 2021/3/8 14:29
	 **/
	void updateFinishOrderInfo(OrderVo orderVo);

	/**
	 * @param orderVo
	 * @return void
	 * @description 修改订单信息
	 * @author 柚子茶
	 * @date 2021/3/8 15:30
	 **/
	void updateOrderInfo(OrderVo orderVo);

}
