package com.fish.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName BusinessController
 * @Description 业务管理的路由控制器
 * @Author 柚子茶
 * @Date 2021/2/25 19:06
 * @Version 1.0
 */
@Controller
@RequestMapping("business")
public class BusinessController {

	/**
	 * @return String
	 * @description 跳转到客户管理界面
	 * @author 柚子茶
	 * @date 2021/2/25 19:08
	 **/
	@RequestMapping("toCustomerManager")
	public String toCustomerManager() {
		return "business/customer/customerManager";
	}


	/**
	 * @return String
	 * @description 跳转到员工管理界面
	 * @author 柚子茶
	 * @date 2021/3/4 16:09
	 **/
	@RequestMapping("toStaffManager")
	public String toStaffManager() {
		return "business/staff/staffManager";
	}

	/**
	 * @return String
	 * @description 跳转到房间信息管理界面
	 * @author 柚子茶
	 * @date 2021/3/5 10:25
	 **/
	@RequestMapping("toRoomManager")
	public String toRoomManager() {
		return "business/room/roomManager";
	}

	/**
	 * @return String
	 * @description 跳转到项目管理界面
	 * @author 柚子茶
	 * @date 2021/3/5 14:02
	 **/
	@RequestMapping("toPedicureManager")
	public String toPedicureManager() {
		return "business/pedicure/pedicureManager";
	}

	/**
	 * @return String
	 * @description 跳转到服务预定界面
	 * @author 柚子茶
	 * @date 2021/3/7 18:44
	 **/
	@RequestMapping("toOrderReservation")
	public String toOrderReservation() {
		return "business/order/orderManager";
	}

	/**
	 * @return String
	 * @description 跳转到足疗服务界面
	 * @author 柚子茶
	 * @date 2021/3/8 10:58
	 **/
	@RequestMapping("toServiceManager")
	public String toServiceManager() {
		return "business/service/serviceManager";
	}


	@RequestMapping("toSaleOrderManager")
	public String toSaleOrderManager(){
		return "business/order/saleOrderManager";
	}

}
