package com.fish.business.dao;

import com.fish.business.domain.Room;
import com.fish.business.vo.RoomVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @InterfaceName RoomDao
 * @Description 房间(Room)数据访问层
 * @Author 柚子茶
 * @Date 2021/3/5 8:57
 * @Version 1.0
 */
public interface RoomDao {

	/**
	 * @param id 房间ID
	 * @return int
	 * @description 删除房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 9:49
	 **/
	int deleteByPrimaryKey(Integer id);

	/**
	 * @param record 房间实体类
	 * @return int
	 * @description 添加房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 9:49
	 **/
	int insert(Room record);

	/**
	 * @param record
	 * @return int
	 * @description 按条件添加部分房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 9:49
	 **/
	int insertSelective(Room record);

	/**
	 * @param id
	 * @return Room
	 * @description 根据ID查询房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 9:49
	 **/
	Room selectByPrimaryKey(Integer id);

	/**
	 * @param record
	 * @return int
	 * @description 根据条件修改房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 9:49
	 **/
	int updateByPrimaryKeySelective(Room record);

	/**
	 * @param record
	 * @return int
	 * @description 修改房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 9:49
	 **/
	int updateByPrimaryKey(Room record);

	/**
	 * @param roomVo
	 * @return List<Room>
	 * @description 根据条件查询房间信息
	 * @author 柚子茶
	 * @date 2021/3/5 10:19
	 **/
	List<Room> selectRoomInfoByList(RoomVo roomVo);

	/**
	 * @param roomNumber
	 * @return Room
	 * @description 校验房间号是否存在
	 * @author 柚子茶
	 * @date 2021/3/9 14:29
	 **/
	Room selectRoomInfoByNumber(@Param("roomNumber") Integer roomNumber);
}