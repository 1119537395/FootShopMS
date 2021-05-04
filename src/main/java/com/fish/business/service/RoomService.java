package com.fish.business.service;

import com.fish.business.domain.Room;
import com.fish.business.vo.RoomVo;
import com.fish.system.utils.DataGridView;

import java.util.List;

/**
 * @InterfaceName RoomService
 * @Description 房间信息管理业务层接口
 * @Author 柚子茶
 * @Date 2021/3/5 10:06
 * @Version 1.0
 */
public interface RoomService {


	/**
	 * @param roomVo 房间信息实体类
	 * @return DataGridView
	 * @description 查询房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:10
	 **/
	DataGridView queryRoomInfo(RoomVo roomVo);


	/**
	 * @param roomVo
	 * @return void
	 * @description 添加房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:12
	 **/
	void addRoomInfo(RoomVo roomVo);

	/**
	 * @param roomVo
	 * @return void
	 * @description 修改房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:14
	 **/
	void updateRoomInfo(RoomVo roomVo);

	/**
	 * @param id
	 * @return void
	 * @description 删除房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:15
	 **/
	void deleteRoomInfo(Integer id);

	/**
	 * @return List<Room>
	 * @description 查询房间号
	 * @author 柚子茶
	 * @date 2021/3/7 21:03
	 **/
	List<Room> initRoomNumberInfo();

	/**
	 * @param roomVo
	 * @return boolean
	 * @description 校验房间号是否存在
	 * @author 柚子茶
	 * @date 2021/3/9 14:27
	 **/
	boolean checkRoomNumber(RoomVo roomVo);
}
