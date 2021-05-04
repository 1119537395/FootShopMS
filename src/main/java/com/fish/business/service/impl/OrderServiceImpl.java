package com.fish.business.service.impl;

import com.fish.business.dao.CustomerDao;
import com.fish.business.dao.OrderDao;
import com.fish.business.dao.RoomDao;
import com.fish.business.dao.StaffDao;
import com.fish.business.domain.Customer;
import com.fish.business.domain.Order;
import com.fish.business.domain.Room;
import com.fish.business.domain.Staff;
import com.fish.business.service.OrderService;
import com.fish.business.vo.OrderVo;
import com.fish.system.domain.User;
import com.fish.system.utils.DataGridView;
import com.fish.system.utils.MessageConstant;
import com.fish.system.utils.RandomUtils;
import com.fish.system.utils.WebUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderServiceImpl
 * @Description 订单业务层接口实现类
 * @Author 柚子茶
 * @Date 2021/3/7 15:57
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private RoomDao roomDao;

	@Autowired
	private StaffDao staffDao;


	/**
	 * @return Map<String, String>
	 * @description 获取订单编号和操作人员名称
	 * @author 柚子茶
	 * @date 2021/3/7 19:14
	 **/
	@Override
	public Map<String, String> initOrderNumberInfo() {
		// 自动生成足疗预定的订单编号
		String pedicureOrderNumber = RandomUtils.createRandomNumberByTime(MessageConstant.ORDER_HEAD);
		// 获取当前登录的用户
		User user = (User) WebUtils.getHttpSession().getAttribute("user");

		// 创建Map集合
		// 将获取到数据封装到map集合中
		Map<String, String> map = new HashMap<>(16);
		map.put("orderNumber", pedicureOrderNumber);
		map.put("userName", user.getUserName());

		return map;
	}

	/**
	 * @param orderVo
	 * @return void
	 * @description 添加预定订单信息
	 * @author 柚子茶
	 * @date 2021/3/7 23:27
	 **/
	@Override
	public void addOrderInfo(OrderVo orderVo) {
		// 根据房间ID修改房间状态
		Room room = new Room();
		room.setId(orderVo.getRoomId());
		room.setRoomState(0);
		this.roomDao.updateByPrimaryKeySelective(room);

		// 根据员工ID修改员工状态
		Staff staff = new Staff();
		staff.setStaffId(orderVo.getStaffId());
		staff.setStaffWorkState(1);
		this.staffDao.updateByPrimaryKeySelective(staff);

		// 根据手机号查询出客户信息
		Customer customer = customerDao.checkCustPhone(orderVo.getCustPhone());
		// 设置客户ID
		orderVo.setCustId(customer.getCustId());
		// 设置创建时间
		orderVo.setCreateTime(new Date());
		// 设置订单状态为预定中
		orderVo.setOrderState(1);
		this.orderDao.insertSelective(orderVo);
	}

	/**
	 * @param orderVo
	 * @return void
	 * @description 添加订单信息
	 * @author 柚子茶
	 * @date 2021/3/8 11:18
	 **/
	@Override
	public void addOrderInfoById(OrderVo orderVo) {
		// 根据房间ID修改房间状态
		Room room = new Room();
		room.setId(orderVo.getRoomId());
		room.setRoomState(0);
		this.roomDao.updateByPrimaryKeySelective(room);

		// 根据员工ID修改员工状态
		Staff staff = new Staff();
		staff.setStaffId(orderVo.getStaffId());
		staff.setStaffWorkState(1);
		this.staffDao.updateByPrimaryKeySelective(staff);

		// 根据手机号查询出客户信息
		Customer customer = customerDao.checkCustPhone(orderVo.getCustPhone());
		// 设置客户ID
		orderVo.setCustId(customer.getCustId());
		// 设置创建时间
		orderVo.setCreateTime(new Date());
		// 设置订单状态为服务中
		orderVo.setOrderState(2);
		this.orderDao.insertSelective(orderVo);
	}

	/**
	 * @param orderVo
	 * @return DataGridView
	 * @description 查询订单信息
	 * @author 柚子茶
	 * @date 2021/3/8 12:37
	 **/
	@Override
	public DataGridView queryOrderInfo(OrderVo orderVo) {
		// 根据客户手机号查询客户信息
		if (null != orderVo.getCustPhone() && !"".equals(orderVo.getCustName())) {
			Customer customer = this.customerDao.checkCustPhone(orderVo.getCustPhone());
			if (null != customer) {
				orderVo.setCustId(customer.getCustId());
			}
		} else {
			orderVo.setCustId(0);
		}

		// 开启分页
		Page<Object> page = PageHelper.startPage(orderVo.getPage(), orderVo.getLimit());
		List<Order> orderList = this.orderDao.selectOrderInfoByList(orderVo);
		return new DataGridView(page.getTotal(), orderList);
	}

	/**
	 * @param orderVo
	 * @return void
	 * @description 处理用户预定订单
	 * @author 柚子茶
	 * @date 2021/3/8 14:22
	 **/
	@Override
	public void updateHandleOrderInfo(OrderVo orderVo) {
		// 改变订单状态为服务中
		orderVo.setOrderState(2);
		this.orderDao.updateByPrimaryKeySelective(orderVo);
	}

	/**
	 * @param orderVo
	 * @return void
	 * @description 订单结算
	 * @author 柚子茶
	 * @date 2021/3/8 14:29
	 **/
	@Override
	public void updateFinishOrderInfo(OrderVo orderVo) {
		// 查询出该订单信息
		Order order = this.orderDao.selectByPrimaryKey(orderVo.getOrderId());
		// 查询出房间信息并更新房间状态
		Room room = this.roomDao.selectByPrimaryKey(order.getRoomId());
		room.setRoomState(1);
		this.roomDao.updateByPrimaryKeySelective(room);
		// 查询出服务人员信息并更新员工状态
		Staff staff = this.staffDao.selectByPrimaryKey(order.getStaffId());
		staff.setStaffWorkState(0);
		this.staffDao.updateByPrimaryKeySelective(staff);

		// 更新订单状态
		orderVo.setOrderState(0);
		this.orderDao.updateByPrimaryKeySelective(orderVo);
	}

	/**
	 * @param orderVo
	 * @return void
	 * @description 修改订单信息
	 * @author 柚子茶
	 * @date 2021/3/8 15:30
	 **/
	@Override
	public void updateOrderInfo(OrderVo orderVo) {
		this.orderDao.updateByPrimaryKeySelective(orderVo);
	}


}
