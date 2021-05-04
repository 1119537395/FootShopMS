package com.fish.business.controller;

import com.fish.business.domain.Customer;
import com.fish.business.domain.Pedicure;
import com.fish.business.domain.Room;
import com.fish.business.domain.Staff;
import com.fish.business.service.*;
import com.fish.business.vo.CustomerVo;
import com.fish.business.vo.OrderVo;
import com.fish.business.vo.PedicureVo;
import com.fish.system.utils.CommonReturnType;
import com.fish.system.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderController
 * @Description 订单管理前端控制器
 * @Author 柚子茶
 * @Date 2021/3/7 18:32
 * @Version 1.0
 */
@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private PedicureService pedicureService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private CustomerService customerService;

	/**
	 * @param pedicureVo
	 * @return Pedicure
	 * @description 根据项目名称查询项目金额
	 * @author 柚子茶
	 * @date 2021/3/7 20:38
	 **/
	@RequestMapping("getPedicureByName")
	public Pedicure getPedicureByName(PedicureVo pedicureVo) {
		return this.pedicureService.getPedicureByName(pedicureVo);
	}

	/**
	 * @return Map<String, String>
	 * @description 获取订单编号和操作人员名称
	 * @author 柚子茶
	 * @date 2021/3/7 19:22
	 **/
	@RequestMapping("initOrderNumberInfo")
	public Map<String, String> initOrderNumberInfo() {
		return this.orderService.initOrderNumberInfo();
	}

	/**
	 * @return List<Pedicure>
	 * @description 查询所有足疗项目信息
	 * @author 柚子茶
	 * @date 2021/3/7 19:42
	 **/
	@RequestMapping("initPedicureInfo")
	public List<Pedicure> initPedicureInfo() {
		return this.pedicureService.initPedicureInfo();
	}

	/**
	 * @return List<Room>
	 * @description 查询房间号
	 * @author 柚子茶
	 * @date 2021/3/7 21:02
	 **/
	@RequestMapping("initRoomNumberInfo")
	public List<Room> initRoomNumberInfo() {
		return this.roomService.initRoomNumberInfo();
	}

	/**
	 * @return List<Staff>
	 * @description 查询员工姓名
	 * @author 柚子茶
	 * @date 2021/3/7 21:05
	 **/
	@RequestMapping("initStaffNameInfo")
	public List<Staff> initStaffNameInfo() {
		return this.staffService.initStaffNameInfo();
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
		Customer customer = this.customerService.checkCustPhone(customerVo.getCustPhone());
		if (null != customer) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @param orderVo 订单实体类
	 * @return CommonReturnType
	 * @description 添加预定订单信息
	 * @author 柚子茶
	 * @date 2021/3/7 23:25
	 **/
	@RequestMapping("addOrderInfo")
	public CommonReturnType addOrderInfo(OrderVo orderVo) {
		try {
			this.orderService.addOrderInfo(orderVo);
			return CommonReturnType.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.ADD_FAILURE;
		}
	}

	/**
	 * @param orderVo 订单实体类
	 * @return CommonReturnType
	 * @description 添加预定订单信息
	 * @author 柚子茶
	 * @date 2021/3/8 11:17
	 **/
	@RequestMapping("addOrderInfoById")
	public CommonReturnType addOrderInfoById(OrderVo orderVo) {
		try {
			this.orderService.addOrderInfoById(orderVo);
			return CommonReturnType.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.ADD_FAILURE;
		}
	}


	/**
	 * @param pedicureVo 项目实体类
	 * @return DataGridView
	 * @description 查询项目信息
	 * @author 柚子茶
	 * @date 2021/3/8 11:11
	 **/
	@RequestMapping("loadPedicureInfoByState")
	public DataGridView loadPedicureInfoByState(PedicureVo pedicureVo) {
		return this.pedicureService.queryPedicureInfoByState(pedicureVo);
	}

	/**
	 * @param orderVo 订单实体类
	 * @return DataGridView
	 * @description 查询订单信息
	 * @author 柚子茶
	 * @date 2021/3/8 12:31
	 **/
	@RequestMapping("loadOrderInfo")
	public DataGridView loadOrderInfo(OrderVo orderVo) {
		return this.orderService.queryOrderInfo(orderVo);
	}

	/**
	 * @param orderVo
	 * @return CommonReturnType
	 * @description 处理客户预定订单
	 * @author 柚子茶
	 * @date 2021/3/8 14:10
	 **/
	@RequestMapping("handleOrderInfo")
	public CommonReturnType handleOrderInfo(OrderVo orderVo) {
		try {
			this.orderService.updateHandleOrderInfo(orderVo);
			return CommonReturnType.HANDLE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.HANDLE_FAILURE;
		}
	}

	/**
	 * @param orderVo
	 * @return CommonReturnType
	 * @description 订单结算
	 * @author 柚子茶
	 * @date 2021/3/8 14:28
	 **/
	@RequestMapping("finishOrderInfo")
	public CommonReturnType finishOrderInfo(OrderVo orderVo) {
		try {
			this.orderService.updateFinishOrderInfo(orderVo);
			return CommonReturnType.FINISH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.FINISH_FAILURE;
		}
	}

	/**
	 * @param orderVo
	 * @return CommonReturnType
	 * @description 修改订单信息
	 * @author 柚子茶
	 * @date 2021/3/8 15:29
	 **/
	@RequestMapping("updateOrderInfo")
	public CommonReturnType updateOrderInfo(OrderVo orderVo) {
		try {
			this.orderService.updateOrderInfo(orderVo);
			return CommonReturnType.MODIFY_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.MODIFY_FAILURE;
		}
	}


}
