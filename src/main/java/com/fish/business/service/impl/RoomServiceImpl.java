package com.fish.business.service.impl;

import com.fish.business.dao.RoomDao;
import com.fish.business.domain.Room;
import com.fish.business.service.RoomService;
import com.fish.business.vo.RoomVo;
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
 * @ClassName RoomServiceImpl
 * @Description 房间信息管理业务层接口实现类
 * @Author 柚子茶
 * @Date 2021/3/5 10:06
 * @Version 1.0
 */
@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;


	/**
	 * @param roomVo
	 * @return DataGridView
	 * @description 查询房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:17
	 **/
	@Override
	public DataGridView queryRoomInfo(RoomVo roomVo) {
		// 开启分页
		Page<Object> page = PageHelper.startPage(roomVo.getPage(), roomVo.getLimit());
		List<Room> roomList = this.roomDao.selectRoomInfoByList(roomVo);
		return new DataGridView(page.getTotal(), roomList);
	}

	/**
	 * @param roomVo
	 * @return void
	 * @description 添加房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:17
	 **/
	@Override
	public void addRoomInfo(RoomVo roomVo) {
		// 获取到当前登录的用户
		User user = (User) WebUtils.getHttpSession().getAttribute("user");
		// 设置当前用户的ID
		roomVo.setCreateUserId(user.getUserId());
		// 设置创建时间
		roomVo.setCreateTime(new Date());
		// 设置新增房间为空房(未被使用)
		roomVo.setRoomState(1);
		// 完成添加操作
		this.roomDao.insertSelective(roomVo);
	}

	/**
	 * @param roomVo
	 * @return void
	 * @description 修改房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:17
	 **/
	@Override
	public void updateRoomInfo(RoomVo roomVo) {
		this.roomDao.updateByPrimaryKeySelective(roomVo);
	}

	/**
	 * @param id 房间ID
	 * @return void
	 * @description 删除房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:17
	 **/
	@Override
	public void deleteRoomInfo(Integer id) {
		this.roomDao.deleteByPrimaryKey(id);
	}

	/**
	 * @return List<Room>
	 * @description 查询房间号
	 * @author 柚子茶
	 * @date 2021/3/7 21:03
	 **/
	@Override
	public List<Room> initRoomNumberInfo() {
		RoomVo roomVo = new RoomVo();
		roomVo.setRoomState(1);
		return this.roomDao.selectRoomInfoByList(roomVo);
	}

	/**
	 * @param roomVo
	 * @return boolean
	 * @description 校验房间号是否存在
	 * @author 柚子茶
	 * @date 2021/3/9 14:27
	 **/
	@Override
	public boolean checkRoomNumber(RoomVo roomVo) {
		if (null != roomVo.getId()) {
			Room room = this.roomDao.selectByPrimaryKey(roomVo.getId());
			if (room.getRoomNumber().equals(roomVo.getRoomNumber())) {
				return false;
			} else {
				Room room1 = this.roomDao.selectRoomInfoByNumber(roomVo.getRoomNumber());
				if (null != room1) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			Room room2 = this.roomDao.selectRoomInfoByNumber(roomVo.getRoomNumber());
			if (null != room2) {
				return true;
			} else {
				return false;
			}
		}
	}
}
