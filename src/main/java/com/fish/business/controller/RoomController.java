package com.fish.business.controller;

import com.fish.business.service.RoomService;
import com.fish.business.vo.RoomVo;
import com.fish.system.utils.CommonReturnType;
import com.fish.system.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RoomController
 * @Description 房间信息管理前端控制器
 * @Author 柚子茶
 * @Date 2021/3/5 10:08
 * @Version 1.0
 */
@RestController
@RequestMapping("room")
public class RoomController {

	@Autowired
	private RoomService roomService;


	/**
	 * @param roomVo 房间实体类
	 * @return DataGridView
	 * @description 查询房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:09
	 **/
	@RequestMapping("loadRoomInfo")
	public DataGridView loadRoomInfo(RoomVo roomVo) {
		return this.roomService.queryRoomInfo(roomVo);
	}

	/**
	 * @param roomVo
	 * @return CommonReturnType
	 * @description 添加房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:11
	 **/
	@RequestMapping("addRoomInfo")
	public CommonReturnType addRoomInfo(RoomVo roomVo) {
		try {
			this.roomService.addRoomInfo(roomVo);
			return CommonReturnType.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.ADD_FAILURE;
		}
	}

	/**
	 * @param roomVo
	 * @return CommonReturnType
	 * @description 修改房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:13
	 **/
	@RequestMapping("updateRoomInfo")
	public CommonReturnType updateRoomInfo(RoomVo roomVo) {
		try {
			this.roomService.updateRoomInfo(roomVo);
			return CommonReturnType.MODIFY_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.MODIFY_FAILURE;
		}
	}

	/**
	 * @param roomVo
	 * @return CommonReturnType
	 * @description 删除房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:15
	 **/
	@RequestMapping("deleteRoomInfo")
	public CommonReturnType deleteRoomInfo(RoomVo roomVo) {
		try {
			this.roomService.deleteRoomInfo(roomVo.getId());
			return CommonReturnType.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return CommonReturnType.DELETE_FAILURE;
		}
	}

	/**
	 * @param roomVo
	 * @return boolean
	 * @description 校验房间号是否存在
	 * @author 柚子茶
	 * @date 2021/3/9 14:27
	 **/
	@RequestMapping("checkRoomNumber")
	public boolean checkRoomNumber(RoomVo roomVo) {
		return this.roomService.checkRoomNumber(roomVo);
	}


}
